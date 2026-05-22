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

### Bidirectional association

The association between `Compagnie` and `Vol` is bidirectional:

- adding a flight to a company updates the flight's company reference;
- changing a flight's company updates both the old and the new company collections;
- removing a flight from a company clears the flight's company reference.
