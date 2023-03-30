package com.emse.spring.faircorp.model;

import java.time.LocalDateTime;

public class StoreData {
        private double temperature;
        private double humidity;
        private double co2;
        private LocalDateTime time;

        // getters and setters
        public double getTemperature() {
            return temperature;
        }
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
        public double getHumidity() {
            return humidity;
        }
        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
        public double getCo2() {
            return co2;
        }
        public void setCo2(double co2) {
            this.co2 = co2;
        }
        public LocalDateTime getTime() {
            return time;
        }
        public void setTime(LocalDateTime time) {
            this.time = time;
        }
}
