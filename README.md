# UTILS
 
   - LINUX ENV
       - **[TERMINAL custom configs](#terminal-custom-configs-linux)**
       - **[TERMINAL install ZSH pretty](#terminal-install-zsh-pretty)**
       
       - **[SLACK Error on login](#slack-error-on-login)**
        
   - [PC Performace on TASKBAR](#pc-performace-on-taskbar)
   - [Edit System File by CLI](#edit-system-file-by-cli)
   - [Markdown tips](#markdown-tips)
   - **IDE configs**
       - [IntelliJ Settings](https://github.com/matheusicaro/private-helpers/blob/master/IDE-confgis/my-intellij-settings.zip)
       - [VS Code](https://github.com/matheusicaro/private-helpers/blob/master/IDE-configs/vscode/keybindings-CURRENTLY.json)

# AWS

- [🚨 SSL AWS CLI error - fix](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#ssl-aws-cli-certificate-error)

- [Install **AWS CLI** (aws-vault)](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#install-aws-cli-aws-vault)
- [**AWS CLI Commands**](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#aws-cli-commands)
   - [Valid Local Access](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#valid-local-access)
   - [Get aws-vault](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#get-aws-vault)
      - [SSO version](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#sso-version)
      - [AWS old version](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#aws-old-version)
      - [Config FILES](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#config-files)
- [CloudWatch insights logs](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#cloudwatch-analytic-insights-logs)
- [Configuring Website Redirecting To External Domains: TO WIX, GODADDY, WORDPRESS](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#configuring-website-redirecting-to-external-domains-to-wix-godaddy-wordpress)
- [DynamoDB Java Integration](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#dynamodb-java-integration)
- [Get **AWS CA** Private Certificate](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#get-aws-ca-private-certificate)
- [How To Add **public CA** To Elastic Bean Aws](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#how-to-add-public-ca-to-elastic-bean-aws)
- [**Kurbenets**](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#kurbenets)
    - [Kubernetes Dashboard: Get Access Token](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#kubernetes-dashboard-get-access-token)
    - [CLI: Get Pod Logs](https://github.com/matheusicaro/private-helpers/blob/master/aws/README.md#cli-get-pod-logs)

# CODE

- [Mongo](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#mongo)
    - [Queries](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#queries)
- [JavaScript](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#JavaScript)
    - [Add script GLOBALLY](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#add-script-globally)
    - [Mocked Functions](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#mocked-functions)
    - [JEST - Tips](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#jest-tips)
- [React](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#react)
    - Eslint start config
    - [Stack React Apps](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#stack-react-apps)
- [Java](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#java)
   - [Array Instances](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#array-instances)
   - [Converter](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#converter)
   - [Certificate CA SSL Importing JAVA Cacerts](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#certificate-ca-ssl-importing-java-cacerts)
   - [Date](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#date)
   - [Encryption](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#encryption)
   - [Lambda Functions](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#lambda-functions)
   - [Map](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#map)
   - [**MAVEN CLI Commands**](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#maven-cli-commands)
   - [Mocked Static Method](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#mocked-static-method)
   - [Regex](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#regex)
   - [String](https://github.com/matheusicaro/private-helpers/blob/master/code/README.md#string) 

---

# LINUX ENV


## SETUP

1. Install NVM and use it to setup Node
<br>    a. `nvm install 16` set it as default
<br>    b. `nvm install 14` and `nvm install 12`
3. DOCKER
<br>    a. [install](https://docs.docker.com/engine/install/ubuntu/)
<br>    b. [Manage docker as a non-root user. Here are the steps](https://docs.docker.com/engine/install/linux-postinstall/)
<br>    c. [Docker Compose](https://docs.docker.com/compose/install/)
4. Install a MongoDB Client UI
<br>    a. [Compass](https://www.mongodb.com/products/compass) or [Robo3T](https://robomongo.org/download)

## TERMINAL CUSTOM CONFIGS LINUX

```sh
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
alias awsx="source _awsx"
alias awstest="aws sqs list-queues"

alias cdenv="code \"/home/matheus/.zshrc\""
alias cdneo="cd \"/home/matheus/repositories/NEO/\""

alias credit="cd \"/home/matheus/repositories/NEO/credit-onboarding-service\""
alias cdcos="cd \"/home/matheus/repositories/NEO/credit-onboarding-service\""
alias savings="cd \"/home/matheus/repositories/NEO/savings-onboarding-service\""
alias cdsos="cd \"/home/matheus/repositories/NEO/savings-onboarding-service\""
alias user="cd \"/home/matheus/repositories/NEO/user-service\""
alias cdus="cd \"/home/matheus/repositories/NEO/user-service\""
alias cduos="cd \"/home/matheus/repositories/NEO/unified-onboarding-service\""
alias invest="cd \"/home/matheus/repositories/NEO/investiment-onboarding-service\""
alias cdios="cd \"/home/matheus/repositories/NEO/investiment-onboarding-service\""
alias identity="cd \"/home/matheus/repositories/NEO/identity-service\""
alias cdis="cd \"/home/matheus/repositories/NEO/identity-service\""

alias cos="code \"/home/matheus/repositories/NEO/credit-onboarding-service\""
alias sos="code \"/home/matheus/repositories/NEO/savings-onboarding-service\""
alias us="code \"/home/matheus/repositories/NEO/user-service\""
alias uos="code \"/home/matheus/repositories/NEO/unified-onboarding-service\""
alias is="code \"/home/matheus/repositories/NEO/identity-service\""

alias disablenet="sudo kill $(pgrep -f /opt/netskope/stagent/stAgentSvc)"
alias disablenet2="sudo kill $(pgrep -f /opt/netskope/stagent/stAgentApp)"

alias processlist="sudo ps -aux"

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

----
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
----

## TERMINAL install ZSH pretty

#### 1) Install Zsh
1. ```sudo apt-get install zsh```
2. Verify it’s installed — `zsh --version`, expects zsh `5.1.1` or more recent
3. Make it your default shell: ```chsh -s $(which zsh)```
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
# Which plugins would you like to load?
# Standard plugins can be found in $ZSH/plugins/
# Custom plugins may be added to $ZSH_CUSTOM/plugins/
# Example format: plugins=(rails git textmate ruby lighthouse)
# Add wisely, as too many plugins slow down shell startup.
plugins=(git zsh-syntax-highlighting zsh-autosuggestions)
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
2. click in the link and select the best one

<br>
<br>

----
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
----

## SLACK Error on login

Just go to this link: https://example-company.slack.com/ssb/signin_redirect/fallback

and copy the key and past in the slack desktop app

----
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
----

## PC Performace on TASKBAR

![image](https://user-images.githubusercontent.com/29001162/174864608-e2b2e42c-1cb0-4d5e-82a3-982e3b855553.png)

<br>
**with new update**
<br>
<br>

![image](https://user-images.githubusercontent.com/29001162/182479311-622e28c2-63d8-4bde-87ac-61f6039a3b3b.png)

                                                            --||--

> * **Windows 11**: [Steps required before](#windows-11)

1. Dowload the **[Perfmonbar app](https://xhmikosr.github.io/perfmonbar/)**
2. Enable taskbar: 
   > Click with right mouse button  >  Tollbars  >  Performace Monitor Bar
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


----

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
----
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
<Br>
----
 
# Markdown tips

**FONT**: [www.w3schools.io/file/markdown-code-fence-blocks](https://www.w3schools.io/file/markdown-code-fence-blocks/)

| Language                        | codetype                                  |
| ------------------------------- | ----------------------------------------- |
| javascript                      | javascript , ecmascript , js , jsx , node |
| html                            | html                                      |
| Shell programming               | console , shell                           |
| bash commands programming       | bash , sh , zsh                           |
| DOS commands and programming    | dos , cmd , bat                           |
| powershell commands programming | ps , powershell                           |
| properties files                | properties                                |
| python language                 | python                                    |
| xml or xslt                     | xml                                       |
| json content                    | json                                      |
| yaml content                    | yaml , yml                                |
| toml                            | toml                                      |
| php code                        | php                                       |
| C# or .net code                 | cs                                        |
| Rust programming code           | rs , rust                                 |
| Swift programming               | swift                                     |
| Typescript program code         | ts , typescript                           |
| Scala                           | scala                                     |
| React                           | jsx                                       |
| R programming                   | R                                         |
| Objective C                     | objectivec , objc                         |
| Lua                             | lua                                       |
| Julia                           | julia , jl                                |
| OCaml                           | ocaml , ml                                |
| Perl                            | pl , perl                                 |

