package kz.study.gson;

public class GsonUsers<E> {
    private String uName;    
    private String uPassword;
    private String uDescription;
    private GsonUserDetail userDetail;
    private E role;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuDescription() {
        return uDescription;
    }

    public void setuDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public GsonUserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(GsonUserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public E getRole() {
        return role;
    }

    public void setRole(E role) {
        this.role = role;
    }
}
