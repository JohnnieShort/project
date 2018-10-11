package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;

@Component
public class StationFromDTOConverter implements Function<StationDTO, IStation> {

    @Autowired
    private IStationService stationService;

    @Override
    public IStation apply(final StationDTO dto) {
        final IStation entity = stationService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
        entity.setCreated(dto.getCreated());
        entity.setUpdated(dto.getUpdated());
        return entity;
    }
}
