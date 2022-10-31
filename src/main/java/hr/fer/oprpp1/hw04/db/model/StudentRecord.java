package hr.fer.oprpp1.hw04.db.model;

import java.util.Objects;

public class StudentRecord {
	
	private String jmbag;
	private String firstName;
	private String lastName;
	private int finalGrade;
	
	
	public StudentRecord(String jmbag, String firstName, String lastName, int finalGrade) {
		super();
		this.jmbag = jmbag;
		this.firstName = firstName;
		this.lastName = lastName;
		this.finalGrade = finalGrade;
	}


	public String getJmbag() {
		return jmbag;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public int getFinalGrade() {
		return finalGrade;
	}


	@Override
	public int hashCode() {
		return Objects.hash(finalGrade, firstName, jmbag, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		return finalGrade == other.finalGrade && Objects.equals(firstName, other.firstName)
				&& Objects.equals(jmbag, other.jmbag) && Objects.equals(lastName, other.lastName);
	}


	@Override
	public String toString() {
		return "jmbag: " + jmbag + ", firstName: " + firstName + ", lastName: "  + lastName + " ,finalGrade: " + finalGrade;
	}
	
	
	
	

}
