package com.epam.peoplebuilder.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {
	private static final String SAMPLE_CSV_FILE_PATH = "C:\\Test\\people\\users-with-header.csv";

	public Map<String, People> CSVRead() throws IOException {
		Map<String, People> peopleMap = new HashMap<String, People>();
		File file = new File(SAMPLE_CSV_FILE_PATH);

		if (file.isFile()) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
						.withIgnoreHeaderCase().withTrim());

				for (CSVRecord csvRecord : csvParser) {
					// Accessing values by Header names
					String id = csvRecord.get("id");
					String surname = csvRecord.get("Surname");
					String name = csvRecord.get("Name");
					String phoneString = csvRecord.get("Phone");
					int phone = Integer.valueOf(phoneString);
					People people = new People(id, surname, name, phone);
					peopleMap.put(id, people);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return peopleMap;
	}

	public void CSVWrite(Map<String, People> peopleMap) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Surname", "Name",
					"Phone"));

			for (Map.Entry<String, People> entry : peopleMap.entrySet()) {
				String id = entry.getKey();
				People people = entry.getValue();
				csvPrinter.printRecord(id, people.getSurname(), people.getName(), people.getPhone());
				csvPrinter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
