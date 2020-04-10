FROM tomcat:9-jdk8

EXPOSE 8080

ENV DB_URL=""
ENV DB_NAME=""
ENV DB_USER=""
ENV DB_PASSWORD=""

RUN rm -rf /usr/local/Cellar/tomcat/9.0.30/libexec/webapps/ROOT
COPY ./ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY ./setenv.sh /usr/local/tomcat/bin/setenv.sh