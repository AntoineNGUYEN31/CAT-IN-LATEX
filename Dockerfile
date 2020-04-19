#Docker image tomcat-latex:0.0.1
FROM ivanpondal/alpine-latex:1.1.0
LABEL maintainer="nguyen.ensma@gmail.com"
RUN apk update &&\
apk add openjdk8
RUN apk add bash && apk add curl
RUN curl -o apache-tomcat-9.0.34.tar.gz http://mirrors.standaloneinstaller.com/apache/tomcat/tomcat-9/v9.0.34/bin/apache-tomcat-9.0.34.tar.gz &&\
tar zxf apache-tomcat-9.0.34.tar.gz &&\
mv apache-tomcat-9.0.34 /usr/local/tomcat &&\
rm apache-tomcat-9.0.34.tar.gz
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh","start"]
