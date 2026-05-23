# Design Decisions

---

### Flight identity vs flight number

A `Vol` has two different identifiers:

- `id: UUID` is the technical identity of the object.
- `numero: String` is the business flight number.

Two different `Vol` objects may technically have the same `numero`, but a single `Compagnie` is not allowed to contain two flights with the same number.

Therefore:

- `Vol.equals()` and `Vol.hashCode()` use the technical UUID.
- flight number uniqueness is enforced by `Compagnie`.
- both `Compagnie.addVol(vol)` and `vol.setCompagnie(compagnie)` preserve this invariant.

---

### Bidirectional association

The association between `Compagnie` and `Vol` is bidirectional:

- adding a flight to a company updates the flight's company reference;
- changing a flight's company updates both the old and the new company collections;
- removing a flight from a company clears the flight's company reference.

---

### Escale as an association class

An `Escale` is not an `Aeroport`.

An airport is a stable domain entity, while an escale represents the passage of a specific flight through an airport. This passage has its own information:

- arrival time
- departure time
- derived duration
- possibly an order in the route

A simple association between `Vol` and `Aeroport` would not be enough, because the association itself carries data. For this reason, `Escale` is modeled conceptually as an association class between `Vol` and `Aeroport`.

The chosen model is:

- one `Vol` contains zero or more `Escale`
- one `Escale` references exactly one `Aeroport`
- no escale is represented by an empty escale collection, not by a fake airport or a `NullEscale`

In the implementation, `Escale` is represented as a normal class contained by `Vol`. It extends `Etape` only to reuse the common temporal attributes `depart`, `arrivee`, and the derived duration calculation. Conceptually, however, an escale remains an enriched relation between a flight and an airport.

---

### Reservation as a domain entity

A `Reservation` is modeled as a real domain entity, not as a simple association between `Client` and `Vol`.

A reservation has its own identity and business data:

- reservation number
- creation date
- price
- client
- passenger
- flight
- current state

This is necessary because a reservation has its own lifecycle: it can be paid, confirmed, or cancelled depending on its current state.

---

### Client and passenger distinction

`Client` and `Passager` are modeled as two separate concepts.

A `Client` is the person or entity that creates and pays for reservations.  
A `Passager` is the person who actually travels on a flight.

This distinction is required because one client may reserve flights for different passengers. Therefore, a reservation references exactly one client and exactly one passenger.

---

### Reservation state model

A reservation has one current state at a time. We do not represent reservation status with multiple booleans such as `paid`, `confirmed`, or `cancelled`, because this could create inconsistent combinations.

Instead, reservation states are modeled with the State pattern.

The possible states are:

- `EnAttente`: initial state
- `Payee`: payment has been completed
- `Confirmee`: reservation is confirmed
- `Annulee`: reservation is cancelled

Each state is responsible for deciding which transitions are allowed and which transitions are forbidden.

### Reservation state transitions

The reservation lifecycle is modeled with the following transitions:

```c
EN_ATTENTE
   -- payer() --> PAYEE
   -- annuler() --> ANNULEE
   -- confirmer() --> forbidden

PAYEE
   -- confirmer() --> CONFIRMEE
   -- payer() --> forbidden
   -- annuler() --> forbidden in the current simplified model

CONFIRMEE
   -- payer(), confirmer(), annuler() --> forbidden

ANNULEE
   -- payer(), confirmer(), annuler() --> forbidden
```

Forbidden transitions throw a TransitionInterditeException.

---

### Why State Pattern instead of a simple enum

A simple enum would be enough if states only stored names.

However, in this model, each state has its own behavior:

- `EnAttente` allows payment and cancellation.
- `Payee` allows confirmation.
- `Confirmee` forbids further modification.
- `Annulee` forbids further modification.

For this reason, the State pattern is more explicit and avoids large conditional blocks inside `Reservation`.

---

### Pricing strategy

Reservation pricing is separated from reservation creation by using the Strategy pattern.

The `PolitiqueTarif` interface defines one operation:

```java
double calculer(double basePrice);
```

---

### Reservation creation with Factory

Reservations are created through `ReservationFactory`.

This centralizes reservation creation and keeps the constructor controlled. The factory is responsible for:

- generating a reservation number;
- applying a pricing policy;
- creating a reservation with its client, passenger and flight.

By default, the factory uses the economy pricing policy.