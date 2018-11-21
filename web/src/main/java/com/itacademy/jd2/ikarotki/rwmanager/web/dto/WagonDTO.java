package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class WagonDTO {
	
	private Integer id;
	@NotNull
	
	private String wagonType;
	@NotNull
	@Range(min = 0)
	private Integer trainId;
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private Double freightPrice;
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private Double capacity;
	
	private Date created;
	
	private Date updated;

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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public String getWagonType() {
		return wagonType;
	}

	public void setWagonType(String wagonType) {
		this.wagonType = wagonType;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Double getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}

	@Override
	public String toString() {
		return "WagonDTO [id=" + id + ", wagonType=" + wagonType + ", trainId=" + trainId + ", freightPrice="
				+ freightPrice + ", capacity=" + capacity + ", created=" + created + ", updated=" + updated + "]";
	}

	

}
