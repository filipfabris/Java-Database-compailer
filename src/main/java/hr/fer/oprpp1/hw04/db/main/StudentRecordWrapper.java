package hr.fer.oprpp1.hw04.db.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public class StudentRecordWrapper {
	List<StudentRecord> students;
	boolean statictics;
	double avrageGrade;
	Map<Integer, Integer> grades;
	
	public StudentRecordWrapper() {
		this.students = new ArrayList<>();
		this.statictics = false;
		this.avrageGrade = 0;
		this.grades = new HashMap<>();
		
		this.grades.put(1, 0);
		this.grades.put(2, 0);
		this.grades.put(3, 0);
		this.grades.put(4, 0);
		this.grades.put(5, 0);
	}
	
	

}
