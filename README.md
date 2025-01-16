# Integrative Development of a CPS2 Project

### License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Getting started
This is an integrated project of IFTTT service. This works as if this condition than that action shall occur. The user will be able to execute the rules which will be containing with condition and action. There will be multiple rules with condition and action.  

## Name
The name of our IFTTT project is 'IDCPS2' inspired from the course name Integrative Development of a CPS2.

## Description
The main motive of this project is to implement IFTTT(If This Than That) service. To implement this service we have an web application where we used SpringBoot as backend and Vue.js as frontend. We had an Iot development kit with various environmental sensors (temperature, illuminance, sound, etc.) and various network interfaces (Wi-Fi, BLE). By using this Iot device we could get data for human presence and received other data temperature, humidity, date and time, CO2 level. To retrive those data we used pico webserver. We also got a web service to simulating the heating. The backend with Spring Boot is our middleware which takes the rules from the end user. For us the end user is Vue.js and the middleware checks whether the condition are mate or not with the Iot device Then it asks for action to the service it is connected. For us the service is heater service.


## Used Tools
- micropython
- picoweb server
- Spring Boot
- Vue Js
- H2 database

## Usage
IFTTT is short for "If This Then That", and rhymes with "Gift." We used to be called 'if this, then that' because Applets would have one trigger and one action. If this happens — then that happens. For example, "presence detected" → "turn heating on".

## Support
- For reporting uses you can do it in the issues section of this repository.
- For contacting the developer for any other reason please email me at tareq.chy@etu.emse.fr or nushrat.jahan@etu.emse.fr or utibeabasi.dan@etu.emse.fr.

## Roadmap
We have some limitation with this IFTTT web application. We  will work on those limitation in futrue. 

## Authors and acknowledgment
Contributed person's name is given below:
- Tareq Md Rabiul Hossain Chy <tareq.chy@etu.emse.fr>






