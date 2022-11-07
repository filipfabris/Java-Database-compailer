package hr.fer.oprpp1.hw04.db.main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hr.fer.oprpp1.hw04.db.model.StudentDatabase;
import hr.fer.oprpp1.hw04.db.model.StudentRecord;
import hr.fer.oprpp1.hw04.db.strategy.ComparisonOperators;
import hr.fer.oprpp1.hw04.db.strategy.ConditionalExpression;
import hr.fer.oprpp1.hw04.db.strategy.FieldValueGetters;
import hr.fer.oprpp1.hw04.db.strategy.QueryFilter;

class studentDBTest {
	
	private static List<String> rows;
	private StudentDatabase db;
	
	@BeforeAll
	static void getFile() {

		try {
			
			URL url = StudentDatabase.class.getClassLoader().getResource("database.txt");
			URI uri = url.toURI();

			Path path = Path.of(uri);
			rows = Files.readAllLines(path, StandardCharsets.UTF_8);

		} catch (IOException e) {
			System.out.println(e.getMessage());

		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void NullTest() {
		assertThrows(RuntimeException.class, () -> db = new StudentDatabase(null));
	}
	
	@Test
	void InitTest() {
		db = new StudentDatabase(rows);
		StudentRecord record = db.forJMBAG("0000000012");
		
		assertEquals("0000000012", record.getJmbag());
		assertEquals("Hrvoje", record.getFirstName());
		assertEquals("Franković", record.getLastName());
		assertEquals(5, record.getFinalGrade());
	}
	
	@Test
	void FilterTest() {
		db = new StudentDatabase(rows);
		
		ConditionalExpression expr;
		expr = new ConditionalExpression(FieldValueGetters.JMBAG, "0000000003", ComparisonOperators.LESS_OR_EQUALS);

		QueryFilter filter = new QueryFilter(List.of(expr));
		
		List<StudentRecord> ouput = db.filter(filter);
		
		assertEquals(3, ouput.size());
		assertEquals("Bosnić", ouput.get(2).getLastName());
	}

}
