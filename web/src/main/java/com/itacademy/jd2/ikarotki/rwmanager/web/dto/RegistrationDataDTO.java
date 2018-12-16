package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationDataDTO extends UserAccountDTO {
    @NotNull
    @Size(min = 1, max = 60)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
