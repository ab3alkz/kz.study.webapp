/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "video_lessons")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VideoLessons.findAll", query = "SELECT g FROM VideoLessons g"),
        @NamedQuery(name = "VideoLessons.findById", query = "SELECT g FROM VideoLessons g where g.id = :id"),
})
public class VideoLessons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "id")
    private Long id;
    @Size(max = 1000)
    @Column(name = "value")
    private String value;
    @Size(max = 2)
    @Column(name = "desc")
    private String desc;

    public VideoLessons() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}