package org.kanyakumari.dao.service.onetomany;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.entity.onetomany.Answer;
import org.kanyakumari.dao.entity.onetomany.Question;
import org.kanyakumari.dao.repository.onetomany.AnswerRepository;
import org.kanyakumari.dao.repository.onetomany.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OneToManyService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void main() {
        create();
        doQuery();
        doQuery2();
    }

    private void create() {
        List<Answer> answers = new ArrayList<>();
        for(int i = 0; i<5;i++) {
            answers.add(Answer.builder().answer("My Answer" + i ).build());
        }
        answerRepository.saveAll(answers);
        Question q1 = Question.builder().question("Question 1").build();
        q1.setAnswers(answers);
        questionRepository.save(q1);
        Question q2 = Question.builder().question("Question 2").build();
        questionRepository.save(q2);

        Answer ans = Answer.builder().answer("Custom Answer").build();
        ans.setQuestion(q2);
        answerRepository.save(ans);
    }

    private void doQuery() {

        log.info("Question Query ............");

        Optional<Question> obj = questionRepository.findById(1L);
        if(obj.isPresent()) {
            System.out.println("Question Id: " + obj.get().getId());
            System.out.println("Question String: " + obj.get().getQuestion());
            System.out.println("Answer are: ");
            List<Answer> answers = obj.get().getAnswers();
            answers.forEach(answer -> System.out.println(answer));
        }

    }

    private void doQuery2() {
        log.info("Answer Query ............");

        Optional<Answer> obj = answerRepository.findById(6L);
        if(obj.isPresent()) {
            System.out.println("Answer Id: " + obj.get().getId());
            System.out.println("Answer String: " + obj.get().getAnswer());
            System.out.println("Question is: " + obj.get().getQuestion());
        }
    }

}
