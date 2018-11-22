package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;

@Component
public class PassengerRouteFromDTOConverter implements Function<PassengerRouteDTO, IPassengerRoute> {
	@Autowired
	private ITrainService trainService;
	
	@Autowired
	private IPassengerRouteService passengerRouteService;

	@Override
	public IPassengerRoute apply(final PassengerRouteDTO dto) {
		final IPassengerRoute entity = passengerRouteService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		

		

		entity.setPassengerRouteType(PassengerRouteType.valueOf(dto.getPassengerRouteType()));
		ITrain train = trainService.createEntity();
				train.setId(dto.getTrainId());
		entity.setTrain(train);
		entity.setIsActual(dto.getIsActual());
		entity.setFrequency(Frequency.valueOf(dto.getFrequency()));
		

		return entity;
	}
}
