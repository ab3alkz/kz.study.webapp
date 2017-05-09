package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_UROVEN")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DUroven.findAll", query = "SELECT g FROM DUroven g"),
        @NamedQuery(name = "DUroven.findById", query = "SELECT g FROM DUroven g where g.id = :id")
})
public class DUroven implements Serializable {

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

    public DUroven() {
    }

    public DUroven(String id) {
        this.id = id;
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
}