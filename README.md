# Elevator System

## About
This is the simulation of the elevator system. There are *n* elevators and *m* floors.
Randomly generated people simulate the usage of the elevator system, and the user can use elevators
like one of those people using the console input.

The Console Interface shows a table with information of all elevators. 
Every elevator updates its state in every step of simulation.

## Execution
### Arguments
```
usage: elev [-eN <elevNumb>] [-fN <floorNumb>] [-i | -n]

Options, flags and arguments may be in any order
-eN <elevNumb>    Elevators number
-fN <floorNumb>   Floors number
-i                Console input
-n                NoInput
```
### Compile
```shell
mvn clean compile
```
### Run tests
```shell
mvn test
```
### Run the simulation
```shell
mvn exec:java -Dexec.args="-eN <elevatorNumber> -fN <floorNumber> [-i | -n]"
```
### Example execution
```shell
$ mvn clean compile
$ mvn test
$ mvn exec:java -Dexec.args="-eN 16 -fN 8 -i"
```
*Warning*: in case of a big number of elevators it is better to run the program on a big screen.

## Future works
- Make elevators run concurrently
- GUI