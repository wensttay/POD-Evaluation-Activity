FROM maven:3.3.9-jdk-8
ADD . /opt/app
RUN chmod +x /opt/app/start.sh
RUN cd /opt/app && mvn compile package -DskipTests=true
CMD /opt/app/start.sh
