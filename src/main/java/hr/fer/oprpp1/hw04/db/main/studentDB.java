package hr.fer.oprpp1.hw04.db.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.oprpp1.hw04.db.model.StudentDatabase;
import hr.fer.oprpp1.hw04.db.model.StudentRecord;
import hr.fer.oprpp1.hw04.db.parser.QueryParser;
import hr.fer.oprpp1.hw04.db.strategy.ConditionalExpression;
import hr.fer.oprpp1.hw04.db.strategy.QueryFilter;

public class studentDB {

	public static void main(String[] args) throws InterruptedException {

		List<String> rows = null;

		try {

			URL url = studentDB.class.getClassLoader().getResource("database.txt");
			URI uri = url.toURI();

			Path path = Path.of(uri);
			rows = Files.readAllLines(path, StandardCharsets.UTF_8);

		} catch (IOException e) {
			System.out.println(e.getMessage());

		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}

		StudentDatabase db = new StudentDatabase(rows);
		PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
		Scanner sc = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

		while (true) {
			try {
				Thread.sleep(100);
				System.out.print("> ");
				String input = sc.nextLine().trim();

				if (input.equalsIgnoreCase("exit")) {
					System.out.println("Goodbye!");
					break;
				}

				if (input.isBlank()) {
					continue;
				}

				List<StudentRecord> queryOutput = dbQuery(db, input);

				dbOutputFormat dbOutObject = new dbOutputFormat(queryOutput);

				out.println(dbOutObject.formatedOutput());
				
			} catch (RuntimeException e) {
				//Bolje isto stavit na .out jer su drugaciji thredovi pa se ispis raspadne za novi upis, stavio sam zato Tread sleep
				System.err.print("Query error\n");
				System.err.println(e.getMessage());
			}
		}

		sc.close();

	}

	private static List<StudentRecord> dbQuery(StudentDatabase db, String query) {

		QueryParser parser = new QueryParser(query);
		List<StudentRecord> queryOutput = new ArrayList<>();

		if (parser.isDirectQuery()) {
			String jmbag = parser.getQueriedJMBAG();
			StudentRecord student = db.forJMBAG(jmbag);
			if (student != null) {
				queryOutput.add(student); // Da ne bi null dodao
			}
		} else {
			List<ConditionalExpression> expressions = parser.getQuery();
			QueryFilter filter = new QueryFilter(expressions);
			queryOutput = db.filter(filter);
		}

		return queryOutput;
	}

}
