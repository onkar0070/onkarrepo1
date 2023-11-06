#!/bin/bash

# Check if the script is run with sudo privileges
if [ "$EUID" -ne 0 ]; then
    echo "Please run this script as root or using sudo."
    exit 1
fi

# Create SSH user 'tech' if it doesn't exist
if ! id "tech" &>/dev/null; then
    useradd -m -s /bin/bash tech
    echo "tech ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers.d/tech
    mkdir -p /home/tech/.ssh
    chmod 700 /home/tech/.ssh
    touch /home/tech/.ssh/authorized_keys
    chmod 600 /home/tech/.ssh/authorized_keys
    chown -R tech:tech /home/tech/.ssh
    echo "SSH user 'tech' has been created successfully."
else
    echo "User 'tech' already exists. Exiting."
    exit 1
fi
