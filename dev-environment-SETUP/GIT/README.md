# SIGNING GIT COMMITS

## with...: `SSH`

1. I used the same SSH key I used for authentication.

   - If you do not already have an ssh key, generate one with:
     - `ssh-keygen -t ed25519 -C "your_email@example.com"`

2. Copy the public key:
   - `pbcopy < ~/.ssh/id_ed25519.pub`
3. Log into **Github**
   - Go to **Settings → SSH and GPG Keys**
   - Click **New SSH Key**
   - Give it a **title**
   - Under **Key type** choose **Signing key**
   - Paste the public key in the **key** box
   - Click **Add SSH Key**
4. Back to your **terminal**, run:

   - Make git sign your commits by default:

     - `git config --global commit.gpgsign true`

   - Tell git to use ssh as a signing key instead of gpg:

     - `git config --global gpg.format ssh`

   - IF you did not use the same key for authentication, you can run this:
     - `git config --global user.signingkey /PATH/TO/.SSH/KEY.PUB`

<br>

Refs: https://docs.github.com/en/authentication/managing-commit-signature-verification/telling-git-about-your-signing-key#telling-git-about-your-ssh-key

<br>
<br>

## with...: `GPG Tool`

### 1) PRE GIT FIRST

1. SET UP YOUR ENV FIRST

   - `macOS`:
     - Install [GPG Tools](https://gpgtools.org/)
     - Generate a new key using **OPENING** GPG Keychain
       - Make sure you use the same email address that your GitHub account uses (you can find this in your git config)
       - do not need to set a password on your key
       - Export the public key by right clicking on the key and clicking "Export..."
     - COPY the key in the DIALOG for the step.
   - `LINUX`
     - install git: https://github.com/git-guides/install-git#install-git-on-linux

<br>
<br>

2. in the file `user_folder/.gitconfig` add:

```sh
[user]
	email = matheusicaro2@hotmail.com
	name = Matheus Icaro
	signingkey = 0000000000000000000000000000000000000000 # key generated in the steps bellow**

[commit]
	gpgsign = true
[credential]
	helper = store
```

<br>
<br>

3.  Add git credentials to stop asking for the login and password. Create a file `user_folder/.git-credentials` and add:

```sh
https://matheusicaro2%40hotmail.com:ghp_REDACTEDREDACTEDREDACTEDREDACTED@github.com #token comes from git token
```

<br>
<br>

### 2) GENERAYE KEY By terminal (NOT NECESSARY FOR macOS)

<br> 1. Install GPG: `sudo apt-get install gpg`

<br> 2 Run: `gpg --gen-key`

<br> 2.1. This will prompt you for your name and email--fill these out. **Make sure you use the same email that is set in your github as a primary email**

<br> 2.2. IMPORTANT: IT IS GOING TO ASK YOU 4 TIMES TO SET A SECRET PASSWORD. DOING THIS WILL BE A BAD TIME. Instead, just leave the fields blank and proceed without a password. **If you do set a password, you will need to enter it every time you commit.**

<br> 3. Run `gpg --list-secret-keys --keyid-format LONG` and copy the 16 character key identifier listed on the SECOND line

<br> 4. Run `gpg --armor --export 0000000000000000000000000000000000000000 gpg-key.txt`

![signing%20git%20commits.png](pictures/signing-git-commits.png)

<br>
<br>

### 3) CONFIGURE GIT

<br> 1. Edit your git config in `~/.gitconfig`

<br> 2. Under `[user]` add `signingkey = <16 character key identifier>`

<br> 3. Under `[commit]` add `gpgsign = true`. _Note that if you do not add this configuration to `commit` then you must make commits with the `-S flag`, like `git commit -S -m “foo bar”`_

```
[user]
	email = matheusicaro2@hotmail.com
	name = Matheus Icaro
	signingkey = 0000000000000000000000000000000000000000

[commit]
	gpgsign = true
```

<br> **ADD KEY TO GITHUB**

<br> 1. Go to the [SSH & GPG Keys page](https://github.com/settings/keys) on GitHub

<br> 2. create new value and past the key generated at **GENERAYE KEY** > step 4

<br> 3. **Save the login and password for the next commits, run:** `git config --global credential.helper store`

---
