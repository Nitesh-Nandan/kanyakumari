package org.kanyakumari.dao.entity.onetomany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    private String question;

    /**
     * Do you really think you need a separate table??
     *
     * Can't you give responsibility to inverse columns
     *
     *  @OneToMany(mappedBy = "question")
     *  private List<Answer> answer;
     *
     */
//    @OneToMany
//    @JoinTable(
//            name = "question_answers",
//            joinColumns = @JoinColumn(name = "question_id"),
//            inverseJoinColumns = @JoinColumn(name = "answer_id")
//    )
//    private List<Answer> answers;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

}
