# IFTTTRuleHub IoT Device part


### License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


<p style="text-align: justify;">
This part will allow anyone to monitor the environment in room using a microcontroller. Here we use an ESP32 board and sensors to read temperature, humidity and CO2 level. The sensor data is then displayed on an OLED display and served as a JSON object through a webserver. The device also uses BLE technology to detect presence in the room.
</P>

<p style="text-align: justify;">
The provided instructions below will guide anyone through the necessary steps to set up the project on anyone's device.
<p>

## Prerequisites
The project requires the following hardware components:

- ESP32 board
- SCD41 CO2 sensor
- OLED display (SH1107)


## Installation 

- Clone the repository on to you local machine
```bash
git clone https://gitlab.emse.fr/tareq.chy/integrative-development-of-a-cps2-project.git
``` 
- Navigate to the project directory:

```
cd </path/IoT part>
```
- Connect your ESP32 board to your computer via USB cable.
- Use a tool such as VScode, Thonny to upload the code onto your ESP32 board
- Connect the SCD41 sensor and the OLED display to your ESP32 board


## Execution or Run

<p style="text-align: justify;">
Once you have installed the code on your board and connected the sensors, you can start the project by running the main.py file. This will start the webserver and display the sensor readings on the OLED display.
<p>

## Usage

- Power up your ESP32 board using a USB power source.
- Wait for the ESP32 board to connect to your WiFi network.
- Wait for the ESP32 board to synchronize its real-time clock (RTC) with an NTP server.
- The collected data should be displayed on the OLED screen and served as JSON data via a web server.
- Can access the JSON data by visiting http://<ESP32_IP_ADDRESS>/data in a web browser.


## Built With

- Micropython - The programming language used
- PicoWeb - The web framework used


## Authors

- Tareq Md Rabiul Hossain Chy <tareq.chy@etu.emse>


## Acknowledgments

- SCD4X library - Library for interfacing with the SCD4X sensor
- SH1107 library - Library for interfacing with the SH1107 display
- aiorepl library - Library for asynchronous REPL (read-eval-print-loop) on MicroPython
- ble_simple_peripheral library - Library for BLE communication
