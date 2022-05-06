# AWS HELPER

# AWS
- [Install **AWS CLI** (aws-vault)](#install-aws-cli-aws-vault)
- [**AWS CLI Commands**](#aws-cli-commands)
   - [Valid Local Access](#valid-local-access)
   - [Get aws-vault](#get-aws-vault)
      - [SSO version](#sso-version)
      - [AWS old version](#aws-old-version)
      - [Config FILES](#config-files)
- [CloudWatch insights logs](#cloudwatch-analytic-insights-logs)
- [Configuring Website Redirecting To External Domains: TO WIX, GODADDY, WORDPRESS](#configuring-website-redirecting-to-external-domains-to-wix-godaddy-wordpress)
- [DynamoDB Java Integration](#dynamodb-java-integration)
- [Get **AWS CA** Private Certificate](#get-aws-ca-private-certificate)
- [How To Add **public CA** To Elastic Bean Aws](#how-to-add-public-ca-to-elastic-bean-aws)
- [**Kurbenets**](#kurbenets)
    - [Kubernetes Dashboard: Get Access Token](#kubernetes-dashboard-get-access-token)
    - [CLI: Get Pod Logs](#cli-get-pod-logs)

---

## INSTALL AWS CLI (AWS-VAULT)

1. install aws cli, [here](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
2. install aws-vault, [here](https://github.com/99designs/aws-vault)
3. add a profile:

   ```
   aws-vault add PROFILE_NAME

   Enter Access Key ID: <put your access_key_id>
   Enter Secret Access Key: <put your secret_access_key>

   Added credentials to profile "PROFILE_NAME" in vault
   ```

4. check your profile:

   ```
   Profile                    Credentials                Sessions
   =======                    ===========                ========
   default                    -                          -
   PROFILE_NAME               PROFILE_NAME               -

   ```

5. done!

---

## AWS CLI Commands


#### VALID LOCAL ACCESS

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

---

#### Get AWS-VAULT

##### SSO version

```powershell
aws-vault list --profiles

aws-vault exec <profile>

aws-vault exec <profile> -- perl ~/.aws/setprofile.pl
```

##### AWS old version

```powershell
 aws-vault exec --duration 1h -- uat ~/.aws/setprofile.pl
 
 aws-vault exec --assume-role-ttl=60m [config-profile] -- ~/scripts/setprofile.pl
 
 aws-vault exec --session-ttl=1h --assume-role-ttl=1h -- <profile> ~/.aws/setprofile.pl
```

##### Config Files

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

- config    _(without an type: .extension)_
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

---

## Kurbenets

#### Kubernetes Dashboard: Get Access Token

```powershell
# TOKEN WILL BE SAVE AT YOUR CLIPBOARD
aws eks get-token --cluster-name <CLUSTER_NAME> | awk -F '\"token\":' '{print $2}' | awk -F '}' '{print $1}' | sed 's/\"//g;s/^\ //g' | pbcopy
  
```

#### CLI: Get Pod Logs

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

---

## CloudWatch Analytic insights logs

```sql
FILTER (@message like “<some-text-part-from-log>“) 
AND (@message like “<some-text-part-from-log>”) 
OR (@message like "<some-text-part-from-log>")
| stats count(*) as exceptionCount by @message
| limit 20
```


---

## GET AWS CA PRIVATE CERTIFICATE

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

## DynamoDB Java Integration

**Dynamo FACTORY**

```java
package project.name.datasource;

import .Bean;
import .Factory;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Factory
public class DynamoConfig {

    @Bean
    public AmazonDynamoDB getClient() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}
```

**INTERFACE:**

```java
package project.name.datasource;

public interface DataSource {

    void save(Object object);
}
```

**IMPLEMENTATION:**

```java
package project.name.datasource;

import arch.context.annotation.Service;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;

@Service
public class DataSourceImpl implements DataSource {

    private final DynamoDBMapper dynamoDBMapper;

    public DataSourceImpl(AppConfig config, AmazonDynamoDB client) {
        TableNameOverride table = TableNameOverride.withTableNameReplacement(config.getDynamoDBTableName());

        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder() // @formatter:off
            .withTableNameOverride(table)
            // Update only elements where are not null
            .withSaveBehavior(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
            .build(); // @formatter:on

        dynamoDBMapper = new DynamoDBMapper(client, mapperConfig);
    }

    @Override
    public void save(Object object) {
        dynamoDBMapper.save(object);
    }
}
```

---

## HOW TO ADD PUBLIC CA TO ELASTIC BEAN AWS

1. Request the public certificate

![add-public-ca-1](https://github.com/matheusicaro/helpers/blob/master/aws/data/add-public-ca-1.png)

2. copy your **CNAME name** and **CNAME value**

![add-public-ca-1](https://github.com/matheusicaro/helpers/blob/master/aws/data/add-public-ca-2.png)

3. In **ANOTHER ACCOUNT**, add your **CNAME name** and **CNAME value**

![add-public-ca-1](https://github.com/matheusicaro/helpers/blob/master/aws/data/add-public-ca-3.png)

---

## CONFIGURING WEBSITE REDIRECTING TO EXTERNAL DOMAINS: TO WIX, GODADDY, WORDPRESS

1.  Create domain authority certificates

![step_1](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-1.png)
![step_2](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-2.png)

2.  Configuring CloudFront for a bucket with domain CNAMEs + previously generated certificate.

![step_3](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-3.png)
![step_4](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-4.png)
![step_5](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-5.png)
![step_6](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-6.png)

3.  WIX domain control

![step_7](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-7.png)

4.  GoDaddy

![step_8](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-8.png)

4.  WordPress

![step_9](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-9.png)
