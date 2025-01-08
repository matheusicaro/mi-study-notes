SUMMARY

- [Horizontal x Vertical SCALE:](#horizontal-x-vertical-scale)
- [When receives lots requests, how to avoid it fall?](#when-receives-lots-requests-how-to-avoid-it-fall)
- [How to build a onboarding Tenants Architecture?](#how-to-build-a-onboarding-tenants-architecture)
- [How to monitory/trace your Tenants?](#how-to-monitorytrace-your-tenants)

<br>
<br>

## Horizontal x Vertical SCALE:

- `Horizontal SCALE`: when you have increase the number of instances
- `Vertical SCALE`: when you increase the resources of a UNIQUE instance (memory, cpu, etc.)

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

