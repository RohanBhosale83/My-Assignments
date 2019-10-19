package com.capgemini.service;

import com.capgemini.model.Person;

public interface IPersonService {

	public void updatePerson(int pid, Person person);

	public void findPerson(Person person);

	void addNewPerson(Person person);

	void printAllPerson();

	void deletePerson(Person person);

	void removePerson(int index);

}
