package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RegistrationDataDTO;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    private IUserAccountService userAccountService;
    private UserAccountFromDTOConverter fromDtoConverter;

    @Autowired
    private RegisterController(IUserAccountService userAccountService, UserAccountFromDTOConverter fromDtoConverter) {
        super();
        this.userAccountService = userAccountService;
        this.fromDtoConverter = fromDtoConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", new RegistrationDataDTO());
        // hashMap.put("registrationMode", true);

        return new ModelAndView("register", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("formModel") final RegistrationDataDTO formModel,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        } else {
            final IUserAccount entity = fromDtoConverter.apply(formModel);
            entity.setPassword(formModel.getPassword());
            userAccountService.save(entity);
            return "redirect:/index";
        }
    }
}
