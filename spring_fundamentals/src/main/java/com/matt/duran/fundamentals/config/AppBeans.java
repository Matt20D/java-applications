package com.matt.duran.fundamentals.config;

import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
        AppUtils.class // lets Dependency Inject the Logger bean into this class
)
public class AppBeans {

    // HERE WE ARE DEMONSTRATING OUR APP USING A COMMAND LINE RUNNER
    // THUS WE CAN THEN run the below lambda when the app begins

//    private static final Logger log = LoggerFactory.getLogger(FundamentalsApplication.class);

    @Bean // fetches the Application Repository from the App Context
          //we would then run this bad boy from the command line
    public CommandLineRunner demo(ApplicationRepository repo, Logger log) {

        return (args) -> {
            repo.save(new Application().builder().id(1).name("Track-zilla").owner("Matthew.Duran")
                    .description("Application for tracking bugs").build());
            repo.save(new Application().builder().id(2).name("expenses").owner("Mary, jones")
                    .description("application to track expense reports").build());
            repo.save(new Application().builder().id(3).name("notifications").owner("barry allen")
                    .description("application to send alerts and notifications to users").build());



            for (Application application : repo.findAll()) {
                log.info("The application is ==> " + application.toString());
            }
        };
    }
}
