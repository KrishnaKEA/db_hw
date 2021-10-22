package com.example.db_hw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbHwApplication {
    public static String ip;
   public static String pass;

    public static void main(String[] args) {
// Check chek
        ip = args[0];
        pass = args[1];

        SpringApplication.run(DbHwApplication.class, args);
    }

}
