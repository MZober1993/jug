FROM jenkins:1.625.3
MAINTAINER Matthias Zober

# Install Jenkins Plugins
COPY resources/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Configure Maven installation location in Jenkins
COPY resources/hudson.tasks.Maven.xml /var/jenkins_home/hudson.tasks.Maven.xml

# Copy Docker config
COPY resources/org.jenkinsci.plugins.docker.commons.tools.DockerTool.xml /var/jenkins_home/org.jenkinsci.plugins.docker.commons.tools.DockerTool.xml

# Install maven
USER root
RUN apt-get update && apt-get install -y wget

# get maven 3.2.2
RUN wget --no-verbose -O /tmp/apache-maven-3.2.2.tar.gz http://archive.apache.org/dist/maven/maven-3/3.2.2/binaries/apache-maven-3.2.2-bin.tar.gz

# verify checksum
RUN echo "87e5cc81bc4ab9b83986b3e77e6b3095 /tmp/apache-maven-3.2.2.tar.gz" | md5sum -c

# install maven
RUN tar xzf /tmp/apache-maven-3.2.2.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.2.2 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-3.2.2.tar.gz
ENV MAVEN_HOME /opt/maven

# copy resources for selenium tests
COPY resources/config.xml /var/jenkins_home/jobs/selenium-hub-test/config.xml
# Switch back to Jenkins user
USER jenkins
