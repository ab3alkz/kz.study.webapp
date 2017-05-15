package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_GAMES_W")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DGamesW.findAll", query = "SELECT g FROM DGamesW g"),
        @NamedQuery(name = "DGamesW.findById", query = "SELECT g FROM DGamesW g where g.id = :id")
})
public class DGamesW implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DESC_KAZ")
    private String descKaz;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLesson;

    public DGamesW() {
    }

    public DGamesW(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescKaz() {
        return descKaz;
    }

    public void setDescKaz(String descKaz) {
        this.descKaz = descKaz;
    }

    public DLesson getdLesson() {
        return dLesson;
    }

    public void setdLesson(DLesson dLesson) {
        this.dLesson = dLesson;
    }
}