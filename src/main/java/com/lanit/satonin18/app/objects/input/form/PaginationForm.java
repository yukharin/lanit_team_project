package com.lanit.satonin18.app.objects.input.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaginationForm
        implements Serializable {

    private Integer maxResult;
    private Integer page;
}
