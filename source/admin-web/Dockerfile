FROM nginx:1.14.2-alpine

RUN rm -rf /usr/share/nginx/html/*

ADD dist /usr/share/nginx/html

ADD nginx.conf /etc/nginx/nginx.conf
