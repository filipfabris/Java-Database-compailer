package hr.fer.oprpp1.hw04.db.strategy;

import java.util.List;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public class QueryFilter implements IFilter {

	private List<ConditionalExpression> expressions;

	public QueryFilter(List<ConditionalExpression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public boolean accepts(StudentRecord record) {
		if (record == null) {
			throw new RuntimeException("record is null");
		}

		for (ConditionalExpression expression : this.expressions) {
			
			IComparisonOperator compareOperator = expression.getComparisonOperator(); // equals, <, > itd.
			IFieldValueGetter fieldGetter = expression.getFieldValueGetter(); // Koji field se usporeduje, iz baze redak
			String value = expression.getValueComparison(); // vrijednost s kojom usporedujemo

			if (compareOperator.satisfied(fieldGetter.get(record), value) == false) {
				return false; //Ako jedan expression ne odgovara varti false
			}

		}
		return true;
	}

}
