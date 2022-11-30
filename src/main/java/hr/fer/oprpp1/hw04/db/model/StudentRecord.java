package hr.fer.oprpp1.hw04.db.model;

import java.util.Objects;


/**
 * The Class StudentRecord.
 */
public class StudentRecord {
	
	/** The jmbag. */
	private String jmbag;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The final grade. */
	private int finalGrade;
	
	
	/**
	 * Instantiates a new student record.
	 *
	 * @param jmbag the jmbag
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param finalGrade the final grade
	 */
	public StudentRecord(String jmbag, String firstName, String lastName, int finalGrade) {
		super();
		this.jmbag = jmbag;
		this.firstName = firstName;
		this.lastName = lastName;
		this.finalGrade = finalGrade;
	}


	/**
	 * Gets the jmbag.
	 *
	 * @return the jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}


	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Gets the final grade.
	 *
	 * @return the final grade
	 */
	public int getFinalGrade() {
		return finalGrade;
	}


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(finalGrade, firstName, jmbag, lastName);
	}


	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "jmbag: " + jmbag + ", firstName: " + firstName + ", lastName: "  + lastName + " ,finalGrade: " + finalGrade;
	}
	
	
	
	

}
