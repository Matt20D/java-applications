package com.matt.duran.fundamentals.repository;

import com.matt.duran.fundamentals.entity.Application;
import org.springframework.data.repository.CrudRepository;

// spring boot will provide the implementations for this crud repo handling the
// Application data points
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    // we now have 4 basic crud operations that we can utilize
}
