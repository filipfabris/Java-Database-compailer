package hr.fer.oprpp1.hw04.db.main;

import java.util.*;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

/**
 * The Class dbOutputFormat is used to format output data from database.
 */
public class dbOutputFormat {
	
	/** The data. */
	private List<StudentRecord> data;
	
	/** The sb. */
	private StringBuilder sb;
	
	/** The name length. */
	private int nameLength;
	
	/** The surname length. */
	private int surnameLength;
	
	/** The jmbag length. */
	private int jmbagLength;
	
	/** The final grade lengh. */
	private int finalGradeLengh;
	
	/**
	 * Instantiates a new db output format.
	 *
	 * @param data the data
	 */
	dbOutputFormat(List<StudentRecord> data){
		this.data = data;
		this.sb = new StringBuilder();
		this.format();
	}
	
	/**
	 * Formated output.
	 *
	 * @return the string
	 */
	public String formatedOutput() {
		return sb.toString();
	}
	
	//Algoritam
	//1. Pronadji najduze prezime i ime
	/**
	 * Format.
	 */
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
	
	/**
	 * Table size.
	 */
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
	
	/**
	 * Start end table.
	 */
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
	
	/**
	 * Element table.
	 *
	 * @param element the element
	 */
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
	
	/**
	 * Adds the spaces.
	 *
	 * @param element the element
	 * @param currentMax the current max
	 */
	private void addSpaces(String element, int currentMax) {
		for(int i = 0; i<currentMax-element.length(); i++) {
			sb.append(" ");
		}
	}
	
	

	

}
