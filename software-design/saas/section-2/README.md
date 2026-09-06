# SECTION-2

Software as a Service (SaaS)

- [SECTION-2](#section-2)
- [Traffic Sharding (Fragment traffic/requests)](#traffic-sharding-fragment-trafficrequests)
- [Database Sharding (Fragment traffic/requests)](#database-sharding-fragment-trafficrequests)
- [Consistent Hashing](#consistent-hashing)
- [SYNC and ASYNC in SaaS](#sync-and-async-in-saas)
- [CACHE in SaaS](#cache-in-saas)

<br>
<br>

# Traffic Sharding (Fragment traffic/requests)

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469621#notes

This is fragment your traffic or request to have a specific SHARED (containers, data-center, etc).

This strategy is used when I want to have a PREMIUM users with more CPU/MEMORY, ETC then other common users.

```mermaid
flowchart LR
    U1["User (authenticated)"] --> Auth
    subgraph Auth["Authentication → Shared layer"]
        LB0[Load Balancer] --> R[Router]
        R --> Map[(Mapping)]
    end
    Auth -->|"1: get context w/ SHARD info"| Iso
    Auth -->|"2: route to specific shard/gateway"| Iso
    subgraph Iso["Isolated layer"]
        subgraph DC1["Datacenter / Hardware 1"]
            LB1[Load Balancer DC1] --> App1[APP-A DC1]
        end
        subgraph DC2["Datacenter / Hardware 2"]
            LB2[Load Balancer DC2] --> App2[APP-A DC2]
        end
        subgraph DC3["Datacenter / Hardware 3"]
            LB3[Load Balancer DC3] --> App3[APP-A DC3]
        end
    end
```

> LOAD BALANCING: is the method of distributing network traffic equally across a pool of resources that support an application, it already have Algorithm of put the request in a free instances

| Traffic Sharding — pros | Traffic Sharding — cons |
| --- | --- |
| Escalabilidade e distribuição de carga | Complexidade operacional e de monitoramento (requer control planes) |
| Isolamento de recursos em menores grupos | Custo pode ser alto e utilização de recurso pode não ser eficiente |
| Cada grupo de usuários pode ser escalado por uso/customização (ex. Premium) | Camada de rede maior — mais hops, ainda que o tempo de resposta possa compensar |
| Redução de área de impacto durante problemas | |
| Reduz e controla tenants e/ou grupo de tenants | |

<br>
<br>
<br>
<br>

# Database Sharding (Fragment traffic/requests)

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469625#notes

Split data in many databases to have a better performance and scalability, distributing resources on servers.

Scenario: imagine have a subscription of products in my platform. Then, I can have a database sharding where my clients go to the databases that I want according to my business rules.

1. user SUBSCRIBE A PRODUCT
2. user has a context with user information > after the authorization
   1. like user-id
3. then, I have an application that decode the user-ID -> db mapping
4. the mapping is basically a map of users group to a specific database
   1. like, users 0-20 to DB 1
   2. like, users 21-40 to DB 1

```mermaid
flowchart LR
    U[User] --> App["APP\nCadastrarProduto()"]
    App -->|"1: key → number 0-100"| Algo[Algoritmo]
    App -->|2| Map[Mapping table]
    App -->|3| DBs
    Algo --> Map
    subgraph Mapping table
        M1["0-20 → db_1.whatever.com"]
        M2["20-80 → db_2.whatever.com"]
        M3["80-100 → db_3.whatever.com"]
    end
    subgraph DBs
        D1[(db_1: 0-20)]
        D2[(db_2: 20-80)]
        D3[(db_3: 80-100)]
    end
```

| Database Sharding — pros | Database Sharding — cons |
| --- | --- |
| Microgerenciamento de usuários via tabela de mapping | Cuidado: o algoritmo pode ter um limite que não permite crescimento de novos usuários |
| Algoritmo "dividir para conquistar" — melhora performance | Alterar o algoritmo pode ser complexo e exigir remanejamento de todos os dados |
| Melhora o Blast Radius, distribui entre databases | Você precisa manter esse mapping manualmente (consistent hashing atualiza isso automaticamente ao add/remover targets) |
| Possibilita escalabilidade horizontal para DBs | |

<br>
<br>
<br>
<br>

# Consistent Hashing

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469627#notes

<br>
<br>
<br>
<br>

# SYNC and ASYNC in SaaS

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469631#notes

> ASYNC in NOT SaaS architecture:

<details><summary> here </summary>

1. user buy something
2. the broker put the request in a topic
3. the payment service process it and get back to the user if fails or success
4. the other services do the same and process everything async and make communication with the users by notification

```mermaid
flowchart LR
    C["Front-end E-Commerce\nRealizarCompra()"] -->|10ms| B[Broker, event Bus, Tópico, Fila]
    B -->|"2000ms"| Pagamento
    B -->|"300ms"| Estoque
    B -->|"200ms"| Entrega
    Pagamento -.->|notification| C
```

</details>

<br>

> ASYNC in SaaS

1. the users which already have the context information etc
2. with that information, we add a different topics per user like PREMIUM or NOT
3. then, the `premium topic` goes to a `premium pool` with best resources
   1. in this pool i can scale more to process it faster
4. in the another pool for `free` users, I can process it lazy

```mermaid
flowchart LR
    UA["User Tenant A (Premium)"] --> FA["Front Tenant A"]
    UB["User Tenant B"] --> FB["Front Tenant B"]
    UC["User Tenant C"] --> FC["Front Tenant C"]
    FA --> T1["Tópico Tier 1 (premium)"]
    FB & FC --> T2["Tópico Tier 2"]
    T1 --> W1[Worker/Back Pool] & W2[Worker/Back Pool]
    T2 --> W3[Worker/Back Pool] & W4[Worker/Back Pool]
    W1 & W2 & W3 & W4 --> DBac[(DB Tenant A,C,E)]
    W1 & W2 & W3 & W4 --> DBbd[(DB Tenant B,D,F)]
```

<br>
<br>
<br>
<br>

# CACHE in SaaS

class: https://www.udemy.com/course/fundamentos-de-arquitetura-saas/learn/lecture/45469633#notes

How to keep the consistency of caching with a SaaS architecture

> CACHE in NOT SaaS architecture:

<details><summary> here </summary>

1. user request the API gateway
2. gateway request a internal pool services
3. services go to the database
   1. database check if data is in the cache
   2. `in cache`: return the data from cache
   3. `not in cache`: run db operation and save i cache
4. return the data to the user

```mermaid
flowchart LR
    TA["Front Tenant A (Silo)"] & TB["Front Tenant B"] & TC["Front Tenant C"] --> GW["API Gateway (10ms)"]
    GW -->|100ms| Pool["Backend Pool (x5)"]
    Pool -->|1000ms| DB["DB Tenant A / B (Bridge)"]
```

Response times without tenant-aware caching: every request pays the full 1410ms round trip (Front → Gateway → Backend Pool → DB), regardless of tenant.

</details>

> CACHE in SaaS architecture:

1. user request the API gateway
2. gateway check the external config and see which users has cache or not
   1. users premium that need a premium service
3. services go to the database
   1. database check if data is in the cache
   2. `in cache`: return the data from cache
   3. `not in cache`: run db operation and save i cache
4. return the data to the user

```mermaid
flowchart LR
    TA["Front Tenant A"] & TB["Front Tenant B"] & TC["Front Tenant C"] --> GW["API Gateway (10ms)"]
    GW -->|100ms| Pool["Backend Pool (x5)"]
    GW -->|100ms| Cfg["External Config"]
    Pool -->|1000ms| DB["DB Tenant A / B"]
    Pool -->|100ms| Cache["Cache: DB Tenant B"]
```

With tenant-aware external config + cache, a request for a cached tenant (e.g. Tenant B) drops from 1510ms to 610ms — the gateway checks config to see which tenants have caching enabled, and routes accordingly.

