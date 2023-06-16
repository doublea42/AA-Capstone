#sam delete
sam build
sam deploy --s3-bucket nss-s3-c02-u5-project-team7-java-andres --parameter-overrides S3Bucket=nss-s3-c02-u5-project-team7-java-andres FrontendDeployment=local  CognitoDomain=team7-java-andres-individual-one
