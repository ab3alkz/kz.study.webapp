package kz.study.gson;

import java.util.List;

/**
 * @author kussein-at
 * @since 07.06.2017.
 */
public class GsonMorphology {

    private String synonymTitle;
    private List<String> synonymResult;
    private String antonymTitle;
    private List<String> antonymResult;

    public String getSynonymTitle() {
        return synonymTitle;
    }

    public void setSynonymTitle(String synonymTitle) {
        this.synonymTitle = synonymTitle;
    }

    public List<String> getSynonymResult() {
        return synonymResult;
    }

    public void setSynonymResult(List<String> synonymResult) {
        this.synonymResult = synonymResult;
    }

    public String getAntonymTitle() {
        return antonymTitle;
    }

    public void setAntonymTitle(String antonymTitle) {
        this.antonymTitle = antonymTitle;
    }

    public List<String> getAntonymResult() {
        return antonymResult;
    }

    public void setAntonymResult(List<String> antonymResult) {
        this.antonymResult = antonymResult;
    }
}