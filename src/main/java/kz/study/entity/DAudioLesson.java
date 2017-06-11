package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_AUDIO_LESSON")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DAudioLesson.findAll", query = "SELECT g FROM DAudioLesson g"),
        @NamedQuery(name = "DAudioLesson.findById", query = "SELECT g FROM DAudioLesson g where g.id = :id"),
        @NamedQuery(name = "DAudioLesson.findByDUrovenId", query = "SELECT g FROM DAudioLesson g where g.dLesson = :id")
})
public class DAudioLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME_RUS")
    private String nameRus;
    @Column(name = "LINK")
    private String link;
    @Column(name = "DESC_KAZ")
    private String descKaz;
    @Column(name = "DESC_LAN")
    private String descLan;
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private DLesson dLesson;

    public DAudioLesson() {
    }

    public DAudioLesson(String id) {
        this.id = id;
    }

    public DAudioLesson(String id, String nameRus, String link, String descKaz, String descLan, DLesson dLesson) {
        this.id = id;
        this.nameRus = nameRus;
        this.link = link;
        this.descKaz = descKaz;
        this.descLan = descLan;
        this.dLesson = dLesson;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public DLesson getdLesson() {
        return dLesson;
    }

    public void setdLesson(DLesson dLesson) {
        this.dLesson = dLesson;
    }
}