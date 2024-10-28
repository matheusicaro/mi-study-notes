# SIGNING GIT COMMITS


1. [signing by new tutorial](#signing-by-new-tutorial)
2. [signing by old tutorial](#signing-by-old-tutorial)

<br>
<br>
<br>

# SIGNING BY NEW TUTORIAL

## Set up your .gitconfig file(image: Terminal on mac)

In your terminal from your user root directory run

```terminal
cat ~/.gitconfig
```
to create your global git configuration file. This command will display the contents of your .gitconfig file if it already exists.

if you get an error that the file does not exist run

```terminal
touch ~/.gitconfig
```

to create the file 

> the . prefix means this is a hidden file and wont be displayed in your finder unless you hit shift + cmd + . (period) or provide explicit folder path in Go > Go to Folder or hit shift + cmd + G


## Configure git settings

configure your user settings by running the following

```terminal
git config --global user.name "John Doe"
git config --global user.email johndoe@neofinancial.com # Should match your Github email
```

> If you make a mistake no worries! You can always run the same command again with corrected info. Check the contents of the file at any time by running cat ~/.gitconfig or navigating to the file in finder

## Create a new ed25519 SSH key

in your terminal run 

```terminal
ssh-keygen -t ed25519 -C "johndoe@neofinancial.com"
```

Hit enter to accept the default file location (`~/.ssh`), otherwise provide a path to store your new ssh key 

> Providing a custom path may be desirable if you’re managing multiple ssh keys, for general use of neo git repos usually only one ssh is necessary and the default location should suffice.

Enter a passphrase if desired (you will have to provide this passphrase every time you `git commit`) otherwise hit enter twice to not set a passcode.

This process will create a new file within the hidden `~/.ssh` folder that contains your new ssh key and public key.

## Add your SSH key to the ssh-agent


start ssh-agent by running

```terminal
eval "$(ssh-agent -s)"
```

check if your ~/.ssh/config file exists in the default location by running

```terminal
open ~/.ssh/config
```

If you get an error run

```terminal
touch ~/.ssh/config
```
to create the file.

Open the file by running open `~/.ssh/config` and add the following lines

```
Host github.com
  AddKeysToAgent yes
  IdentityFile ~/.ssh/id_ed25519
```

save your changes (cmd +S) and exit.

> If you chose to store your SSH key on a custom path change line 4 to reflect your chosen path 

> If you chose to add a passphrase to your key, you can add a UseKeychain line to use your mac’s built in password storage. For more info on configuring keychain see GitHub docs here


## Add your SSH key to your .gitconfig file

Add your SSH key to your .gitconfig file

We need to tell git to use the SSH key that was generated in the previous step. In your terminal run 

```terminal
git config --global user.signingkey ~/.ssh/id_ed25519.pub
```

to add the path to your public key to your git configuration file. This tells git where to look for your SSH key. If you chose to store your key not on the default path then provide your custom path.


## Tell git to use your SSH key to sign commits

> In the past you may have used a gpg key to sign your commits. However SSH signature verification is available in Git 2.34 or later. No gpg key needed! 

Run the following to add a gpg format to your `~/.gitconfig` file that we created earlier.

```terminal
git config --global gpg.format ssh
```

your `~/.gitconfig` file should now look something like this (run `cat ~/.gitconfig` to display file content)

```
[user]
	name = John Doe
	email = john.doe@neofinancial.com
	signingkey = /Users/john.doe/.ssh/id_ed25519.pub
[gpg]
	format = ssh
```

Auto sign your commits


## Add SSH key to GitHub

> You’ll need to add 2 keys to github.com, one to sign and one to authenticate. You can use the same SSH key for both use cases.

In your terminal run

```
pbcopy < ~/.ssh/id_ed25519.pub
```

This copies the contents of the id_ed25519.pub file (your public SSH key) to your clipboard

You can also navigate to the hidden folder (see above) and copy the content directly

In your github account navigate to Settings > SSH and GPG keys > New SSH key 

Or log into your account and click this link → https://github.com/settings/ssh/new

Create a title for your SSH key, select Key type `Authentication Key`, paste your public key and click `Add SSH key`

Repeat this process this time selecting Key type `Signing Key`.


<br>
<br>
<br>
<br>

# SIGNING BY OLD TUTORIAL

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
