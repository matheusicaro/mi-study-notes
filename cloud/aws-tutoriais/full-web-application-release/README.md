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

<details>
<summary>2.1__ FIRST, Deploy a default APP _________________________________</summary>

#### SETUP DEFAULT APP in Beanstalk

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

2.2.1. If there is no `EC2 instance profile`, **WE SHOULD [CREATE IT HERE](https://github.com/matheusicaro/mysite-backend/blob/master/docs/cloud/IAM-Role/README.md) **

![Alt text](data/beanstalk/default-app/image-3.png)
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

N/A => Next

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
</details>

<br>

<details>
<summary>2.2__ SECONDLY, Deploy My cusom App _________________________________</summary>

#### SETUP MY CUSTOM APP in Beanstalk

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

</details>


<br>
<br>
<br>


## 3. Create CodePipeline - - > [HERE](data//codepipeline/README.md)

<details>
<summary>Open it here: </summary>

#### Create CodePipeline here

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

</details>

<br>
<br>
<br>

#### 4. Setup my CUSTOM APP in Elastic Beanstalk - - > [HERE](data//beanstalk/my-custom-app/README.md)

#### 5. Setup SSL CERTIFICATE - - > [HERE](data//ssl-certificate/README.md)

#### 6. Setup ROUTE from `service...` to new ELASTIC BEING STALK

   6.1. GO to the ROUTE-53 and edit the value with the new host from the Beanstalk URL

![alt text](data/image.png)
