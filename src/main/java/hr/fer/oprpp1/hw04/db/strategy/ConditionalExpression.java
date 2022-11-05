package hr.fer.oprpp1.hw04.db.strategy;

/**
 * The Class ConditionalExpression.
 */
public class ConditionalExpression {
	
	/** The field value getter. */
	private IFieldValueGetter fieldValueGetter;
	
	/** The comparison operator. */
	private IComparisonOperator comparisonOperator;
	
	/** The value comparison. */
	private String valueComparison;
	
	/**
	 * Instantiates a new conditional expression.
	 *
	 * @param fieldValueGetter the field value getter
	 * @param valueComparison the value comparison
	 * @param comparisonOperator the comparison operator
	 */
	public ConditionalExpression(IFieldValueGetter fieldValueGetter, String valueComparison,
			IComparisonOperator comparisonOperator) {
		super();
		this.fieldValueGetter = fieldValueGetter;
		this.valueComparison = valueComparison;
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * Gets the field value getter.
	 *
	 * @return the field value getter
	 */
	public IFieldValueGetter getFieldValueGetter() {
		return fieldValueGetter;
	}

	/**
	 * Gets the value comparison.
	 *
	 * @return the value comparison
	 */
	public String getValueComparison() {
		return valueComparison;
	}

	/**
	 * Gets the comparison operator.
	 *
	 * @return the comparison operator
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "fieldValueGetter: " + fieldValueGetter + ", comparisonOperator: " + comparisonOperator + ", valueComparison: " + valueComparison;
	}
	
	
	
	
	
	

}
