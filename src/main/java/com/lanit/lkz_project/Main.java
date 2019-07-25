package com.lanit.lkz_project;

import com.google.gson.Gson;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.entities_service.UserService;


public class Main {

    Gson gson = new Gson();


    public static void main(String[] args) {
        UserService service = new UserService();
        User user = service.getUser(1L);

        Gson gson = new Gson();
        gson.fromJson("{\"firstName\":\"GarryVlad\",\"lastName\":\"LadatBDEFD\",\"login\":\"login4567882\",\"password\":\"password7789\",\"orgId\":\"1\"}", User.class);
        System.out.println(user);
    }
}
