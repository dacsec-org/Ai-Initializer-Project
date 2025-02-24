##!/bin/bash
#
## i used this to tweek the install scripts, and to create the directories and files for development.
## run this from root directory of the project.
## You must add your own .env with your huggingface token here:
## ./home/pai/.gpg/pai-token/.env/HUGGINGFACE_API_TOKEN=your_token_here
## its used in the 'downloaders-mod' module.
#TODO: tweek the install scripts to these maps.
## Define the project directory structure
#PROJECT_DIRS=(
#  "./etc/project-ai-initializer"
#  "./etc/systemd/project-ai-initializer"
#  "./home/pai/.project-ai-initializer/models"
#  "./home/pai/.project-ai-initializer/checksums"
#  "./home/pai/project-ai-initializer/cache"
#  "./opt/project-ai-initializer"
#  "./var/run/project-ai-initializer/models"
#  "./var/run/project-ai-initializer/data"
#  "./var/log/project-ai-initializer/logs"
#  "./home/pai/.gnupg/pai-token"
#)
#
## Create the directories
#for dir in "${PROJECT_DIRS[@]}"; do
#  if [ ! -d "$dir" ]; then
#    mkdir -p "$dir"
#    echo "Created directory: $dir"
#  else
#    echo "Directory already exists: $dir"
#  fi
#done
#
## Define the project files to create
#PROJECT_FILES=(
#  "./etc/project-ai-initializer/tpai.conf"
#  "./etc/systemd/project-ai-initializer/tpai.service"
#  "./usr/share/applications/project-ai-initializer.desktop"
#)
#
## Create the files
#for file in "${PROJECT_FILES[@]}"; do
#  if [ ! -f "$file" ]; then
#    touch "$file"
#    echo "Created file: $file"
#  else
#    echo "File already exists: $file"
#  fi
#done
#
#echo "Directory structure and files created for development."
