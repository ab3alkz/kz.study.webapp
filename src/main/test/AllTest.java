//import com.sun.istack.internal.Nullable;
//import kz.study.entity.DEnding;
//import kz.study.gson.GsonAllDic;
//import kz.study.gson.GsonResult;
//import org.junit.Test;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static kz.study.util.Util.getGsonResult;
//import static kz.study.util.Util.isNullOrEmpty;
//
//
///**
// * @author kussein-at
// * @since 19.04.2017.
// */
//public class AllTest {
//
//    private static final String driverName = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/arma";
//    private static final String user = "pma";
//    private static final String pass = "123456";
//    private static List<String> mainName = new ArrayList<>();
//
//    @Test
//    public void getTest() throws SQLException {
//        List<String> textList = new ArrayList<>();
//        String newS = "асланмен әлихан доп ойнады ";
//        textList.addAll(Arrays.asList(newS.toLowerCase().split(" ")));
//
//        if (!isNullOrEmpty(newS)) {
//            getEngingAnalyze(newS, 1);
//        }
//
//    }
//
//    private String getValueOrEmpty(String value) {
//        if (!isNullOrEmpty(value)) {
//            return value;
//        }
//        return "";
//    }
//
//    private void getEngingAnalyze(String ending, long type) throws SQLException {
//        if (type == 1) {
//            for (GsonAllDic string : getTestBd()) {
//                if (ending.equalsIgnoreCase(string.getValue())) {
//                    DEnding obj = getTestBdC((Integer) string.getAddDopValue());
//                    System.out.println("Тубири: " + mainName);
//                    System.out.println(string.getValue() + getTestDEnding(Integer.parseInt(string.getAddValue())).getValue() + "    " + getValueOrEmpty(obj.getValue()));
//                }
//            }
//        }
//        else {
//            for (GsonAllDic string : getTestBd()) {
//                if (ending.contains(string.getValue())) {
//
//                    DEnding obj = getTestBdC((Integer) string.getAddDopValue());
//
//                    System.out.println("Тубири: " + ending.replace(string.getValue(), ""));
//
//                    System.out.println(string.getValue() + "    " + obj.getValue());
//                }
//            }
//        }
//    }
//
//    /**
//     * @desc Все окончания
//     */
//    @Nullable
//    private static List<GsonAllDic> getTestBd() throws SQLException {
//        List<GsonAllDic> list = new ArrayList<>();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, user, pass);
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM all_ending s");
//            while (rs.next()) {
//                GsonAllDic gsonAllDic = new GsonAllDic();
//                gsonAllDic.setId(rs.getString("id"));
//                gsonAllDic.setAddValue(String.valueOf(rs.getInt("d_ending_id")));
//                gsonAllDic.setValue(rs.getString("value"));
//                gsonAllDic.setAddDopValue(rs.getInt("D_CASE_ID"));
//                list.add(gsonAllDic);
//            }
//            return list;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            assert rs != null;
//            rs.close();
//            stmt.close();
//            conn.close();
//        }
//        return null;
//    }
//
//    @Nullable
//    private DEnding getTestBdC(int id) throws SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        DEnding dending = new DEnding();
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, user, pass);
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM d_case WHERE id = " + id;
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                dending.setId((long) rs.getInt("id"));
//                dending.setValue(rs.getString("name"));
//            }
//            return dending;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            rs.close();
//            stmt.close();
//            conn.close();
//        }
//        return null;
//    }
//
//    @Nullable
//    private DEnding getTestDEnding(int id) throws SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        DEnding dending = new DEnding();
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, user, pass);
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM d_ending WHERE id = " + id;
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                dending.setId((long) rs.getInt("id"));
//                dending.setValue(rs.getString("name"));
//            }
//            return dending;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            rs.close();
//            stmt.close();
//            conn.close();
//        }
//        return null;
//    }
//
//    private String getReplaceSpecialChars(String str) {
//        return str
//                .replace("                    ", " ")
//                .replace("                   ", " ")
//                .replace("                  ", " ")
//                .replace("                 ", " ")
//                .replace("                ", " ")
//                .replace("               ", " ")
//                .replace("              ", " ")
//                .replace("             ", " ")
//                .replace("            ", " ")
//                .replace("           ", " ")
//                .replace("          ", " ")
//                .replace("         ", " ")
//                .replace("        ", " ")
//                .replace("       ", " ")
//                .replace("      ", " ")
//                .replace("     ", " ")
//                .replace("    ", " ")
//                .replace("   ", " ")
//                .replace("  ", " ")
//                .replace("  ", " ")
//                .replace("  ", " ")
//                .replace("  ", " ")
//                .replace("  ", " ")
//                .replace("  ", " ")
//                .replace("-", "")
//                .replace("...", ".")
//                .replace("..", ".")
//                .replace(",,", ",")
//                .replace(", ", ",")
//                .replace("???", "?")
//                .replace("??", "?")
//                .replace("!!!", "!")
//                .replace("!!", "!")
//                .replace("<", "")
//                .replace(">", "")
//                .replace("'", "")
//                .replace("\"", "")
//                .replace("/", "")
//                .replace("+", "")
//                .replace("\n", "")
//                .replace("*", "")
//                .replace("`", "")
//                .replace("`", "").toLowerCase();
//
//    }
//
//
//    @Test
//    public void intelectualQuestionValidate() {
//        String dBAnswer = "Зат есім - заттың, құбылыстың атын білдіріп, кім? не? деген сұраққа жауап беретін сөз табы. Күнделікті өмірде кездесетін, әдеттегі жай нәрселерді ғана емес, табиғат пен қоғамдық өмірдегі ұшырасатын әр алуан құбылыстар мен уақиғаларды, ұғымдар мен түсініктерді де қамтиды..";
//        String userAnsw = "заттың, құбылыстың атын білдіріп, не? кім? деген сұраққа жауап беретін сөз табы.";
//        GsonResult gson = isIntelectualQuestionValidate(userAnsw, dBAnswer);
//
//        if (gson.getResult()) {
//            gson = (GsonResult) gson.getMessage();
//        }
//        System.out.println(gson.getResult() + " " + gson.getMessage());
//    }
//
//    private GsonResult isIntelectualQuestionValidate(String userAnsw, String dBAnswer) {
//        userAnsw = getReplaceSpecialChars(userAnsw);
//        dBAnswer = getReplaceSpecialChars(dBAnswer);
//        userAnsw = userAnsw.trim();
//        dBAnswer = dBAnswer.trim();
//
//        if (isNullOrEmpty(dBAnswer)) {
//            return getGsonResult(false, getGsonResult(false, "Деректер қорында жауап жазылмаған"));
//        }
//        if (isNullOrEmpty(userAnsw)) {
//            return getGsonResult(false, getGsonResult(true, 0));
//        }
//        if (userAnsw.equals(dBAnswer)) {
//            return getGsonResult(true, getGsonResult(true, 100));
//        }
//
//        String[] dBAnswerSentenceArr = dBAnswer.split("\\.");
//        String[] uAnswerSentenceArr = userAnsw.split("\\.");
//        Integer containsSentences = 0;
//
//        for (String dBAnswerSentence : dBAnswerSentenceArr) {
//            if (stringContainsItemFromList(dBAnswerSentence, uAnswerSentenceArr)) {
//                containsSentences++;
//            }
//        }
//
//        String[] dBAnswerWordsArr = dBAnswer.split(" ");
//        String[] uAnswerWordsArr = userAnsw.split(" ");
//
//
//        Integer containsWords = 0;
//        for (String dBAnswerWord : dBAnswerWordsArr) {
//            if (stringContainsItemFromList(dBAnswerWord, uAnswerWordsArr)) {
//                containsWords++;
//            }
//        }
//
//
//        return getGsonResult(false,
//                "Совпадение предложений <" + containsSentences + ">  из " + dBAnswerSentenceArr.length + " & " + uAnswerSentenceArr.length +
//                        getNewLine() + "Совпадение слов <" + containsWords + "> из " + dBAnswerWordsArr.length + " & " + uAnswerWordsArr.length);
//    }
//
//    private String getNewLine() {
//        return "\n";
//    }
//
//    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
//        for (int i = 0; i < items.length; i++) {
//            if (inputStr.trim().contains(items[i].trim())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//}
//
