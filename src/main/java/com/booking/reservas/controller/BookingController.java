package com.booking.reservas.controller;

import com.booking.reservas.booking.BookService;
import com.booking.reservas.exception.BookingNotFoundException;
import com.booking.reservas.exception.BookingWithEmailAlreadyException;
import com.booking.reservas.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/booking")
public class BookingController
{

   private final BookService bookService;

    public BookingController(@Autowired BookService bookService)
    {
        this.bookService = bookService;
    }

    @PostMapping
    public Booking create (@RequestBody BookingDto bookingDto)
    {
        Optional<Booking> optionalBooking = bookService.findByEmail(bookingDto.getEmail());
        if (!optionalBooking.isPresent()) {
            return bookService.create(new Booking(bookingDto));
        } else {
            throw new BookingWithEmailAlreadyException();
        }
    }
    @GetMapping("/{id}")
    @RolesAllowed("ADMIN")
    public Booking findById(@PathVariable String id){
        Optional<Booking> optionalBooking = bookService.findById(id);
        if(optionalBooking.isPresent())
        {
            return optionalBooking.get();
        }
        else {
            throw new BookingNotFoundException();
        }
    }
    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public Booking update(@PathVariable String id, @RequestBody BookingDto bookingDto)
    {
        Optional<Booking> booking = bookService.findById(id);
        if(booking.isPresent()){
            return bookService.update(booking.get(), bookingDto );
        }
        else{
            throw new BookingNotFoundException();
        }
    }

    @RolesAllowed("ADMIN")
   @DeleteMapping("/{id}")

    public boolean delete(@PathVariable String id){

        boolean delete = bookService.delete(id);
        if(delete){
            return true;
        }
        else{
            throw new BookingNotFoundException();
        }

    }

}
