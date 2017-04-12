package kz.study.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @since 12.04.2017
 * @author amanzhol-ak.
 */
@Entity
@Table(name = "game_result", schema = "arma")
public class GameResult {
    private String id;
    private String gameId;
    private String uName;
    private int result;

    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "game_Id")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "uName")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "result")
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameResult that = (GameResult) o;

        if (result != that.result) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (gameId != null ? !gameId.equals(that.gameId) : that.gameId != null) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (gameId != null ? gameId.hashCode() : 0);
        result1 = 31 * result1 + (uName != null ? uName.hashCode() : 0);
        result1 = 31 * result1 + result;
        return result1;
    }
}