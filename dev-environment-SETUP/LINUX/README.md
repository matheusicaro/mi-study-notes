# LINUX

- [BOOT ISO through Linux](#boot-iso-through-linux)
- [SETUP](#setup)
  - [UBUNTU](/dev-environment-SETUP/LINUX/UBUNTU/README.md)
  - [KUBUNTU](/dev-environment-SETUP/LINUX/KUBUNTU/README.md)

<br>
<br>

## BOOT ISO through Linux

**[Deeping Boot Maker](https://www.deepin.org/en/original/deepin-boot-maker/)**, best program! its like **[Rufus](https://rufus.ie/en/)** for Windows

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/4d8f01eb-87aa-4e95-8133-8567a0980778)

1. `sudo apt-get install zsh`
2. Verify it’s installed — `zsh --version`, expects zsh `5.1.1` or more recent
3. Make it your default shell: `chsh -s $(which zsh)`
4. **Restart the system**
5. Test that it worked with `echo $SHELL`, Expects /bin/zsh
6. Test with `$SHELL --version` expects zsh `5.1.1` or similar
