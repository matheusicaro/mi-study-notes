# Inter Stack for React Apps

![i-app-stack-1](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-1.png?raw=true)

![i-app-stack-2](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-2.png?raw=true)

- A funcao lambda pode atuar em 4 pontos diferentes
  1 - no VIEWER-REQUEST quando a solicitação esta chegando antes de ser bater no cloud-front
  2 - no ORIGIN-REQUEST quando já passou do cloud-front e antes de buscar no bucker S3
  3 - no ORIGIN-RESPONSE após ser retornado os dado do bucket S3
  4 - no VIEWER-RESPONSE após passar pelo cloud-front e antes de ser retornado para o user

![i-app-stack-3](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-3.png?raw=true)

- Processo de deploy por feature para cada branch, onde que o nome da branch gera o hash do qual fica como nome da pasta no S3 e tambem no prefixo de endereço do host:

![i-app-stack-4](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-4.png?raw=true)

- A manipulaçao para buscar diferentes versoes de site em pastas no bucker é feito por uma LAMBDA:

![i-app-stack-5](https://github.com/matheusicaro/private-helpers/blob/master/code/data/i-app-stack-5.png?raw=true)

