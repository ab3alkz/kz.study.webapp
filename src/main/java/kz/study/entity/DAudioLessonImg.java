package kz.study.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author a.kussein
 */
@Entity
@Table(name = "D_AUDIO_LESSON_IMG")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DAudioLessonImg.findAll", query = "SELECT u FROM DAudioLessonImg u"),
        @NamedQuery(name = "DAudioLessonImg.findById", query = "SELECT u FROM DAudioLessonImg u where u.dAudioLesson = :id "),
})
public class DAudioLessonImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ID")
    private String id;
    @Column(name = "LINK")
    private String link;
    @Lob
    @Column(name = "IMG")
    private byte[] img;
    @JoinColumn(name = "D_AUDIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private DAudioLesson dAudioLesson;

    public DAudioLessonImg(String id, String link, DAudioLesson dAudioLesson, byte[] img) {
        this.id = id;
        this.link = link;
        this.dAudioLesson = dAudioLesson;
        this.img = img;
    }

    public DAudioLessonImg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DAudioLesson getdAudioLesson() {
        return dAudioLesson;
    }

    public void setdAudioLesson(DAudioLesson dAudioLesson) {
        this.dAudioLesson = dAudioLesson;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}