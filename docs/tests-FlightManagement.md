# Tests
---

###### CompagnieTest

`CompagnieTest` verifies that a company can correctly create and manage flights.

It checks that:

- a company can create a flight and automatically link it to itself;
- `creerVol(...)` correctly initializes the flight number, dates, departure airport, and arrival airport;
- flight duration is correctly calculated;
- moving a flight from one company to another updates both company collections;
- removing a flight from a company also clears the flight's company reference;
- creating a flight with missing information throws an exception;
- the same `Vol` object cannot appear twice in the same company;
- a company refuses two flights with the same flight number;
- `addVol(...)` refuses a flight whose number already exists in the company;
- automatically generated flight numbers are unique;
- the automatic generator skips manually used numbers such as `VOL-1`.
---

###### VolTest

`VolTest` verifies the behavior and identity rules of a flight.

It checks that:

- a flight correctly stores its number, dates, departure airport, and arrival airport;
- flight duration is correctly calculated from departure and arrival dates;
- missing departure or arrival dates return `null` for the duration;
- `setCompagnie(...)` adds the flight to the company collection;
- `setCompagnie(null)` removes the flight from its previous company;
- changing a flight's company updates both the old and new company collections;
- two flights with the same flight number still have different technical identities;
- two flights with different numbers are not equal;
- a `HashSet` distinguishes two flights with the same business number but different UUIDs;
- `setCompagnie(...)` refuses assigning a flight to a company if another flight with the same number already exists.
