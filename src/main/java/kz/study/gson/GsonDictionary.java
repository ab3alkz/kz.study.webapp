package kz.study.gson;

public class GsonDictionary {

    private String id;
    private String parentId;
    private String code;
    private String nameRus;
    private String nameKaz;

    public GsonDictionary() {
        
    }
    
    public GsonDictionary(String id, String code, String nameRus, String nameKaz) {
        this.id = id;
        this.code = code;
        this.nameRus = nameRus;
        this.nameKaz = nameKaz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}