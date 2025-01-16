#!/bin/bash

LOG_FILE="/var/log/project-ai-initializer/install.log"
HUGGINGFACE_API_URL="https://huggingface.co/api/models/qwen2.5-gguf:0.5b"
LLM_FILE="/home/${USER}/.project-ai-initializer/models/Qwen_Qwen2.5-0.5B-GGUF.zip"
UNZIPPED_DIR="/home/${USER}/.project-ai-initializer/models/Qwen_Qwen2.5-0.5B-GGUF"

# Function to log messages
log() {
    echo "$(date +'%Y-%m-%d %H:%M:%S') - $1" | tee -a "$LOG_FILE"
}

# Function to prompt for sudo password
prompt_sudo() {
    if [ "$EUID" -ne 0 ]; then
        log "This script must be run as root. Please enter your sudo password."
        if ! sudo -v; then
            log "Sudo authentication failed. Exiting."
            exit 1
        fi
    fi
}

# Function to rollback changes on failure
rollback() {
    log "Rolling back changes..."
    # Remove created directories
    for dir in "${PROJECT_DIRS[@]}"; do
        if [ -d "$dir" ]; then
            rm -rf "$dir"
            log "Removed directory: $dir"
        fi
    done

    # Remove created files
    for file in "${PROJECT_FILES[@]}"; do
        if [ -f "$file" ]; then
            rm -f "$file"
            log "Removed file: $file"
        fi
    done

    # Remove token file
    if [ -f /etc/security/project-ai-initializer.token ]; then
        rm -f /etc/security/project-ai-initializer.token
        log "Removed token file: /etc/security/project-ai-initializer.token"
    fi

    # Remove cloned repository
    if [ -d /opt/project-ai-initializer ]; then
        rm -rf /opt/project-ai-initializer
        log "Removed cloned repository: /opt/project-ai-initializer"
    fi

    # Remove BTRFS subvolume
    if sudo btrfs subvolume list / | grep -q "/home/$USER/.ai-initializer-project/models"; then
        sudo btrfs subvolume delete /home/"$USER"/.ai-initializer-project/models
        log "Removed BTRFS subvolume: /home/$USER/.ai-initializer-project/models"
    fi

    log "Rollback complete."
}

# Function to validate Hugging Face token
validate_token() {
    local token=$1
    local response
    response=$(curl -s -o /dev/null -w "%{http_code}" -H "Authorization: Bearer $token" "$HUGGINGFACE_API_URL")
    if [ "$response" -ne 200 ]; then
        log "Invalid Hugging Face token. Exiting."
        rollback
        exit 1
    fi
    log "Hugging Face token validated successfully."
}

# Function to generate SHA-512 checksum with LLM name prepended
generate_checksum() {
    local file_path=$1
    local checksum_file=$2
    local llm_name
    llm_name=$(basename "$file_path" .zip)
    if sha512sum "$file_path" | awk -v llm_name="$llm_name" '{print llm_name " " $0}' > "$checksum_file"; then
        log "Generated SHA-512 checksum for $file_path with LLM name prepended"
    else
        log "Failed to generate SHA-512 checksum for $file_path. Exiting."
        rollback
        exit 1
    fi
}

# Function to unzip the LLM file
unzip_llm() {
    local zip_file=$1
    local dest_dir=$2
    if unzip "$zip_file" -d "$dest_dir"; then
        log "Unzipped $zip_file to $dest_dir"
    else
        log "Failed to unzip $zip_file. Exiting."
        rollback
        exit 1
    fi
}

# Function to create a nocow snapshot
create_nocow_snapshot() {
    local source_path=$1
    local snapshot_path=$2
    if sudo btrfs property set "$source_path" compression "" && sudo btrfs subvolume snapshot "$source_path" "$snapshot_path"; then
        log "Created nocow snapshot at $snapshot_path"
    else
        log "Failed to create nocow snapshot. Exiting."
        rollback
        exit 1
    fi
}

# Prompt for sudo password
prompt_sudo

# Detect system hardware
log "Detecting system hardware..."
CPU_INFO=$(lscpu | grep "Model name" | awk -F: '{print $2}' | xargs)
MEMORY_INFO=$(free -h | grep "Mem:" | awk '{print $2}')
DISK_INFO=$(df -h / | grep "/" | awk '{print $2}')

log "CPU: $CPU_INFO"
log "Memory: $MEMORY_INFO"
log "Disk: $DISK_INFO"

# Detect GPU and CUDA
log "Detecting GPU and CUDA..."
if command -v nvidia-smi &> /dev/null; then
  GPU_INFO=$(nvidia-smi --query-gpu=name --format=csv,noheader)
  CUDA_VERSION=$(nvcc --version | grep "release" | awk '{print $6}' | sed 's/,//')
  log "GPU: $GPU_INFO"
  log "CUDA Version: $CUDA_VERSION"
else
  log "No NVIDIA GPU detected or nvidia-smi not installed."
fi

# Define the project directory structure
PROJECT_DIRS=(
  "/etc/project-ai-initializer"
  "/systemd/project-ai-initializer"
  "/usr/share/applications"
  "/home/${USER}/.project-ai-initializer/models"
  "/home/${USER}/.project-ai-initializer/checksums"
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
    if ! mkdir -p "$dir"; then
      log "Failed to create directory: $dir. Exiting."
      rollback
      exit 1
    fi
    log "Created directory: $dir"
  else
    log "Directory already exists: $dir"
  fi
done

# Define the project files to create
PROJECT_FILES=(
  "/etc/project-ai-initializer/project-ai-initializer.conf"
  "/systemd/project-ai-initializer/project-ai-initializer.service"
  "/usr/share/applications/project-ai-initializer.desktop"
  "/var/run/project-ai-initializer/project-ai-initializer.sock"
  "/var/log/project-ai-initializer/project-ai-initializer.log"
)

# Create the files
for file in "${PROJECT_FILES[@]}"; do
  if [ ! -f "$file" ]; then
    if ! touch "$file"; then
      log "Failed to create file: $file. Exiting."
      rollback
      exit 1
    fi
    log "Created file: $file"
  else
    log "File already exists: $file"
  fi
done

# Validate Hugging Face token
HUGGINGFACE_TOKEN="YOUR_API_TOKEN_HERE"
validate_token "$HUGGINGFACE_TOKEN"

# Create and secure the token file for huggingface token
if ! echo "$HUGGINGFACE_TOKEN" > /etc/security/project-ai-initializer.token; then
  log "Failed to create token file. Exiting."
  rollback
  exit 1
fi
if ! chmod 600 /etc/security/project-ai-initializer.token; then
  log "Failed to set permissions on token file. Exiting."
  rollback
  exit 1
fi
log "Created and secured token file: /etc/security/project-ai-initializer.token"

log "Installation complete."

# Clone the project repository
log "Cloning the project repository..."
if ! git clone https://github.com/your-repo/project-ai-initializer.git /opt/project-ai-initializer; then
    log "Failed to clone the repository. Exiting."
    rollback
    exit 1
fi

# Build the project
log "Building the project..."
cd /opt/project-ai-initializer || exit
if ! ./build.sh; then
    log "Build failed. Exiting."
    rollback
    exit 1
fi

# Load AppArmor profile
log "Loading AppArmor profile..."
if ! sudo apparmor_parser -r /etc/apparmor.d/usr.bin.project-ai-initializer; then
    log "Failed to load AppArmor profile. Exiting."
    rollback
    exit 1
fi

# Run TornadoVM installer script
log "Running TornadoVM installer script..."
cd /home/"$USER"/TornadoVM || exit
if ! ./tornado_installer.sh; then
    log "TornadoVM installation failed. Exiting."
    rollback
    exit 1
fi

# Mount the volume containing the root subvolume to /mnt
sudo mount '$(df --output=source / | tail -n 1)' /mnt

# Create a btrfs subvolume where the snapshots will be stored
sudo btrfs subvolume create /mnt/home/"$USER"/.ai-initializer-project/models.@snapshots

# Add an entry in fstab to mount the snapshots subvolume
echo "$(df --output=source / | tail -n 1) /.ai-initializer-project/models/.snapshots btrfs defaults,subvol=.ai-initializer-project/models/@snapshots 0 0" | sudo tee -a /etc/fstab

# Mount the snapshots subvolume
sudo mount /.snapshots

# Unmount /mnt
sudo umount /mnt

# Delete the models directory as it is now a BTRFS subvolume
if ! rm -rf /home/"$USER"/.project-ai-initializer/models; then
    log "Failed to delete the models directory. Exiting."
    rollback
    exit 1
fi

# Generate SHA-512 checksum of the LLM
CHECKSUM_FILE="/home/${USER}/.project-ai-initializer/checksums/llm.sha512"
generate_checksum "$LLM_FILE" "$CHECKSUM_FILE"

# Unzip the LLM file
unzip_llm "$LLM_FILE" "$UNZIPPED_DIR"

# Create nocow(no copy on write) snapshot of the unzipped LLM
SNAPSHOT_PATH="/home/${USER}/.project-ai-initializer/models/llm_snapshot"
create_nocow_snapshot "$UNZIPPED_DIR" "$SNAPSHOT_PATH"

log "Installation and setup complete."
