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
    @Column(name = "NAME_KAZ")
    private String nameKaz;
    @Column(name = "NAME_LAN")
    private String nameLan;
    @Column(name = "DESC_RUS")
    private String descRus;
    @Column(name = "DESC_KAZ")
    private String descKaz;
    @Column(name = "DESC_LAN")
    private String descLan;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLessonId;

    public DGrammarLesson() {
    }

    public DGrammarLesson(String id) {
        this.id = id;
    }

    public DGrammarLesson(String id, String nameRus, String nameKaz, String nameLan, String descRus, String descKaz, String descLan, DLesson dLessonId) {
        this.id = id;
        this.nameRus = nameRus;
        this.nameKaz = nameKaz;
        this.nameLan = nameLan;
        this.descRus = descRus;
        this.descKaz = descKaz;
        this.descLan = descLan;
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

    public String getNameKaz() {
        return nameKaz;
    }

    public void setNameKaz(String nameKaz) {
        this.nameKaz = nameKaz;
    }

    public String getNameLan() {
        return nameLan;
    }

    public void setNameLan(String nameLan) {
        this.nameLan = nameLan;
    }

    public String getDescRus() {
        return descRus;
    }

    public void setDescRus(String descRus) {
        this.descRus = descRus;
    }

    public String getDescKaz() {
        return descKaz;
    }

    public void setDescKaz(String descKaz) {
        this.descKaz = descKaz;
    }

    public String getDescLan() {
        return descLan;
    }

    public void setDescLan(String descLan) {
        this.descLan = descLan;
    }

    public DLesson getdLessonId() {
        return dLessonId;
    }

    public void setdLessonId(DLesson dLessonId) {
        this.dLessonId = dLessonId;
    }
}