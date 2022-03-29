package com.booking.reservas.exception;

import com.booking.reservas.error.ErrorCodeEnum;
import com.booking.reservas.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class BookingNotFoundException
        extends InternalServerErrorException
{
    public BookingNotFoundException()
    {
        super( new ServerErrorResponseDto( "Booking not found", ErrorCodeEnum.BOOKING_NOT_FOUND, HttpStatus.NOT_FOUND ),
                HttpStatus.NOT_FOUND );
    }
}
