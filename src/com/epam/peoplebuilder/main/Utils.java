package com.epam.peoplebuilder.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {
	private Map<String, People> peopleMap = new HashMap<String, People>();

	public Map<String, People> getPeopleMap() {
		return peopleMap;
	}

	public void managePeopleFromCommandLine() {

		try {
			peopleMap = new CSVHelper().CSVRead();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			System.out.println("Please, enter \"create\", \"read\", \"update\" or \"delete\"");
			Scanner scaner = new Scanner(System.in);
			String input = scaner.nextLine();
			switch (input) {
			case "create":
				System.out.println("Please, enter \"id\", \"surname\", \"name\" and \"phone\"");
				String inputPeople = scaner.nextLine();
				String[] inputArray = inputPeople.split(" ");
				for (int i = 0; i < inputArray.length; i = i + 4) {
					String id = inputArray[i];
					String surname = inputArray[i + 1];
					String name = inputArray[i + 2];
					String phone = inputArray[i + 3];
					int phoneInt = Integer.valueOf(phone);
					People people = new People(id, surname, name, phoneInt);
					peopleMap.put(id, people);
					System.out.println("Please, enter \"save\", or \"exit\"");
				}
				break;
			case "read":
				for (Map.Entry<String, People> pair : peopleMap.entrySet()) {
					People value = pair.getValue();
					System.out.println(value);
				}
				break;

			case "update":
				System.out.println("Please, enter people id for update people");
				String inputPeopleIDForUpdate = scaner.nextLine();
				if (peopleMap.containsKey(inputPeopleIDForUpdate)) {
					System.out.println("Please, enter \"surname\", \"name\" and \"phone\"");
					String inputPeopleForUpdate = scaner.nextLine();
					String[] inputArrayForUpdate = inputPeopleForUpdate.split(" ");
					String phoneForUpdate = inputArrayForUpdate[2];
					int phone = Integer.valueOf(phoneForUpdate);
					People peopleAfterUpdate = new People(inputPeopleIDForUpdate, inputArrayForUpdate[0],
							inputArrayForUpdate[1], phone);
					peopleMap.put(inputPeopleIDForUpdate, peopleAfterUpdate);
					System.out.println("Please, enter \"save\", or \"exit\"");

				} else {
					System.out.println("There is no person with such id, please, try again");
				}
				break;

			case "delete":
				System.out.println("Please, enter people id");
				String peopleId = scaner.next();
				if (peopleMap.containsKey(peopleId)) {
					peopleMap.remove(peopleId);
					System.out.println("The operation was successful");
				} else {
					System.out.println("There is no person with such id, please, try again");
				}
				break;

			case "save":
				new CSVHelper().CSVWrite(peopleMap);
				System.out.println("Saved successful");
				break;

			case "exit":
				System.exit(1);
			default:
				break;
			}
		}
	}

	public void generateFile(Map<String, People> peopleMap) throws IOException {
		File file = new File("C:\\Test\\people\\people.txt");
		if (file.isFile()) {
			System.out.println("File exists");
		} else {

			file.createNewFile();
			System.out.println("File created! ");
			FileWriter writer = new FileWriter(file);
			try {

				for (Map.Entry<String, People> pair : peopleMap.entrySet()) {
					People value = pair.getValue();
					String valueStr = value.toString();
					writer.write(valueStr);
				}
			} finally {
				writer.close();
			}
		}
	}
}