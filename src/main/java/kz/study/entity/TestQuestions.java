package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by amanzhol-ak on 17.04.2017.
 */
@Entity
@Table(name = "test_questions")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TestQuestions.findAll", query = "SELECT g FROM TestQuestions g "),
        @NamedQuery(name = "TestQuestions.findBySrcId", query = "SELECT g FROM TestQuestions g WHERE g.srcId = :srcId")
})
public class TestQuestions {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "question")
    private String question;
    @Basic
    @Column(name = "answ_1")
    private String answ1;
    @Basic
    @Column(name = "answ_2")
    private String answ2;
    @Basic
    @Column(name = "answ_3")
    private String answ3;
    @Basic
    @Column(name = "answ_4")
    private String answ4;
    @Basic
    @Column(name = "SRC_ID")
    private String srcId;

    public TestQuestions() {

    }

    public TestQuestions(String id) {
        this.id = id;
    }

    public TestQuestions(String id, String question, String answ1, String answ2, String answ3, String answ4, String srcId) {
        this.id = id;
        this.question = question;
        this.answ1 = answ1;
        this.answ2 = answ2;
        this.answ3 = answ3;
        this.answ4 = answ4;
        this.srcId = srcId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsw1() {
        return answ1;
    }

    public void setAnsw1(String answ1) {
        this.answ1 = answ1;
    }

    public String getAnsw2() {
        return answ2;
    }

    public void setAnsw2(String answ2) {
        this.answ2 = answ2;
    }

    public String getAnsw3() {
        return answ3;
    }

    public void setAnsw3(String answ3) {
        this.answ3 = answ3;
    }

    public String getAnsw4() {
        return answ4;
    }

    public void setAnsw4(String answ4) {
        this.answ4 = answ4;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }
}
