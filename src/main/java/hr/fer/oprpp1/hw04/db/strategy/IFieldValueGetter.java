package hr.fer.oprpp1.hw04.db.strategy;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public interface IFieldValueGetter {
	
	public String get(StudentRecord record);

}
