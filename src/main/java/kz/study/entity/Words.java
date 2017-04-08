package kz.study.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by amanzhol-ak on 08.04.2017.
 */
@Entity
public class Words {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "value_kz")
    private String valueKz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueKz() {
        return valueKz;
    }

    public void setValueKz(String valueKz) {
        this.valueKz = valueKz;
    }

    public Words(String id) {
        this.id = id;
    }
    public Words() {
    }
}
