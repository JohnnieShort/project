package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TicketDTO {

    private Integer id;

    private Date created;

    private Date updated;

    private Double price;

    @Min(1)
    private Integer quantity;

    @Valid
    private PassengerDTO passenger = new PassengerDTO();

    private Integer passengerId;

    @NotNull
    private Integer passengerRouteId;

    @NotNull
    private Integer stationFromId;

    @NotNull
    private Integer stationToId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getPassengerRouteId() {
        return passengerRouteId;
    }

    public void setPassengerRouteId(Integer passengerRouteId) {
        this.passengerRouteId = passengerRouteId;
    }

    public Integer getStationFromId() {
        return stationFromId;
    }

    public void setStationFromId(Integer stationFromId) {
        this.stationFromId = stationFromId;
    }

    public Integer getStationToId() {
        return stationToId;
    }

    public void setStationToId(Integer stationToId) {
        this.stationToId = stationToId;
    }

    @Override
    public String toString() {
        return "TicketDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", price=" + price
                + ", passengerId=" + passengerId + ", passengerRouteId=" + passengerRouteId + ", stationFromId="
                + stationFromId + ", stationToId=" + stationToId + "]";
    }

    public PassengerDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDTO passenger) {
        this.passenger = passenger;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
