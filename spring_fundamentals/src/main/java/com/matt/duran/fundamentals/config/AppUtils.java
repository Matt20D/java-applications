package com.matt.duran.fundamentals.config;

import com.matt.duran.fundamentals.FundamentalsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class AppUtils {

    @Bean // create the Logger Object
    public Logger createAppLogger() {
        return LoggerFactory.getLogger(FundamentalsApplication.class);
    }

}
