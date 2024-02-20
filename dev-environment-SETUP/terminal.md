1. [Install ZSH pretty](#install-zsh-pretty)
2. [CUSTOM Terminal config](#custom-terminal-config)

<br>
<br>

# Install ZSH pretty

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
<Br>

# CUSTOM Terminal config

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
#
#
#
##############
#
#   NVM
#
##############
#
############## LINUX ENV
#
# export NVM_DIR="$HOME/.nvm"
# [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"                   # This loads nvm
# [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion" # This loads nvm bash_completion
#
#
export NVM_DIR="$HOME/.nvm"
[ -s "/opt/homebrew/opt/nvm/nvm.sh" ] && \. "/opt/homebrew/opt/nvm/nvm.sh"                                       # This loads nvm
[ -s "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm" ] && \. "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm" # This loads nvm bash_completion

# ALIAS
alias force_restart="sudo shutdown -r now"
alias force_shutdown="sudo shutdown -p now"

alias awstest="aws sqs list-queues"

alias cdenv="code \"/Users/matheus/.zshrc\""

alias credit="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cdcos="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias savings="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cdsos="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias user="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cdus="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cduos="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias invest="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cdios="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias identity="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias cdis="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""

alias cos="code \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias sos="code \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias us="code \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias uos="code \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""
alias is="code \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""

alias processlist="sudo ps -aux"
alias husky_reinstall="rm -rf .git/hooks & npm install"
alias skip_git="git commit --no-verify -m"
alias skip_husky="skip_git"

alias checkout="git checkout"
alias reset_integration="git branch -d -f integration & git pull --all"
alias branch_delete="git branch -d -f"
alias build="npm run build"
alias types="npm run generate:types:force"
alias test="npm run test"
alias pipeline="npm run build && npm run lint && npm run generate:types && npm run test"

# ENVS
export GIT_SSL_NO_VERIFY=1
export AWS_CA_BUNDLE="~/.aws/ca_bundle.pem"

#########################################################
# NEO Config setup
#########################################################

alias disablenet="sudo kill $(pgrep -f /opt/EXAMPLE-DLP-AGENT/agent)"
alias disablenet="sudo kill $(pgrep -f /opt/EXAMPLE-DLP-AGENT/agent)"

alias cdneo="cd \"/Users/example/DEVELOPMENT/repositories/EXAMPLE-ORG/example-service\""

###-begin-neo-completions-###
#
# yargs command completion script
#
# Installation: neo completions >> ~/.zshrc
#    or neo completions >> ~/.zsh_profile on OSX.
#
_neo_yargs_completions() {
    local reply
    local si=$IFS
    IFS=$'
' reply=($(COMP_CWORD="$((CURRENT - 1))" COMP_LINE="$BUFFER" COMP_POINT="$CURSOR" neo --get-yargs-completions "${words[@]}"))
    IFS=$si
    _describe 'values' reply
}
compdef _neo_yargs_completions neo

### it should add following script to your bash profile =========>

###-begin-neo-completions-###
#
# yargs command completion script
#
# Installation: neo autocomplete:script >> ~/.bashrc
#    or neo autocomplete:script >> ~/.bash_profile on OSX.
#
_yargs_completions() {
    local cur_word args type_list

    cur_word="${COMP_WORDS[COMP_CWORD]}"
    args=("${COMP_WORDS[@]}")

    # ask yargs to generate completions.
    type_list=$(neo --get-yargs-completions "${args[@]}")

    COMPREPLY=($(compgen -W "${type_list}" -- ${cur_word}))

    # if no match was found, fall back to filename completion
    if [ ${#COMPREPLY[@]} -eq 0 ]; then
        COMPREPLY=()
    fi

    return 0
}
complete -o default -F _yargs_completions neo
###-end-neo-completions-###

###-end-neo-completions-###

#
#
#
# ============================================ END#
#
#
#
#
#

export PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
export PUPPETEER_EXECUTABLE_PATH=$(which chromium)

```
