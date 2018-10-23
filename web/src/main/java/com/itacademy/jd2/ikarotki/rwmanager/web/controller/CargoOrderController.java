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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoOrderService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoOrderFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoOrderToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoOrderDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/cargoOrder")
public class CargoOrderController extends AbstractController<CargoOrderDTO> {
	private ICargoOrderService cargoOrderService;
	private CargoOrderToDTOConverter toDtoConverter;
	private CargoOrderFromDTOConverter fromDtoConverter;

	@Autowired
	private CargoOrderController(ICargoOrderService cargoOrderService, CargoOrderToDTOConverter toDtoConverter,
			CargoOrderFromDTOConverter fromDtoConverter) {
		super();
		this.cargoOrderService = cargoOrderService;
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

		final CargoOrderFilter filter = new CargoOrderFilter();
		prepareFilter(gridState, filter);

		final List<ICargoOrder> entities = cargoOrderService.find(filter);
		List<CargoOrderDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
        gridState.setTotalCount(cargoOrderService.getCount(filter));


        final Map<String, Object> models = new HashMap<>();
        models.put("gridItems", dtos);
        return new ModelAndView("cargoOrder.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICargoOrder newEntity = cargoOrderService.createEntity();
		CargoOrderDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("cargoOrder.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CargoOrderDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "cargoOrder.edit";
		} else {
			final ICargoOrder entity = fromDtoConverter.apply(formModel);
			cargoOrderService.save(entity);
			return "redirect:/cargoOrder";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		cargoOrderService.delete(id);
		return "redirect:/cargoOrder";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICargoOrder dbModel = cargoOrderService.get(id);
		final CargoOrderDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("cargoOrder.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CargoOrderDTO dto = toDtoConverter.apply(cargoOrderService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("cargoOrder.edit", hashMap);
	}

}