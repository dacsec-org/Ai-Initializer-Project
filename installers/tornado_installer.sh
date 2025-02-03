#!/bin/bash

# Define the target directory
TARGET_DIR="/home/${USER}/TornadoVM"

# Check if the directory already exists
if [ -d "$TARGET_DIR" ]; then
  echo "Directory $TARGET_DIR already exists."
else
  # Create the directory
  mkdir -p "$TARGET_DIR"
  echo "Created directory: $TARGET_DIR"
fi

# Clone the TornadoVM repository
echo "Cloning the TornadoVM repository..."
git clone https://github.com/beehive-lab/TornadoVM.git "$TARGET_DIR"

echo "TornadoVM has been cloned to $TARGET_DIR"

# Detect GPU and set backend
BACKEND="opencl"
if command -v nvidia-smi &> /dev/null; then
  BACKEND="ptx"
elif command -v spirv-as &> /dev/null; then
  BACKEND="spirv"
else
  echo "SPIR-V drivers not detected. Skipping SPIR-V backend."
fi

# Run the TornadoVM installer with the detected backend
cd "$TARGET_DIR" || exit
./bin/tornadovm-installer --jdk graal-jdk-21 --backend opencl,$BACKEND
