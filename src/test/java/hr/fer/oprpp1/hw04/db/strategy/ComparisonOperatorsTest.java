package hr.fer.oprpp1.hw04.db.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComparisonOperatorsTest {
	
    private IComparisonOperator oper;

	@Test
	void LESStest() {
		oper = ComparisonOperators.LESS;
		assertEquals(true, oper.satisfied("Ana", "Jasna"));
	}
	
	@Test
	void LESS2() {
		oper = ComparisonOperators.LESS;
		assertEquals(true, oper.satisfied("1", "2"));
	}
	
	@Test
	void LIKE1test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(false, oper.satisfied("Zagreb", "Aba*"));
	}
	
	@Test
	void LIKE2test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(false, oper.satisfied("AAA", "AA*AA"));
	}
	
	@Test
	void LIKE3test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(true, oper.satisfied("AAAA", "AA*AA"));
	}
	
	@Test
	void LIKE4test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(true, oper.satisfied("Auto", "*uto"));
	}
	
	@Test
	void LIKE5test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(false, oper.satisfied("TENK99Tenk", "TENK"));
	}
	
	@Test
	void LIKE6test() {
		oper = ComparisonOperators.LIKE;
		assertEquals(true, oper.satisfied("TENK99Tenk", "TENK*"));
	}
	
	@Test
	void LIKE7test() {
		oper = ComparisonOperators.LIKE;
		assertThrows(RuntimeException.class, () -> oper.satisfied("TENK99Tenk", "TEN*K*"));
	}
	

}
