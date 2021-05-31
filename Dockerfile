FROM tomcat:8.5
RUN ls
COPY ./ControlEscolarWar.war /usr/local/tomcat/webapps/ROOT.war
