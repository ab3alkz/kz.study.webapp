package kz.study.gson;

import java.util.List;

/**
 * Created by amanzhol-ak on 23.04.2017.
 */
public class GsonIntelectualQuestion  {
    private int id;
    private String question;
    private String answ;
    private String dbAnsw;
    private GsonResult result;
    private Integer srcId;
    private List<GsonIntelectualQuestion> data;

    public List<GsonIntelectualQuestion> getData() {
        return data;
    }

    public void setData(List<GsonIntelectualQuestion> data) {
        this.data = data;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsw() {
        return answ;
    }

    public void setAnsw(String answ) {
        this.answ = answ;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbAnsw() {
        return dbAnsw;
    }

    public void setDbAnsw(String dbAnsw) {
        this.dbAnsw = dbAnsw;
    }


    public GsonResult getResult() {
        return result;
    }

    public void setResult(GsonResult result) {
        this.result = result;
    }
}
