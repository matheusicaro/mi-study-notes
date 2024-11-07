# AWS HELPER

# AWS

- [Valid Local Access](#valid-local-access)
- [Get aws-vault](#get-aws-vault)
    - [SSO version](#sso-version)
    - [AWS old version](#aws-old-version)
    - [Config FILES](#config-files)

---


#### VALID LOCAL ACCESS

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

#### Get AWS-VAULT


##### SSO version

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

##### AWS old version

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

##### Config Files

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
aws_session_token=FwoGZXIvYXdzEI[...]kwYyLdA5Nagfwyhs6a4JAg1xs3X72RSj7/ZGmPDR0vmI2Wtvmf29YHH9GIHMCBRzMg==
aws_secret_access_key=q/jaJLzvhLg[...]MqZVZtW3DWO9a
aws_access_key_id=ASIA4T[...]OHIJBU

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