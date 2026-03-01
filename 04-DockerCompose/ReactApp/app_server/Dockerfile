FROM node:alpine3.22
WORKDIR /usr/local/app/
ENV REACT_APP_BASE_URL=http://192.168.56.10:5000
ADD ./app/ ./
RUN rm -f /usr/local/app/package-lock.json
RUN npm install
RUN npm run build
RUN  npm install -g serve
