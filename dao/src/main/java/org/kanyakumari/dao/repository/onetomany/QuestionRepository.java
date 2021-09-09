package org.kanyakumari.dao.repository.onetomany;

import org.kanyakumari.dao.entity.onetomany.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
