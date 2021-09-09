package org.kanyakumari.dao.repository.onetoone;

import org.kanyakumari.dao.entity.onetoone.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
