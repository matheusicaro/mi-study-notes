# ============================================
#
#
#
#  NEO dev environment setup
#
#
#
# ============================================
alias run_mongo_local="colima start & docker-compose pull"
# alias pipeline="npm run build && npm run lint && npm run generate:types && npm run test"
# alias types="npm run generate:types:force"

# ============================================
#
#
#
#  CHROMIUM Crawler setup
#
#
#
# ============================================
export PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
export PUPPETEER_EXECUTABLE_PATH=$(which chromium)
eval "$(~/.local/bin/mise activate)"
# ============================================
#
#
#
#  MY CUSTOM ENVIRONMENT
#
#
#
# ============================================
export GIT_SSL_NO_VERIFY=1

export PATH="/opt/homebrew/bin:$PATH"
export HOMEBREW_CASK_OPTS="--appdir=~/Applications"
export ZPLUG_HOME="$HOME/.zplug"
# export AWS_CA_BUNDLE="~/.aws/ca_bundle.pem"

export NVM_DIR="$HOME/.nvm"
[ -s "/opt/homebrew/opt/nvm/nvm.sh" ] && \. "/opt/homebrew/opt/nvm/nvm.sh"                                       # This loads nvm
[ -s "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm" ] && \. "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm" # This loads nvm bash_completion

#
# Previous setup for NVM
#
# export NVM_DIR="$HOME/.nvm"
# [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"                   # This loads nvm
# [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion" # This loads nvm bash_com

# Add RVM to PATH for scripting. Make sure this is the last PATH variable change.
export PATH="$PATH:$HOME/.rvm/bin"
eval "$(rbenv init - zsh)"

#
#
#__________________________________________ALIAS
alias awstest="aws sqs list-queues"

alias cdenv="code \"/Users/matheus.icaro/.zshrc\""

alias processlist="sudo ps -aux"
alias husky_reinstall="rm -rf .git/hooks & npm install"
alias skip_git="git commit --no-verify -m"
alias skip_husky="skip_git"

alias checkout="git checkout"
alias reset_integration="git branch -d -f integration & git pull --all"
alias reset_branch="git branch -d -f"
alias branch_delete="git branch -d -f"
alias build="npm run build"
alias test="npm run test"

alias python="python3"

#
#
#__________________________________________ zsh setup
export ZSH="$HOME/.oh-my-zsh"

ZSH_THEME="robbyrussell"

plugins=(git zsh-syntax-highlighting zsh-autosuggestions)

source $ZSH/oh-my-zsh.sh
