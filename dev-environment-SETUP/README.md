1. [Setup the environment](#setup)

2. [TERMINAL and alias](/dev-environment-SETUP/terminal.md)

## SETUP

- [ ] 1. install curl: `sudo apt-get install curl`

- [ ] 2. Add sudo commands (shutdown and reboot)
     <br> 2.1. run: `sudo visudo`
     <br> 2.2. Past in the end

  ```sh
  # DISABLED SUDO PASSWORD WHEN REBOOT OR RESTART
  <USER_NAME_HERE> ALL = NOPASSWD: /sbin/shutdown
  <USER_NAME_HERE> ALL = NOPASSWD: /sbin/reboot
  ```

- [x] 3. VS CODE

- [x] 4. [Terminal ZSH](https://github.com/matheusicaro/private-helpers/blob/master/UTILS.md#terminal-install-zsh-pretty)

- [ ] 5. Install NVM and use it to setup Node
     <br> 4.1. `nvm install 16` set it as default
     <br> 4.2. `nvm install 14` and `nvm install 12`

- [ ] 6. DOCKER
     <br> 5.1. [install](https://docs.docker.com/engine/install/LINUX/UBUNTU/)
     <br> 5.2. [Manage docker as a non-root user. Here are the steps](https://docs.docker.com/engine/install/linux-postinstall/)
     <br> 5.3. [Docker Compose](https://docs.docker.com/compose/install/)
     <br> 5.4. `sudo apt install docker-compose`
     <br> 5.5. install dev environment docker here: https://github.com/matheusicaro/private-helpers/tree/master/Docker

- [ ] 7. MongoDB Client UI
     <br> 6.1. [Compass](https://www.mongodb.com/products/compass) or [Robo3T](https://robomongo.org/download)

- [ ] 8. [AWS cli](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

- [x] 0. [Signing Git Commings](https://github.com/matheusicaro/private-helpers/blob/master/UTILS.md#signing-git-commits)
