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
