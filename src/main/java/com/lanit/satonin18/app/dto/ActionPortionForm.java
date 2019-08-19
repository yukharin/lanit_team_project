package com.lanit.satonin18.app.dto;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ActionPortionForm {

    //DTO
    //All Parameters ARE REQUARED, so primitive types = OK!

    @NotNull
    private Integer notificationId;
    @NotNull
    private Integer idActionType;
    @NotNull
    private Integer idNotificationStatus;
    @Size(min = 2, max = 300)
    private String content;
    @NotNull
    private Integer idUserImplementor;
}
