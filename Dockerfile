FROM  java:openjdk-8u111-jdk-alpine
ADD localtime /etc/localtime
ADD timezone /etc/timezone
COPY ruoyi-admin/target/*.jar /opt/local/app.jar
VOLUME /tmp
CMD ["--server.port=8080"]
ENV MYPATH /opt/local
WORKDIR $MYPATH
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]
#ADD app.jar app.jar
