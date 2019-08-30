package com.lanit.satonin18.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data //get and set
@AllArgsConstructor
public class UserJson {
    String name;
    int age;
}
