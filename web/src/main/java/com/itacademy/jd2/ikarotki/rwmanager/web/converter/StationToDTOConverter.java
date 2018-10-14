package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;

@Component
public class StationToDTOConverter implements Function<IStation, StationDTO> {

    @Override
    public StationDTO apply(final IStation entity) {
        final StationDTO dto = new StationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}
