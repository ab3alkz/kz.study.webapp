package session;

import javax.ejb.Stateless;

/**
 * Created by amanzhol-ak on 29.03.2017.
 */

@Stateless
public class TestSession {

    public String getTestString() {
        String str = "Hello test";
        System.out.println(str);
        return str;
    }

}
