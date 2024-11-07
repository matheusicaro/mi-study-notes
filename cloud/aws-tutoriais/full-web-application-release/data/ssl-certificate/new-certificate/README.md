# NEW SSL CERTIFICATE SETUP → → in the NEW ACCOUNT

## 1. Request certificate in [AWS Certificate Manager (ACM)](https://us-east-1.console.aws.amazon.com/acm/home?region=us-east-1#/welcome)

![Alt text](image.png)
<br>

## 2. Update and access the request created

![Alt text](image-1.png)

## 3. Copy the values

![Alt text](image-2.png)

<br>
<br>

## 4. Go to the MAIN ACCOUNT

### 4.1. Go to [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

![Alt text](image-3.png)

### 4.2. Create a new Record with the values copied in STEP 3

![Alt text](image-4.png)

### 4.3. Wait some minutes to get it ISSUED

![Alt text](image-5.png)

<br>
<br>

## 5. Go to beanstalk

### 5.1. Go to Instance traffic and scaling

### 5.2. Add a listener

![Alt text](image-6.png)

### 5.3. The certificate will be ready to be added

![Alt text](image-7.png)

### 5.4. Apply and restart the service

![Alt text](image-8.png)

<br>
<br>

## 6. Copy the domain when the service is ready again

![Alt text](image-10.png)

<br>
<br>

## 7. Go to the mean account in [Route 53](https://us-east-1.console.aws.amazon.com/route53/v2/home?region=us-east-1#Home)

Replace the domain

![Alt text](image-9.png)

## 8. ⚠️🚨⚠️🚨⚠️ WAIT SOME MINUTES ~10 until the changes being applied 🚨

![Alt text](image-11.png)
