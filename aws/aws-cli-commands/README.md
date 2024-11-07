# AWS HELPER

# AWS

- [Valid Local Access](#valid-local-access)
- [Get **AWS CA** Private Certificate](#get-aws-ca-private-certificate)
- [Get aws-vault](#get-aws-vault)
    - [SSO version](#sso-version)
    - [AWS old version](#aws-old-version)
    - [Config FILES](#config-files)
- [**Kubernetes**](#kubernetes)
    - [Dashboard: Get Access Token](#kubernetes-dashboard-get-access-token)
    - [Dashboard: Get Pod Logs](#kubernetes-get-pod-logs)

---


# VALID LOCAL ACCESS

<details>
<summary>Open here</summary>


```powershell
aws-vault exec <profile>

# Check which role/account it gotten
aws sts get-caller-identity

# Test access in a service
aws dynamodb list-tables

#Check environments values (Windows - use cmder)
aws-vault exec <profile> -- env | grep AWS

#Get values of access and set in your app =>
AWS_ACCESS_KEY_ID=
AWS_SECRET_ACCESS_KEY=
```

<br>
<br>
</details>
---

# Get AWS CA Private Certificate

<details>
<summary>Open here</summary>

1. list the certificates by cli:

```powershell
aws acm list-certificates

# output
{
    "CertificateSummaryList": [
{
    "CertificateArn": "arn:aws:acm:us-east-1:XXXXXXX:certificate/XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX",
    "DomainName": "mydomain.com"
}
    ]
}
```

2. get your certificate by arn:

```powershell
aws acm get-certificate --certificate-arn arn:aws:acm:us-east-1:XXXXXXX:certificate/XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX

# output
   {
   	"Certificate": "-----BEGIN CERTIFICATE-----\nM .... mN9ti3m+9oqPIA==\n-----END CERTIFICATE-----\n",
   	"CertificateChain": "-----BEGIN CERTIFICATE-----\nMII8mYtWDTANBgkqhkiG9w0BAQsF\nADA5MQswCQYDVQQGEwJSlQOYiypok1JR4U\
   			     nakcjMS9cmvqtmg5iUaQqqcT5NJ0hGA==\n-----END CERTIFICATE-----\n-----BEGIN CERTIFICATE-----\nMIIEdTCCA12gAwDAwMDBaFw0zNDA2
   			     MjgxNzM5MZ3/VyVOEVqQdZe4O/Ui5GjLIAZHYcSNPYeehu\nVsyuLAOQ1xk4meTKCRlb/weWsKh/NEnfVqn3sF/tM+2MR7cwA130A4w=\n-----END CERTIFICATE-----"
   }
```

---

<br>
<br>
</details>
---

# Get AWS-VAULT

## SSO version

<details>
<summary>Open here</summary>

```powershell
aws-vault list --profiles

aws-vault exec <profile>

aws-vault exec <profile> -- perl ~/.aws/setprofile.pl
```

<br>
<br>
</details>
---

## AWS old version

<details>
<summary>Open here</summary>

```powershell
 aws-vault exec --duration 1h -- uat ~/.aws/setprofile.pl

 aws-vault exec --assume-role-ttl=60m [config-profile] -- ~/scripts/setprofile.pl

 aws-vault exec --session-ttl=1h --assume-role-ttl=1h -- <profile> ~/.aws/setprofile.pl
```

<br>
<br>
</details>
---

## Config Files

<details>
<summary>Open here</summary>


- setprofile.pl

```
#!/usr/bin/env perl
open( CF, '>',"$ENV{HOME}/.aws/credentials");
print CF <<EOF;
[default]
aws_session_token=$ENV{AWS_SESSION_TOKEN}
aws_secret_access_key=$ENV{AWS_SECRET_ACCESS_KEY}
aws_access_key_id=$ENV{AWS_ACCESS_KEY_ID}
EOF

system "export AWS_SESSION_TOKEN=$ENV{AWS_SESSION_TOKEN}";
system "export AWS_SECRET_ACCESS_KEY=$ENV{AWS_SECRET_ACCESS_KEY}";
system "export AWS_ACCESS_KEY_ID=$ENV{AWS_ACCESS_KEY_ID}";
system "export AWS_DEFAULT_REGION=$ENV{AWS_REGION}";
system "export AWS_REGION=$ENV{AWS_REGION}";
system "aws configure set default.region $ENV{AWS_REGION}";
```

- credentials _(without an type: .extension)_

```
[default]
aws_session_token=EXAMPLESESSIONTOKEN1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ
aws_secret_access_key=EXAMPLESECRETACCESSKEY1234567890ABCD
aws_access_key_id=ASIAEXAMPLE123456789

```

- config _(without an type: .extension)_

```
[profile sso]
sso_start_url=<https://SERVER.awsapps.com/start/>
sso_region=us-east-1
sso_account_id=<ACCOUNT_ID>
sso_role_name=<ROLE>
region=us-east-1
output=json

[default]
region = us-east-1
output=json

[profile prd]
source_profile=<source>
role_arn=arn:aws:iam::<ACCOUNT_NUMBER>:role/<ROLE>
role_session_name=<SESSION_NAME>                        // the e-mail is usually used
region=sa-east-1

[...]

[profile uat]
source_profile=<source>
role_arn=arn:aws:iam::<ACCOUNT_NUMBER>:role/<ROLE>
role_session_name=<SESSION_NAME>                        // the e-mail is usually used
region=us-east-1

```

<br>
<br>
</details>
---





# Kubernetes
## Kubernetes Dashboard Get Access Token

<details>
<summary>Open here</summary>

```powershell
# TOKEN WILL BE SAVE AT YOUR CLIPBOARD
aws eks get-token --cluster-name <CLUSTER_NAME> | awk -F '\"token\":' '{print $2}' | awk -F '}' '{print $1}' | sed 's/\"//g;s/^\ //g' | pbcopy
  
```
<br>
<br>
</details>
---

## Kubernetes Get Pod Logs

<details>
<summary>Open here</summary>

```powershell
# GET AWS ACCESS TOKEN
aws-vault exec <PROFILE> -- perl ~/.aws/setprofile.pl

# CONNECT INTO THE CLUSTER
aws eks --region sa-east-1 update-kubeconfig --name <CLUSTER_NAME>

# DO LIST PODS	- <OPTIONAL_CONTEXT> => NAME_SPACE: [ default, prd, uat, ... ]
kubectl get pods -n <OPTIONAL_CONTEXT>

# SET THE CONTEXT IF IT IS NECESSARY
kubectl config set-context --current --namespace=<NAME_SPACE>

# PRINT LOG
kubectl logs <POD_NAME>

# BUILD LOG FILE
kubectl logs <POD_NAME> > file.txt

```

<br>
<br>
</details>
---

#### CLI: Get Pod Logs


# END