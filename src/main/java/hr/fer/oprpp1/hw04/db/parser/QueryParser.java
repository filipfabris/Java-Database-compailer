package hr.fer.oprpp1.hw04.db.parser;


import java.util.*;

import hr.fer.oprpp1.hw04.db.lexer.Lexer;
import hr.fer.oprpp1.hw04.db.lexer.Token;
import hr.fer.oprpp1.hw04.db.lexer.TokenType;
import hr.fer.oprpp1.hw04.db.strategy.ComparisonOperators;
import hr.fer.oprpp1.hw04.db.strategy.ConditionalExpression;
import hr.fer.oprpp1.hw04.db.strategy.FieldValueGetters;
import hr.fer.oprpp1.hw04.db.strategy.IComparisonOperator;
import hr.fer.oprpp1.hw04.db.strategy.IFieldValueGetter;

/**
 * The Class QueryParser.
 */
public class QueryParser {
	
	/** The current token. */
	private Token currentToken;
	
	/** The lexer. */
	private Lexer lexer;
	
	
	/** The expressions. */
	private List<ConditionalExpression> expressions;
    
    /** The Constant variableGetter. */
    private static final Map<String, IFieldValueGetter> variableGetter;
    
    /** The Constant operatorGetter. */
    private static final Map<String, IComparisonOperator> operatorGetter;
    
    static {
    	variableGetter = new HashMap<>();
    	variableGetter.put("jmbag", FieldValueGetters.JMBAG);
    	variableGetter.put("firstname", FieldValueGetters.FIRST_NAME);
    	variableGetter.put("lastname", FieldValueGetters.LAST_NAME);

    	operatorGetter = new HashMap<>();
    	operatorGetter.put(">", ComparisonOperators.GREATER);
    	operatorGetter.put("<", ComparisonOperators.LESS);
    	operatorGetter.put(">=", ComparisonOperators.GREATER_OR_EQUALS);
    	operatorGetter.put("<=", ComparisonOperators.LESS_OR_EQUALS);
    	operatorGetter.put("=", ComparisonOperators.EQUALS);
    	operatorGetter.put("!=", ComparisonOperators.NOT_EQUALS);
    	operatorGetter.put("like", ComparisonOperators.LIKE);
    }
    
	/**
	 * Instantiates a new query parser.
	 *
	 * @param lexer the lexer
	 */
	QueryParser(Lexer lexer){
		this.lexer = lexer;
		this.expressions = new LinkedList<>();

		nextToken();
		this.parse();
	}

	/**
	 * Instantiates a new query parser.
	 *
	 * @param string the string
	 */
	public QueryParser(String string) {
		this(new Lexer(string));
	}

	//Gramatika
	/**
	 * Parses the.
	 */
	//VARIABLE - OPERATOR - VALUE_GRADE/VALUE_JMBAG/VALUE_NAME
	private void parse() {	
		if(isTokenOfType(TokenType.QUERY) == false) {
			throw new RuntimeException("Query");
		}
		
		boolean connector = true;
		while(true) {
			this.nextToken();
			if (isTokenOfType(TokenType.EOF)) {
				break; // kraj
			}
			
			if(connector == true) {
				this.parseExpresion();
				connector = false;
			}
						
			if(isTokenOfType(TokenType.CONNECTORS)) {
				connector = true;
				continue;
			}
			
			if (isTokenOfType(TokenType.EOF) == false) {
				throw new RuntimeException("Connector is missing");
			}
			
		}

	}



	/**
	 * Parses the expresion.
	 */
	private void parseExpresion() {
		
		//jmbag,firstname,lastname
		String dbVariable = currentToken.getValue();
		if(isTokenOfType(TokenType.VARIABLE) == false) {
			throw new RuntimeException("Variable is missing");
		}
		IFieldValueGetter ifv = variableGetter.get(currentToken.getValue());
		
		
		//operator
		this.nextToken();
		if(isTokenOfType(TokenType.OPERATOR) == false) {
			throw new RuntimeException("Operator is missing");
		}
		IComparisonOperator ico = operatorGetter.get(currentToken.getValue());
		
		//vrijednost
		this.nextToken();
		String value = currentToken.getValue();
		if(this.isTypeValid(dbVariable) == false) {
			throw new RuntimeException("Wrong input for query");
		}
		
		ConditionalExpression localExpression = new ConditionalExpression(ifv, value, ico);
		expressions.add(localExpression);
		
		nextToken();
	}

	/**
	 * Next token.
	 */
	private void nextToken() {
		currentToken = lexer.nextToken();
	}

	/**
	 * Checks if is token of type.
	 *
	 * @param type the type
	 * @return true, if is token of type
	 */
	private boolean isTokenOfType(TokenType type) {
		return currentToken.getType().equals(type);
	}
	
	/**
	 * Checks if is type valid.
	 *
	 * @param dbVariable the db variable
	 * @return true, if is type valid
	 */
	private boolean isTypeValid(String dbVariable) {
		if(dbVariable.equals("jmbag") && isTokenOfType(TokenType.VALUE_JMBAG) == true) {
			return true;
		}else if(dbVariable.equals("firstname") && isTokenOfType(TokenType.VALUE_NAME) == true) {
			return true;

		}else if(dbVariable.equals("lastname") && isTokenOfType(TokenType.VALUE_NAME) == true) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * Checks if is direct query.
	 *
	 * @return true, if is direct query
	 */
	public boolean isDirectQuery() {
		if(expressions.size() == 1 && 
		   expressions.get(0).getFieldValueGetter() == FieldValueGetters.JMBAG && 
		   expressions.get(0).getComparisonOperator() == ComparisonOperators.EQUALS){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the queried JMBAG.
	 *
	 * @return the queried JMBAG
	 */
	public String getQueriedJMBAG() {
		if (isDirectQuery() == false) {
			throw new IllegalStateException("not direct query!");
		}
        return expressions.get(0).getValueComparison();
	}
	
	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public List<ConditionalExpression> getQuery() {
		return expressions;
	}

}
