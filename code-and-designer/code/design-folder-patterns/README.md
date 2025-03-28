Summary:

- [Front-end](#front-end)
  - [By Features and file types](#by-features-and-file-types)
  - [By Features](#by-features)
- [Back-end](#back-end)
- [Both end](#both-end)
  - [VIPER](#viper)
  - [MVC](#mvc)
  - [MVP](#mvp)
  - [MVVM](#mvvm)
  - [MVI](#mvi)

<br>
<br>

# Front-end

Folders definition:

UI COMPONENTS: <br>
── `components`....: _React components - the main UI building blocks._<br>
── `design-system`....: _Fundamental UI elements and patterns based on the design system._<br>
── `icons`....: _SVG icons that are meant to be used inline._<br>

STYLES: <br>
── `styles`: Contains (global) CSS or CSS-in-JS styles.

REACT SPECIFIC: <br>
── `hooks`....: _Custom React hooks for shared logic._<br>
── `hocs`....: _React Higher-order Components._<br>
── `contexts/providers`....: _Contains React Contexts and Providers._<br>

STATE MANAGEMENT: <br>
── `states/store`....: _Global state management logic (Zustand, Valtio, Jotai, etc.)_<br>
── `reducers, store, actions, selectors`....: _Redux-specific logic_<br>

ROUTING: <br>
── `routes/router`....: _Defining routes (if you're using React Router or the like)._<br>
── `pages`....: _Defining entry-point components for pages._<br>

TYPESCRIPT AND CONFIGURATIONS: <br>
── `types`....: _For general TypeScript types, enums and interfaces._<br>
── `configs`....: _Configs for the application (e.g. environment variables)_<br>
── `constants`....: _Constant unchanged values (e.g. export const MINUTES_PER_HOUR = 60)_<br>

SERVER COMMUNICATION: <br>
── `api`....: _For logic that communicates with the server(s)._<br>
── `graphql`....: _GraphQL-specific code._<br>

UTILITIES & EXTERNAL INTEGRATIONS <br>
── `utils`....: _Utilities for universal logic that is not related to business logic or any technologies, e.g. string manipulations, mathematic calculations, etc._<br>
── `lib`....: _Utilities that are related to certain technologies, e.g. DOM manipulations, HTML-related logic, localStorage, IndexedDB, etc._<br>
── `plugins`....: _Third-party plugins (e.g. i18n, Sentry, etc.)_<br>

BUSINESS LOGIC <br>
── `services`....: _Encapsulates main business & application logic._<br>
── `helpers`....: _Provides business-specific utilities._<br>

<br>
<br>

## By Features and file types

- Project Size: Medium to Large
- Advantages:
  - Simple & straightforward
  - Stuff are grouped by features
- Disadvantages:
  - Logic related to a feature is still spread across multiple folder types

Now let's come back to the problem statement where the payment module needs to be modified or removed. With this structure, it's a lot easier to do that now.

<details><summary>Structure: </summary>

```
└── src/
    ├── assets/
    ├── api/
    ├── configs/
    ├── components/
    │   ├── __tests__
    │   ├── auth/
    │   │   └── SignUpForm.tsx
    │   ├── payment/
    │   │   └── PaymentForm.tsx
    │   ├── common/
    │   │   └── Button.tsx
    │   └── employees/
    │       ├── EmployeeList.tsx
    │       └── EmployeeSummary.tsx
    ├── hooks/
    │   ├── __tests__
    │   ├── auth/
    │   │   └── useAuth.ts
    │   ├── payment/
    │   │   └── usePayment.ts
    │   └── employees/
    │       ├── useEmployees.ts
    │       └── useUpdateEmployee.ts
    ├── lib/
    ├── services/
    ├── states/
    └── utils/
```

</details>

## By Features

- Project Size: Large and Complex
- Advantages:
  - Stuff are clearly grouped by features/modules
  - Features/Modules are clear representations of objects in the real world
- Disadvantages:
  - You'll have to be well-aware of the business logic to make the right grouping decisions

With this, if you are to remove or modify the payment logic, you'll know right away where to start.

<details><summary>Structure: </summary>

```
└── src/
    ├── assets/
    ├── features/
    │   ├── core/
    │   │   ├── __tests__
    │   │   ├── components/
    │   │   ├── design-system/
    │   │   │   └── Button.tsx
    │   │   ├── hooks/
    │   │   ├── lib/
    │   │   └── utils/
    │   ├── payment/
    │   │   ├── __tests__
    │   │   ├── components/
    │   │   │   └── PaymentForm.tsx
    │   │   ├── hooks/
    │   │   │   └── usePayment.ts
    │   │   ├── lib/
    │   │   ├── services/
    │   │   ├── states/
    │   │   └── utils/
    │   ├── auth/
    │   │   ├── __tests__
    │   │   ├── components/
    │   │   │   └── SignUpForm.tsx
    │   │   ├── hooks/
    │   │   │   └── useAuth.ts
    │   │   ├── lib/
    │   │   ├── services/
    │   │   ├── states/
    │   │   └── utils/
    │   └── employees/
    │       ├── __tests__
    │       ├── components/
    │       │   ├── EmployeeList.tsx
    │       │   └── EmployeeSummary.tsx
    │       ├── hooks/
    │       │   ├── useEmployees.ts
    │       │   └── useUpdateEmployee.ts
    │       ├── services/
    │       ├── states/
    │       └── utils/
    └── ...
```

</details>

<br>
<br>

# Back-end

<br>
<br>

# Both end

## VIPER

<br>
<br>

## MVC

<br>
<br>

## MVP

<br>
<br>

## MVVM

<br>
<br>

## MVI

