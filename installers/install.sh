#!/bin/bash

# Detect system hardware
echo "Detecting system hardware..."
CPU_INFO=$(lscpu | grep "Model name" | awk -F: '{print $2}' | xargs)
MEMORY_INFO=$(free -h | grep "Mem:" | awk '{print $2}')
DISK_INFO=$(df -h / | grep "/" | awk '{print $2}')

echo "CPU: $CPU_INFO"
echo "Memory: $MEMORY_INFO"
echo "Disk: $DISK_INFO"

# Detect GPU and CUDA
echo "Detecting GPU and CUDA..."
if command -v nvidia-smi &> /dev/null; then
  GPU_INFO=$(nvidia-smi --query-gpu=name --format=csv,noheader)
  CUDA_VERSION=$(nvcc --version | grep "release" | awk '{print $6}' | sed 's/,//')
  echo "GPU: $GPU_INFO"
  echo "CUDA Version: $CUDA_VERSION"
else
  echo "No NVIDIA GPU detected or nvidia-smi not installed."
fi

# Define the project directory structure
PROJECT_DIRS=(
  "/etc/project-ai-initializer"
  "/systemd/project-ai-initializer"
  "/usr/share/applications"
  "/home/${USER}/.project-ai-initializer/models/checksums"
  "/home/${USER}/TornadoVM"
  "/home/${USER}/project-ai-initializer.cache"
  "/opt/project-ai-initializer"
  "/var/run/project-ai-initializer"
  "/var/log/project-ai-initializer"
  "/etc/security"
)

# Create the directories
for dir in "${PROJECT_DIRS[@]}"; do
  if [ ! -d "$dir" ]; then
    mkdir -p "$dir"
    echo "Created directory: $dir"
  else
    echo "Directory already exists: $dir"
  fi
done

# Create placeholder configuration files
touch /etc/project-ai-initializer/project-ai-initializer.conf
echo "Created file: /etc/project-ai-initializer/project-ai-initializer.conf"

touch /systemd/project-ai-initializer/project-ai-initializer.service
echo "Created file: /systemd/project-ai-initializer/project-ai-initializer.service"

touch /usr/share/applications/project-ai-initializer.desktop
echo "Created file: /usr/share/applications/project-ai-initializer.desktop"

touch /var/run/project-ai-initializer/project-ai-initializer.sock
echo "Created file: /var/run/project-ai-initializer/project-ai-initializer.sock"

touch /var/log/project-ai-initializer/project-ai-initializer.log
echo "Created file: /var/log/project-ai-initializer/project-ai-initializer.log"

# Create and secure the token file
echo "YOUR_API_TOKEN_HERE" > /etc/security/project-ai-initializer.token
chmod 600 /etc/security/project-ai-initializer.token
echo "Created and secured token file: /etc/security/project-ai-initializer.token"

echo "Installation complete."

# Clone the project repository
echo "Cloning the project repository..."
git clone https://github.com/your-repo/project-ai-initializer.git /opt/project-ai-initializer

# Build the project
echo "Building the project..."
cd /opt/project-ai-initializer || exit
./build.sh

# Load AppArmor profile
echo "Loading AppArmor profile..."
sudo apparmor_parser -r /etc/apparmor.d/usr.bin.project-ai-initializer

# Run TornadoVM installer script
echo "Running TornadoVM installer script..."
/home/${USER}/TornadoVM/tornado_installer.sh

echo "Installation and setup complete."
