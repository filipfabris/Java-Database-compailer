package hr.fer.oprpp1.hw04.db.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

class QueryFilterTest {
	
	private List<ConditionalExpression> expr;
	private QueryFilter queryFilter;
	
    @BeforeEach
    void Init() {
    	expr = new ArrayList<>();
        this.expr.add(new ConditionalExpression(FieldValueGetters.JMBAG, "0000000003", ComparisonOperators.LESS_OR_EQUALS));
	}
    
    
	@Test
	void Nulltest() {
		assertThrows(NullPointerException.class, ()-> queryFilter = new QueryFilter(null));
	}
	
	@Test
	void Jmbagtest() {
		queryFilter = new QueryFilter(expr);
		
		assertEquals(true, queryFilter.accepts(new StudentRecord("0000000001", "Marin", "Akšamović", 3)));
		assertEquals(true, queryFilter.accepts(new StudentRecord("0000000002", "Petra", "Bakamović", 4)));
		assertEquals(true, queryFilter.accepts(new StudentRecord("0000000003", "Andrea", "Bosnić", 4)));
		assertEquals(false, queryFilter.accepts(new StudentRecord("0000000004", "Marin", "Božić", 4)));
	}
	
	@Test
	void JmbagMultipletest() {
        this.expr.add(new ConditionalExpression(FieldValueGetters.FIRST_NAME, "M*", ComparisonOperators.LIKE));
		queryFilter = new QueryFilter(expr);
		
		assertEquals(true, queryFilter.accepts(new StudentRecord("0000000001", "Marin", "Akšamović", 3)));
		assertEquals(false, queryFilter.accepts(new StudentRecord("0000000002", "Petra", "Bakamović", 4)));
		assertEquals(false, queryFilter.accepts(new StudentRecord("0000000003", "Andrea", "Bosnić", 4)));
		assertEquals(false, queryFilter.accepts(new StudentRecord("0000000004", "Marin", "Božić", 4)));
	}

}
