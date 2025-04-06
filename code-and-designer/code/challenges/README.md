
## Caclulate MEAN, MEDIAN and MODE


- MEAN: a media entre os inputs

```typescript
/*
   1) SUM all the values => TOTAL_SUM
   2) GET THE TOTAL OF ALL THE ELEMENT SOMADOS
   3) return TOTAL_SUM / ELEMENTS.lenght
*/
const MEAN = (numbers:number[]) => {
     // 1)
    let TOTAL_SUM = 0
     // 2)
    numbers.forEach(num => { TOTAL_SUM += num});

    const TOTAL_ELEMENTS = numbers.length
    // 3)
    return TOTAL_SUM / TOTAL_ELEMENTS;
}

// BEST for (2)
    const TOTAL_SUM = numbers.reduce(
        (acumulated, element) => acumulated + element, INITIAL_VALUE_HERE = 0
    );

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
    const IS_PAR = sorted_list.length % 2 === 0

    // *** get INTEGER => Math.floor()
    const middle = Math.floor(sorted_list.length / 2);
    
    // 3.1)
    if (IS_PAR) {
        const middle_LEFT_SIDE = sorted_list[middle - 1]
        const middle_RIGHT_SIDE = sorted_list[middle + 2]
        
        return (middle_LEFT_SIDE + middle_RIGHT_SIDE) / 2;
    }

    // 3.2)
    return sorted_list[middle]
}
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