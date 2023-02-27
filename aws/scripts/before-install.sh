
#!/bin/bash
set -xe

# Delete the old  directory as needed.
if [ -d /usr/local/codedeployresources ]; then
    rm -rf /home/ubuntu/hbdonf-hj-api
fi

mkdir -vp /home/ubuntu/hbdonf-hj-api