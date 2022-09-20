package com.matt.duran.fundamentals.service;

import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.entity.ApplicationDTO;
import com.matt.duran.fundamentals.exception.ApplicationNotFoundException;
import com.matt.duran.fundamentals.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Autowired // using constructor injection!
    private ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> listApplications() { return (List<Application>) applicationRepository.findAll(); }

    @Override
    public Application findApplication(long id) {

        // the optional return value, allows us to test if we have found something.
        // may be there or may not be there.
        Optional<Application> optionalApplication = applicationRepository.findById(id);

        if(optionalApplication.isPresent())
            return optionalApplication.get();
        else
            throw new ApplicationNotFoundException("We could not find that application");
    }

    @Override
    public ApplicationDTO saveApp(ApplicationDTO newApp) {
        // CRUD repo will actually return the entity that has been saved for you

        // This is the Design pattern that I need to follow in order to adhere to
        // threading and concurrency principles
        Application newAppImmutable = Application.builder()
                                            .owner(newApp.getOwner())
                                            .name(newApp.getName())
                                            .id(newApp.getId())
                                            .description(newApp.getDescription())
                                            .build();

        applicationRepository.save(newAppImmutable);
        return newApp; // return what was passed in

    }

    @Override
    public void deleteApp(long id) throws ApplicationNotFoundException {

        // if we make it past here then we have the id in our DB
        Application appToDelete = findApplication(id);

        // now delete it
        applicationRepository.delete(appToDelete);

    }
}
