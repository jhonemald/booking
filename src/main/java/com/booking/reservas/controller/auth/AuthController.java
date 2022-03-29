package com.booking.reservas.controller.auth;

import com.booking.reservas.booking.BookService;
import com.booking.reservas.exception.InvalidCredentialsException;
import com.booking.reservas.model.Booking;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static com.booking.reservas.config.Constants.CLAIMS_ROLES_KEY;
import static com.booking.reservas.config.Constants.TOKEN_DURATION_MINUTES;

@RestController
@RequestMapping( "v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    private final BookService bookService;

    public AuthController( @Autowired BookService bookService )
    {
        this.bookService = bookService;
    }

    @PostMapping
    public TokenDto login( @RequestBody LoginDto loginDto )
    {
       Optional <Booking> booking = bookService.findByEmail( loginDto.email );
        if ( BCrypt.checkpw( loginDto.getPassword(), booking.get().getPasswordHash() ) )
        {
            return generateTokenDto( booking.get() );
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( Booking booking, Date expirationDate )
    {
        return Jwts.builder()
                .setSubject( booking.getId() )
                .claim( CLAIMS_ROLES_KEY, booking.getRoles() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto( Booking booking )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( booking, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}