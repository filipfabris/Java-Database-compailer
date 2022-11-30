package hr.fer.oprpp1.hw04.db.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class ComparisonOperators.
 */
public class ComparisonOperators {

	/** The less. */
	// Anonimna
	public static IComparisonOperator LESS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			boolean otuput = value1.compareTo(value2) < 0;
			return otuput;
		}
	};

	/** The less or equals. */
	// Lambda extende
	public static IComparisonOperator LESS_OR_EQUALS = ((String value1, String value2) -> {
		return value1.compareTo(value2) <= 0;
	});

	/** The greater. */
	// Lambda
	public static IComparisonOperator GREATER = ((value1, value2) -> value1.compareTo(value2) > 0);

	/** The greater or equals. */
	public static IComparisonOperator GREATER_OR_EQUALS = ((value1, value2) -> value1.compareTo(value2) >= 0);

	/** The equals. */
	public static IComparisonOperator EQUALS = ((value1, value2) -> value1.compareTo(value2) == 0);

	/** The not equals. */
	public static IComparisonOperator NOT_EQUALS = ((value1, value2) -> value1.compareTo(value2) != 0);

	/** The like. */
	public static IComparisonOperator LIKE = ((String value1, String value2) -> {
		
		if(value1 == null || value2 == null) {
			throw new NullPointerException("inputs to satisifed method should not be null");
		}
		
		if(value1.equals(value2) == true) {
			return true;
		}
		
		Pattern regex = Pattern.compile("[*]");
		Matcher specialSign = regex.matcher(value2);
		int count = 0;
		while(specialSign.find()) {
			count++;
		}
		
		if(count > 1) {
			throw new RuntimeException(String.format("Special sign '*' can occur at most once, in your input it was inserted %d times", count));
		}
		
		// inside value2 like statment
		int i;
		boolean special = false;
		for (i = 0; i < value2.length(); i++) {
			// check for *
			if (value2.charAt(i) == '*') {
				i++;
				special = true;
				break;
				
			}

			if (value1.charAt(i) != value2.charAt(i)) {
				return false;
			}
		}

		if(special == true) {
			String other = value2.substring(i, value2.length());
			
			String subValue1 = value1.substring(i-1, value1.length());
			
			if (subValue1.endsWith(other)) {
				return true;
			}
		}

		return false;
	});

}
