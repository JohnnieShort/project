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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.WagonFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.WagonToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.WagonDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.ListDTO;

@Controller
@RequestMapping(value = "/wagon")
public class WagonController extends AbstractController<WagonDTO> {
	private IWagonService wagonService;
	private WagonToDTOConverter toDtoConverter;
	private WagonFromDTOConverter fromDtoConverter;

	@Autowired
	private WagonController(IWagonService wagonService, WagonToDTOConverter toDtoConverter,
			WagonFromDTOConverter fromDtoConverter) {
		super();
		this.wagonService = wagonService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false, defaultValue = "id") final String sortColumn) {

		final ListDTO<WagonDTO> listDTO = getListDTO(req);
		listDTO.setSort(sortColumn);

		final WagonFilter filter = new WagonFilter();
		prepareFilter(listDTO, filter);

		final List<IWagon> entities = wagonService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.LIST_MODEL_ATTRIBUTE, listDTO);
		return new ModelAndView("wagon.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IWagon newEntity = wagonService.createEntity();
		WagonDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("wagon.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final WagonDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "wagon.edit";
		} else {
			final IWagon entity = fromDtoConverter.apply(formModel);
			wagonService.save(entity);
			return "redirect:/wagon";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		wagonService.delete(id);
		return "redirect:/wagon";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IWagon dbModel = wagonService.get(id);
		final WagonDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("wagon.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final WagonDTO dto = toDtoConverter.apply(wagonService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("wagon.edit", hashMap);
	}

}
