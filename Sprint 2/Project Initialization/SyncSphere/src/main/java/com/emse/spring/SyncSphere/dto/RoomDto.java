package com.emse.spring.SyncSphere.dto;
import com.emse.spring.SyncSphere.model.Room;


public class RoomDto {
    private Long id;
    private String name;
    private Integer floor;
    private Double temperature;
    private Double humidity;
    private Double co2;



    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.temperature = room.getTemperature();
        this.humidity = room.getHumidity();
        this.co2 = room.getCo2();
    }


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    public Integer getFloor() {
        return floor;
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


}