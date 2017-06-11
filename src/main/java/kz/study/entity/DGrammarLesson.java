package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_GRAMMAR_LESSON")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DGrammarLesson.findAll", query = "SELECT g FROM DGrammarLesson g"),
        @NamedQuery(name = "DGrammarLesson.findById", query = "SELECT g FROM DGrammarLesson g where g.id = :id"),
        @NamedQuery(name = "DGrammarLesson.findByDUrovenId", query = "SELECT g FROM DGrammarLesson g where g.dLessonId = :id")
})
public class DGrammarLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME_RUS")
    private String nameRus;
    @Column(name = "DESC_RUS")
    private String descRus;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLessonId;

    public DGrammarLesson() {
    }

    public DGrammarLesson(String id) {
        this.id = id;
    }

    public DGrammarLesson(String id, String nameRus, String descRus, DLesson dLessonId) {
        this.id = id;
        this.nameRus = nameRus;
        this.descRus = descRus;
        this.dLessonId = dLessonId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getDescRus() {
        return descRus;
    }

    public void setDescRus(String descRus) {
        this.descRus = descRus;
    }

    public DLesson getdLessonId() {
        return dLessonId;
    }

    public void setdLessonId(DLesson dLessonId) {
        this.dLessonId = dLessonId;
    }
}