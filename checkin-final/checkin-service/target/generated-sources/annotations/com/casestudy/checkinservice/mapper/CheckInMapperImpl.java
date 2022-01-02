package com.casestudy.checkinservice.mapper;

import com.casestudy.checkinservice.dto.CheckInDto;
import com.casestudy.checkinservice.models.CheckIn;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1300.v20210419-1022, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class CheckInMapperImpl implements CheckInMapper {

    @Override
    public CheckInDto toDto(CheckIn checkin) {
        if ( checkin == null ) {
            return null;
        }

        CheckInDto checkInDto = new CheckInDto();

        checkInDto.setFirst_name( checkin.getFirst_name() );
        checkInDto.setFlight_id( checkin.getFlight_id() );
        checkInDto.setLast_name( checkin.getLast_name() );
        checkInDto.setQuantity( checkin.getQuantity() );
        checkInDto.setSeat_no( checkin.getSeat_no() );
        checkInDto.setSeat_type( checkin.getSeat_type() );
        checkInDto.set_id( checkin.get_id() );

        return checkInDto;
    }

    @Override
    public List<CheckInDto> toDtoList(List<CheckIn> checkin) {
        if ( checkin == null ) {
            return null;
        }

        List<CheckInDto> list = new ArrayList<CheckInDto>( checkin.size() );
        for ( CheckIn checkIn : checkin ) {
            list.add( toDto( checkIn ) );
        }

        return list;
    }

    @Override
    public CheckIn fromDto(CheckInDto checkinDto) {
        if ( checkinDto == null ) {
            return null;
        }

        CheckIn checkIn = new CheckIn();

        checkIn.setFirst_name( checkinDto.getFirst_name() );
        checkIn.setFlight_id( checkinDto.getFlight_id() );
        checkIn.setLast_name( checkinDto.getLast_name() );
        checkIn.setQuantity( checkinDto.getQuantity() );
        checkIn.setSeat_no( checkinDto.getSeat_no() );
        checkIn.setSeat_type( checkinDto.getSeat_type() );
        checkIn.set_id( checkinDto.get_id() );

        return checkIn;
    }

    @Override
    public List<CheckIn> fromDtoList(List<CheckInDto> checkinDtos) {
        if ( checkinDtos == null ) {
            return null;
        }

        List<CheckIn> list = new ArrayList<CheckIn>( checkinDtos.size() );
        for ( CheckInDto checkInDto : checkinDtos ) {
            list.add( fromDto( checkInDto ) );
        }

        return list;
    }
}
