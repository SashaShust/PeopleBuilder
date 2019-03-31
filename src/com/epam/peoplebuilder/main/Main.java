package com.epam.peoplebuilder.main;

import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Utils utils = new Utils();
		// utils.peopleMap = createTestMap();
		// Map<String, People> peopleMap = createTestMap();

		utils.managePeopleFromCommandLine();
		Map<String, People> peopleMap = utils.getPeopleMap();
		printValues(peopleMap);
	}

	// printValues(peopleMap);
	/*
	 * try { utils.generateFile(peopleMap); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 */

	public static void printValues(Map<String, People> peopleMap) {
		for (Map.Entry<String, People> pair : peopleMap.entrySet()) {
			People value = pair.getValue();
			System.out.println(value);
		}
	}

}
