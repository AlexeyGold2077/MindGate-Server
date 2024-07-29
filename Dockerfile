FROM tomcat:9.0.91-jdk17
LABEL authors="Alexey Zolotarev"
ADD target/MindGate-4.0.0.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]