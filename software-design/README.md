SUMMARY

- [System Design](#system-design)
- [System Architecture](#system-architecture)
  - [Front-end architectures](#front-end-architectures)
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

**Architecture evolution** (a typical path as systems grow):

```mermaid
flowchart LR
    A[Monolith] --> B[Modular Monolith] --> C[Microservices] --> D[Serverless]
```

- Monolith is not "legacy" by definition — it can outperform Serverless for simpler workloads
- Microservices bring high complexity; Modular Monolith ≠ Microservices
- Serverless isn't automatically the best option — it can be the most expensive, and is arguably a form of "macroservices"
- Microservices and Serverless *can* have better uptime, but that's not guaranteed by the architecture alone

<br>
<br>

## Front-end architectures

- [Link here](./frontend-architecture/README.md)

  <br>
  <br>

## SaaS: Software as a service

- [Link here](./saas/README.md)

  <br>
  <br>

## Three-Tier Architecture

This is the architecture more basic possible, very common!
This is split in 3 parts:

1. Presentation: mobile, front-end
2. Application layer: services
3. Data layer: database, etc.

```mermaid
flowchart LR
    U[User laptop] --> P[Presentation Layer\nFrontend\nNGINX / Apache / S3]
    P --> A[Application Layer\nBackend\nApache, WebLogic, Python...]
    A --> D[(Data Layer\nDatastore\nOracle, PostgreSQL, DB2, MySQL...)]
```

| | Notes |
| --- | --- |
| Scaling | Each layer scales independently — only scale the layer under load |
| Operability | Easy to operate, monitor, and debug |
| Security | Each layer can be secured independently |
| Testability | Easy to test and reuse code |
| Fit | Usually monolithic, fits less-complex architectures |

<br>
<br>

## Container Architecture or Architecture by containers

Is running my services by container. Each container is a copy of other, then, you can add a loadbalancer and give requests to each other... The containers are the same but run in different ports and locale, so if you one of them fall, you have the another one.

1. You might need one orchestrator to work with Redundancies or Circuit breaker

```mermaid
flowchart LR
    U[User] --> N[Network Layer]
    N --> S1[Server 1\nAPP1:8912, APP2:8080]
    N --> S2[Server 2\nAPP2:8293, APP3:8092]
    N --> S3[Server 3\nAPP1:8092, APP3:8093]
    S1 --> DB[(Database)]
    S2 --> DB
    S3 --> DB
```

**Container orchestrator** — a control plane manages many data planes (one per node), each running its own set of app containers and reporting CPU/memory usage back up:

```mermaid
flowchart LR
    CP[Control Plane] --> DP1[Data Plane 1\nAPP1, APP2]
    CP --> DP2[Data Plane 2\nAPP2, APP3]
    CP --> DP3[Data Plane 3\nAPP1, APP3]
```

| Vantagens | Desvantagens |
| --- | --- |
| Portabilidade | Complexidade na gestão |
| Eficiência de recurso | Monitoramento e logging |
| Isolamento | Neighbor Noise |
| Escalabilidade | Custos |
| Deployments e Rollbacks simples | Complexidade de rede |
| Organização | Requisitos de Microserviços |

<br>
<br>

## Serverless Architecture

In Serverless you don't need to manager the servers, because the company/provider is already manager it for you. Then, you can only run your code, logic without need to manager the hardware, etc..

```mermaid
flowchart LR
    U[User] -->|"PUT /produto/5"| GW[API Gateway]
    GW --> F1[Criar Usuário]
    GW --> F2[Deletar Usuário]
    GW --> F3[Pagamento]
    GW --> F4[Estorno]
    GW --> F5[Relatorio]
    GW --> F6[Criar Produto]
    GW --> F7[Atualizar Produto]
    F6 --> DB[(Database)]
```

**Decomposition** — a monolith evolves into independent microservices, each broken further into single-purpose functions:

```mermaid
flowchart LR
    M[Monolith] --> S1[Microservice] & S2[Microservice] & S3[Microservice]
    S1 --> F1[Função] & F2[Função]
    S2 --> F3[Função] & F4[Função]
    S3 --> F5[Função] & F6[Função]
```

| Vantagens | Desvantagens |
| --- | --- |
| Eficiência de recurso | Complexidade Arquitetônica |
| Escalabilidade "automática" | Limitações de tempo e quantidade |
| Observabilidade | Observabilidade (double-edged: harder without the right tooling) |
| Abstração de recursos de infra | Customizações não disponíveis |
| Deployment rápido e simples | Tempo de inicialização (cold start) |
| Foco no código/negócio | Vendor Lock-in |
| — | Custo (can spike unpredictably) |

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

**SYNC:**

```mermaid
sequenceDiagram
    participant C as Client (Front-end E-commerce)
    C->>+Pagamento: 1. RealizarCompra() — 2000ms
    Pagamento-->>-C: response
    C->>+Estoque: 2. — 300ms
    Estoque-->>-C: response
    C->>+Entrega: 3. — 200ms
    Entrega-->>-C: response
```

**ASYNC:**

```mermaid
flowchart LR
    C["Front-end E-commerce: RealizarCompra()"] -->|10ms| B[Broker / Event Bus / Tópico / Fila]
    B -->|"1: 2000ms"| Pagamento
    B -->|"2: 300ms"| Estoque
    B -->|"3: 200ms"| Entrega
```

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

| | SQS | SNS |
| --- | --- | --- |
| Push vs Poll | Poll | Push |
| Relationship | Many to one | Many to many |
| Consumer types | Application to Application | Application to Application, Application to Person |
| Persistence | ✅ | ❌ |
| Reliability / Retries | ✅ | ❌ |
| Batching | ✅ | ❌ |

**Consumer types by delivery target:**

| Application to Application | Application to Person |
| --- | --- |
| Lambda, SQS, Kinesis, Endpoint, Eventfork Pipeline | Chatbot, In-App, SMS, Email, Pagerduty |

**Persistence/retry flow:**

```mermaid
flowchart LR
    subgraph SQS_flow["SQS — Persistence: Retention Period"]
        C1[Client] --> SQS1[SQS]
        SQS1 <-->|Poll messages| L1[Lambda]
    end
    subgraph SNS_flow["SNS — No Persistence"]
        C2[Client] --> SNS1[SNS]
        SNS1 --> K1[Kinesis Firehose]
        SNS1 --> L2[Lambda]
        SNS1 --> SQS2[SQS]
    end
```

SQS can add a Redrive Policy. This policy defines how many times a failed message should be retried before it will be moved to a Dead Letter Queue (DLQ). The DLQ handles failed messages. For example, you could save failed messages in a bucket and inform the developer about them.

SNS doesn't offer retries when the client fails. In case a consumer is not available or the consumer fails to work on the message (e.g. push notification won't come through) the message can't be repeated. This is due to the asynchronous nature of SNS.

```mermaid
flowchart LR
    subgraph SQS_dlq["SQS — has retries + DLQ"]
        P1[Producer] -->|sends message| Q1[SQS]
        Q1 -->|consumer polls| Lam1[Lambda]
        Lam1 -->|message fails| X1{Max retries?}
        X1 -->|retry| Q1
        X1 -->|yes| DLQ[DLQ]
    end
    subgraph SNS_nodlq["SNS — no retries, message lost"]
        P2[Producer] --> Sns1[SNS]
        Sns1 --> Lam2[Lambda]
        Lam2 -->|message fails| Gone((message gone))
    end
```

</details>

<details><summary>Kafka </summary>

```mermaid
flowchart LR
    Prod0[Producer 0] --> T0P0[Topic 0, Par 0]
    Prod0 --> T1P1[Topic 1, Par 1]
    Prod1[Producer 1] --> T0P0
    Prod1 --> T0P1[Topic 0, Par 1]
    Prod2[Producer 2] --> T1P1
    Prod2 --> T0P1
    subgraph Broker1[Broker]
        T0P0
        T1P1
    end
    subgraph Broker2[Broker]
        T0P1
        T1P0[Topic 1, Par 0]
    end
    T0P0 --> Cons0[Consumer 0]
    T1P1 --> Cons1[Consumer 1]
    T0P1 --> Cons0
    T0P1 --> Cons2[Consumer 2]
    T1P0 --> Cons1
```

Typical use: Apache Kafka sits between producers (custom apps) and consumers (microservices, monitoring, analytics) on one side, and data sources (Bloomberg, Twitter, NoSQL, Oracle, SFDC, Hadoop, Data Warehouse) on the other.

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

```mermaid
flowchart LR
    U[User laptop] <--> P[Presentation Layer\nNGINX / Apache / S3]
    P <--> GW[API Gateway]
    GW <--> A[Application Layer\nApache, WebLogic, Python...]
    A <--> D[(Data Layer\nOracle, PostgreSQL, DB2, MySQL...)]
```

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

```mermaid
flowchart LR
    U[User laptop] --> P[Presentation Layer] --> GW[API Gateway] --> LB[Load Balancer]
    LB --> T1[Target x.x.x.1]
    LB --> T2[Target x.x.x.2]
    LB --> T3[Target x.x.x.3]
    LB --> T4[Target x.x.x.4]
    T1 & T2 & T3 & T4 --> D[(Data Layer)]
```

- Distributes load; enables horizontal scalability
- Used for redundancy/high availability (different from DNS TTL — DNS maps name→IP, LB distributes traffic across IPs)
- Reduces load on the origin server (if external/managed LB)
- Controls user affinity (stickiness)
- Health-checks targets, monitors them
- Facilitates deployment
- Manages SSL certs at the edge so the application doesn't have to

<br>
<br>

## Caching and `CDN (data more close to the user by location)`

CDN is a service/strategy that for the first request of the user, they load a picture/video and save in the database more close to the user location. Then, for the next users, that data will be requested easier. It is a kind of caching strategies.

```mermaid
flowchart LR
    U["User (Rio Grande do Sul)"] --> LB[Load Balancer]
    LB --> N1[NGINX] & N2[NGINX] & N3[NGINX]
    N1 --> CDN
    subgraph DC1["DC = Ceará"]
        N1
        N2
        N3
        CDN --> O1[Imgs Prod1]
        CDN --> O2[Imgs Prod2]
        CDN --> O3[Imgs Prod3]
    end
    CDN -->|"500ms, copy"| O4
    subgraph DC2["DC = Santa Catarina"]
        O4[Imgs Prod1]
    end
```

1. User from the South requests a product image
2. CDN receives the request, gets the object from the datastore in the nearest DC (Ceará)
3. The object is copied/replicated to the closest datacenter for that user (Santa Catarina) — future requests from that region hit the closer copy

| Caching — pros | Caching — cons |
| --- | --- |
| Reduces response time, more efficient resource use | Costs increase |
| Avoids overhead of repetitive/expensive DB actions | Architecture becomes more complex (more moving pieces) |
| Can improve efficiency of other DB queries | Requires managing TTL and invalidation |
| Increases scalability/availability, reduces single point of failure | Doesn't fit every case — sometimes you need strong consistency |

<br>
<br>

## Stateless (x) Stateful

- `Stateful`: the server holds state (code, local DB, files on disk, backups) — I can't lose this specific server
- `Stateless`: state is externalized (separate DB/storage/backup services) — I can lose the server without losing data

```mermaid
flowchart LR
    subgraph Stateful["Stateful — one server holds everything"]
        S1[Servidor: código, binários, regras\n+ base de dados local\n+ files em disco\n+ backups]
    end
    subgraph Stateless["Stateless — state externalized"]
        S2[Servidor: código, binários, regras] 
        DB2[(Base de dados local)]
        F2[Files em disco]
        B2[Backups e versões antigas]
    end
```

| Stateless | Stateful |
| --- | --- |
| Aplicações escaláveis mais simples (horizontal) | Aplicações escaláveis não são simples (vertical) |
| Maior resiliência e menor complexidade de implementação | Menor resiliência e maior complexidade de implementação |
| Manutenção mais "simples" | Depuração de código mais simples |
| Repetição de dados (across instances) | Dado centralizado |
| Requer outras coisas (IaC, LB, ASG...) | Geralmente só requer um DNS |
| Comunicação via rede | Comunicação via barramento |
| Monitoração (harder — many instances) | Monitoração (simpler — one place) |
| Eficiência de deployment e entregas | Deployments mais difíceis |

<br>
<br>

## Monolith (x) Modular Monolith (x) Microservices

- `Monolith`: everything is in the same port
- `Modular Monolith`: the modules are in different ports, so I can add in different servers but they can use the same database
- `Microservices`: Everything is independent of each other. DATABASES NEED TO BE INDEPENDENT FOR EACH SERVICES, they can have references like in the database X in table Y we have a field.ID that refer to another service > database.

```mermaid
flowchart LR
    subgraph Mono["Monolito — single app, port 8080"]
        M1[NF / Entrega / Pagamento / Estoque]
    end
    subgraph Modular["Monolito Modular — separate ports, shared DB"]
        MM1["8080: Entrega"]
        MM2["8081: NF"]
        MM3["8082: Pagamento"]
        MM4["8083: Estoque"]
    end
```

```mermaid
flowchart TB
    subgraph MonoFlow["Monolito"]
        U1[User] --> Sale1[Sale] --> NF1[NF] & E1[Entrega] & P1[Pagamento] & S1[Estoque]
        NF1 & E1 & P1 & S1 --> DB1[(Shared DB)]
    end
    subgraph MicroFlow["Microsserviços"]
        U2[User] --> Sale2[Sale] --> NF2[NF] & E2[Entrega] & P2[Pagamento] & S2[Estoque]
        NF2 --> DB2A[(DB)]
        E2 --> DB2B[(DB)]
        P2 --> DB2C[(DB)]
        S2 --> DB2D[(DB)]
    end
```

| Aspecto | Monolito | Microsserviços |
| --- | --- | --- |
| Deployment | Simple (single unit) | More complex (many units) |
| Código | Concentrated | Modular, distributed |
| Logs, métricas e tracing | Fácil | Complexo |
| Escalabilidade | Difícil (scale everything together) | Boa (scale services independently) |
| Testabilidade | Fácil | Difícil (more moving parts, contracts) |
| Blast radius / impacto | Alto (one bug can bring down everything) | Baixo (contained to one service) |
| Custo | Menor | Maior |
| Operação | Fácil | Difícil |
| Segurança | Fácil (one surface) | Difícil (many surfaces) |

<br>
<br>
