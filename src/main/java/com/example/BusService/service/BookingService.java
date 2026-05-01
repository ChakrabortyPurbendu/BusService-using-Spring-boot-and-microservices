package com.example.BusService.service;


import com.example.BusService.entity.Booking;
import com.example.BusService.entity.Bus;
import com.example.BusService.repository.BookingRepository;
import com.example.BusService.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository  busRepository;

    public Booking bookTicket(Booking booking)
    {
         Bus bus = busRepository.findById(booking.getBusId())
                 .orElseThrow(()-> new RuntimeException("Bus not found"));

         if(bus.getSeats() < booking.getSeatsBooked())
         {
             throw new RuntimeException("not enough seats available");

         }
         bus.setSeats(bus.getSeats()-booking.getSeatsBooked());

         busRepository.save(bus);

         return bookingRepository.save(booking);


    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public String cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Bus bus = busRepository.findById(booking.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Restore seats
        bus.setSeats(bus.getSeats() + booking.getSeatsBooked());
        busRepository.save(bus);

        bookingRepository.deleteById(id);

        return "Booking cancelled successfully";
    }

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();


    }
}
