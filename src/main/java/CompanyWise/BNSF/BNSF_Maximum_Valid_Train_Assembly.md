# BNSF — Maximum Valid Train Assembly

## Problem statement

You are a train dispatcher responsible for assembling trains from a pool of available locomotives and railcars.

Your goal is to create the maximum possible number of valid trains. If multiple assembly plans create the same maximum number of trains, choose the plan with the greatest total railcar length across all trains.

## Terminology

- A **locomotive** is the engine that pulls a train.
- A **railcar** is a cargo car, container car, passenger car, or wagon attached behind the locomotive.

Example:

```text
[Locomotive] -> [Railcar] -> [Railcar] -> [Railcar]
    Engine        Cargo        Cargo        Cargo
```

## Data model

### Locomotive

Each locomotive has a unique ID and a maximum pulling capacity measured in tons.

```java
class Locomotive {
    String id;
    double pullCapacityTons;
}
```

Example:

```text
L1: pull capacity = 100 tons
```

### Railcar

Each railcar has a unique ID, weight in tons, and length in feet.

```java
class Railcar {
    String id;
    double weightTons;
    double lengthFt;
}
```

Example:

```text
R1: weight = 40 tons, length = 5,000 ft
```

## Valid-train requirements

A train is valid only when all the following conditions are satisfied:

1. It contains between **one and three locomotives**.
2. It contains **at least one railcar**.
3. The combined length of its railcars is at most **15,000 ft**.
4. The combined weight of its railcars does not exceed the combined pulling capacity of its locomotives.
5. A locomotive may be assigned to at most one train.
6. A railcar may be assigned to at most one train.
7. Locomotives and railcars may be left unused.

When multiple locomotives are assigned to one train, their pulling capacities are added together.

For example:

```text
Locomotive L1 capacity = 100 tons
Locomotive L2 capacity = 70 tons

Combined capacity = 170 tons
```

## Optimization objectives

Optimize the assembly plan lexicographically, in this exact order:

1. **Primary objective:** maximize the number of valid trains.
2. **Secondary objective:** among plans having the same maximum train count, maximize the total combined railcar length across all trains.

The primary objective always takes priority.

For example:

```text
Plan A: 2 trains, total length = 24,000 ft
Plan B: 3 trains, total length = 18,000 ft
```

Plan B wins because it creates more trains.

If the train counts are equal:

```text
Plan A: 3 trains, total length = 30,000 ft
Plan B: 3 trains, total length = 35,000 ft
```

Plan B wins because it has the greater total railcar length.

## Required method

Implement a method similar to:

```java
AssemblyPlan assembleTrains(
    List<Locomotive> locomotives,
    List<Railcar> railcars
);
```

The returned plan should contain:

- The maximum number of trains.
- The total railcar length across all trains.
- The locomotive IDs assigned to each train.
- The railcar IDs assigned to each train.

Possible result classes:

```java
class Train {
    List<String> locomotiveIds;
    List<String> railcarIds;
}

class AssemblyPlan {
    int trainCount;
    double totalLengthFt;
    List<Train> trains;
}
```

## Example

### Input

```text
Locomotives:
L1: capacity = 100 tons
L2: capacity = 80 tons
L3: capacity = 60 tons

Railcars:
R1: weight = 50 tons, length = 5,000 ft
R2: weight = 40 tons, length = 4,000 ft
R3: weight = 60 tons, length = 6,000 ft
R4: weight = 30 tons, length = 3,000 ft
```

### One optimal assembly

```text
Train 1:
  Locomotives: [L1]
  Railcars: [R1, R2]
  Capacity: 100 tons
  Railcar weight: 90 tons
  Railcar length: 9,000 ft

Train 2:
  Locomotives: [L2]
  Railcars: [R3]
  Capacity: 80 tons
  Railcar weight: 60 tons
  Railcar length: 6,000 ft

Train 3:
  Locomotives: [L3]
  Railcars: [R4]
  Capacity: 60 tons
  Railcar weight: 30 tons
  Railcar length: 3,000 ft
```

### Output summary

```text
Maximum train count = 3
Total railcar length = 18,000 ft
```

## Clarifications to ask the interviewer

The appropriate algorithm depends heavily on the missing constraints. Before implementing, clarify:

1. What is the maximum number of locomotives?
2. What is the maximum number of railcars?
3. Must the algorithm return an exact optimum, or is a heuristic acceptable?
4. Do the locomotives' own weights and lengths count toward the train limits?
5. Can railcars be reordered arbitrarily?
6. How should floating-point values be compared?
7. If plans tie on both objectives, can either plan be returned?
8. What should happen for null, empty, or invalid input?

## Assumption for an exact coding solution

If the input is small, an exact backtracking solution can enumerate locomotive groups and compatible railcar subsets. The problem is combinatorial and the exact solution has exponential worst-case complexity.

For large inputs, this is more appropriately formulated as an integer linear programming problem or solved using an explicitly accepted heuristic.
