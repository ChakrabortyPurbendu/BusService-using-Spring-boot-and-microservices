package com.example.BusService.Controller;


import com.example.BusService.entity.Booking;
import com.example.BusService.repository.BookingRepository;
import com.example.BusService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking getBooking(@RequestBody Booking booking)
    {

        return bookingService.bookTicket(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable long id)
    {
        return bookingService.getBookingById(id);

    }

    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable long id)
    {
        bookingService.cancelBooking(id);
        return "Booking deleted Successfully";

    }
}
