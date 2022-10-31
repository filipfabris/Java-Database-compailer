package hr.fer.oprpp1.hw04.db.strategy;

public class ConditionalExpression {
	
	private IFieldValueGetter fieldValueGetter;
	private IComparisonOperator comparisonOperator;
	private String valueComparison;
	
	public ConditionalExpression(IFieldValueGetter fieldValueGetter, String valueComparison,
			IComparisonOperator comparisonOperator) {
		super();
		this.fieldValueGetter = fieldValueGetter;
		this.valueComparison = valueComparison;
		this.comparisonOperator = comparisonOperator;
	}

	public IFieldValueGetter getFieldValueGetter() {
		return fieldValueGetter;
	}

	public String getValueComparison() {
		return valueComparison;
	}

	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	@Override
	public String toString() {
		return "fieldValueGetter: " + fieldValueGetter + ", comparisonOperator: " + comparisonOperator + ", valueComparison: " + valueComparison;
	}
	
	
	
	
	
	

}
