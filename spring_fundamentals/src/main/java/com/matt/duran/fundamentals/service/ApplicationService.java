package com.matt.duran.fundamentals.service;

import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.entity.ApplicationDTO;

import java.util.List;

public interface ApplicationService {

    List<Application> listApplications();

    Application findApplication(long id);

    ApplicationDTO saveApp(ApplicationDTO newApp);

    void deleteApp(long id);
}
