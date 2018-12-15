package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/passenger")
public class PassengerController extends AbstractController<PassengerDTO> {
	private IPassengerService passengerService;
	private PassengerToDTOConverter toDtoConverter;
	private PassengerFromDTOConverter fromDtoConverter;

	@Autowired
	private PassengerController(IPassengerService passengerService, PassengerToDTOConverter toDtoConverter,
			PassengerFromDTOConverter fromDtoConverter) {
		super();
		this.passengerService = passengerService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PassengerFilter filter = new PassengerFilter();
		prepareFilter(gridState, filter);

		final List<IPassenger> entities = passengerService.find(filter);
		List<PassengerDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(passengerService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("passenger.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		PassengerDTO dto = new PassengerDTO();
		hashMap.put("formModel", dto);

		return new ModelAndView("passenger.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PassengerDTO formModel, final BindingResult result,
			final HttpServletRequest req) {
		if (result.hasErrors()) {
			return "passenger.edit";
		} else {
			final IPassenger entity = fromDtoConverter.apply(formModel);
			passengerService.save(entity);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.toString().equals("ROLE_USER")) {
					return "redirect:/personalPage";
				}
			}
			return "redirect:/passenger";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		passengerService.delete(id);
		return "redirect:/passenger";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id,
			final HttpServletRequest req) {
		final IPassenger dbModel = passengerService.get(id);
		final PassengerDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		hashMap.put("url", req.getHeader("referer"));
		return new ModelAndView("passenger.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id,
			final HttpServletRequest req) {
		final PassengerDTO dto = toDtoConverter.apply(passengerService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		return new ModelAndView("passenger.edit", hashMap);
	}

}
