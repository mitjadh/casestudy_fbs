package com.casestudy.checkinservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.checkinservice.models.CheckIn;


public interface CheckInRepository extends MongoRepository<CheckIn,Long>{
	

}

