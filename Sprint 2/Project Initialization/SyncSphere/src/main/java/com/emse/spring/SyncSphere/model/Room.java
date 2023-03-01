package com.emse.spring.SyncSphere.model;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    @Column(nullable=false)
    private Integer floor;
    @Column(nullable=false)
    private String name;

    private Double temperature;
    private Double humidity;
    private Double co2;


    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    private Set<Heater> heaters;


    public Room() {
    }

    public Room(Integer floor,String name) {
        this.floor = floor;
        this.name = name;
    }


    public Room(Integer floor,String name, Double temperature, Double humidity, Double co2) {
        this.floor=floor;
        this.name = name;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }


    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    public Integer getFloor() {
        return this.floor;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public Double getTemperature() {
        return temperature;
    }


    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
    public Double getHumidity() {
        return humidity;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }
    public Double getCo2() {
        return co2;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }
    public Set<Heater> getHeaters() {
        return heaters;
    }


}