FROM tomcat:9-jdk17
ADD target/WebStore.war /usr/local/tomcat/webapps/WebStore.war
EXPOSE 8080
CMD ["catalina.sh", "run"]