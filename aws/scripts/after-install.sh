
#!/bin/bash
set -xe

#sudo su

# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://hbdonf-hj-api/HBDONF-HJ-0422-api/HBDONF-HJ-0422-api-1.0.0-SNAPSHOT.war /usr/local/tomcat9/webapps/ROOT.war


# Ensure the ownership permissions are correct.
chown -R tomcat:tomcat /usr/local/tomcat9/webapps