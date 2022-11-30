package hr.fer.oprpp1.hw04.db.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

class ConditionalExpressionTest {
	
	private StudentRecord record;
	
    private ConditionalExpression expr;
    
    
	@Test
	void SurnameTest() {
		this.record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
		expr = new ConditionalExpression(FieldValueGetters.LAST_NAME, "Fa*", ComparisonOperators.LIKE);
		
		boolean otput = expr.getComparisonOperator().satisfied
				(expr.getFieldValueGetter().get(record), expr.getValueComparison());
		
		assertEquals(true, otput);
	}
	
	@Test
	void FirstnameTest() {
		this.record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
		expr = new ConditionalExpression(FieldValueGetters.FIRST_NAME, "Fi*p", ComparisonOperators.LIKE);
		
		boolean otput = expr.getComparisonOperator().satisfied
				(expr.getFieldValueGetter().get(record), expr.getValueComparison());
		
		assertEquals(true, otput);
	}
	
	@Test
	void JmbagTest() {
		this.record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
		expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0036532834", ComparisonOperators.EQUALS);
		
		boolean otput = expr.getComparisonOperator().satisfied
				(expr.getFieldValueGetter().get(record), expr.getValueComparison());
		
		assertEquals(true, otput);
	}
	
	@Test
	void Throws1Test() {
		this.record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
		expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0036532834", ComparisonOperators.EQUALS);
				
		assertThrows(NullPointerException.class, () -> expr.getComparisonOperator().satisfied
				(expr.getFieldValueGetter().get(record), null));
	}
	
	@Test
	void Throws2Test() {
		this.record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
		expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0036532834", ComparisonOperators.LIKE);
				
		assertThrows(NullPointerException.class, () -> expr.getComparisonOperator().satisfied
				(expr.getFieldValueGetter().get(record), null));
	}

}
