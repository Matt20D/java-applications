package com.matt.duran.fundamentals;


import com.matt.duran.fundamentals.config.AppBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication//(scanBasePackages = {"com.matt.duran.fundamentals"}) // shorthand for @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan

// Toggle this to on in order to enable the AppBeans Logger to run
// this is kind of how it works to have the Beans passed around, for utilization.
@Import(
        AppBeans.class
)
public class FundamentalsApplication {



    public static void main(String[] args) {
        SpringApplication.run(FundamentalsApplication.class, args);
    }





}
