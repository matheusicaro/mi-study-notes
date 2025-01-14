SUMMARY

- [Horizontal x Vertical SCALE:](#horizontal-x-vertical-scale)
- [When receives lots requests, how to avoid it fall?](#when-receives-lots-requests-how-to-avoid-it-fall)
- [How to build a onboarding Tenants Architecture?](#how-to-build-a-onboarding-tenants-architecture)
- [How to monitory/trace your Tenants?](#how-to-monitorytrace-your-tenants)
- [How to test/prevent a huge access VOLUME coming up?](#how-to-testprevent-a-huge-access-volume-coming-up)
- [Function (vs) No-functional requirements?](#function-vs-no-functional-requirements)

<br>
<br>

## Horizontal x Vertical SCALE:

- `Horizontal SCALE`: when you have increase the number of instances.
- `Vertical SCALE`: when you increase the resources for the UNIQUE instance (memory, cpu, etc.) you have.
- `Diagonal SCALE`: when is a hybrid of horizontal and vertical scaling.

<details><summary>Picture: </summary>

![alt text](image-3.png)

</details>

<br>
<br>

## When receives lots requests, how to avoid it fall?

1. ADD LIMIT P/ REQUESTS:

   1. we can add a limit of requests per client-token
   2. have a system that know how much token it can provide, and then,
   3. give some tokens and scale if reach 70% of the tokens

2. CIRCUIT BREAKER: avoid timeouts
   1. add a layer between the CLIENT and SERVICE
   2. this layer might be a service as well, that will:
   3. check each request status and timing
   4. if the main service reach a limit of requests, the circuit break can turn the system off, then:
   5. avoid time outs then return quick user messages

![alt text](image.png)

<Br>
<br>

## How to build a onboarding Tenants Architecture?

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469653#notes

![alt text](image-1.png)

<br>
<br>

## How to monitory/trace your Tenants?

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469663#notes

![alt text](image-2.png)

## How to test/prevent a huge access VOLUME coming up?

1. `Load testing`: Load testing is the process of measuring the performance of the system under the anticipated load. Basically you volume right now is supporting 100 requests per second, lest test on double it with 200? and 300? and 400?_** this is load testing, how you services/application supports with artificial production traffic and DNS configuration changes for load testing**_

## Function (vs) No-functional requirements?

- `Functional Requirements`: Feature that the system will offer to the user.
  - *Questions to make to understand the requirements*:
    - What are the features that we need to design for this system?
    - What are the edge cases we need to consider, if any, in our design?

- `Non-Functional Requirements`: The quality constraints that the system must satisfy according to the project grows or in contract, for example: Portability, Security, Maintainability, Reliability, Scalability, Performance, Reusability, Flexibility.
  - *Questions to make to understand the requirements*:
    - Our system should record metrics and analytics?
    - Service heath and performance monitoring?

Examples: 
<details>
<summary>1. Online Banking System</summary>

1. Functional Requirements:
   - Users should be able to log in with their username and password.
   - Users should be able to check their account balance.
   - Users should receive notifications after making a transaction.

2. Non-functional Requirements:
   - The system should respond to user actions in less than 2 seconds.
   - All transactions must be encrypted and comply with industry security standards.
   - The system should be able to handle 100 million users with minimal downtime.
</details>

<details>
<summary>2. Food Delivery App</summary>

1. Functional Requirements
   - Users can browse the menu and place an order.
   - Users can make payments and track their orders in real time.

2. Non-functional Requirements:
   - The app should load the restaurant menu in under 1 second.
   - The system should support up to 50,000 concurrent orders during peak hours.
   - The app should be easy to use for first-time users, with an intuitive interface.
</details>

Differences