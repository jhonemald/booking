package com.booking.reservas.repository;

import com.booking.reservas.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface BookingRepository extends MongoRepository<Booking, String >
{

    Optional<Booking> findByEmail (String email);


}
