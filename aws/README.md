
# AWS HELPER

 - [Connect local services](#connectlocalservices)

## Connect local services
  
 1. `aws-vault exec <profile>`
 2. Check which role/account it gotten
	`aws sts get-caller-identity`
 3. Test access in a service
	`aws dynamodb list-tables`
 5. Check environments values (Windows - use cmder)
	`aws-vault exec me -- env | grep AWS`
 4. Get values of access and set in your app
    `AWS_ACCESS_KEY_ID=`
    `AWS_SECRET_ACCESS_KEY=`
