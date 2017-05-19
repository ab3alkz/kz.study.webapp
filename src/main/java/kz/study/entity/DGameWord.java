package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author amanzhol-ak
 * @since 09.05.2017.
 */
@Entity
@Table(name = "D_GAME_WORD")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DGameWord.findAll", query = "SELECT g FROM DGameWord g"),
        @NamedQuery(name = "DGameWord.findById", query = "SELECT g FROM DGameWord g where g.id = :id"),
        @NamedQuery(name = "DGameWord.findByDUrovenId", query = "SELECT g FROM DGameWord g where g.dLesson = :id")
})
public class DGameWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLesson;
    @Column(name = "QUESTION")
    private String question;
    @JoinColumn(name = "D_ANSWER_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DGameWordAnswer dGameWordAnswer;
    @Column(name = "ANSWER")
    private String answer;

    public DGameWord(String id, DLesson dLesson, String question, DGameWordAnswer dGameWordAnswer, String answer) {
        this.id = id;
        this.dLesson = dLesson;
        this.question = question;
        this.dGameWordAnswer = dGameWordAnswer;
        this.answer = answer;
    }

    public DGameWord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DLesson getdLesson() {
        return dLesson;
    }

    public void setdLesson(DLesson dLesson) {
        this.dLesson = dLesson;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public DGameWordAnswer getdGameWordAnswer() {
        return dGameWordAnswer;
    }

    public void setdGameWordAnswer(DGameWordAnswer dGameWordAnswer) {
        this.dGameWordAnswer = dGameWordAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}