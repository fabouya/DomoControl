# DomoControl

  
## Synopsis

Ce projet concerne la création d'un moteur en Java pour gérer des controles domotique à l'aide d'un Raspberry Pi.


## Code Example

Dans cet exemple en particulier, il s'agit de gerer un ecosysteme centré sur une piscine, à savoir :
- controle et programmation de la filtration
- controle et programmation du robot
- controle et programmation de l'éclairage immergé
- relevé de température de l'eau
- affichage de la température sur un panneau led distant

## Motivation



## Installation

Install Java Openjdk

le classique :
```
apt-get update  
apt-get install oracle-java8-jdk  
```

Install wired Pi

La procedure complète se trouve ici : [wiringpi installation guide](http://wiringpi.com/download-and-install/)

Install Pi4J

...

Grab java libraries
- json simple
- active mq
- log4j

Install ActiveMQ server


## API Reference

### Architecture générale

![Screenshot](docs/img/archi1.png)

### Architecture interne

![Screenshot](docs/img/archi2.png)


### Hierarchie des classes

![Screenshot](docs/img/hierarchie.png)

## Tests

Describe and show how to run the tests with code examples.

## Contributors

Fabrice LEGRAND. 

## License

Ce projet est publié sous licence GNU GPL V3.
