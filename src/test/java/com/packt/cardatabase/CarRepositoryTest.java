package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest

public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRepository repository;

    //Test Cases
    @Test
    public void saveCar() {
        Owner owner1 = new Owner("Maxim","Gorky");
        entityManager.persistAndFlush(owner1);
        Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, owner1);
        entityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCars() {
       entityManager.persistAndFlush(new Car("Tesla","Model X", "White", "ABC-1234", 2017, 86000, null));
       entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007",2015,24500,null));
       repository.deleteAll();
       assertThat(repository.findAll()).isNull();

    }
}
