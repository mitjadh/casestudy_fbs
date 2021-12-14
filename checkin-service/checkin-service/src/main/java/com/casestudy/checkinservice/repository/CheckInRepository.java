package com.casestudy.checkinservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.checkinservice.models.CheckIn;

@Repository
public interface CheckInRepository extends MongoRepository<CheckIn,Long>{


}

