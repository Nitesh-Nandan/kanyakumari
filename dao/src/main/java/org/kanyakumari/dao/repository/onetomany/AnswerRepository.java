package org.kanyakumari.dao.repository.onetomany;

import org.kanyakumari.dao.entity.onetomany.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
