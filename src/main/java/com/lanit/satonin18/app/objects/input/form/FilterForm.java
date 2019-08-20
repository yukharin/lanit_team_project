package com.lanit.satonin18.app.objects.input.form;

import lombok.Data;

import java.util.List;

@Data
public class FilterForm {
    private List<Integer> idFilterStatus;
    private Boolean showArchive;
}
