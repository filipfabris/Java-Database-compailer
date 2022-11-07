package hr.fer.oprpp1.hw04.db.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QueryParserTest {
	
	private QueryParser queryParser;

	@Test
	void directQueryTest() {
		String input = "query jmbag =\"0123456789\" ";
		queryParser = new QueryParser(input);
		assertEquals(true, queryParser.isDirectQuery());
		assertEquals("0123456789", queryParser.getQueriedJMBAG());
		assertEquals(1, queryParser.getQuery().size());
	}
	
	@Test
	void nonDirectQueryTest() {
		String input = "query jmbag=\"0123456789\" and lastName>\"J\"";
		queryParser = new QueryParser(input);
		
		assertEquals(false, queryParser.isDirectQuery());
		assertThrows(IllegalStateException.class, () -> queryParser.getQueriedJMBAG());
		assertEquals(2, queryParser.getQuery().size());
	}
	
	@Test
	void nonDirectQueryCaseInsensitiveTest() {
		String input = "query jMbaG=\"0123456789\" and lAstName>\"J\"";
		queryParser = new QueryParser(input);
		
		assertEquals(false, queryParser.isDirectQuery());
		assertThrows(IllegalStateException.class, () -> queryParser.getQueriedJMBAG());
		assertEquals(2, queryParser.getQuery().size());
	}
	
	@Test
	void nonDirectQueryCaseInsensitive2Test() {
		String input = "quEry jMbaG=\"0123456789\" aNd lAstName>\"J\"";
		queryParser = new QueryParser(input);
		
		assertEquals(false, queryParser.isDirectQuery());
		assertThrows(IllegalStateException.class, () -> queryParser.getQueriedJMBAG());
		assertEquals(2, queryParser.getQuery().size());
	}
	
	@Test
	void throwsTest() {
		String input = "jMbaG=\"0123456789\" and lAstName>\"J\"";
		assertThrows(RuntimeException.class, () -> queryParser = new QueryParser(input));
	}
	
	@Test
	void unknownTokenTest() {
		String input = "quEry jMbaG=\"0123456789\" or lAstName>\"J\"";
		assertThrows(RuntimeException.class, () -> queryParser = new QueryParser(input));
	}
	
	

}
