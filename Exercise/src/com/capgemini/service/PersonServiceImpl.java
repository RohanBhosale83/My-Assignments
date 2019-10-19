package com.capgemini.service;

import java.util.ArrayList;

import com.capgemini.model.Person;

public class PersonServiceImpl implements IPersonService {

	ArrayList<Person> personList = new ArrayList<Person>();

	@Override
	public void printAllPerson() {
		if (!personList.isEmpty()) {
			for (int i = 0; i < personList.size(); i++) {
				if (personList.size() > 0) {
					System.out.println("Person " + (i + 1) + "-> " + personList.get(i));
				}

			}
		} else {
			System.out.println("Person list is empty !!!");
		}

	}

	@Override
	public void addNewPerson(Person person) {
		personList.add(person);

	}

	@Override
	public void updatePerson(int pid, Person person) {
		removePerson(pid);
		addNewPerson(person);
	}

	@Override
	public void findPerson(Person person) {

		personList.forEach(x -> {
			if (person.getpId() == x.getpId() && person.getpName().equals(x.getpName()) && person.getpAge() == x.getpAge()) {
				System.out.println("Person:: " + person);
			} else {
				System.out.println("Person not found !!!");
			}
		});

		/*
		 * for (Person pers : personList) { if (pers.equals(person)) {
		 * System.out.println("Person:: " + person); } else {
		 * System.out.println("Person not found !!!"); } }
		 */

	}

	@Override
	public void deletePerson(Person person) {
		personList.remove(person);
		printAllPerson();
	}

	@Override
	public void removePerson(int index) {
		Person person = personList.get(index - 1);
		personList.remove(person);

	}

}
