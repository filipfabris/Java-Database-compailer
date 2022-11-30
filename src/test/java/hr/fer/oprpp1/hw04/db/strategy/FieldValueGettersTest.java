package hr.fer.oprpp1.hw04.db.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

class FieldValueGettersTest {

	private StudentRecord record = new StudentRecord("0036532834", "Filip", "Fabris", 4);
	private IFieldValueGetter fieldValueGetter;
	
	@Test
	void NameTest() {
		fieldValueGetter = FieldValueGetters.FIRST_NAME;
		assertEquals(fieldValueGetter.get(record), "Filip");
	}
	
	@Test
	void SurnameTest() {
		fieldValueGetter = FieldValueGetters.LAST_NAME;
		assertEquals(fieldValueGetter.get(record), "Fabris");
	}
	
	@Test
	void JMBAGTest() {
		fieldValueGetter = FieldValueGetters.JMBAG;
		assertEquals(fieldValueGetter.get(record), "0036532834");
	}
	

}
