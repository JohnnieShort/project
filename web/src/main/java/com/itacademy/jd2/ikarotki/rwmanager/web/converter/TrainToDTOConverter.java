package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;

@Component
public class TrainToDTOConverter implements Function<ITrain, TrainDTO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainToDTOConverter.class);
	@Override
	public TrainDTO apply(final ITrain entity) {
		final TrainDTO dto = new TrainDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setTrack(entity.getTrack());
		dto.setTrainType(entity.getTrainType().name());

		ILocomotive locomotive = entity.getLocomotive();
		if (locomotive != null) {
			dto.setLocomotiveId(locomotive.getId());
			try {
			dto.setLocomotiveName(locomotive.getName());
			}catch(LazyInitializationException e) {
				LOGGER.warn("LazyInitializationException occured");
				return dto;
			}
		}
		return dto;
	}
}
