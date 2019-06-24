package com.lanit.satonin18.model.entity.other_approach.use_xml_lombok;

import lombok.*;

@Data //get and set
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Organization { //Serializable

    private int id;

    private String name;

    //TODO in MySQL type = TINYINT(1)
    private boolean orgType;
}