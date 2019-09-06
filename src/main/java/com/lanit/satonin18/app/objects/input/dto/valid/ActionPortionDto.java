package com.lanit.satonin18.app.objects.input.dto.valid;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class ActionPortionDto
        implements Serializable {

    @NotNull
    private Integer notificationId;
    @NotNull
    private Integer idUserImplementor;
    @NotNull
    private Integer idActionType;
    @NotNull
    private Integer idNotificationStatus;

    @NotNull //todo size (dont check on NULL)on Tomcat, on old ver validation
    @Size(min = 2, max = 300)
    private String content;
}
