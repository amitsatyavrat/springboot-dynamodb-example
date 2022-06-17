package com.javabhakt.aws.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.javabhakt.aws.entity.Person;

@Repository
public class PersonRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public Person addPerson(Person person) {
		mapper.save(person);
		return person;
	}

	public Person findPersonById(String personId) {
		return mapper.load(Person.class, personId);

	}

	public String deletePerson(Person person) {
		mapper.delete(person);
		return "Person deleted !!";
	}

	public String editPerson(Person person) {
		mapper.save(person, buidExpression(person));
		return "Person edited !!";

	}

	private DynamoDBSaveExpression buidExpression(Person person) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		return dynamoDBSaveExpression;
	}
}
