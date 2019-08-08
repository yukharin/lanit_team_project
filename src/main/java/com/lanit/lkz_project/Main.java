package com.lanit.lkz_project;

import java.time.*;

public class Main {

    public static void main(String[] args) {
        Instant instant = Instant.now().plusSeconds(6000);
        System.out.println(Duration.between(instant, Instant.now()).dividedBy(10).toNanos());
        System.err.println(LocalDateTime.now());
        System.err.println(LocalDate.now());
        System.err.println(ZonedDateTime.now());
        System.err.println(ZonedDateTime.now().getChronology());
        System.err.println(ZonedDateTime.now().getZone());
        System.err.println(LocalDate.now().plusMonths(236));
        System.err.println(LocalDate.now().plus(Period.ofMonths(236)));

    }
}
