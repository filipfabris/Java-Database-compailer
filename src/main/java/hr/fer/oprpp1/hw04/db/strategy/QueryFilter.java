package hr.fer.oprpp1.hw04.db.strategy;

import java.util.List;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public class QueryFilter implements IFilter{
	
	List<ConditionalExpression> expressions;
	
	public QueryFilter(List<ConditionalExpression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public boolean accepts(StudentRecord record) {
		if(record == null) {
			throw new RuntimeException("record is null");
		}
		
		 for (ConditionalExpression expression: this.expressions) {
	            if (!expression.getComparisonOperator().satisfied(expression.getFieldValueGetter().get(record), expression.getValueComparison())) {
	                return false;
	            }
	        }
	        return true;
	}

}
