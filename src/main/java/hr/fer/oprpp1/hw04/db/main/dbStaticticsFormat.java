package hr.fer.oprpp1.hw04.db.main;

import java.security.KeyStore.Entry;
import java.util.Map;

import javax.xml.crypto.Data;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public class dbStaticticsFormat implements Format{
	
	private StringBuilder sb;
	
	private StudentRecordWrapper scw;
	
	private int grade;
	
	private int gradeCount;
	
	public dbStaticticsFormat(StudentRecordWrapper scw) {
		this.scw = scw; 
		this.sb = new StringBuilder();
		this.format();
	}

	private void format() {
		if(scw.students == null || scw.students.size() == 0) {
			sb.append("Records selected: 0");
		}else {
			sb.append("Avrage grade: " + scw.avrageGrade + "\n");
			this.tableSize();
			this.startEndTable();
			
			for(Map.Entry<Integer, Integer> element: scw.grades.entrySet()) {
				this.elementTable(element);
			}
			
			this.startEndTable();
			sb.append("Records selected: ");
			sb.append(scw.students.size());
			sb.append("\n");
		}
		return;
	}
	
	private void tableSize() {
		StudentRecord student = scw.students.get(0);
		this.grade = 1;
		this.gradeCount = scw.grades.get(1);
		
		for(Integer element: scw.grades.values()) {
			if(this.gradeCount < element) {
				this.gradeCount = element;
			}
		}
	}
	
	private void startEndTable() {
		sb.append("+");
		for(int i = 0; i<grade+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		for(int i = 0; i<this.gradeCount+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		
		sb.append("\n");
	}
	
	private void elementTable(Map.Entry<Integer, Integer> el) {
		
		sb.append("|");
		sb.append(" ");
		sb.append(el.getKey());
		this.addSpaces(el.getKey().toString(), this.grade);
		sb.append(" ");
		sb.append("|");
		
		sb.append(" ");
		sb.append(el.getValue());
		this.addSpaces(el.getValue().toString(), this.gradeCount);
		sb.append(" ");
		sb.append("|");
		

		sb.append("\n");
	}
	
	private void addSpaces(String element, int currentMax) {
		for(int i = 0; i<currentMax-element.length(); i++) {
			sb.append(" ");
		}
	}
	
	public String formatedOutput() {
		return sb.toString();
	}
	
	


}
