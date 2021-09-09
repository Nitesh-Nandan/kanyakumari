package org.kanyakumari.dao.service.onetoone;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.entity.onetoone.Answer;
import org.kanyakumari.dao.entity.onetoone.Question;
import org.kanyakumari.dao.repository.onetoone.AnswerRepository;
import org.kanyakumari.dao.repository.onetoone.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OneToOneService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void main() {
        test1();
        doQuery();
        doQuery2();
    }

    private void test1() {
        Answer ans1 = Answer.builder().answer("Second Ans").build();
        Question qs1 = Question.builder().question("Second Quest").build();

        qs1.setAnswer(ans1);
        answerRepository.save(ans1);
        questionRepository.save(qs1);

//        qs1.setAnswer(ans1);
//        questionRepository.save(qs1);
//        answerRepository.save(ans1);
    }

    private void doQuery() {
        Optional<Question> qs1 = questionRepository.findById(1L);
        if(qs1.isPresent()) {
            System.out.println("QID : " + qs1.get().getId());
            System.out.println("Question : " + qs1.get().getQuestion());
            System.out.println("Answer: " + qs1.get().getAnswer().getAnswer());
        }

    }

    private void doQuery2() {
        Optional<Answer> ans1 = answerRepository.findById(1L);
        if(ans1.isPresent()) {
            System.out.println("AnsID : " + ans1.get().getId());
            System.out.println("Answer : " + ans1.get().getAnswer());
            System.out.println("Question: " + ans1.get().getQuestion().getQuestion());
        }
    }

}
