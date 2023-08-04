- LINUX
  - [BOOT ISO through Linux](#boot-iso-through-linux)
  - [TERMINAL install ZSH pretty](#terminal-install-zsh-pretty)
  - [TERMINAL custom configs](#terminal-custom-configs-linux)
  - [SIGNING GIT COMMITS](#signing-git-commits)
  - [Edit System File by CLI](#edit-system-file-by-cli)
  
- [WINDOWS](#windows)
  - [PC Performace on TASKBAR](#pc-performace-on-taskbar)


# LINUX

## BOOT ISO through Linux
**[Deeping Boot Maker](https://www.deepin.org/en/original/deepin-boot-maker/)**, best program! its like **[Rufus](https://rufus.ie/en/)** for Windows

![image](https://github.com/matheusicaro/private-helpers/assets/29001162/4d8f01eb-87aa-4e95-8133-8567a0980778)


## TERMINAL install ZSH pretty

#### 1) Install Zsh

1. `sudo apt-get install zsh`
2. Verify it’s installed — `zsh --version`, expects zsh `5.1.1` or more recent
3. Make it your default shell: `chsh -s $(which zsh)`
4. **Restart the system**
5. Test that it worked with `echo $SHELL`, Expects /bin/zsh
6. Test with `$SHELL --version` expects zsh `5.1.1` or similar

#### 2) Install Oh-My-Zsh

Run this in your new terminal:

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
```

#### 3) Copy my custom configs

available in the **menu** at [TERMINAL CUSTOM CONFIGS LINUX ](#terminal-custom-configs-linux)

#### 4) Add important plugins

1. install `highlighting` plugin

```
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting
```

2. install `auto suggestions` plugin

```
git clone https://github.com/zsh-users/zsh-autosuggestions $ZSH_CUSTOM/plugins/zsh-autosuggestions
```

3. add the configs at `.zshrc`

```sh
**** =>>>>> SEARCH FOR PLUGINS AND ADD THIS LINE

plugins=(git zsh-syntax-highlighting zsh-autosuggestions)rr
```

#### 5) Add themes to be pretty

1. open `.zshrc` and search for `ZSH_THEME`

sould have something like:

```sh
# Set name of the theme to load --- if set to "random", it will
# load a random theme each time oh-my-zsh is loaded, in which case,
# to know which specific one was loaded, run: echo $RANDOM_THEME
# See https://github.com/ohmyzsh/ohmyzsh/wiki/Themes
ZSH_THEME="robbyrussell"
```

2. click [in the link](https://github.com/ohmyzsh/ohmyzsh/wiki/Themes) and select the best one







<Br>
<Br>
<Br>
<Br>
<Br>
____________________________________________________________________________________
<Br>
<Br>
<Br>
<Br>
<Br>






## TERMINAL CUSTOM CONFIGS LINUX

```sh
# If you come from bash you might have to change your $PATH.
# export PATH=$HOME/bin:/usr/local/bin:$PATH

# Path to your oh-my-zsh installation.
export ZSH="$HOME/.oh-my-zsh"

# Set name of the theme to load --- if set to "random", it will
# load a random theme each time oh-my-zsh is loaded, in which case,
# to know which specific one was loaded, run: echo $RANDOM_THEME
# See https://github.com/ohmyzsh/ohmyzsh/wiki/Themes
ZSH_THEME="robbyrussell"

# Set list of themes to pick from when loading at random
# Setting this variable when ZSH_THEME=random will cause zsh to load
# a theme from this variable instead of looking in $ZSH/themes/
# If set to an empty array, this variable will have no effect.
# ZSH_THEME_RANDOM_CANDIDATES=( "robbyrussell" "agnoster" )

# Uncomment the following line to use case-sensitive completion.
# CASE_SENSITIVE="true"

# Uncomment the following line to use hyphen-insensitive completion.
# Case-sensitive completion must be off. _ and - will be interchangeable.
# HYPHEN_INSENSITIVE="true"

# Uncomment one of the following lines to change the auto-update behavior
# zstyle ':omz:update' mode disabled  # disable automatic updates
# zstyle ':omz:update' mode auto      # update automatically without asking
# zstyle ':omz:update' mode reminder  # just remind me to update when it's time

# Uncomment the following line to change how often to auto-update (in days).
# zstyle ':omz:update' frequency 13

# Uncomment the following line if pasting URLs and other text is messed up.
# DISABLE_MAGIC_FUNCTIONS="true"

# Uncomment the following line to disable colors in ls.
# DISABLE_LS_COLORS="true"

# Uncomment the following line to disable auto-setting terminal title.
# DISABLE_AUTO_TITLE="true"

# Uncomment the following line to enable command auto-correction.
# ENABLE_CORRECTION="true"

# Uncomment the following line to display red dots whilst waiting for completion.
# You can also set it to another string to have that shown instead of the default red dots.
# e.g. COMPLETION_WAITING_DOTS="%F{yellow}waiting...%f"
# Caution: this setting can cause issues with multiline prompts in zsh < 5.7.1 (see #5765)
# COMPLETION_WAITING_DOTS="true"

# Uncomment the following line if you want to disable marking untracked files
# under VCS as dirty. This makes repository status check for large repositories
# much, much faster.
# DISABLE_UNTRACKED_FILES_DIRTY="true"

# Uncomment the following line if you want to change the command execution time
# stamp shown in the history command output.
# You can set one of the optional three formats:
# "mm/dd/yyyy"|"dd.mm.yyyy"|"yyyy-mm-dd"
# or set a custom format using the strftime function format specifications,
# see 'man strftime' for details.
# HIST_STAMPS="mm/dd/yyyy"

# Would you like to use another custom folder than $ZSH/custom?
# ZSH_CUSTOM=/path/to/new-custom-folder

# Which plugins would you like to load?
# Standard plugins can be found in $ZSH/plugins/
# Custom plugins may be added to $ZSH_CUSTOM/plugins/
# Example format: plugins=(rails git textmate ruby lighthouse)
# Add wisely, as too many plugins slow down shell startup.
plugins=(git zsh-syntax-highlighting zsh-autosuggestions)

source $ZSH/oh-my-zsh.sh

# User configuration

# export MANPATH="/usr/local/man:$MANPATH"

# You may need to manually set your language environment
# export LANG=en_US.UTF-8

# Preferred editor for local and remote sessions
# if [[ -n $SSH_CONNECTION ]]; then
#   export EDITOR='vim'
# else
#   export EDITOR='mvim'
# fi

# Compilation flags
# export ARCHFLAGS="-arch x86_64"

# Set personal aliases, overriding those provided by oh-my-zsh libs,
# plugins, and themes. Aliases can be placed here, though oh-my-zsh
# users are encouraged to define aliases within the ZSH_CUSTOM folder.
# For a full list of active aliases, run `alias`.
#
# Example aliases
# alias zshconfig="mate ~/.zshrc"
# alias ohmyzsh="mate ~/.oh-my-zsh"

# ============================================
#
#
#
#  MY CUSTOM ENVIRONMENT
#
#
#
# ============================================

# colored GCC warnings and errors
#export GCC_COLORS='error=01;31:warning=01;35:note=01;36:caret=01;32:locus=01:quote=01'

# Add an "alert" alias for long running commands.  Use like so:
#   sleep 10; alert
alias alert='notify-send --urgency=low -i "$([ $? = 0 ] && echo terminal || echo error)" "$(history|tail -n1|sed -e '\''s/^\s*[0-9]\+\s*//;s/[;&|]\s*alert$//'\'')"'

export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"                   # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion" # This loads nvm bash_completion

# ALIAS
alias force_restart="sudo shutdown -r now"
alias force_shutdown="sudo shutdown -P now"

alias awsx="source _awsx"
alias awstest="aws sqs list-queues"

alias cdenv="code \"/home/matheus/.zshrc\""
alias cdneo="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/\""

alias credit="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/credit-onboarding-service\""
alias cdcos="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/credit-onboarding-service\""
alias savings="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/savings-onboarding-service\""
alias cdsos="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/savings-onboarding-service\""
alias user="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/user-service\""
alias cdus="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/user-service\""
alias cduos="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/unified-onboarding-service\""
alias invest="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/investiment-onboarding-service\""
alias cdios="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/investiment-onboarding-service\""
alias identity="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/identity-service\""
alias cdis="cd \"/home/matheus/DEVELOPMENT/repositories/NEO/identity-service\""

alias cos="code \"/home/matheus/DEVELOPMENT/repositories/NEO/credit-onboarding-service\""
alias sos="code \"/home/matheus/DEVELOPMENT/repositories/NEO/savings-onboarding-service\""
alias us="code \"/home/matheus/DEVELOPMENT/repositories/NEO/user-service\""
alias uos="code \"/home/matheus/DEVELOPMENT/repositories/NEO/unified-onboarding-service\""
alias is="code \"/home/matheus/DEVELOPMENT/repositories/NEO/identity-service\""

alias disablenet="sudo kill $(pgrep -f /opt/netskope/stagent/stAgentSvc)"
alias disablenet2="sudo kill $(pgrep -f /opt/netskope/stagent/stAgentApp)"

alias processlist="sudo ps -aux"
alias husky_reinstall="rm -rf .git/hooks & npm install"

alias integration_git="git checkout integration"
alias integration_git_reset="git branch -d -f integration & git pull --all"
alias reset_git_integration="integration_reset"

# ENVS
export GIT_SSL_NO_VERIFY=1
export AWS_CA_BUNDLE="~/.aws/ca_bundle.pem"
#
#
#
# ============================================ END#
#
#
#
#
#
```






<Br>
<Br>
<Br>
<Br>
<Br>
____________________________________________________________________________________
<Br>
<Br>
<Br>
<Br>
<Br>






## SIGNING GIT COMMITS

  
#### >>> PRE GIT FIRST

  1. install git: https://github.com/git-guides/install-git#install-git-on-linux
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
  
   3. Add git credentials to stop asking for the login and password. Create a file `user_folder/.git-credentials` and add:
```sh
https://matheusicaro2%40hotmail.com:ghp_REDACTEDREDACTEDREDACTEDREDACTED@github.com #token comes from git token
``` 

   <br> **GENERAYE KEY**
   
   <br> 1. Install GPG: `sudo apt-get install gpg`
   
   <br> 2 Run: `gpg --gen-key`
   
   <br> 2.1. This will prompt you for your name and email--fill these out. **Make sure you use the same email that is set in your github as a primary email**
   
   <br> 2.2. IMPORTANT: IT IS GOING TO ASK YOU 4 TIMES TO SET A SECRET PASSWORD. DOING THIS WILL BE A BAD TIME. Instead, just leave the fields blank and proceed without a password. **If you do set a password, you will need to enter it every time you commit.**
   
   <br> 3. Run `gpg --list-secret-keys --keyid-format LONG` and copy the 16 character key identifier listed on the SECOND line
   
   <br> 4. Run `gpg --armor --export 0000000000000000000000000000000000000000 gpg-key.txt`
   
   ![signing%20git%20commits.png](https://github.com/matheusicaro/private-helpers/blob/master/files/signing%20git%20commits.png)

   <br> **CONFIGURE GIT**
   
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






<Br>
<Br>
<Br>
<Br>
<Br>
____________________________________________________________________________________
<Br>
<Br>
<Br>
<Br>
<Br>






## Edit System File by CLI

##### Mac IOS / Linux

```powershell
# open file   -     <FILE> => [ .zshrc, .bash_profile ] => Example: sudo vim ~/.zshrc
sudo vim ~/<FILE>

# exit file
ctrl+c

# set command
Digit ˜:˜

# save and close file command
Digit “wq”

# confirm
Digit Enter

```






<Br>
<Br>
<Br>
<Br>
<Br>
____________________________________________________________________________________
<Br>
<Br>
<Br>
<Br>
<Br>






# WINDOWS

## PC Performace on TASKBAR

![image](https://user-images.githubusercontent.com/29001162/174864608-e2b2e42c-1cb0-4d5e-82a3-982e3b855553.png)

<br>
**with new update**
<br>
<br>

![image](https://user-images.githubusercontent.com/29001162/182479311-622e28c2-63d8-4bde-87ac-61f6039a3b3b.png)

                                                            --||--

> - **Windows 11**: [Steps required before](#windows-11)

1. Dowload the **[Perfmonbar app](https://xhmikosr.github.io/perfmonbar/)**
2. Enable taskbar:
   > Click with right mouse button > Tollbars > Performace Monitor Bar
3. set my xml config:

> C:\Users\{User}\AppData\Roaming\PerfmonBar\config.xml

```xml
<?xml version="1.0" encoding="utf-8"?>

<!-- ============================================================================================================= -->
<!--     you can reload the configuration file with right click -> Performance Monitor - (Reload Configuration)    -->
<!-- ============================================================================================================= -->

<perfbar>
    <counters>

        <!-- ======================================================================================== -->
        <!--   you can run `typeperf -q>counters.txt` to list all available counters on your system   -->
        <!-- ======================================================================================== -->
        <counter name="CPU_USAGE" value="\Processor Information(_Total)\% Processor Time"/>
        <counter name="AVAILABLE_MEMORY" value="\Memory\Available MBytes"/>

    </counters>
    <pages>

        <!-- ======================================================================== -->
        <!-- use left button click on the performance bar to switch between the pages -->
        <!-- ======================================================================== -->

        <page offsetY="6">

            <lines>

                <line fontFamily="Segoe UI" fontSize="8" fontItalic="false" fontBold="true" fontColor="469fe3">

        		        <!-- ======================================================================================== -->
        		        <!-- you can use the "characters" attribute to ensure a minimum length of the displayed value -->
        		        <!-- ======================================================================================== -->
                    <display prefix=" CPU     " suffix="% " counter="CPU_USAGE"/>

                </line>

                <line fontFamily="Segoe UI" fontSize="7" fontItalic="false" fontBold="true" fontColor="3ded69">

        		        <!-- ======================================================================================== -->
        		        <!--       divide here means the total of the MEMORY                                          -->
        		        <!--       so the calc is: TOTAL_AVAILABLE / TOTAL_MEMORY = AVAILABLE_PERCENT                 -->
        		        <!-- ======================================================================================== -->
                    <display prefix=" RAM  + " suffix="%" counter="AVAILABLE_MEMORY" decimals="0" divide="320" />

                </line>

            </lines>

        </page>
    </pages>
    <settings minSizeX="10" minSizeY="10">
    </settings>
</perfbar>


```

#### Windows 11

1. Install **ExplorerPatcher** according to [issue 46](https://github.com/XhmikosR/perfmonbar/issues/46)
2. My default cofings:

![image](https://user-images.githubusercontent.com/29001162/176318577-8dadfb0d-d572-4559-b24c-59fa1547e3ec.png)

