package com.lanit.lkz_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.lkz_project.entities.NotificationStatus;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        NotificationStatus status = NotificationStatus.APPROVED;
        String json = mapper.writeValueAsString(NotificationStatus.APPROVED);
        System.err.println(json);
    }
}
