# Step 1: Create a test environment (e.g., using Docker)
docker run -it --name test-environment ubuntu:latest /bin/bash

# Step 2: Prepare the test environment
apt update
apt install -y git sudo lscpu

# Step 3: Copy the installation scripts to the test environment
# Assuming the scripts are in the current directory on your host machine
docker cp install.sh test-environment:/root/
docker cp project-ai-initializer.dpkg test-environment:/root/
docker cp tornado_installer.sh test-environment:/root/

# Step 4: Run the install.sh script
docker exec -it test-environment /bin/bash -c "cd /root && bash install.sh --install-deps"

# Step 5: Verify the installation
# Check that directories and files are created
docker exec -it test-environment /bin/bash -c "ls -l /etc/project-ai-initializer"
docker exec -it test-environment /bin/bash -c "ls -l /opt/project-ai-initializer"
docker exec -it test-environment /bin/bash -c "systemctl status project-ai-initializer.service"

# Step 6: Run the dpkg script with different options
docker exec -it test-environment /bin/bash -c "cd /root && bash project-ai-initializer.dpkg postinst --install-deps"
docker exec -it test-environment /bin/bash -c "cd /root && bash project-ai-initializer.dpkg postinst"
