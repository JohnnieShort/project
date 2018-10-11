package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;

@Controller
@RequestMapping(value = "/station")
public class StationController {
	@Autowired
    private IStationService stationService;

    @Autowired
    private StationToDTOConverter toDtoConverter;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest req) {

        final List<IStation> entities = stationService.getAll();
        List<StationDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

        final HashMap<String, Object> models = new HashMap<>();
        models.put("list", dtos);
        return new ModelAndView("station.list", models);
    }
}
