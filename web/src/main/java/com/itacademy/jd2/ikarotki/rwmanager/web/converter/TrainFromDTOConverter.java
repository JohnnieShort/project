package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;

@Component
public class TrainFromDTOConverter implements Function<TrainDTO, ITrain> {
	@Autowired
	private ITrainService trainService;

	@Override
	public ITrain apply(final TrainDTO dto) {
		final ITrain entity = trainService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setLocomotive(dto.getLocomotive());

		return entity;
	}
}
