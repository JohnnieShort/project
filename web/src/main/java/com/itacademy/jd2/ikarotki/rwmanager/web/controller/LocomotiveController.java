package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.LocomotiveFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.LocomotiveToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.LocomotiveDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/locomotive")
public class LocomotiveController extends AbstractController<LocomotiveDTO> {
	private ILocomotiveService locomotiveService;
	private LocomotiveToDTOConverter toDtoConverter;
	private LocomotiveFromDTOConverter fromDtoConverter;

	@Autowired
	private LocomotiveController(ILocomotiveService locomotiveService, LocomotiveToDTOConverter toDtoConverter,
			LocomotiveFromDTOConverter fromDtoConverter) {
		super();
		this.locomotiveService = locomotiveService;
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

		final LocomotiveFilter filter = new LocomotiveFilter();
		prepareFilter(gridState, filter);

		final List<ILocomotive> entities = locomotiveService.find(filter);
		List<LocomotiveDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(locomotiveService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("locomotive.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ILocomotive newEntity = locomotiveService.createEntity();
		LocomotiveDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("locomotive.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final LocomotiveDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "locomotive.edit";
		} else {
			final ILocomotive entity = fromDtoConverter.apply(formModel);
			locomotiveService.save(entity);
			return "redirect:/locomotive";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		locomotiveService.delete(id);
		return "redirect:/locomotive";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ILocomotive dbModel = locomotiveService.get(id);
		final LocomotiveDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("locomotive.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final LocomotiveDTO dto = toDtoConverter.apply(locomotiveService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("locomotive.edit", hashMap);
	}

}
