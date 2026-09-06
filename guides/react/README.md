# Master Guide

## Avoiding Stale Closures + Optimizing Effects

A **stale closure bug** occurs when a function or callback captures variables from an earlier render cycle and continues referencing those outdated values even after the state or props have changed.

---

### Quick Decision Flowchart

Use this decision logic when writing `useEffect`, `useCallback`, or handling async callbacks:

```text
Is your effect updating state based on previous state?
 ├── YES ──> Option 1: Use Functional Update: setCount(prev => prev + 1)
 └── NO
      │
      Does the effect need to RE-RUN completely when the value changes?
       ├── YES ──> Option 2: Add to Dependency Array: [userId]
       └── NO  ──> Option 3: Use useRef: valueRef.current
```

---

### Practical Rules to Remember

1. **Prefer `setCount(c => c + 1)` over `[count]`** for state updates inside callbacks to eliminate effect setup/teardown churn ($O(N)$ performance cost).
2. **Never lie to dependency arrays.** If you add a variable to the dependency array, ensure you intend for the entire effect to re-execute when it changes.
3. **Use `useRef` as a bridge** when bridging React state with non-React event listeners or third-party imperative libraries.

### Strategy Matrix

| Strategy | Best Used For | Effect Teardown Cost | Re-renders Component? |
| :--- | :--- | :--- | :--- |
| **1. Functional Updates** | Updating state based on current/prev state | **Zero** ($O(1)$) | Yes |
| **2. Hook Dependencies** | Synchronizing side-effects to variable changes | **High** ($O(N)$ churn) | Yes |
| **3. `useRef` Reference** | Reading volatile values inside long-lived callbacks | **Zero** ($O(1)$) | No (ref updates don't re-render) |

---

### Option 1: Functional State Updates (Top Choice for State Updates)

#### When to Use
Use whenever the new state depends on the previous state inside timers, intervals, or event listeners.

#### Why It Works
React passes the guaranteed latest state directly into your updater callback. You don't need to capture or reference external state variables within the closure scope.

#### Code Example
```jsx
import React, { useState, useEffect } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      // ✅ Functional update: Always receives the latest 'prevCount'
      setCount(prevCount => prevCount + 1);
    }, 1000);

    // Runs ONLY when component unmounts
    return () => clearInterval(timer);
  }, []); // Empty dependency array is perfectly safe here

  return <h1>Count: {count}</h1>;
}
```

---

### Option 2: Hook Dependency Array (Standard React Synchronization)

#### When to Use
Use when an effect **must restart** whenever a dependency changes (e.g., subscribing to a new WebSocket topic when `topicId` changes or fetching API data when `userId` changes).

#### Why It Works
Tells React to tear down the old effect and spin up a fresh one with updated closure scope variables every time a listed dependency updates.

#### Code Example
```jsx
import React, { useState, useEffect } from 'react';

function UserProfile({ userId }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    let isCancelled = false;

    async function fetchUserData() {
      const response = await fetch(`/api/users/${userId}`);
      const data = await response.json();
      if (!isCancelled) setUser(data);
    }

    fetchUserData();

    // Cleanup runs every time 'userId' changes before setup runs again
    return () => {
      isCancelled = true;
    };
  }, [userId]); // ✅ Effect tears down and re-runs when userId changes

  return <div>{user ? user.name : 'Loading...'}</div>;
}
```

---

### Option 3: `useRef` Container (Escape Hatch for Long-Lived Handlers)

#### When to Use
Use when an event listener, WebSocket handler, or timer needs to **read** the latest state or prop value without tearing down and recreating the subscription.

#### Why It Works
`useRef` returns a mutable object (`.current`) that persists across re-renders. Reading `.current` always reads the live value without requiring the hook to depend on state.

#### Code Example
```jsx
import React, { useState, useEffect, useRef } from 'react';

function KeyLogger() {
  const [message, setMessage] = useState('');
  
  // 1. Create a ref to hold the mutable value
  const messageRef = useRef(message);

  // 2. Keep the ref synchronized with current state on every render
  useEffect(() => {
    messageRef.current = message;
  }, [message]);

  // 3. Attach the event listener ONCE
  useEffect(() => {
    const handleKeyPress = (e) => {
      if (e.key === 'Enter') {
        // ✅ Reads the live value from the ref without stale closures!
        console.log('Submitted message:', messageRef.current);
      }
    };

    window.addEventListener('keydown', handleKeyPress);
    return () => window.removeEventListener('keydown', handleKeyPress);
  }, []); // Empty deps: Listener is attached ONCE, never torn down!

  return (
    <input 
      value={message} 
      onChange={(e) => setMessage(e.target.value)} 
    />
  );
}
```

---

## Hooks vs Alternatives — Code Examples

Each one below answers "why this instead of the obvious alternative," with the smallest snippet that makes the difference visible.

### `useContext` + a Provider (vs. prop-drilling / a full store)

**When to use:** a value needed at many different depths in the tree (current user, theme, locale) — not a value only one child needs (that's just a normal prop or composition).

```jsx
// 1. Create the context once
const CurrentUserContext = createContext(null);

// 2. Provide it high in the tree — no intermediate component needs to know it exists
function App() {
  const currentUser = useCurrentUser();
  return (
    <CurrentUserContext.Provider value={currentUser}>
      <Dashboard /> {/* Dashboard, Sidebar, etc. don't take a currentUser prop at all */}
    </CurrentUserContext.Provider>
  );
}

// 3. Read it directly wherever it's needed, however deep
function UserBadge() {
  const currentUser = useContext(CurrentUserContext);
  return <span>{currentUser.name}</span>;
}
```

**The tradeoff to say out loud:** every consumer of a context re-renders whenever the provided value changes — fine for something that changes rarely (current user, theme), a poor fit for something that changes on every keystroke. That's why a single deeply-nested prop is usually better solved with composition (pass the component itself down), and Context is reserved for genuinely wide, infrequently-changing values.

### `useMemo` (vs. recomputing every render)

**When to use:** the computation is measurably expensive relative to a render — not "just in case."

```jsx
function ProductList({ products, filterText }) {
  // Without useMemo: this expensive filter/sort reruns on EVERY render,
  // including renders caused by unrelated state changes elsewhere in the tree.
  const visibleProducts = useMemo(() => {
    return products
      .filter((p) => p.name.includes(filterText))
      .sort((a, b) => b.popularity - a.popularity);
  }, [products, filterText]); // only recompute when these actually change

  return <ul>{visibleProducts.map((p) => <li key={p.id}>{p.name}</li>)}</ul>;
}
```

**The trap:** wrapping cheap computations (`a + b`, a short `.map`) in `useMemo` "for performance" adds a dependency array to maintain and a memoization check to run every render — that's often *slower* than just recomputing. Reach for it when you've actually noticed the cost (a large sort/filter/derived data structure), not by reflex.

### `useCallback` (vs. redefining the function inline)

**When to use:** the function's *reference identity* is consumed downstream — a `React.memo`-wrapped child, or another hook's dependency array.

```jsx
function Parent() {
  const [count, setCount] = useState(0);

  // Without useCallback: a brand-new function every render means
  // MemoizedChild re-renders every time too, defeating React.memo entirely.
  const handleClick = useCallback(() => {
    setCount((c) => c + 1);
  }, []); // stable reference across renders

  return <MemoizedChild onClick={handleClick} />;
}

const MemoizedChild = React.memo(function MemoizedChild({ onClick }) {
  console.log("MemoizedChild rendered");
  return <button onClick={onClick}>Click</button>;
});
```

**The trap:** `useCallback` on a handler that's only ever passed to a plain `<button onClick={...}>` (not memoized, not a dependency anywhere) buys you nothing — the memoization overhead has no payoff because nothing downstream cares about reference identity.

### A dedicated store — Redux/Zustand/Jotai (vs. stuffing everything into Context)

**When to use:** app-wide state touched by many unrelated features, with complex update logic — not one shallow value.

```jsx
// Zustand example — the shape of the problem Context doesn't solve well
import { create } from "zustand";

const useCartStore = create((set) => ({
  items: [],
  addItem: (item) => set((state) => ({ items: [...state.items, item] })),
  removeItem: (id) =>
    set((state) => ({ items: state.items.filter((i) => i.id !== id) })),
}));

// Any component can subscribe to ONLY the slice it needs —
// a component reading `items.length` doesn't re-render when unrelated store fields change.
function CartBadge() {
  const itemCount = useCartStore((state) => state.items.length);
  return <span>{itemCount}</span>;
}
```

**The tradeoff to say out loud:** Context has no built-in selectors — every consumer re-renders on any change to the provided value, however large. A dedicated store gives you per-field subscriptions, devtools, and middleware (logging, persistence) that Context doesn't provide by design. Reach for a store when the state is genuinely shared and frequently updated across unrelated features; reach for Context when it's a narrow, rarely-changing value.

### Custom hooks (vs. copy-pasting the same effect/state trio)

**When to use:** the same stateful pattern (fetch + loading + error, pagination, debounce) shows up in more than one component.

```jsx
// Extract the repeated pattern once...
function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;
    setLoading(true);
    fetch(url)
      .then((res) => res.json())
      .then((json) => { if (!cancelled) { setData(json); setLoading(false); } })
      .catch((err) => { if (!cancelled) { setError(err); setLoading(false); } });
    return () => { cancelled = true; };
  }, [url]);

  return { data, loading, error };
}

// ...and every component that needs "fetch this URL" just calls the hook.
function UserProfile({ userId }) {
  const { data: user, loading, error } = useFetch(`/api/users/${userId}`);
  if (loading) return <Spinner />;
  if (error) return <ErrorMessage error={error} />;
  return <div>{user.name}</div>;
}
```

**Why this beats the older alternatives:** a Higher-Order Component or render-props version of the same idea adds a wrapper layer to the component tree and makes props harder to trace ("where did this prop come from?"). A custom hook composes like a function call — no tree distortion, and the logic is colocated with the component that uses it instead of split across a wrapping layer.