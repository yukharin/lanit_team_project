package com.lanit.satonin18.app.objects.input.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderByForm
        implements Serializable {

    private Boolean desc;
    private String orderFieldName;
}
