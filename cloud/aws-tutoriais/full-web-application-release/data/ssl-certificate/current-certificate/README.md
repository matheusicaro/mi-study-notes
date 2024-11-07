# Do DEPRECATE the current SSL CERTIFICATE SETUP

## 1. Change the OLD certificate FIRST to avoid have issues

### 1.1. Create a new certificate like:

![Alt text](image-11.png)

### 1.2. Copy the key and value

![Alt text](image-12.png)

## 2. Go to the MAIN ACCOUNT

### 2.1. Go to [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

### 2.2. Add the new certificate from the current year <2023>

![Alt text](image-13.png)

### 2.3. Wait until the service be validated and go the Beanstalk to add the new certificate

![Alt text](image-14.png)

### 2.4. Go to Instance traffic and scaling

![Alt text](image-15.png)

### 2.5. Apply and wait the service restarts

### 2.6. Check if the certificate is not being used: Ineligible

![Alt text](image-16.png)

### 2.6. Delete the previous certificate

Go the ACM in the other account and identity the register key

![Alt text](image-9.png)
