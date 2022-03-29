package com.booking.reservas.model;

import com.booking.reservas.controller.BookingDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document
public class Booking {

    @Id
    private  String id;
    private  String name;
    @Indexed(unique = true)
    private String email;
    private long phone;
    private Date date; //Fecha y hora
    List<RoleEnum> roles;



    String passwordHash;

    public Booking() {
    }

    public Booking(String id, String name, String email, long phone, Date date) {


        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }

  public Booking(BookingDto bookingDto)
    {
      this( null, bookingDto.getName(), bookingDto.getEmail(), bookingDto.getPhone(), bookingDto.getDate());
        roles = new ArrayList<>( Collections.singleton( RoleEnum.USER ) );
        //TODO uncomment this line
        passwordHash = BCrypt.hashpw( bookingDto.getPassword(), BCrypt.gensalt() );
    }
 /* public Booking( BookingDto bookingDto )
  {
      name = bookingDto.getName();

      email = bookingDto.getEmail();

      roles = new ArrayList<>( Collections.singleton( RoleEnum.USER ) );
      //TODO uncomment this line
      passwordHash = BCrypt.hashpw( bookingDto.getPassword(), BCrypt.gensalt() );
  }
*/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

     public void setEmail(String email) {
        this.email = email;
    }


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void update( BookingDto bookingDto )
    {
        this.name = bookingDto.getName();
        this.email = bookingDto.getEmail();
        //TODO uncomment these lines
        if ( bookingDto.getPassword() != null )
        {
            this.passwordHash = BCrypt.hashpw( bookingDto.getPassword(), BCrypt.gensalt() );
        }
    }


}
