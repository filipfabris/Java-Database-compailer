package hr.fer.oprpp1.hw04.db.strategy;

import hr.fer.oprpp1.hw04.db.model.StudentRecord;

public interface IFilter {
	public boolean accepts(StudentRecord record);

}