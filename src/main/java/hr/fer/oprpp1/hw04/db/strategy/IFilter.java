package hr.fer.oprpp1.hw04.db.strategy;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

/**
 * The Interface IFilter.
 */
public interface IFilter {
	
	/**
	 * Accepts.
	 *
	 * @param record the record
	 * @return true, if successful
	 */
	public boolean accepts(StudentRecord record);

}