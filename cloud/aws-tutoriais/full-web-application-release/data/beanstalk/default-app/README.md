# SETUP DEFAULT APP in Beanstalk

## 1. Create AWS DEFAULT application in [Elastic Beanstalk here](https://ca-central-1.console.aws.amazon.com/elasticbeanstalk/home?region=ca-central-1#/applications)

![Alt text1](image.png)

## 2. Create Environment

![Alt text2](image-1.png)

<br>
<br>

### 2.1. STEP: Configure environment

- Application name: `mi-backend-<YEAR>`
- Environment name: `Mi-backend-<YEAR>-env`
- domain: `mi-backend-<YEAR>`

![Alt text](image-2.png)
![Alt text](image-2.2.png)

<br>
<br>

### 2.2. STEP: Configure service access

2.2.1. If there is no `EC2 instance profile`, **WE SHOULD [CREATE IT HERE](https://github.com/matheusicaro/mysite-backend/blob/master/docs/cloud/IAM-Role/README.md) **

![Alt text](image-3.png)
![Alt text](image-4.png)

<br>
<br>

### 2.3. STEP: Set up networking, database, and tags

N/A => Next

<br>
<br>

### 2.4. STEP: Configure instance traffic and scaling

N/A => Next

<br>
<br>

### 2.5. STEP: Configure updates, monitoring, and logging

N/A => Next

<br>
<br>

### 2.6 The application should be created successfully

![Alt text](image-5.png)

<br>
<br>
