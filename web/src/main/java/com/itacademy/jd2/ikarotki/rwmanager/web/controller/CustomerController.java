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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICustomerService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CustomerFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CustomerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CustomerDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.ListDTO;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends AbstractController<CustomerDTO> {
	private ICustomerService customerService;
	private CustomerToDTOConverter toDtoConverter;
	private CustomerFromDTOConverter fromDtoConverter;

	@Autowired
	private CustomerController(ICustomerService customerService, CustomerToDTOConverter toDtoConverter,
			CustomerFromDTOConverter fromDtoConverter) {
		super();
		this.customerService = customerService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false, defaultValue = "id") final String sortColumn) {

		final ListDTO<CustomerDTO> listDTO = getListDTO(req);
		listDTO.setSort(sortColumn);

		final CustomerFilter filter = new CustomerFilter();
		prepareFilter(listDTO, filter);

		final List<ICustomer> entities = customerService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.LIST_MODEL_ATTRIBUTE, listDTO);
		return new ModelAndView("customer.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICustomer newEntity = customerService.createEntity();
		CustomerDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("customer.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CustomerDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "customer.edit";
		} else {
			final ICustomer entity = fromDtoConverter.apply(formModel);
			customerService.save(entity);
			return "redirect:/customer";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		customerService.delete(id);
		return "redirect:/customer";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICustomer dbModel = customerService.get(id);
		final CustomerDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("customer.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CustomerDTO dto = toDtoConverter.apply(customerService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("customer.edit", hashMap);
	}

}
