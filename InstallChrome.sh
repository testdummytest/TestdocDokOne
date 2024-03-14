#!/bin/bash
set -ex

# Download the Chrome package
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

# Install the Chrome package
sudo apt install ./google-chrome-stable_current_amd64.deb

# If installation is successful, print a message
echo "Google Chrome has been installed successfully."

# Optionally, you can also set up Chrome options here if needed
# For example:
# echo "Setting up Chrome options..."
# sudo sed -i 's/"$@"/"$@" --disable-dev-shm-usage --ignore-ssl-errors=yes --ignore-certificate-errors/' /usr/bin/google-chrome
# echo "Chrome options set up successfully."
