# Ai-Initializer-Project

## Project Description

Ai-Initializer-Project is a local, customizable, hardware-accelerated, private, secure,
universal AI large-language-model client designed for any Linux/UNIX 
distribution.

## Installation options

1. Clone the repository:

   ```bash
   git clone https://github.com/aaronms1/Ai-Initializer-Project
   
2. cd into the project directory:

   ```bash
   cd Ai-Initializer-Project
   ```
   
3. run the ./installers/install.sh script:

   ```bash
   ./installers/install.sh
   ```
   
## deb package installation

1. Download the deb package from the releases page.(in progress)

2. Install the deb package:

   ```bash
   sudo dpkg -i Ai-Initializer-Project.deb
   ```
   
## docker installation

1. Pull the docker image from the docker hub:

   ```bash
   docker pull aaronms1/ai-initializer-project(in progress)
   ```
   
2. Run the docker image:

   ```bash
   
    docker run -p 8080:8080 aaronms1/ai-initializer-project
    ```

[//]: # (To run the application locally, follow these steps:)


[//]: # (1. Clone the repository:)

[//]: # (   ```bash)

[//]: # (   git clone https://github.com/githubnext/workspace-blank.git)

[//]: # (   cd workspace-blank)

[//]: # (   ```)

[//]: # ()
[//]: # (2. Install dependencies using Maven or Gradle:)

[//]: # (   ```bash)

[//]: # (   # For Maven)

[//]: # (   mvn install)

[//]: # (   # For Gradle)

[//]: # (   gradle build)

[//]: # (   ```)

[//]: # (3. Run the application:)

[//]: # (   ```bash)

[//]: # (   # For Maven)

[//]: # (   mvn spring-boot:run)

[//]: # ()
[//]: # (   # For Gradle)

[//]: # (   gradle bootRun)

## Usage Guide

Once the application is running, you can access the Vaadin front end in your browser or use your current 
desktop environment (DE) to interact with the LLM.

## Default LLM: qwen2.5 0.5b

Qwen2.5 is the latest series of Qwen large language models. For Qwen2.5, a range of base language models and 
instruction-tuned models are released, with sizes ranging from 0.5 to 72 billion parameters. Qwen2.5 introduces 
the following improvements over Qwen2:

## Customization and Security Features

- **Customization**: The application allows for extensive customization of the LLM, leveraging the BTRFS file system.
- **Hardware Acceleration**: The application supports hardware acceleration for improved performance.
- **Systemd Service**: The application includes a systemd service for easy management and monitoring.
- **Stand-alone option**: The application can be run as a stand-alone service or integrated into an existing system.
- **Security**: The application includes robust security features, such as 
  data encryption, user authentication and authorization, and secure communication protocols.
- **PAM Authentication**: The application uses Pluggable Authentication Modules (PAM) for user authentication.
- **AppArmor Profiles**: The application uses AppArmor profiles - a Linux security module
- to ensure the privacy and security of user data.

[//]: # (## AI Options)

[//]: # ()
[//]: # (- Cybersecurity)

[//]: # (- Chat Completion)

[//]: # (- Embedding)

[//]: # (- Text to Image)

[//]: # (- Audio Transcription)

[//]: # (- Text to Speech)

[//]: # (- Moderation)

## Project Directory Structure

- /etc/project-ai-initializer/
   - *.conf
- /etc/apparmor/project-ai-initializer/
   - *.profile
- /systemd/project-ai-initializer/
   - *.service
- /usr/share/applications/
   - project-ai-initializer.desktop
- /home/${USER}.project-ai-initializer/
   - models/
      - checksums
- /home/${USER}/
   - TornadoVM
- /home/${USER}/project-ai-initializer.cache/
   - *.cache
- /opt/
   - project-ai-initializer
- /var/run/project-ai-initializer/
   - project-ai-initializer.sock
- /var/log/project-ai-initializer/
   - *.log





