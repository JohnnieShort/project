package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.TrainType;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;

@Component
public class TrainFromDTOConverter implements Function<TrainDTO, ITrain> {
	@Autowired
	private ITrainService trainService;
	@Autowired
	private ILocomotiveService locomotiveService;

	@Override
	public ITrain apply(final TrainDTO dto) {
		final ITrain entity = trainService.createEntity();
		entity.setId(dto.getId() != null ? dto.getId() : null);
		entity.setCreated(dto.getCreated() != null ? dto.getCreated() : null);
		entity.setUpdated(dto.getUpdated() != null ? dto.getUpdated() : null);
		entity.setTrack(dto.getTrack() != null ? dto.getTrack() : null);
		entity.setTraintype(TrainType.valueOf(dto.getTrainType()));

		final ILocomotive locomotive = locomotiveService.createEntity();
		locomotive.setId(dto.getLocomotiveId());
		//locomotive.setName(dto.getLocomotiveName());
		entity.setLocomotive(locomotive);

		return entity;
	}
}
