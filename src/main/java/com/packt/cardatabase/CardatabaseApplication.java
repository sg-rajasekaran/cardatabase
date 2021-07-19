package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication extends SpringBootServletInitializer {
	@Autowired
	private CarRepository repository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository urepository;

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CardatabaseApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello SpringBoot");
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Add owner objects and save these to db
			Owner owner1 = new Owner("John","Johnson");
			Owner owner2 = new Owner("Mary","Robinson");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);
			//Add car object with link to owners and save these to db
			logger.info("Owners Added");
			Car car1 = new Car("Ford","Mustang","Red","ADF-1121",2017,59000,owner1);
			Car car2 = new Car("Nissan","Leaf","White","SSJ-3002",2014,29000,owner2);
			Car car3 = new Car("Toyota","Prius","Silver","KKO-0212",2018,39000,owner1);
			repository.save(car1);
			repository.save(car2);
			repository.save(car3);

			logger.info("Cars Added");
			/*owner1.getCars().add(car1);
			owner1.getCars().add(car2);
			owner2.getCars().add(car3);
			owner1.getCars().add(car3);
			owner2.getCars().add(car2);
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);
			logger.info("Related Owners and Cars");*/
			//username: user password:user
			urepository.save(new User("user","$2a$10$8ncI0C/PpFSh5RhM2m96NONWP1m36ujTDWp2txbymk0txwCKNInMS","USER"));
			urepository.save(new User("admin","$2a$10$ZMpFcMWcNueVgkwqQDyaE.1PoVq2NvSfEXlYUNwDtx2kKCaNygceW","ADMIN"));
		};
	}

}
