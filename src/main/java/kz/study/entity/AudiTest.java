package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 15.05.2017.
 */
@Entity
@Table(name = "AUDI_TEST")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AudiTest.findAll", query = "SELECT g FROM AudiTest g"),
        @NamedQuery(name = "AudiTest.findById", query = "SELECT g FROM AudiTest g where g.id = :id"),
        @NamedQuery(name = "AudiTest.findBySrcId", query = "SELECT g FROM AudiTest g where g.srcId = :srcId")
})
public class AudiTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "frame")
    private String frame;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "src_id")
    private int srcId;

    public AudiTest() {
    }

    public AudiTest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }
}
