FROM ubuntu:noble
LABEL version="1.0" author="Taras Chulii"
RUN apt-get update && apt-get install -yq nginx >/dev/null 2>&1
EXPOSE 80
COPY ./index.html /var/www/html/index.html
COPY ./default //etc/nginx/sites-available/default
WORKDIR /var/www/html/
ENTRYPOINT ["nginx", "-g", "daemon off;"]
