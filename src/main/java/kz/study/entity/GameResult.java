package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

/**
 * @since 12.04.2017
 * @author amanzhol-ak.
 */
@Entity
@Table(name = "GAME_RESULT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "GameResult.findAll", query = "SELECT g FROM GameResult g order by g.gDate desc"),
        @NamedQuery(name = "GameResult.findById", query = "SELECT g FROM GameResult g WHERE g.id = :id")
})
public class GameResult implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "game_Id")
    private String gameId;
    @Column(name = "uName")
    private String uName;
    @Column(name = "info")
    private String info;
    @Column(name = "result")
    private Long result;
    @Column(name = "d_date")
    private Date gDate;

    public GameResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getgDate() {
        return gDate;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }
}
