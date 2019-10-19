package com.capgemini.main;

import java.util.Scanner;

import com.capgemini.model.Person;
import com.capgemini.service.PersonServiceImpl;

public class UI {

	static PersonServiceImpl personService = new PersonServiceImpl();
	public static Scanner sc = new Scanner(System.in);
	public static int pid = 0;
	public static String pName = "";
	public static int pAge = 0;

	public static void main(String[] args) {

		System.out.println("Please enter your choice:: " + "\n 1.Print Person" + "\n 2.Add Person"
				+ "\n 3.Update Person" + "\n 4.Search Person" + "\n 5.Remove Person");

		while (true) {
			System.out.println("Please enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				printPerson();
				break;
			case 2:
				addPerson();
				break;
			case 3:
				modifyPerson();
				break;
			case 4:
				searchPerson();
				break;
			case 5:
				removePerson();
				break;
			default:
				System.out.println("You have selected a wrong choice !!!");
				break;
			}

		}

	}

	private static void printPerson() {
		personService.printAllPerson();
	}

	private static void addPerson() {
		Person person = new Person();
		System.out.println("Please enter person details");
		pid = sc.nextInt();
		pName = sc.next();
		pAge = sc.nextInt();
		person.setpId(pid);
		person.setpName(pName);
		person.setpAge(pAge);

		personService.addNewPerson(person);
		System.out.println("Person added successfully");
		printPerson();

	}

	private static void modifyPerson() {
		System.out.println("Please enter number to update");
		pid = sc.nextInt();
		Person person = new Person();
		System.out.println("Enter details to be updated");
		pid = sc.nextInt();
		pName = sc.next();
		pAge = sc.nextInt();
		person.setpId(pid);
		person.setpName(pName);
		person.setpAge(pAge);
		personService.updatePerson(pid,person);
		System.out.println("Person updated successfully");
		printPerson();
		

	}

	private static void searchPerson() {
		Person person = new Person();
		System.out.println("Please enter person details");
		pid = sc.nextInt();
		pName = sc.next();
		pAge = sc.nextInt();
		person.setpId(pid);
		person.setpName(pName);
		person.setpAge(pAge);
		personService.findPerson(person);
	}

	private static void removePerson() {
		System.out.println("Please enter number to remove");
		pid = sc.nextInt();
		personService.removePerson(pid);
		System.out.println("Person removed successfully");
		printPerson();
		
	}

}
