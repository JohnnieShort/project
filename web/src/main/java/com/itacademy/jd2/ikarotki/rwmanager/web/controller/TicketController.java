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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.ListDTO;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends AbstractController<TicketDTO> {
	private ITicketService ticketService;
	private TicketToDTOConverter toDtoConverter;
	private TicketFromDTOConverter fromDtoConverter;

	@Autowired
	private TicketController(ITicketService ticketService, TicketToDTOConverter toDtoConverter,
			TicketFromDTOConverter fromDtoConverter) {
		super();
		this.ticketService = ticketService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false, defaultValue = "id") final String sortColumn) {

		final ListDTO<TicketDTO> listDTO = getListDTO(req);
		listDTO.setSort(sortColumn);

		final TicketFilter filter = new TicketFilter();
		prepareFilter(listDTO, filter);

		final List<ITicket> entities = ticketService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.LIST_MODEL_ATTRIBUTE, listDTO);
		return new ModelAndView("ticket.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ITicket newEntity = ticketService.createEntity();
		TicketDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final TicketDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "ticket.edit";
		} else {
			final ITicket entity = fromDtoConverter.apply(formModel);
			ticketService.save(entity);
			return "redirect:/ticket";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		ticketService.delete(id);
		return "redirect:/ticket";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITicket dbModel = ticketService.get(id);
		final TicketDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TicketDTO dto = toDtoConverter.apply(ticketService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("ticket.edit", hashMap);
	}

}
