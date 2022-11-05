package hr.fer.oprpp1.hw04.db.strategy;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

/**
 * The Interface IFieldValueGetter.
 */
public interface IFieldValueGetter {
	
	/**
	 * Gets the specific column from studentRecord
	 *
	 * @param record the record
	 * @return the string
	 */
	public String get(StudentRecord record);

}
