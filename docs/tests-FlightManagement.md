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
----

###### Additional unit tests

- `org.uca.aeroport`:
  - `AeroportTest`: tests constructor initialization, validation of invalid code/name/city and `toString()` content.
  - `VilleTest`: tests basic behavior of the `Ville` class and its `toString()` method.
  - `EtapeTest`: verifies duration computation and handling of missing dates in `Etape`.
  - `EscaleTest`: checks that `Escale` correctly initializes departure, arrival and associated airport.
  - `NullEscaleTest`: verifies that `NullEscale.getInstance()` is a singleton and behaves as a valid `Escale`.
- `org.uca.reservation`:
  - `ReservationTest`: extended to cover valid state transitions (create → pay → confirm, create → cancel) and forbidden transitions that must throw `TransitionInterditeException`.
  - `ClientTest`: checks getters/setters, the unmodifiable reservations list, and the behavior of `addReservation` / `removeReservation`.
  - `TarifEcoTest`, `TarifBusinessTest`, `TarifPromoTest`: verify that each pricing strategy applies the correct price calculation.
  - `ReservationFactoryTest`: ensures that `ReservationFactory` uses the correct final price depending on the chosen `PolitiqueTarif` (eco, business or promo).


