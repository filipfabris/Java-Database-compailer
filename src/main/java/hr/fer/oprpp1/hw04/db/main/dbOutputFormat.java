package hr.fer.oprpp1.hw04.db.main;

import java.util.*;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public class dbOutputFormat {
	
	private List<StudentRecord> data;
	private StringBuilder sb;
	
	private int nameLength;
	private int surnameLength;
	private int jmbagLength;
	private int finalGradeLengh;
	
	dbOutputFormat(List<StudentRecord> data){
		this.data = data;
		this.sb = new StringBuilder();
		this.format();
	}
	
	public String formatedOutput() {
		return sb.toString();
	}
	
	//Algoritam
	//1. Pronadji najduze prezime i ime
	//2. Na temelju toga odlucit cemo koliko ce biti veliko polje
	private void format() {
		if(data == null || data.size() == 0) {
			sb.append("Records selected: 0");
		}else {
			this.tableSize();
			this.startEndTable();
			
			for(StudentRecord element: data) {
				this.elementTable(element);
			}
			
			this.startEndTable();
			sb.append("Records selected: ");
			sb.append(data.size());
			sb.append("\n");
		}
		return;
	}
	
	private void tableSize() {
		StudentRecord student = data.get(0);
		this.jmbagLength = student.getJmbag().length();
		this.finalGradeLengh = String.valueOf(student.getFinalGrade()).length();
		this.nameLength = student.getFirstName().length();
		this.surnameLength = student.getLastName().length();
		
		for(StudentRecord element: data) {
			if(this.nameLength < element.getFirstName().length()) {
				this.nameLength = element.getFirstName().length();
			}
			if(this.surnameLength < element.getLastName().length()) {
				this.surnameLength = element.getLastName().length();
			}
		}
	}
	
	private void startEndTable() {
		sb.append("+");
		for(int i = 0; i<this.jmbagLength+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		for(int i = 0; i<this.nameLength+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		for(int i = 0; i<this.surnameLength+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		for(int i = 0; i<this.finalGradeLengh+2; i++) {
			sb.append("=");
		}
		sb.append("+");
		
		sb.append("\n");
	}
	
	private void elementTable(StudentRecord element) {
		
		sb.append("|");
		sb.append(" ");
		sb.append(element.getJmbag());
		this.addSpaces(element.getJmbag(), this.jmbagLength);
		sb.append(" ");
		sb.append("|");
		
		sb.append(" ");
		sb.append(element.getFirstName());
		this.addSpaces(element.getFirstName(), this.nameLength);
		sb.append(" ");
		sb.append("|");
		
		sb.append(" ");
		sb.append(element.getLastName());
		this.addSpaces(element.getLastName(), this.surnameLength);
		sb.append(" ");
		sb.append("|");
		
		sb.append(" ");
		sb.append(element.getFinalGrade());
		this.addSpaces(String.valueOf(element.getFinalGrade()), this.finalGradeLengh);
		sb.append(" ");
		sb.append("|");

		sb.append("\n");
	}
	
	private void addSpaces(String element, int currentMax) {
		for(int i = 0; i<currentMax-element.length(); i++) {
			sb.append(" ");
		}
	}
	
	

	

}
