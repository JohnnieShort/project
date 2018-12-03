package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;

@Component
public class RouteItemFromDTOConverter implements Function<RouteItemDTO, IRouteItem> {
	@Autowired
	private IRouteItemService routeItemService;
	@Autowired
	private IPassengerRouteService passengerRouteService;
	@Autowired
	private IStationService stationService;

	@Override
	public IRouteItem apply(final RouteItemDTO dto) {
		final IRouteItem entity = routeItemService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		
		IPassengerRoute pREntity = passengerRouteService.createEntity();
		pREntity.setId(dto.getPassengerRouteId());
		entity.setPassengerRoute(pREntity);
		
		
		final Date departureDate = dto.getDepartureDate();
        if (departureDate != null) {
            final Calendar fullDateCalendar = Calendar.getInstance();
            fullDateCalendar.setTime(departureDate);

            final Date departureTime = dto.getDepartureTime();
            if (departureTime != null) {
                final Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(departureTime);
                fullDateCalendar.set(Calendar.HOUR_OF_DAY,
                        timeCalendar.get(Calendar.HOUR_OF_DAY));
                fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            }

            entity.setDeparture(fullDateCalendar.getTime());
        }
        final Date arrivalDate = dto.getArrivalDate();
        if (arrivalDate != null) {
            final Calendar fullDateCalendar = Calendar.getInstance();
            fullDateCalendar.setTime(arrivalDate);

            final Date arrivalTime = dto.getArrivalTime();
            if (arrivalTime != null) {
                final Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(arrivalTime);
                fullDateCalendar.set(Calendar.HOUR_OF_DAY,
                        timeCalendar.get(Calendar.HOUR_OF_DAY));
                fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            }

            entity.setArrival(fullDateCalendar.getTime());
        }
		
		IStation stationFrom = stationService.createEntity();
		stationFrom.setId(dto.getStationFromId());
		if (dto.getStationFromName() != null) {
			stationFrom.setName(dto.getStationFromName());
		}
		entity.setStationFrom(stationFrom);

		IStation stationTo = stationService.createEntity();
		stationTo.setId(dto.getStationToId());
		if (dto.getStationToName() != null) {
			stationTo.setName(dto.getStationToName());
		}
		entity.setStationTo(stationTo);

		entity.setOrdinalNum(dto.getOrdinalNum());

		return entity;
	}
}
