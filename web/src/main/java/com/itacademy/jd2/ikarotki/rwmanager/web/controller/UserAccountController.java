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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RegistrationDataDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.UserAccountDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController<UserAccountDTO> {
	private IUserAccountService userAccountService;
	private UserAccountToDTOConverter toDtoConverter;
	private UserAccountFromDTOConverter fromDtoConverter;

	@Autowired
	private UserAccountController(IUserAccountService userAccountService, UserAccountToDTOConverter toDtoConverter,
			UserAccountFromDTOConverter fromDtoConverter) {
		super();
		this.userAccountService = userAccountService;
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

		final UserAccountFilter filter = new UserAccountFilter();
		prepareFilter(gridState, filter);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm(final HttpServletRequest req) {
		final Map<String, Object> hashMap = new HashMap<>();
		
		RegistrationDataDTO dto = new RegistrationDataDTO();
		hashMap.put("formModel", dto);
		String url = req.getHeader("referer");
		hashMap.put("url", url);

		return new ModelAndView("register", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result,
			final HttpServletRequest req) {
		if (result.hasErrors()) {
			return "userAccount.edit";
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);

			userAccountService.save(entity);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.toString().equals("ROLE_USER")) {
					return "redirect:/personalPage";
				}
			}
			return "redirect:/userAccount";
		}

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id,
			final HttpServletRequest req) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id,
			final HttpServletRequest req) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("formModel", dto);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		return new ModelAndView("userAccount.edit", hashMap);
	}

}
