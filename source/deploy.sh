#! /bin/bash

docker-compose down

docker image list | grep vision | awk '{ print $3 }' | xargs docker image rm

cd admin-web
yarn run build
docker build -t registry.cn-beijing.aliyuncs.com/jimmyxu/vision-web:1.0.0 .
cd ..
cd admin-service
mvn clean package -Dmaven.test.skip=true
docker build -t registry.cn-beijing.aliyuncs.com/jimmyxu/vision-service:1.0.0 .
cd ..
