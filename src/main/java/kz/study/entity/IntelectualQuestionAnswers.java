package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 23.04.2017.
 */
@Entity
@Table(name = "intelectual_question_answers")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "IntelectualQuestionAnswers.findAll", query = "SELECT g FROM IntelectualQuestionAnswers g "),
        @NamedQuery(name = "IntelectualQuestionAnswers.findByQuestionId", query = "SELECT g FROM IntelectualQuestionAnswers g WHERE g.questionId = :questionId")
})
public class IntelectualQuestionAnswers  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "QUESTION_ID")
    private int questionId;
    @Basic
    @Column(name = "ANSWER")
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public IntelectualQuestionAnswers(int id) {
        this.id = id;
    }

    public IntelectualQuestionAnswers() {
    }
}
