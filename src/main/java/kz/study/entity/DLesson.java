package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_LESSON")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DLesson.findAll", query = "SELECT g FROM DLesson g"),
        @NamedQuery(name = "DLesson.findByDUrovenId", query = "SELECT g FROM DLesson g where g.dUroven = :id"),
        @NamedQuery(name = "DLesson.findById", query = "SELECT g FROM DLesson g where g.id = :id"),
})
public class DLesson implements Serializable {

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
    @JoinColumn(name = "D_LESSON_ID", referencedColumnName = "ID")
    @OneToOne
    private DUroven dUroven;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dLesson")
    private DVideoLesson dVideoLesson;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dLesson")
    private DAudioLesson dAudioLesson;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dLessonId")
    private DGrammarLesson dGrammarLesson;

    public DLesson() {
    }

    public DLesson(String id) {
        this.id = id;
    }

    public DLesson(String id, String nameRus, String nameKaz, String nameLan, DUroven dUroven) {
        this.id = id;
        this.nameRus = nameRus;
        this.nameKaz = nameKaz;
        this.nameLan = nameLan;
        this.dUroven = dUroven;
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

    public DUroven getdUroven() {
        return dUroven;
    }

    public void setdUroven(DUroven dUroven) {
        this.dUroven = dUroven;
    }
}