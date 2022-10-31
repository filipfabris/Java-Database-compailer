package hr.fer.oprpp1.hw04.db.model;

import java.util.*;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;
import hr.fer.oprpp1.hw04.db.strategy.IFilter;

public class StudentDatabase {

	List<StudentRecord> records;
	Map<String, StudentRecord> indexRecords;

	public StudentDatabase(List<String> dataInput) {
		if (dataInput == null) {
			throw new RuntimeException("Input is empty");
		}

		this.records = new LinkedList<>();
		this.indexRecords = new HashMap<>();
		this.populateRecords(dataInput);
	}
	
	public StudentRecord forJMBAG(String jmbag) {
		StudentRecord temp = indexRecords.get(jmbag);
		return temp;
	}
	
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> outList = new LinkedList<>();
		
		for(StudentRecord element: records) {
			if(filter.accepts(element)) {
				outList.add(element);
			}
		}
		
		return outList;
	}
	
	
	private void populateRecords(List<String> dataInput) {

		for (String element : dataInput) {

			String[] tmp = element.split("\\t"); // spilit line by tab

			if (tmp.length != 4) {
				throw new IllegalArgumentException("There must be only 4 arguments per row");
			}

			int grade;
			try {
				grade = Integer.parseInt(tmp[3]);
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Typo fo grade input");
			}

			if (grade > 5 || grade < 1) {
				throw new IllegalArgumentException("Grade must be between 1 and 5");
			}

			StudentRecord record = new StudentRecord(tmp[0], tmp[2], tmp[1], grade);
			records.add(record);
			
			if(indexRecords.get(tmp[0]) == null) {
				indexRecords.put(tmp[0], record);
			}else {
				throw new RuntimeException("duplicated jmbags");
			}
		}

	}

}
