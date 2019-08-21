package com.lanit.satonin18.app.objects.input.dto.valid;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class ActionPortionDtoValid
        implements Serializable {

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
