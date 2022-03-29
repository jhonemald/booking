package com.booking.reservas.booking;

import com.booking.reservas.controller.BookingDto;
import com.booking.reservas.exception.BookingNotFoundException;
import com.booking.reservas.model.Booking;
import com.booking.reservas.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceMongoDb implements BookService
{
    private final BookingRepository bookingRepository;

    public BookingServiceMongoDb(@Autowired BookingRepository bookingRepository)
    {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save( booking);
    }

    @Override
    public Optional<Booking> findById(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Optional <Booking> findByEmail( String email )
    {
        return bookingRepository.findByEmail(email);
    }

    @Override
    public Booking update(Booking booking, BookingDto bookingDto) {
        booking.update(bookingDto);
        return bookingRepository.save(booking);
    }


    @Override
    public boolean delete( String id ) {
        if( bookingRepository.existsById( id ))
        {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> all() {
        return bookingRepository.findAll();
    }


}
