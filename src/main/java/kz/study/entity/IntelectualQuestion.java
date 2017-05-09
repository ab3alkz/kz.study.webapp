package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 23.04.2017.
 */
@Entity
@Table(name = "Intelectual_question")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "IntelectualQuestion.findAll", query = "SELECT g FROM IntelectualQuestion g "),
        @NamedQuery(name = "IntelectualQuestion.findBySrcId", query = "SELECT g FROM IntelectualQuestion g WHERE g.srcId = :srcId")
})
public class IntelectualQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "QUESTION")
    private String question;
    @Basic
    @Column(name = "SRC_ID")
    private Integer srcId;

    @Column(name = "ANSWER")
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public IntelectualQuestion(Integer id) {
        this.id = id;
    }

    public IntelectualQuestion() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
