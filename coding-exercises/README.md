Summary

- [Call center implement using SOLID + POO](#call-center-implement-using-solid--poo)
- [Caclulate MEAN, MEDIAN and MODE](#caclulate-mean-median-and-mode)

## Call center implement using SOLID + POO

> **Recognize this pattern by:** "route request to the first of N handlers that can accept it" → **Chain of Responsibility**. Cue words: dispatch, first available, fallback to next.

Develop a call center application capable of routing incoming calls to individual agents.
The solution should be object-oriented and ready for change.
Design the classes and data structures needed to route an incoming call to the first available agent.
The solution should include a dispatch method that assigns the call to an agent.
Agents can have different statuses such as Available, Busy, or Offline.

> > > > RESOLVING IT

Requirements: _collect the requirements_

1. Call center routing based on agent availability.
2. The solution must be object-oriented and ready for change.
3. A dispatch method to route the incoming call.
4. Agent statuses like Available, Busy, and Offline.

Solution: _define the solution_

1. Call: The call that needs to be routed.
2. Agent: The agent who handles the call with a specific status.
3. Handler (Chain of Responsibility): A chain that processes the call based on the agent's status.

<details><summary>STEP (1): defining the base </summary>

```typescript
// 1. Agent Status Enum
enum AgentStatus {
  Available = "Available",
  Busy = "Busy",
  Offline = "Offline",
}

// 2. Agent Interface - Adheres to SOLID principles (Interface Segregation and Liskov Substitution)
interface IAgent {
  id: number;
  name: string;
  status: AgentStatus;
  handleCall(call: Call): boolean; // Returns true if call was handled
}

// 3. Call Class - Encapsulates call data
class Call {
  constructor(public callerId: string, public issue: string) {}
}

// 4. Base Handler - Chain of Responsibility
abstract class CallHandler {
  protected nextHandler: CallHandler | null = null;

  setNext(handler: CallHandler): CallHandler {
    this.nextHandler = handler;
    return handler;
  }

  abstract handle(call: Call): void;
}
```

</details>

<details><summary>STEP (2): Implementing from the base </summary>

```typescript
// 5. Agent Call Handlers
class AvailableAgentHandler extends CallHandler {
  private agent: IAgent;

  constructor(agent: IAgent) {
    super();
    this.agent = agent;
  }

  handle(call: Call): void {
    if (this.agent.status === AgentStatus.Available) {
      console.log(`Call routed to ${this.agent.name} (Available)`);
    } else if (this.nextHandler) {
      this.nextHandler.handle(call);
    } else {
      console.log("No available agents. Call could not be routed.");
    }
  }
}

class BusyAgentHandler extends CallHandler {
  private agent: IAgent;

  constructor(agent: IAgent) {
    super();
    this.agent = agent;
  }

  handle(call: Call): void {
    if (this.agent.status === AgentStatus.Busy) {
      console.log(`Call routed to ${this.agent.name} (Busy)`);
    } else if (this.nextHandler) {
      this.nextHandler.handle(call);
    } else {
      console.log("No agents available. Call could not be routed.");
    }
  }
}

class OfflineAgentHandler extends CallHandler {
  private agent: IAgent;

  constructor(agent: IAgent) {
    super();
    this.agent = agent;
  }

  handle(call: Call): void {
    if (this.agent.status === AgentStatus.Offline) {
      console.log(`Agent ${this.agent.name} is offline. Trying next agent.`);
    } else if (this.nextHandler) {
      this.nextHandler.handle(call);
    } else {
      console.log("No agents available. Call could not be routed.");
    }
  }
}
```

</details>
<details><summary>STEP (4): Implementing Agent and CallCenter </summary>

```typescript
// 6. Agent Class Implementing IAgent Interface
class Agent implements IAgent {
  constructor(public id: number, public name: string, public status: AgentStatus) {}

  handleCall(call: Call): boolean {
    console.log(`${this.name} handling the call: ${call.issue}`);
    return true;
  }
}

// 7. Call Center - Main Dispatch System
class CallCenter {
  private handlers: CallHandler[] = [];

  constructor(private agents: IAgent[]) {
    this.createHandlerChain();
  }

  private createHandlerChain() {
    this.agents.forEach((agent) => {
      let handler: CallHandler;

      switch (agent.status) {
        case AgentStatus.Available:
          handler = new AvailableAgentHandler(agent);
          break;
        case AgentStatus.Busy:
          handler = new BusyAgentHandler(agent);
          break;
        case AgentStatus.Offline:
          handler = new OfflineAgentHandler(agent);
          break;
        default:
          handler = new AvailableAgentHandler(agent); // Default case if needed
      }

      if (this.handlers.length > 0) {
        this.handlers[this.handlers.length - 1].setNext(handler);
      }

      this.handlers.push(handler);
    });
  }

  // Dispatch method to assign the call to the first available agent
  dispatchCall(call: Call) {
    if (this.handlers.length > 0) {
      this.handlers[0].handle(call);
    } else {
      console.log("No agents in the system.");
    }
  }
}
```

</details>

<details><summary>STEP (5): Using it</summary>

```typescript
// 8. Example Usage

// Creating Agents
const agent1 = new Agent(1, "Alice", AgentStatus.Available);
const agent2 = new Agent(2, "Bob", AgentStatus.Busy);
const agent3 = new Agent(3, "Charlie", AgentStatus.Offline);

// Creating Call Center and Adding Agents
const callCenter = new CallCenter([agent1, agent2, agent3]);

// Dispatching a Call
const incomingCall = new Call("12345", "Technical Issue");
callCenter.dispatchCall(incomingCall);
```

</details>

## Caclulate MEAN, MEDIAN and MODE

> **Recognize this pattern by:** basic array-stats warm-up question. Cue words: average, middle value, most frequent.

- MEAN: a media entre os inputs

```typescript
/*
   1) SUM all the values => TOTAL_SUM
   2) GET THE TOTAL OF ALL THE ELEMENT SOMADOS
   3) return TOTAL_SUM / ELEMENTS.lenght
*/
const MEAN = (numbers: number[]) => {
  // 1)
  let TOTAL_SUM = 0;
  // 2)
  numbers.forEach((num) => {
    TOTAL_SUM += num;
  });

  const TOTAL_ELEMENTS = numbers.length;
  // 3)
  return TOTAL_SUM / TOTAL_ELEMENTS;
};

// BEST for (2)
const TOTAL_SUM = numbers.reduce((acumulated, element) => acumulated + element, (INITIAL_VALUE_HERE = 0));
```

- MEDIAN: The median is the middle value of input list

```typescript
/*
    1) sort the list from ASC or DESC
    2) get the input.LENGTH
    3) check:
      3.1) IF is PAR   > get the both middle and calculate the mean of it
      3.2) IF is IMPAR > the middle is the MEDIAN
*/
const MEDIAN = (numbers) => {
  // 1)
  const sorted_list = [...numbers].sort((a, b) => a - b);
  // 2)
  const IS_PAR = sorted_list.length % 2 === 0;

  // *** get INTEGER => Math.floor()
  const middle = Math.floor(sorted_list.length / 2);

  // 3.1)
  if (IS_PAR) {
    const middle_LEFT_SIDE = sorted_list[middle - 1];
    const middle_RIGHT_SIDE = sorted_list[middle + 2];

    return (middle_LEFT_SIDE + middle_RIGHT_SIDE) / 2;
  }

  // 3.2)
  return sorted_list[middle];
};
```

- MODE: The mode is the most common number that appears in the list

```typescript
/*
    1) create a MAP to count it
    2) interate and count the times of each duplicated number
    3) find the max frequency
    4) sort the VALUES
    5) get the higherst ones
*/
function MODE(numbers: number[]): number[] {

  // 1)
  const frequencyMap: { [numberAsKey: number]: number } = {};

  // 2)
  numbers.forEach(num => {
    frequencyMap[num] = (frequencyMap[num] || 0) + 1;
  });

  const VALUES = []

  // 3) Find the maximum frequency
  const maxFrequency = Object.entry(frequencyMap).forEach((entry) => {
    const numberKey = entry[0]
    const frequency = entry[1]

    VALUES.push({ numberAsKey, frequency })
  });

  // 4) DESC
  VALUES.sort((a, b) => b.frequency a.frequency);

  //  5) get the higherst ones
  return parseInt(VALUES[0].numberAsKey);
}
```

