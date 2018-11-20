package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;

@Component
public class TrainToDTOConverter implements Function<ITrain, TrainDTO> {
	@Override
	public TrainDTO apply(final ITrain entity) {
		final TrainDTO dto = new TrainDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setTrack(entity.getTrack());

		ILocomotive locomotive = entity.getLocomotive();
		if (locomotive != null) {
			dto.setLocomotiveId(locomotive.getId());
		}
		return dto;
	}
}
