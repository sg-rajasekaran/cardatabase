package com.packt.cardatabase.web;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarRepository repository;
    @RequestMapping(value="/cars",method=RequestMethod.GET)
    public Iterable<Car> getCars() {
        return repository.findAll();
    }

    @RequestMapping(value="/cars",method=RequestMethod.POST)
    public Car createCar(@RequestBody Car car) {
        return repository.save(car);
    }
}
