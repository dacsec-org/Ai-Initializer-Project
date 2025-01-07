# Ai-Initializer-Project

## Project Description

Ai-Initializer-Project is a local, customizable, hardware-accelerated, private, secure, universal AI large-language-model client designed for any Linux/UNIX distribution utilizing BTRFS (required for LLM customization) on any device. The default LLM is qwen2.5 0.5b, a lightweight 'general' chat/adviser.

## Setup Instructions(placeholder, will be updated)

1. Clone the repository:
   ```bash
   git clone https://github.com/githubnext/workspace-blank.git
   cd workspace-blank
   ```

2. Install dependencies using Maven or Gradle:
   ```bash
   # For Maven
   mvn install

   # For Gradle
   gradle build
   ```

3. Run the application:
   ```bash
   # For Maven
   mvn spring-boot:run

   # For Gradle
   gradle bootRun
   ```

## Usage Guide

Once the application is running, you can access the Vaadin front end in your browser or use your current desktop environment (DE) to interact with the LLM.

## Default LLM: qwen2.5 0.5b

Qwen2.5 is the latest series of Qwen large language models. For Qwen2.5, a range of base language models and instruction-tuned models are released, with sizes ranging from 0.5 to 72 billion parameters. Qwen2.5 introduces the following improvements over Qwen2:

- It possesses significantly more knowledge and has greatly enhanced capabilities in coding and mathematics, due to specialized expert models in these domains.
- It demonstrates significant advancements in instruction following, long-text generation (over 8K tokens), understanding structured data (e.g., tables), and generating structured outputs, especially in JSON format. It is also more resilient to diverse system prompts, improving role-play and condition-setting for chatbots.
- It supports long contexts of up to 128K tokens and can generate up to 8K tokens.
- It offers multilingual support for over 29 languages, including Chinese, English, French, Spanish, Portuguese, German, Italian, Russian, Japanese, Korean, Vietnamese, Thai, Arabic, and more.

Please note: all models except the 3B and 72B are released under the Apache 2.0 license, while the 3B and 72B models are under the Qwen license.

## Customization and Security Features

- **Customization**: The application allows for extensive customization of the LLM, leveraging the BTRFS file system.
- **Security**: The application includes robust security features, such as data encryption and user authentication, to ensure the privacy and security of user data.

## AI Options

- Cybersecurity
- Chat Completion
- Embedding
- Text to Image
- Audio Transcription
- Text to Speech
- Moderation
