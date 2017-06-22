package kz.study.gson;

import java.util.List;

/**
 * @author kussein-at
 * @since 07.06.2017.
 */
public class GsonMorphologyMain {

    private GsonMorphology synonym;
    private GsonMorphology antonym;
    private GsonMain mainAnalyze;

    public GsonMorphology getSynonym() {
        return synonym;
    }

    public void setSynonym(GsonMorphology synonym) {
        this.synonym = synonym;
    }

    public GsonMorphology getAntonym() {
        return antonym;
    }

    public void setAntonym(GsonMorphology antonym) {
        this.antonym = antonym;
    }

    public GsonMain getMainAnalyze() {
        return mainAnalyze;
    }

    public void setMainAnalyze(GsonMain mainAnalyze) {
        this.mainAnalyze = mainAnalyze;
    }
}