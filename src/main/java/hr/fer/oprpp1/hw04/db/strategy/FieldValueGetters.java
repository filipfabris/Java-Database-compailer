package hr.fer.oprpp1.hw04.db.strategy;

/**
 * The Class FieldValueGetters.
 */
public class FieldValueGetters {
	
	/** The IFieldValueGetter get FIRST_NAME. */
	public final static IFieldValueGetter FIRST_NAME = ((record -> record.getFirstName()));
	
	/** The IFieldValueGetter get LAST_NAME. */
	public final static IFieldValueGetter LAST_NAME = ((record -> record.getLastName()));

	/** The IFieldValueGetter get JMBAG. */
	public final static IFieldValueGetter JMBAG = ((record -> record.getJmbag()));


}
