package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoOrderDTO;

@Component
public class CargoOrderToDTOConverter implements Function<ICargoOrder, CargoOrderDTO> {
	 @Override
	    public CargoOrderDTO apply(final ICargoOrder entity) {
	        final CargoOrderDTO dto = new CargoOrderDTO();
	        dto.setId(entity.getId());
	        dto.setCreated(entity.getCreated());
	        dto.setUpdated(entity.getUpdated());
	        
	        dto.setCargoRoute(entity.getCargoRoute());
	        dto.setCustomer(entity.getCustomer());
	        dto.setStationFrom(entity.getStationFrom());
	        dto.setStationTo(entity.getStationTo());
	        dto.setDate(entity.getDate());
	        dto.setWeight(entity.getWeight());
	        dto.setPrice(entity.getPrice());
	        
	        
	        return dto;
	    }
}

