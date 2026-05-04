# Roman Numbers

**Génie Logiciel L3 - TP3**

[Sujet](https://loriscroce.frama.io/enseignement/genie_logiciel_l3/tp3) , [Guide JUnit](https://docs.junit.org/5.7.2/user-guide/)

1. Added Testing (Tests de réussite,Tests d’échec,Tests de validité)
   > ./gradlew test
2. Implemented toRoman() et fromRoman() , passes all the tests
   - _getRomanFromNumber(int a)_
   - _getNumberFromRoman(String a)_
3. Implemented and tested methods **java.lang.Number**
   - _toString()_
   - _toDouble()_
   - _toFloat()_
   - _toLong()_
   - _toInt()_
4. Implemented and tested **comparable interface**
   - _compareTo(RomanNumber other)_
5. Made Setters() **validation mandatory** for the RomanNumber class. To prevent invalid Roman numbers like " _IIIII_ -> _V_ ".
   - _setRoman(String roman)_
   - _setValue(int value)_
6. Added constraints to the constructer _RomanNumber(int value, String roman)_ to prevent inconsistent Roman and normal numbers. Ex: " _IV_ -> _5_ " or " _V_ -> _4_ ".
   - _RomanNumber(int value, String roman)_

---

## Gradlew

1. > ./gradlew run

   Run with the run task, which assembles the application and executes some script or binary

2. > ./gradlew build

   Gradle builds for the build task to designate assembling all outputs and running all

3. > ./gradlew clean

   Delete the contents of the build directory using the clean task. Doing so will cause pre-computed outputs to be lost

- > ./gradlew test

  Runs the test task for all subprojects when invoked from the root project
  (if it is correct it should run all the tests and just show the summary of all test, if not it will only show the mistakes.)
  - > build/reports/tests/test/index.html

    To see the details of the tests on the browser.
