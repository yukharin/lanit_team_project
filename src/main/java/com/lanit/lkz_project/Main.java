package com.lanit.lkz_project;

import java.time.Duration;
import java.time.Instant;


public class Main {


    public static void main(String[] args) {
        System.err.println(Instant.MAX);
        System.err.println(Duration.between(Instant.now(), Instant.now()));

    }

}
