package hr.fer.oprpp1.hw04.db.strategy;

public class ComparisonOperators {

	// Anonimna
	public static IComparisonOperator LESS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			boolean otuput = value1.compareTo(value2) < 0;
			return otuput;
		}
	};

	// Lambda extende
	public static IComparisonOperator LESS_OR_EQUALS = ((String value1, String value2) -> {
		return value1.compareTo(value2) <= 0;
	});

	// Lambda
	public static IComparisonOperator GREATER = ((value1, value2) -> value1.compareTo(value2) > 0);

	public static IComparisonOperator GREATER_OR_EQUALS = ((value1, value2) -> value1.compareTo(value2) >= 0);

	public static IComparisonOperator EQUALS = ((value1, value2) -> value1.compareTo(value2) == 0);

	public static IComparisonOperator NOT_EQUALS = ((value1, value2) -> value1.compareTo(value2) != 0);

	public static IComparisonOperator LIKE = ((String value1, String value2) -> {
		
		
		// inside value2 like statment
		int i;
		for (i = 0; i < value2.length(); i++) {
			// check for *
			if (value2.charAt(i) == '*') {
				i++;
				break;
				
			}

			if (value1.charAt(i) != value2.charAt(i)) {
				return false;
			}
		}

		String other = value2.substring(i, value2.length());

		if (value1.endsWith(other)) {
			return true;
		}

		return false;
	});

}
