package kz.study.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_GAME_BRAIN")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DGameBrain.findAll", query = "SELECT g FROM DGameBrain g"),
        @NamedQuery(name = "DGameBrain.findByDLessonId", query = "SELECT g FROM DGameBrain g where g.dLesson = :dLesson")
})
public class DGameBrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "id")
    private String id;
    @Column(name = "DESC_RUS")
    private String descRus;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLesson;

    public DGameBrain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescRus() {
        return descRus;
    }

    public void setDescRus(String descRus) {
        this.descRus = descRus;
    }

    public DLesson getdLesson() {
        return dLesson;
    }

    public void setdLesson(DLesson dLesson) {
        this.dLesson = dLesson;
    }
}