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

- Configure updates, monitoring, and logging - optional

![alt text](image-1.png)

![alt text](image-2.png)
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

## [4. Setup SSL CERTIFICATE && Redirect from `service...` to new ELASTIC BEING STALK](./../alias-from-domain-to-another-url/README.md)

<br>

## 5. Finish the APP account setup

<details>
<summary>Open it here: </summary>

<br>
<br>

    1. Back to Elastic Beanstalk here

- [Elastic Beanstalk here](https://ca-central-1.console.aws.amazon.com/elasticbeanstalk/home?region=ca-central-1#/applications)

<br>
<br>
<br>

    2. Enable the load balance with 1 instance only

![alt text](./data/beanstalk/my-custom-app/image.png)

<br>
<br>
<br>

    1. Add the HTTPS for 443 with the certificated added "in step 4"

![alt text](./data/beanstalk/my-custom-app/image-1.png)

<br>
<br>

    4. The service now should be ready and listening from the HTTPS

    5. CREATE the bill ALERT TO AVOID SPEND ANY CENT IN CLOUD

![alt text](./data/beanstalk/my-custom-app/image-2.png)

    6. EDIT THE ALERT AGAIN PARA DAILY alerts

![alt text](./data/beanstalk/my-custom-app/image-3.png)
