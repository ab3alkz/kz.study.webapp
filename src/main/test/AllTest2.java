import com.sun.istack.internal.Nullable;
import kz.study.entity.DEnding;
import kz.study.gson.GsonAllDic;
import kz.study.gson.GsonResult;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.isNullOrEmpty;


/**
 * @author kussein-at
 * @since 19.04.2017.
 */
public class AllTest2 {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";
    private static final String[] aSymbols = new String[]{"а", "ә", "е", "ё", "и", "о", "ө", "ұ", "ү", "ы", "і", "э", "ю", "я"};
    private static final String[] bSymbols = new String[]{"б", "в", "г", "ғ", "д", "ж", "з", "й", "к", "қ",
            "л", "м", "н", "ң", "п", "р", "с", "т", "у", "ф", "х", "һ", "ц", "ч", "ш", "щ", "ъ", "ь"};
    private static final String[] halouRay = new String[]{"қы", "кі", "ғы", "гі"};// жұрнақты қалау рай
    private static final String[] tuyqRay = new String[]{"ып", "іп", "п"};// жұрнақты көсемшеге бол
    private static final String[] questionEsimdik = new String[]{"ма", "ме", "ба", "бе", "па", "пе", "ша", "ше"};// Сөйлемде сұрау есімдігінің қолданылуы арқылы
    private String sentenceType;
    private String vowel;
    private String consonant;
    private String qLabel;
    private String halou;
    private String tuyq;
    private String esimdik;

    /**
     * @desc Все окончания
     */
    @Nullable
    private static List<GsonAllDic> getTestBd() throws SQLException {
        List<GsonAllDic> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM all_ending s");
            while (rs.next()) {
                GsonAllDic gsonAllDic = new GsonAllDic();
                gsonAllDic.setId(rs.getString("id"));
                gsonAllDic.setAddValue(String.valueOf(rs.getInt("d_ending_id")));
                gsonAllDic.setValue(rs.getString("value"));
                gsonAllDic.setAddDopValue(rs.getInt("D_CASE_ID"));
                list.add(gsonAllDic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    private static Boolean findIntoArrays(Character osnId, String... array) {
        for (String seArray : array) {
            if (seArray.equals(String.valueOf(osnId))) return true;
        }

        return false;
    }

    public String getSentenceType() {
        return sentenceType;
    }

    public void setSentenceType(String sentenceType) {
        this.sentenceType = sentenceType;
    }

    public String getVowel() {
        return vowel;
    }

    public void setVowel(String vowel) {
        this.vowel = vowel;
    }

    public String getConsonant() {
        return consonant;
    }

    public void setConsonant(String consonant) {
        this.consonant = consonant;
    }

    public String getqLabel() {
        return qLabel;
    }

    public void setqLabel(String qLabel) {
        this.qLabel = qLabel;
    }

    public String getHalou() {
        return halou;
    }

    public void setHalou(String halou) {
        this.halou = halou;
    }

    public String getTuyq() {
        return tuyq;
    }

    public void setTuyq(String tuyq) {
        this.tuyq = tuyq;
    }

    public String getEsimdik() {
        return esimdik;
    }

    public void setEsimdik(String esimdik) {
        this.esimdik = esimdik;
    }

    @Test
    public void getTest() throws SQLException {
        String newS = "асланмен әлихан доп ойнады ";

        if (!isNullOrEmpty(newS)) {
            for (String s : newS.split(" ")) {
                if (s.length() >= 3) {
                    int i = s.length();
                    String sr = s.substring(i / 2);
                    getEngingAnalyze(s, sr);
                }
            }
        }

    }

    private String getValueOrEmpty(String value) {
        if (!isNullOrEmpty(value)) {
            return value;
        }
        return "";
    }

    private void getEngingAnalyze(String newS, String ending) throws SQLException {
        for (GsonAllDic string : getTestBd()) {
            if (ending.contains(string.getValue())) {
                DEnding obj = getTestBdC((Integer) string.getAddDopValue());
                System.out.println(newS + " " + string.getValue() + " " + getTestDEnding(Integer.parseInt(string.getAddValue())).getValue() + "    " + getValueOrEmpty(obj.getValue()));
            }
        }
    }

    @Nullable
    private DEnding getTestBdC(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DEnding dending = new DEnding();
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM d_case WHERE id = " + id;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dending.setId((long) rs.getInt("id"));
                dending.setValue(rs.getString("name"));
            }
            return dending;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Nullable
    private DEnding getTestDEnding(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DEnding dending = new DEnding();
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM d_ending WHERE id = " + id;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dending.setId((long) rs.getInt("id"));
                dending.setValue(rs.getString("name"));
            }
            return dending;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Test
    public void getStringALetters() {
        int a = 0;
        int b = 0;
        String text = "асланмен әлихан доп ойнады айтқысы ";

        text = text.toLowerCase();

        this.setqLabel("Жақсыз сөйлемнің баяндауыштары мынадай тұлғада келеді: ");
        List<String> qalauList = new ArrayList<>();
        List<String> tuyqList = new ArrayList<>();
        List<String> esimdikList = new ArrayList<>();
        for (String str : text.split(" ")) {
            for (String s : halouRay) {
                if (str.contains(s)) {
                    this.setHalou("жұрнақты қалау рай етістікке бол көмекші етістігі тіркесіп келеді: " + str + " " + s);
                    qalauList.add(halou);
                }
            }
            for (String s : tuyqRay) {
                if (str.contains(s)) {
                    this.setTuyq("жұрнақты көсемшеге бол (көбіне болма тәрізді болымсыз түрінде) көмекші етістігі тіркесіп келеді: " + str + " " + s);
                    tuyqList.add(tuyq);
                }
            }
            for (String s : questionEsimdik) {
                if (str.contains(s)) {
                    this.setEsimdik("Сөйлемде сұрау есімдігінің қолданылуы арқылы " + str + " " + s);
                    esimdikList.add(tuyq);
                }
            }
        }

        char[] myCharArray = text.toCharArray();
        for (Character s : myCharArray) {
            if (findIntoArrays(s, aSymbols)) {
                a += 1;
            }
            if (findIntoArrays(s, bSymbols)) {
                b += 1;
            }
        }
        this.setVowel("Дауысты дыбыстар саны: " + a);//гласный
        this.setConsonant("Дауыссыз дыбыстар саны: " + b);//согласный

        System.out.println(a);
        System.out.println(b);
    }
}

