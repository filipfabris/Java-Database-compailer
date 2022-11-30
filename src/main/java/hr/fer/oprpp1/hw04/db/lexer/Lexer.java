package hr.fer.oprpp1.hw04.db.lexer;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class Lexer.
 */
public class Lexer {

	/** The data. */
	private char[] data; // ulazni tekst
	
	/** The current token. */
	private Token token; // trenutni token
	
	/** The current index. */
	private int currentIndex; // indeks prvog neobrađenog znaka

	/** List of database column names. */
	private static final List<String> dataBaseVariableName;

	static {
		dataBaseVariableName = new LinkedList<>();
		dataBaseVariableName.add("jmbag");
		dataBaseVariableName.add("firstName");
		dataBaseVariableName.add("lastName");
	}

	/**
	 * Instantiates a new lexer.
	 *
	 * @param text the text
	 */
	public Lexer(String text) {

		if (text == null) {
			throw new NullPointerException();
		}

		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.token = null;
	}

	/**
	 * Next token.
	 *
	 * @return the token
	 */
	public Token nextToken() {

		if (token != null && token.getType() == TokenType.EOF) {
			token = new Token(TokenType.EOF, null);
			return token;
		}

		// Skip blanks
		this.skipBlanks();

		// EOF
		if (currentIndex >= data.length) {
			token = new Token(TokenType.EOF, null);
			return token;
		}

		// Value
		if (data[currentIndex] == '"') {
			StringBuilder sb = new StringBuilder();

//			sb.append(data[currentIndex]);
			currentIndex++;
			
			int quoteCounter = 1;
			while (currentIndex < data.length) {
				if (data[currentIndex] == '"') {
//					sb.append(data[currentIndex]);
					currentIndex++;
					quoteCounter++;
					break;
				}
				sb.append(data[currentIndex]);
				currentIndex++;
			}
			
			if(quoteCounter != 2) {
				throw new RuntimeException("Value never closed");
			}
			
			String output = sb.toString();
			
//			String jmbagChecker = output.substring(1, output.length()-1);
			String jmbagChecker = output;
			
			if(jmbagChecker.matches("\\d+")) {
				return new Token(TokenType.VALUE_JMBAG, output);
			}else {
				return new Token(TokenType.VALUE_NAME, output);
			}
		}

		// OPERATORS
		if (String.valueOf(data[currentIndex]).matches("[<>=!]")) {
			String output = String.valueOf(data[currentIndex]);
			currentIndex++;

			if (String.valueOf(data[currentIndex]).matches("[=]")) {
				output = output + String.valueOf(data[currentIndex]);
				currentIndex++;
			}
			
			if(output.equalsIgnoreCase("!")) {
				return new Token(TokenType.NOT, output);
			}

			return new Token(TokenType.OPERATOR, output);
		}

		// Word
		if (Character.isLetter(data[currentIndex])) {
			StringBuilder sb = new StringBuilder();

			while (currentIndex < data.length) {

				if (Character.isLetter(data[currentIndex]) || data[currentIndex] == '-') {
					sb.append(data[currentIndex]);
					currentIndex++;
				} else {
					break;
				}
			}

			String output = sb.toString();

			// Je li query
			if (output.equalsIgnoreCase("query")) {
				return new Token(TokenType.QUERY, output.toLowerCase());
				// Je li Database Variable
			} else if (dataBaseVariableName.stream().anyMatch(output::equalsIgnoreCase)) {
				return new Token(TokenType.VARIABLE, output.toLowerCase());
			} else if (output.equalsIgnoreCase("and")) {
				return new Token(TokenType.CONNECTORS, output.toLowerCase());
			} else if (output.equalsIgnoreCase("like")) {
				return new Token(TokenType.OPERATOR, output.toLowerCase());
			}else if(output.equalsIgnoreCase("with-statistics")) {
				return new Token(TokenType.STATISTICS, output.toLowerCase());
			}

		}

		// Unknown character type
		throw new LexerException("Error during lexical analysic");
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * Skip blanks.
	 */
	private void skipBlanks() {
		while (currentIndex < data.length) {
			if (data[currentIndex] == '\r' || data[currentIndex] == '\n' || data[currentIndex] == '\t'
					|| data[currentIndex] == ' ') {
				this.currentIndex++;
			} else {
				break;
			}
		}
	}

}
