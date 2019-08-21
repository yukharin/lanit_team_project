package com.lanit.satonin18.app.objects.input.dto.valid;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class RegistrationDtoValid
        implements Serializable {

    @NotBlank
    @Size(min = 2, max = 45)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 45)
    private String lastName;

    @NotNull
    private Integer organizationId;
    //-----------------------------------------------------------------
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

//    TODO no true regexp
    @NotBlank
    @Size(min = 8, max = 60)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Пароль должен состоять из латинских букв и иметь как минимум одну заглавную букву, одну строчную и одну цифру")
    private String password;

//    @NotEmpty
//    private List<Integer> authorityId;

//    @NotNull
//    private Boolean enabled;
//    @NotNull
//    private Boolean accountNonExpired;
//    @NotNull
//    private Boolean accountNonLocked;
//    @NotNull
//    private Boolean credentialsNonExpired;
}
