package com.casestudy.checkinservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.casestudy.checkinservice.dto.CheckInDto;
import com.casestudy.checkinservice.models.CheckIn;

@Mapper(componentModel="spring")
public interface CheckInMapper {
	
	CheckInDto toDto(CheckIn checkin);    // entity to dto

    List<CheckInDto> toDtoList(List<CheckIn> checkin);          // list to listdto

    CheckIn fromDto(CheckInDto checkinDto);   // dto to entity

    List<CheckIn> fromDtoList(List<CheckInDto> checkinDtos); 
}
