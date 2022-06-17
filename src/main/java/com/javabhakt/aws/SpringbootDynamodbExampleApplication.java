package com.javabhakt.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javabhakt.aws.entity.Person;
import com.javabhakt.aws.repository.PersonRepository;

@SpringBootApplication
@RestController
public class SpringbootDynamodbExampleApplication {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamodbExampleApplication.class, args);
	}

	@PostMapping("/savePerson")
	public Person savePerson(@RequestBody Person person) {
		return repository.addPerson(person);

	}

	@GetMapping("/getPerson/{personId}")
	public Person findPerson(@PathVariable String personId) {
		return repository.findPersonById(personId);

	}

	@DeleteMapping("/deletePerson")
	public String deletePerson(@RequestBody Person person) {
		return repository.deletePerson(person);

	}

	@PutMapping("/updatePerson")
	public String updatePerson(@RequestBody Person person) {
		return repository.editPerson(person);

	}

}
