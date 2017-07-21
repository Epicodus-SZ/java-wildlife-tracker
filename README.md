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

### Coding
BUGS
1: endangered_animals should inherit from animals. - FIXED
2: Endangered Animal sightings page does not properly show results in table form, making it difficult to read.  New to improve the page layout. - FIXED
3: Data reporting is messed up.  "all sightings" report does not list endangered animals.
4: Index.vtl has a conditional If statement attempts to not create sections of the form if the animal and endangered animals are blank. - FIXED
5: Sighting does not track timestamp of sighting. I'll need to add "date" to the sighting class and db tables. - FIXED
6: Sighting names location LatLong, but its just a location string, so I'll change the variable name to 'location' to more accurately describe the item.
7: Need to improve UI layout in the animal sightings list (animal.vtl)



### License

Copyright (c) 2017 **_MIT License_**
