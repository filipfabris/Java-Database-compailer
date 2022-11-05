
package hr.fer.oprpp1.hw04.db.lexer;

/**
 * The Class Token.
 */
public class Token {
	
	/** The type. */
	private TokenType type;
	
	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new token.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Token [type=" + type + ", value=" + value + "]";
	}
	
	

}
