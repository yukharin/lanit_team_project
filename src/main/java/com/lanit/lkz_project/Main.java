package com.lanit.lkz_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Main {

    Logger logger = LoggerFactory.getLogger(Main.class);

//    public static void main(String[] args) {
//        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
//                ZoneId.of("America/New_York"));
//        // 1969-07-16T09:32-04:00[America/New_York]
//        System.out.println("apollo11launch: " + apollo11launch);
//
//        Instant instant = apollo11launch.toInstant();
//        System.out.println("instant: " + instant);
//
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
//        System.out.println("zonedDateTime: " + zonedDateTime);
//
//        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
//                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
//        // Constructs March 31 3:30
//        System.out.println("skipped: " + skipped);
//
//        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
//                // End of daylight savings time
//                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
//        // 2013-10-27T02:30+02:00[Europe/Berlin]
//        ZonedDateTime anHourLater = ambiguous.plusHours(1);
//        // 2013-10-27T02:30+01:00[Europe/Berlin]
//        System.out.println("ambiguous: " + ambiguous);
//        System.out.println("anHourLater: " + anHourLater);
//
//        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
//                LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
//        System.out.println("meeting: " + meeting);
//        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
//        // Caution! Won’t work with daylight savings time
//        System.out.println("nextMeeting: " + nextMeeting);
//        nextMeeting = meeting.plus(Period.ofDays(7)); // OK
//        System.out.println("nextMeeting: " + nextMeeting);
//    }

    @Resource(lookup = "java:comp/DefaultDataSource")
    private DataSource dataSource;

    public static void main(String[] args) throws NamingException {
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH mm, dd MMM yyyy");
//
//        ZonedDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.of("America/Los_Angeles"));
//        ZonedDateTime zonedDateTime = localDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
//
//
//        System.out.println(format.format(localDateTime));
//
//        LocalDateTime localDateTime1 = LocalDateTime.now();
//
//        ZonedDateTime zoned = localDateTime1.atZone(ZoneId.of("Asia/Yerevan"));
//
//        String instant = LocalDateTime.now().toString();
//        System.err.println(instant);
//
//        LocalDateTime finalTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
//        LocalDateTime zoneddd = finalTime.atZone(ZoneId.of("America/Los_Angeles")).toLocalDateTime();
//        System.err.println(zoneddd);
//        System.err.println(finalTime);
//
//        ZonedDateTime greenwich = LocalDateTime.now().atZone(ZoneId.of("UTC"));
//
//        System.err.println(localDateTime1);
//        System.err.println(zoned);
//        System.err.println(greenwich);
//
//        LocalDateTime localDateTime2 = LocalDateTime.now().atZone(ZoneId.of("Asia/Yekaterinburg")).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
////        System.err.println(instant.atZone(ZoneId.of("UTC")));
//        System.err.println(localDateTime2);
//
//        Locale[] locales = Locale.getAvailableLocales();
//
//        List<Locale> list = Arrays.asList(locales);
//        System.err.println(list.size());
//
////        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
//
//        Locale localee = Locale.getDefault();
//        System.err.println(localee.getCountry());
//        System.err.println(localee.getLanguage());
//
//        Locale.setDefault(Locale.CHINESE);
//
//        for (Locale locale : list) {
//            System.err.println("Country: " + locale.getCountry());
//            System.out.println("Locale: " + locale);
//            System.out.println("Language: " + locale.getLanguage());
//            System.out.println("Variant: " + locale.getVariant());
//            System.out.println("Display name: " + locale.getDisplayName());
//            System.out.println("Display Language: " + locale.getDisplayLanguage());
//            System.out.println("Display Country: " + locale.getDisplayCountry());
//            System.out.println("ISO3 Language: " + locale.getISO3Language());
//        }


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "password7788";
        String encoded = bCryptPasswordEncoder.encode(password);
        System.err.println(encoded);
        System.err.println(encoded.length());

        for (int i = 0; i < 100; i++) {
            String passEncoded = bCryptPasswordEncoder.encode(password);
            System.err.println(bCryptPasswordEncoder.matches(password, encoded));
        }

        Context namingComtext = new InitialContext();

    }
}
