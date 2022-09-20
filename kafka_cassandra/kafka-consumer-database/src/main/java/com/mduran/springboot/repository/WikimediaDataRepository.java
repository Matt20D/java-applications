package com.mduran.springboot.repository;

import com.mduran.springboot.entity.WikimediaEventData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

// if you want to use the JPA repository then you just replace Cassandra with JPA
@Repository
public interface WikimediaDataRepository extends CassandraRepository<WikimediaEventData, Long> {
}
