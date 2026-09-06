# Folder Structure Patterns — Quick Review

## Front-end folder glossary

| Folder | Purpose |
| --- | --- |
| `components` | React components — main UI building blocks |
| `design-system` | Fundamental UI elements/patterns from the design system |
| `icons` | Inline SVG icons |
| `styles` | Global CSS / CSS-in-JS |
| `hooks` | Custom React hooks (shared logic) |
| `hocs` | Higher-order components |
| `contexts` / `providers` | React Contexts and Providers |
| `states` / `store` | Global state (Zustand, Valtio, Jotai...) |
| `reducers`, `actions`, `selectors` | Redux-specific logic |
| `routes` / `router` | Route definitions |
| `pages` | Entry-point components per page |
| `types` | TS types, enums, interfaces |
| `configs` | App config (env vars, etc.) |
| `constants` | Unchanged constant values |
| `api` / `graphql` | Server-communication logic |
| `utils` | Universal logic unrelated to business/tech (string/math helpers) |
| `lib` | Tech-specific utilities (DOM, localStorage, IndexedDB) |
| `plugins` | 3rd-party plugins (i18n, Sentry) |
| `services` | Main business/application logic |
| `helpers` | Business-specific utilities |

## By Features+Types vs By Features

| | By Features & File Types | By Features (feature folders) |
| --- | --- | --- |
| Project size | Medium–Large | Large & complex |
| Shape | Top-level folders are file *types* (`components/`, `hooks/`...), each has a subfolder per feature | Top-level folders are *features* (`payment/`, `auth/`...), each has its own `components/`, `hooks/`, `services/` |
| Pro | Simple, straightforward | Feature logic fully co-located — delete a feature, delete a folder |
| Con | Logic for one feature still spread across many top-level folders | Requires knowing the business domain well to draw the right boundaries |

```
# By Features & File Types            # By Features
src/                                   src/
├── components/                       ├── features/
│   ├── payment/PaymentForm.tsx       │   ├── payment/
│   └── employees/...                 │   │   ├── components/PaymentForm.tsx
├── hooks/                            │   │   ├── hooks/usePayment.ts
│   ├── payment/usePayment.ts         │   │   └── services/
│   └── employees/...                 │   └── auth/...
└── services/                         └── ...
```

**Rule of thumb:** small/medium app → by type. Large app with clear domains → by feature.
