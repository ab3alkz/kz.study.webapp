package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by amanzhol-ak on 23.04.2017.
 */
@Entity
@Table(name = "intelectual_question")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "IntelectualQuestion.findAll", query = "SELECT g FROM IntelectualQuestion g "),
        @NamedQuery(name = "IntelectualQuestion.findBySrcId", query = "SELECT g FROM IntelectualQuestion g WHERE g.srcId = :srcId")
})
public class IntelectualQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "QUESTION")
    private String question;
    @Basic
    @Column(name = "SRC_ID")
    private Integer srcId;

    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    @OneToMany
    private List<IntelectualQuestionAnswers> answersList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public IntelectualQuestion(int id) {
        this.id = id;
    }

    public IntelectualQuestion() {
    }

    public List<IntelectualQuestionAnswers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<IntelectualQuestionAnswers> answersList) {
        this.answersList = answersList;
    }
}
