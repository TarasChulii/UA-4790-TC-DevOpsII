#Base Image
FROM ubuntu:noble
#Add label
LABEL version="1.0" author="Taras Chulii"
# replace shell with bash so we can source files
RUN rm /bin/sh && ln -s /bin/bash /bin/sh

# Set NVM environment variables
ENV NVM_DIR=/usr/local/nvm
ENV NODE_VERSION=20.20.0
ENV PATH=$NVM_DIR/versions/node/v$NODE_VERSION/bin:$PATH

# update the repository sources list and install dependencies
RUN apt-get update && apt-get install -y curl && apt-get -y autoclean

# install nvm
RUN mkdir -p "$NVM_DIR"; curl -o- "https://raw.githubusercontent.com/nvm-sh/nvm/master/install.sh" | bash 

# install node and npm
RUN source $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default \
    && node -v \
    && npm -v

#Copy application into container
COPY ./example-app-nodejs-backend-react-frontend/ /usr/local/app/
RUN rm -f /usr/local/app/package-lock.json

#Set Wokr dir
WORKDIR /usr/local/app/

#Open port
EXPOSE 3000

#Build and run application
RUN npm install > /dev/null 2>&1
RUN npm run build
ENTRYPOINT ["npm", "start"]
