pipeline {
    agent any

    environment {
        REMOTE_HOST_IPS = "192.168.56.10 192.168.56.11 192.168.56.12"
        REMOTE_USER  = "jenkins"
        SSH_KEY     = "jn_ed_pk_w_pf"
    }

    stages {
          stage('Install Apache') {
            steps {
                script {
                    def ips = env.REMOTE_HOST_IPS.split()
                    def available_ips = []

                    for (String ip : ips) {
                        def ping_result = sh(script: "ping -c 1 -W 1 ${ip} || true", returnStdout: true, allowEmptyStdout: true)
                        if (ping_result.contains("1 received")) {
                            echo "IP ${ip} is available."
                            sshagent(credentials: [env.SSH_KEY]) {
                                sh """
                                    ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${ip} '
                                    OS_VER=\$(cat /etc/os-release | grep -e "^ID=" | sed "s/ID=//" | sed "s/\\"//g")
                                    echo "Detected the following OS: \$OS_VER"
                                    case \$OS_VER in
                                        rocky|Rocky)
                                            if dnf info httpd &> /dev/null
                                                then
                                                    echo "Apache2 is installed"
                                                else
                                                    echo "Apache2 is not detected. INSTALLING.............."
                                                    sudo dnf update -y &> /dev/null
                                                    sudo dnf install -y httpd &> /dev/null
                                                    sudo systemctl enable httpd &> /dev/null
                                                    sudo systemctl start httpd &> /dev/null
                                            fi
                                        ;;
                                        ubuntu|Ubuntu)
                                            if dpkg -s apache2 &> /dev/null
                                                then
                                                    echo "Apache2 is installed"
                                                else
                                                    echo "Apache2 is not detected. INSTALLING.............."
                                                    sudo apt update -y &> /dev/null
                                                    sudo apt install -y apache2 &> /dev/null
                                                    sudo systemctl enable apache2 &> /dev/null
                                                    sudo systemctl start apache2 &> /dev/null
                                            fi
                                        ;;
                                        alpine|Alpine)
                                            if rc-status -s | grep apache2  &> /dev/null
                                                then
                                                    echo "Apache2 is installed"
                                                else
                                                    echo "Apache2 is not detected. INSTALLING.............."
                                                    sudo apk update &> /dev/null
                                                    sudo apk add apache2 &> /dev/null
                                                    sudo rc-update add apache2 default &> /dev/null
                                                    sudo rc-service apache2 start &> /dev/null
                                            fi
                                        ;;                       
                                        *)
                                            echo "Unsupported OS"
                                        ;;
                                    esac
                                    '
                                """
                            }
                        } else {
                            echo "IP ${ip} is unreachable. Skipping action."
                        }
                    }
                }
            }
        }

        stage('Check Apache Logs') {
            steps {
                script {
                    def ips = env.REMOTE_HOST_IPS.split()
                    def available_ips = []

                    for (String ip : ips) {
                        def ping_result = sh(script: "ping -c 1 -W 1 ${ip} || true", returnStdout: true, allowEmptyStdout: true)
                        if (ping_result.contains("1 received")) {
                            echo "IP ${ip} is available."
                            sshagent(credentials: [env.SSH_KEY]) {
                            sh """
                                ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${ip} '
                                OS_VER=\$(cat /etc/os-release | grep -e "^ID=" | sed "s/ID=//" | sed "s/\\"//g")
                                echo "Detected the following OS: \$OS_VER"
                                case \$OS_VER in
                                    rocky|Rocky)
                                        if dnf info httpd &> /dev/null
                                            then
                                                LOGS_PATH="/etc/httpd/logs/access_log"
                                            else
                                                echo "Apache is not installed"
                                                exit 1
                                        fi
                                    ;;
                                    ubuntu|Ubuntu)
                                        if dpkg -s apache2 &> /dev/null
                                            then
                                                LOGS_PATH="/var/log/apache2/access.log.1"
                                            else
                                                echo "Apache is not installed"
                                                exit 1
                                        fi
                                    ;;
                                    alpine|Alpine)
                                        if rc-status -s | grep apache2  &> /dev/null
                                            then
                                            LOGS_PATH="/var/log/apache2/access.log"
                                            else
                                                echo "Apache is not installed"
                                                exit 1
                                        fi
                                    ;;                       
                                    *)
                                        echo "Unsupported OS"
                                    ;;
                                esac
                                if [[ -n "\$LOGS_PATH" ]]
                                    then
                                        ERR_5XX=\$(sudo grep -E " 5[0-9]{2} " \$LOGS_PATH | wc -l)
                                        ERR_4XX=\$(sudo grep -E " 4[0-9]{2} " \$LOGS_PATH | wc -l)
                                fi
                                if [[ \$ERR_5XX -gt 0 ]]
                                    then
                                        echo "Have been found \$ERR_5XX ERRORS of type 5XX in ACCESS log \$LOGS_PATH"
                                fi                           
                                if [[ \$ERR_4XX -gt 0 ]]
                                    then
                                        echo "Have been found \$ERR_4XX ERRORS of type 4XX in ACCESS log \$LOGS_PATH"
                                fi
                                if [[ \$ERR_5XX -eq 0 && \$ERR_4XX -eq 0 ]]
                                    then 
                                        echo "Errors 4XX or 5XX have NOT been found in ACCESS log \$LOGS_PATH"
                                fi
                                '
                            """
                            }
                        } else {
                            echo "IP ${ip} is unreachable. Skipping action."
                        }
                    }
                }
            }
        }
    }
}
