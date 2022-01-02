package com.casestudy.bookingservice.service;

import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.casestudy.bookingservice.models.Booking;
import com.casestudy.bookingservice.models.DatabaseSequence;
import com.casestudy.bookingservice.repository.BookingRepository;

import java.util.Objects;

@Service
public class SequenceGeneratorService {


	@Autowired
    private  MongoOperations mongoOperations; 

	@Autowired
	private BookingRepository bookingRepository;
	
    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public  long generateSequence(String seqName) {
    	//find field where _id is present and modify
    	//increment by 1 if sequence is zero
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
    
    public Page<Booking> getPassengerPagination(Integer pageNumber, Integer pageSize) {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "_id");
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return bookingRepository.findAll(pageable);
	
}
}
