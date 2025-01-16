SUMMARY

- [Interview Questions](#interview-questions)
- [System Design](#system-design)
- [System Architecture](#system-architecture)
  - [SaaS: Software as a service](#saas-software-as-a-service)
  - [Three-Tier Architecture](#three-tier-architecture)
  - [Container Architecture or Architecture by containers](#container-architecture-or-architecture-by-containers)
  - [Serverless Architecture](#serverless-architecture)
  - [Architecture based in events (SYNC x ASYNC)](#architecture-based-in-events-sync-x-async)
    - [SNS (x) SQS (x) KAFKA](#sns-x-sqs-x-kafka)
- [System Patterns](#system-patterns)
  - [API Gateway Pattern](#api-gateway-pattern)
  - [Load Balancing Pattern](#load-balancing-pattern)
  - [Caching and `CDN (data more close to the user by location)`](#caching-and-cdn-data-more-close-to-the-user-by-location)
  - [Stateless (x) Stateful](#stateless-x-stateful)
  - [Monolith (x) Modular Monolith (x) Microservices](#monolith-x-modular-monolith-x-microservices)

<br>
<br>

# Interview Questions

link here: [Interview Questions](./questions/README.md)

- [Horizontal x Vertical SCALE:](#horizontal-x-vertical-scale)
- [When receives lots requests, how to avoid it fall?](#when-receives-lots-requests-how-to-avoid-it-fall)
- [How to build a onboarding Tenants Architecture?](#how-to-build-a-onboarding-tenants-architecture)
- [How to monitory/trace your Tenants?](#how-to-monitorytrace-your-tenants)
- [How to test/prevent a huge access VOLUME coming up?](#how-to-testprevent-a-huge-access-volume-coming-up)
- [Function (x) No-functional requirements?](#function-vs-no-functional-requirements)
  <br>
  <br>

# System Design

> TODO: I need to check the boxes when the systems are done!

- Famous systems:

  - [ ] Consuming huge data in a endpoint (streaming?)
  - [ ] [Uber](https://www.geeksforgeeks.org/system-design-of-uber-app-uber-system-architecture)
  - [ ] [Dropbox](https://www.geeksforgeeks.org/design-dropbox-a-system-design-interview-question)
  - [ ] [Github](https://www.geeksforgeeks.org/how-to-design-a-rate-limiter-api-learn-system-design/)
  - [ ] [Push notifications](https://www.geeksforgeeks.org/design-notification-services-system-design)
  - [ ] [Autocomplete search](https://www.geeksforgeeks.org/googles-search-autocomplete-high-level-designhld/)
  - [ ] [Web crawler](https://www.geeksforgeeks.org/design-web-crawler-system-design/)
  - [ ] [URL Shorter (bit.ly, TinyURL, etc)](https://www.geeksforgeeks.org/system-design-url-shortening-service/)

- Video Streaming Service

  - [ ] [Youtube](https://www.geeksforgeeks.org/system-design-of-youtube-a-complete-architecture/)
  - [ ] [Netflix](https://www.geeksforgeeks.org/system-design-netflix-a-complete-architecture/)

- Social Network

  - [ ] [Reddit](https://www.geeksforgeeks.org/design-reddit-system-design)
  - [ ] [Twitter](https://www.geeksforgeeks.org/design-twitter-a-system-design-interview-question)
  - [ ] [Instagram](https://www.geeksforgeeks.org/design-instagram-a-system-design-interview-question)
  - [ ] [Facebook](https://www.geeksforgeeks.org/desiging-facebook-messenger-system-design-interview)

- Message/Chat Services

  - [ ] [Facebook messenger](https://www.geeksforgeeks.org/desiging-facebook-messenger-system-design-interview)
  - [ ] [WhatsApp](https://www.geeksforgeeks.org/designing-whatsapp-messenger-system-design)

<br>
<br>

# System Architecture

- `Monolito modular` is some services in the same machine sharing resources, like same database?

![alt text](./pictures/image.png)
<br>
<br>

## SaaS: Software as a service

- [Link here](./saas-software-as-service/README.md)

  <br>
  <br>

## Three-Tier Architecture

This is the architecture more basic possible, very common!
This is split in 3 parts:

1. Presentation: mobile, front-end
2. Application layer: services
3. Data layer: database, etc.

<details><summary>picture: </summary>

![alt text](./pictures/image-1.png)

</details>
<br>
<br>

## Container Architecture or Architecture by containers

Is running my services by container. Each container is a copy of other, then, you can add a loadbalancer and give requests to each other... The containers are the same but run in different ports and locale, so if you one of them fall, you have the another one.

1. You might need one orchestrator to work with Redundancies or Circuit breaker
<details><summary>picture: </summary>

![alt text](./pictures/image-7.png)
![alt text](./pictures/image-8.png)
![alt text](./pictures/image-9.png)

</details>
<br>
<br>

## Serverless Architecture

In Serverless you don't need to manager the servers, because the company/provider is already manager it for you. Then, you can only run your code, logic without need to manager the hardware, etc..

<details><summary>picture: </summary>

![alt text](./pictures/image-10.png)
![alt text](./pictures/image-11.png)
![alt text](./pictures/image-12.png)

</details>
<br>
<br>

## Architecture based in events (SYNC x ASYNC)

- `SYNC`: the request call each resource/service and answer to the user

  - ADVANTAGES: easy logs, monitoring, observability
  - DISADVANTAGES: the advantages of ASYNC

- `ASYNC`: receive the call and return a response with like `{ status: processing }`. Then, process many things behind and return layer
  - ADVANTAGES: quickly client response, scale, easy integration with new systems, easy retries in case when fails
  - DISADVANTAGES: the advantages of SYNC

Another thing of ASYNC x SYNC is that is not easy to handle with RACE CONDITIONS, when you consume a event and save in the database,
but another service needs to have the same data updated before a request. For example:

1. user create account
2. account is being replicated
3. user try to buy something
4. **the product services returns error that user was not found in the replicated-users collection**
   1. how to resolve? events retries?

<details><summary>pictures: </summary>

SYNC:

![alt text](./pictures/image-17.png)

ASYNC:

![alt text](./pictures/image-16.png)

</details>

### SNS (x) SQS (x) KAFKA

- `SNS`: its a topic that receive messages from producers and distributes to the listenings
- `SQS`: its a a queue that receive messages from SNS or services that publish in the queue as a publishers.
  - the queue should only delivery the message 1 only, to avoid duplicate data or process
- `apache KAFKA`: Very large volumes of data, flexible scalability, and basic routing needs.
  - you can audit the message cause kafka as a log that you can audit it in case needs (replicate a bug? gov auditor?)
  - lots power but lots complexity to implement and manager
  - runs in a cluster and you are responsible for this infrastructure
  - indicate to process real time data

<details><summary>SNS x SQS</summary>

reference: https://blog.awsfundamentals.com/aws-sns-vs-sqs-what-are-the-main-differences

SNS is simply forwarding all messages to your subscribed consumers and SQS saves the messages in a queue and waits till they get picked up

![alt text](./pictures/image-21.png)

![alt text](./pictures/image-22.png)
![alt text](./pictures/image-23.png)

SQS can add a Redrive Policy. This policy defines how many times a failed message should be retried before it will be moved to a Dead Letter Queue (DLQ). The DLQ handles failed messages. For example, you could save failed messages in a bucket and inform the developer about them.

SNS doesn't offer retries when the client fails. In case a consumer is not available or the consumer fails to work on the message (e.g. push notification won't come through) the message can't be repeated. This is due to the asynchronous nature of SNS.

![alt text](./pictures/image-24.png)

</details>

<details><summary>Kafka </summary>

![alt text](./pictures/image-25.png)
![alt text](./pictures/image-26.png)
</details>

<br>
<br>

# System Patterns

## API Gateway Pattern

API Gateway is a pattern that manager the follow rules:

1. Credentials
   - _like context, routes, etc._
   - _NOT AUTHORIZATION, the authorization should be another service_
2. Routes
3. Contracts validation
4. Loading balancer
5. Limits of calls: like, hey my services handle only with 100 request per minute, then return 429 too many request.
6. Cache: might have a cache here, and add a TTL for some routes

<details><summary>picture: </summary>

![alt text](./pictures/image-13.png)

</details>
<br>
<br>

## Load Balancing Pattern

Is pattern as well, and basically you manager the request and distributes the request between the services or gateway.

1. (+) can add health check for the services connected
2. (-) have mo latency once you have more layers behind the client's call
3. (+) manager SSL certificates
4. (+) Help on releasing V1, V2, V3 with deploy canary for example

Algorithms that also do Load Balance:

- Round Robin
- Least Connections
- Least Time
- Hash
- IP Hash
- Random with Two Choices

<details><summary>picture: </summary>

![alt text](./pictures/image-14.png)
![alt text](./pictures/image-15.png)

</details>
<br>
<br>

## Caching and `CDN (data more close to the user by location)`

CDN is a service/strategy that for the first request of the user, they load a picture/video and save in the database more close to the user location. Then, for the next users, that data will be requested easier. It is a kind of caching strategies.

<details><summary>example: </summary>

1. user comes from SOUTH
2. CDN receives the request
3. get the object from DATASTORE

![alt text](./pictures/image-18.png)

<br>

4. replicate the data received to the closest database to the user

![alt text](./pictures/image-19.png)

</details>

<details><summary>Advantages x Disadvantages: </summary>

![alt text](./pictures/image-20.png)

</details>
<br>
<br>

## Stateless (x) Stateful

- `Stateless`: we need the server, so I cant lose this server
- `Stateless`: we have some isolated, I can lose my server that I wont lose my database, etc...

<details><summary>picture: </summary>

![alt text](./pictures/image-3.png)

![alt text](./pictures/image-2.png)

</details>
<br>
<br>

## Monolith (x) Modular Monolith (x) Microservices

- `Monolith`: everything is in the same port
- `Modular Monolith`: the modules are in different ports, so I can add in different servers but they can use the same database
- `Microservices`: Everything is independent of each other. DATABASES NEED TO BE INDEPENDENT FOR EACH SERVICES, they can have references like in the database X in table Y we have a field.ID that refer to another service > database.

<details><summary>picture: </summary>

![alt text](./pictures/image-4.png)
![alt text](./pictures/image-5.png)
![alt text](./pictures/image-6.png)

</details>
<br>
<br>

