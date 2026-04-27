package com.example.BusService.service;


import com.example.BusService.entity.Bus;
import com.example.BusService.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public Bus updateBus(Long id, Bus updatedBus) {
        Bus bus = busRepository.findById(id).orElse(null);

        if (bus != null) {
            bus.setBusName(updatedBus.getBusName());
            bus.setSource(updatedBus.getSource());
            bus.setDestination(updatedBus.getDestination());
            bus.setSeats(updatedBus.getSeats());
            return busRepository.save(bus);
        }

        return null;
    }

    public String deleteBus(Long id) {
        if (busRepository.existsById(id)) {
            busRepository.deleteById(id);
            return "Bus deleted successfully";
        }
        return "Bus not found";
    }



}
