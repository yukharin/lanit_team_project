package com.lanit.satonin18.app.objects.input.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterForm
        implements Serializable {

    private List<Integer> idFilterStatus;
    private Boolean showArchive;
}
