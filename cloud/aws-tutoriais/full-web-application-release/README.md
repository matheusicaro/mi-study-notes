# FULL WEB APPLICATION RELEASE IN AWS

<br>

- [How to Redirect My Domain To External Domains: TO WIX, GODADDY, WORDPRESS](/cloud/aws-tutoriais/redirect-domain-in-aws-to-external-servers/README.md)


<br>

---

<br>
<br>

## 1. Create a new E-MAIL account

I normally create with like: `cloud_<year>@gmail.com`

<br>

## 2. Create AWS DEFAULT application in Elastic Beanstalk

> < ! > for any error, check the **[documentation HERE](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/environments-cfg-autoscaling-launch-templates.html#environments-cfg-autoscaling-launch-templates-options)**

<details>
<summary>Open it here: </summary>

    1. Create AWS DEFAULT application in Elastic Beanstalk

- [Elastic Beanstalk here](https://ca-central-1.console.aws.amazon.com/elasticbeanstalk/home?region=ca-central-1#/applications)

![Alt text1](data/beanstalk/default-app/image.png)

<br>
<br>
<br>

    2. Create Environment

![Alt text2](data/beanstalk/default-app/image-1.png)

<br>
<br>
<br>

        2.1. STEP: Configure environment

- Application name: `mi-backend-<YEAR>`
- Environment name: `Mi-backend-<YEAR>-env`
- domain: `mi-backend-<YEAR>`

![Alt text](data/beanstalk/default-app/image-2.png)
![Alt text](data/beanstalk/default-app/image-2.2.png)

<br>
<br>
<br>

        2.2. STEP: Configure service access

<br>

<br>

- 2.2.1 create and use new service role
- 2.2.2 check the roles names and roles to be created in IAM

![Alt text](data/beanstalk/default-app/image-3.png)

---

<details>
<summary>````| 2.2.3. | - Create the IAM Roles here</summary>

<br>

- Create the role `aws-elasticbeanstalk-ec2-role`
  - AWSElasticBeanstalkMulticontainerDocker
  - AWSElasticBeanstalkWebTier
  - AWSElasticBeanstalkWorkerTier

![Alt text](data/IAM-Role/image-1.png)
![Alt text](data/IAM-Role/image-2.png)
![Alt text](data/IAM-Role/image-3.png)
![Alt text](data/IAM-Role/image-4.png)

<br>
<br>

- Create the role `aws-elasticbeanstalk-service-role`
  - AWSElasticBeanstalkEnhancedHealth
  - AWSElasticBeanstalkManagedUpdatesCustomerRolePolicy

![Alt text](data/IAM-Role/image-5.png)


        ````| 2.2.3. | - END

</details>

---
<br>

- select the roles after being created ( _step 2.2.3. above_ ^ )
![Alt text](data/beanstalk/default-app/image-4.png)

<br>
<br>
<br>

        2.3. STEP: Set up networking, database, and tags

N/A => Next

<br>
<br>
<br>

        2.4. STEP: Configure instance traffic and scaling

- select the disk ( THIS WAS MY MISTAKE IN 2024 with the new updates )

![alt text](image.png)

<br>
<br>
<br>

        2.5. STEP: Configure updates, monitoring, and logging

N/A => Next

<br>
<br>
<br>

        2.6 The application should be created successfully

![Alt text](data/beanstalk/default-app/image-5.png)

<br>
<br>
</details>

<br>


## 3. Create CodePipeline

<details>
<summary>Open it here: </summary>

<br>

- [CodePipeline here](https://ca-central-1.console.aws.amazon.com/codesuite/codepipeline/start?region=ca-central-1)

<br>

![Alt text](data/codepipeline/image-1.png)

<br>
<br>

    1. STEP 1:

![Alt text](data/codepipeline/image-2.png)

<br>
<br>
<br>

    2. STEP 2:

Connect to Github by `@matheusicaro`

![Alt text](data/codepipeline/image-3.png)

![Alt text](data/codepipeline/image-4.png)

<br>
<br>
<br>

    3. STEP 3:

![Alt text](data/codepipeline/image-5.png)

<br>
<br>
<br>

    4. STEP 4:

![Alt text](data/codepipeline/image-6.png)

<br>
<br>
<br>

    5. STEP 5:

The application should be created successfully!!!

![Alt text](data/codepipeline/image-7.png)

<br>
<br>
</details>

<br>


## 4. Setup my CUSTOM APP in Elastic Beanstalk

<details>
<summary>Open it here: </summary>

<br>
<br>

    1. Back to Elastic Beanstalk here]
 
- [Elastic Beanstalk here](https://ca-central-1.console.aws.amazon.com/elasticbeanstalk/home?region=ca-central-1#/applications)

<br>
<br>
<br>

    2. Go to the other application and check the configs

![Alt text](data/beanstalk/my-custom-app/image.png)

<br>
<br>
<br>

    3. Instance traffic and scaling

![Alt text](data/beanstalk/my-custom-app/image-1.png)

![Alt text](data/beanstalk/my-custom-app/image-2.png)

![Alt text](data/beanstalk/my-custom-app/image-3.png)

![Alt text](data/beanstalk/my-custom-app/image-4.png)

![Alt text](data/beanstalk/my-custom-app/image-5.png)

<br>
<br>
<br>

    4. Updates, monitoring, and logging

![Alt text](data/beanstalk/my-custom-app/image-6.png)
![Alt text](data/beanstalk/my-custom-app/image-7.png)
![Alt text](data/beanstalk/my-custom-app/image-8.png)
![Alt text](data/beanstalk/my-custom-app/image-9.png)

<br>
<br>
<br>

    5. Check if the API is ready

![Alt text](data/beanstalk/my-custom-app/image-11.png)
![Alt text](data/beanstalk/my-custom-app/image-10.png)

<br>
<br>
</details>

<br>


## 5. Setup SSL CERTIFICATE 

<details>
<summary>| 5.1 | - Deprecate the current certificate FIRST to avoid have issues</summary>
<br>

    5.1.1. Change the OLD certificate FIRST to avoid have issues

<br>

        5.1.1.1. Create a new certificate like:

![Alt text](data/ssl-certificate/current-certificate/image-11.png)

<br>
<br>
<br>

        5.1.1.2. Copy the key and value

![Alt text](data/ssl-certificate/current-certificate/image-12.png)

<br>
<br>
<br>

    5.1.2. Go to the MAIN ACCOUNT
<br>

        5.1.2.1. Go to [Route 53]
        
- [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

<br>

        5.1.2.2. Add the new certificate from the current year <2023>

![Alt text](data/ssl-certificate/current-certificate/image-13.png)

<br>
<br>
<br>

        5.1.2.3. Wait until the service be validated and go the Beanstalk to add the new certificate

![Alt text](data/ssl-certificate/current-certificate/image-14.png)

<br>
<br>
<br>

        5.1.2.4. Go to Instance traffic and scaling

![Alt text](data/ssl-certificate/current-certificate/image-15.png)

<br>
<br>
<br>

        5.1.2.5. Apply and wait the service restarts

<br>

        5.1.2.6. Check if the certificate is not being used: Ineligible

![Alt text](data/ssl-certificate/current-certificate/image-16.png)

<br>
<br>
<br>

        5.1.2.7. Delete the previous certificate

Go the ACM in the other account and identity the register key

![Alt text](data/ssl-certificate/current-certificate/image-17.png)

<br>
</details>
<br>

<details>
<summary>|  5.2  | - Add new SSL CERTIFICATE to the **new account**</summary>
<br>


      5.2.1. Request certificate in [AWS Certificate Manager (ACM)]
<br>

- [AWS Certificate Manager (ACM)](https://us-east-1.console.aws.amazon.com/acm/home?region=us-east-1#/welcome)

![Alt text](data/ssl-certificate/new-certificate/image.png)

<br>
<br>
<br>

      5.2.2. Update and access the request created

![Alt text](data/ssl-certificate/new-certificate/image-1.png)

<br>
<br>
<br>

    5.2.3. Copy the values

![Alt text](data/ssl-certificate/new-certificate/image-2.png)

<br>
<br>
<br>

    5.2.4. Go to the MAIN ACCOUNT

<br>

        5.2.4.1. Go to [Route 53]

<br>

- [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

![Alt text](data/ssl-certificate/new-certificate/image-3.png)

<br>
<br>
<br>

        5.2.4.2. Create a new Record with the values copied in STEP 3

<br>

![Alt text](data/ssl-certificate/new-certificate/image-4.png)

<br>
<br>
<br>

        5.2.4.3. Wait some minutes to get it ISSUED

![Alt text](data/ssl-certificate/new-certificate/image-5.png)

<br>
<br>
<br>

    5.2.5. Go to beanstalk

<br>

        5.2.5.1. Go to Instance traffic and scaling

<br>

        5.2.5.2. Add a listener

<br>

![Alt text](data/ssl-certificate/new-certificate/image-6.png)

<br>
<br>
<br>

        5.2.5.3. The certificate will be ready to be added

<br>

![Alt text](data/ssl-certificate/new-certificate/image-7.png)

<br>
<br>
<br>

        5.2.5.4. Apply and restart the service

<br>

![Alt text](data/ssl-certificate/new-certificate/image-8.png)

<br>
<br>
<br>

    5.2.6. Copy the domain when the service is ready again

<br>

![Alt text](data/ssl-certificate/new-certificate/image-10.png)

<br>
<br>
<br>

    5.2.7. Go to the mean account in [Route 53]

<br>

- [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

- Replace the domain

![Alt text](data/ssl-certificate/new-certificate/image-9.png)

<br>
<br>
<br>

    5.2.8. ⚠️🚨⚠️🚨⚠️ WAIT SOME MINUTES ~10 until the changes being applied 🚨

<br>

![Alt text](data/ssl-certificate/new-certificate/image-11.png)

<br>
</details>
<br>

<details>
<summary>|  5.3  | - Delete the previous DATA in the DEPRECATE ACCOUNT 🔫</summary>
<br>

- 3.1. Delete the certificates
- 3.2. Delete environment and apps
- 3.3. Delete the ACCOUNT

<br>
<br>
</details>

<br>

## 6. Setup ROUTE from `service...` to new ELASTIC BEING STALK
<br>

      6.1. GO to the ROUTE-53 and edit the value with the new host from the Beanstalk URL

![alt text](data/image.png)
