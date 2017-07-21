## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
* `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

## CODING TO DO
6: Sighting names location LatLong, but its just a location string, so I'll change the variable name to 'location' to more accurately describe the item.

## CODING FIXES
1. EndangeredAnimal class now inherits from Animal.
2. Endangered animal sightings page does not properly show results in table form, making it difficult to read.  New to improve the page layout.
3. Index.vtl has a conditional If statement attempts to not create sections of the form if the animal and endangered animals are blank.
4. Sighting does not track timestamp of sighting. I'll need to add "date" to the sighting class and db tables.
5. Need to improve UI layout in the animal sightings list (animal.vtl)
6. New Animal view was loading all animals in model (performance issue).  Data was removed from the view.
7. Created Health enum class to enforce proper health options.
8. Throw exception when attempts are made to set health outside enum options.
9. Health dropdown on new animal form gets health options from health enum.
10. Created an Age enum class to enforce proper age options.
11. Throw exception when attempts are made to set age outside of the Age enum options.
12. Age dropdown on new animal form now gets options from the age enum.
13. endangered_sighting POST route was loading unnecessary data into the model. I removed the code for performance reasons.
14. For performance reasons, after a sighting was submitted, I redirected the user to the home page.



### License

Copyright (c) 2017 **_MIT License_**
