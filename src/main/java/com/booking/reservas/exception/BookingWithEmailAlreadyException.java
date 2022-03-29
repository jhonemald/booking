package com.booking.reservas.exception;

import com.booking.reservas.error.ErrorCodeEnum;
import com.booking.reservas.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class BookingWithEmailAlreadyException extends InternalServerErrorException
{
    public BookingWithEmailAlreadyException()
    {
        super( new ServerErrorResponseDto( "Booking  email already registered", ErrorCodeEnum. BOOKING_WITH_EMAIL_ALREADY_EXISTS, HttpStatus.  BAD_REQUEST ),
                HttpStatus.  BAD_REQUEST);
    }
}

