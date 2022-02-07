
# AWS HELPER

 - [Connect local services](#connect-local-services)


## Install AWS CLI (aws-vault)

1. install aws cli, [here](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
2. install aws-vault, [here](https://github.com/99designs/aws-vault)
3. add a profile:
	```
	aws-vault add PROFILE_NAME
	
	Enter Access Key ID: <put your access_key_id>
	Enter Secret Access Key: <put your secret_access_key>
	
	Added credentials to profile "PROFILE_NAME" in vault
	```
3. check your profile:
	```
	Profile                    Credentials                Sessions
	=======                    ===========                ========
	default                    -                          -
	PROFILE_NAME               PROFILE_NAME               -

	```

4. done!

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
	
	
## GET AWS CA PUBLIC CERTIFICATE

1. list the certificates by cli:
	``` 
	C:\Users\Matheus Icaro                                                                                                                
	λ aws acm list-certificates                                                                                                           
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
	```
	C:\Users\Matheus Icaro                                                                                                                
	λ aws acm get-certificate --certificate-arn arn:aws:acm:us-east-1:XXXXXXX:certificate/XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX 

	{ 
		"Certificate": "-----BEGIN CERTIFICATE-----\nM .... mN9ti3m+9oqPIA==\n-----END CERTIFICATE-----\n", 
		"CertificateChain": "-----BEGIN CERTIFICATE-----\nMII8mYtWDTANBgkqhkiG9w0BAQsF\nADA5MQswCQYDVQQGEwJSlQOYiypok1JR4U\
				     nakcjMS9cmvqtmg5iUaQqqcT5NJ0hGA==\n-----END CERTIFICATE-----\n-----BEGIN CERTIFICATE-----\nMIIEdTCCA12gAwDAwMDBaFw0zNDA2
				     MjgxNzM5MZ3/VyVOEVqQdZe4O/Ui5GjLIAZHYcSNPYeehu\nVsyuLAOQ1xk4meTKCRlb/weWsKh/NEnfVqn3sF/tM+2MR7cwA130A4w=\n-----END CERTIFICATE-----" 
	}
	```


## Configuring static website with a redirect to Wix, GoDaddy, Wordpress domain

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

 4. GoDaddy

 ![step_8](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-8.png)

 4. WordPress

 ![step_9](https://github.com/matheusicaro/helpers/blob/master/aws/data/redirect%20another%20domain-9.png)
