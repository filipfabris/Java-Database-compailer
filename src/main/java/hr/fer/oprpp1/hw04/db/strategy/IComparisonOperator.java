package hr.fer.oprpp1.hw04.db.strategy;

/**
 * The Interface IComparisonOperator.
 */
public interface IComparisonOperator {

	/**
	 * Satisfied.
	 *
	 * @param value1 the value 1
	 * @param value2 the value 2
	 * @return true, if successful
	 */
	public boolean satisfied(String value1, String value2);
}
