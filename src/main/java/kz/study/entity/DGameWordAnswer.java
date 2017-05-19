package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author amanzhol-ak
 * @since 09.05.2017.
 */
@Entity
@Table(name = "D_GAME_WORD_ANSWER")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DGameWordAnswer.findAll", query = "SELECT g FROM DGameWordAnswer g"),
        @NamedQuery(name = "DGameWordAnswer.findById", query = "SELECT g FROM DGameWordAnswer g where g.id = :id")
})
public class DGameWordAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "VAR1")
    private String var1;
    @Column(name = "VAR2")
    private String var2;
    @Column(name = "VAR3")
    private String var3;
    @Column(name = "VAR4")
    private String var4;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dGameWordAnswer")
    private DGameWord dGameWord;

    public DGameWordAnswer() {
    }

    public DGameWordAnswer(String id) {
        this.id = id;
    }

    public DGameWordAnswer(String id, String var1, String var2, String var3, String var4) {
        this.id = id;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }
}