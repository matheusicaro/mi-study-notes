# LINUX

- [BOOT ISO through Linux](#boot-iso-through-linux)
- [SETUP](#setup)
  - [UBUNTU](/dev-environment-SETUP/LINUX/UBUNTU/README.md)
  - [KUBUNTU](/dev-environment-SETUP/LINUX/KUBUNTU/README.md)

<br>
<br>

# Install.....: `libssl1`

#### UBUNTU 22.04

```
  wget http://archive.ubuntu.com/LINUX/UBUNTU/pool/main/o/openssl/libssl1.1_1.1.0g-2ubuntu4_amd64.deb
  sudo dpkg -i libssl1.1_1.1.0g-2ubuntu4_amd64.deb
```

Font: https://gist.github.com/joulgs/c8a85bb462f48ffc2044dd878ecaa786

<Br>
<Br>
____________________________________________________________________________________
<Br>
<Br>

## BOOT ISO through Linux

**[Deeping Boot Maker](https://www.deepin.org/en/original/deepin-boot-maker/)**, best program! its like **[Rufus](https://rufus.ie/en/)** for Windows

![image](/dev-environment-SETUP/LINUX/pictures/BOOT-ISO-through-Linux.png)

1. `sudo apt-get install zsh`
2. Verify it’s installed — `zsh --version`, expects zsh `5.1.1` or more recent
3. Make it your default shell: `chsh -s $(which zsh)`
4. **Restart the system**
5. Test that it worked with `echo $SHELL`, Expects /bin/zsh
6. Test with `$SHELL --version` expects zsh `5.1.1` or similar
