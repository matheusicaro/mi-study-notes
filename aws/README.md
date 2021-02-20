
# AWS HELPER

 - [Connect local services](#connect-local-services)

## Connect local services
  
 1. `aws-vault exec <profile>`
 
 2. Check which role/account it gotten \
	`aws sts get-caller-identity`
	
 3. Test access in a service \
	`aws dynamodb list-tables`
	
 5. Check environments values (Windows - use cmder) \
	`aws-vault exec <profile> -- env | grep AWS`
	
 4. Get values of access and set in your app \
 	`AWS_ACCESS_KEY_ID=`
	`AWS_SECRET_ACCESS_KEY=`

## Configuring static website with a redirect to Wix domain

 1. Create domain authority certificates

![step_1](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-1.png)
![step_2](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-2.png)

 2. Configuring CloudFront for a bucket with domain CNAMEs + previously generated certificate.
 
 ![step_3](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-3.png)
 ![step_4](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-4.png)
 ![step_5](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-5.png)
 ![step_6](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-6.png)

 3. WIX domain control

 ![step_7](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-7.png)

