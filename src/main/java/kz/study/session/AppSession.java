/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.session;

import kz.study.entity.*;
import kz.study.gson.*;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static kz.study.util.DateUtil.dateToString;
import static kz.study.util.DateUtil.stringToSqlDate;
import static kz.study.util.Util.*;
import static kz.study.wrapper.Serialization.*;
import static kz.study.wrapper.Wrapper.*;


/**
 * @author a.amanzhol
 */
@Stateless
public class AppSession extends Utx {


    private static final Logger logger = LoggerFactory.getLogger(AppSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;


    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 30;

    private List<Integer> randList;

    public List<Words> getRandom10WordList() {
        List<Words> list = em.createNamedQuery("Words.findAll").setFirstResult(0).setMaxResults(100).getResultList();
        List<Words> result = new ArrayList<>();
        randList = new ArrayList<>();
        Integer cnt = 10 > list.size() - 1 ? list.size() - 1 : 10;
        for (int i = 0; i < cnt; i++) {
            Integer randIdx = getRandIdxWord(0, list.size() - 1);
            randList.add(randIdx);
            result.add(list.get(randIdx));
        }
        return result;
        /*List<Words> wordsList = new ArrayList<>();
        int i =1;
        wordsList.add(new Words("a"+(i++),"Сәлеметсізбе"));
        wordsList.add(new Words("a"+(i++),"Жаз"));
        wordsList.add(new Words("a"+(i++),"Көктем"));
        wordsList.add(new Words("a"+(i++),"Күз"));
        wordsList.add(new Words("a"+(i++),"Әке"));
        wordsList.add(new Words("a"+(i++),"Қазақстан"));
        wordsList.add(new Words("a"+(i++),"Қыс"));
        wordsList.add(new Words("a"+(i++),"Балапан"));
        wordsList.add(new Words("a"+(i++),"Мысық"));
        wordsList.add(new Words("a"+(i++),"Қазақ"));
        return wordsList;*/
    }

    public List<GsonTestQuestions> getRandom25Guestions(Integer srcId, Integer start, Integer count, String lang) {
        List<TestQuestions> list = getTestQuestionsList(srcId, 0, 100);

        List<GsonTestQuestions> result = new ArrayList<>();
        randList = new ArrayList<>();
        Integer cnt = 20 > list.size() - 1 ? list.size() - 1 : 20;
        for (int i = 0; i < cnt; i++) {
            Integer randIdx = getRandIdxWord(0, list.size() - 1);
            randList.add(randIdx);
            result.add(wrapToGsonTestQuestions(list.get(randIdx), lang));
        }

        /*for (int i = 1; i <= 55; i++) {
            TestQuestions t = new TestQuestions(getSequenceNextVal(), i + " Lorem Ipsum is simply dummy ?", "text of the printing ",
                    " typesetting industry", "standard dummy text", "ever since the 1500s", 2);

            result.add(t);
        }*/
        return result;
    }

    public GsonDatatableData getTestingListById(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 10;
        GsonDatatableData data = new GsonDatatableData();
        data.setData(getTestQuestionsList(srcId, start, count));
        data.setPos(start);
        data.setTotal_count(getTestQuestionsCount(srcId).intValue());
        return data;
    }

    private Integer getRandIdxWord(Integer minimum, Integer maximum) {
        Integer rand = getRandom(minimum, maximum);
        if (randList.contains(rand)) {
            return getRandIdxWord(minimum, maximum);
        }
        return rand;
    }

    private static Integer getRandom(Integer minimum, Integer maximum) {
        Random r = new Random();
        return r.nextInt((maximum - minimum) + 1) + minimum;
    }

    public List<Words> getTestTypeList() {
        return em.createNamedQuery("TestType.findAll").getResultList();
    }

    public List<TestType> getTestTypeListByIsPublic(Integer isPublic, String lang) {
        return wrapToGsonTestTypeList(em.createNamedQuery("TestType.findByIsPublic").setParameter("isPublic", isPublic).getResultList(), lang);
    }

    public List<GameResult> getGameResultList() {
        return wrapToGsonGameResultList(em.createNamedQuery("GameResult.findAll").setFirstResult(0).setMaxResults(10).getResultList());
    }

    public List<TestQuestions> getTestQuestionsList(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 12;

        return em.createNamedQuery("TestQuestions.findBySrcId").setParameter("srcId", srcId).setFirstResult(start).setMaxResults(count).getResultList();
    }

    public Long getTestQuestionsCount(Integer srcId) {
        return (Long) getSingleResultOrNull(em.createQuery("SELECT count(g) FROM TestQuestions g WHERE g.srcId = :srcId").setParameter("srcId", srcId));
    }

    public Integer getSequenceNextVal() {
        ArmaSequense sequense = (ArmaSequense) getSingleResultOrNull(em.createNamedQuery("ArmaSequense.findVal"));
        if (sequense == null) {
            sequense = new ArmaSequense(1, 0);
        }
        sequense.setVal(sequense.getVal() + 1);
        em.merge(sequense);

        return sequense.getVal();
    }

    public GsonResult setGameResult(Integer gameId, String type, String uName, Long result, String json) {

        GameResult obj = new GameResult();
        obj.setId(getSequenceNextVal());
        obj.setGameId(new TestType(gameId));
        obj.setResult(result);
        obj.setuName(new Users(uName));
        obj.setInfo(json);
        obj.setgDate(stringToSqlDate(dateToString(new java.util.Date(), "dd.MM.yyyy HH.mm.ss"), "dd.MM.yyyy HH.mm.ss"));
        em.merge(obj);

        switch (type) {
            case "intelectualTest":
                GsonIntelectualQuestion gson = wrapToGsonIntelectualQuestionByJsonString(json);
                return intelectualQuestionValidate(gson);
        }
        return getGsonResult(true, null);
    }

    public GsonResult removeQuestionById(Integer id) {
        Query q = em.createQuery("delete  FROM TestQuestions g WHERE g.id = :id").setParameter("id", id);
        q.executeUpdate();
        return getGsonResult(true, null);
    }

    public GsonResult saveQuestion(String json) {
        try {
            GsonTestQuestions gson = wrapToGsonTestQuestionsByJsonString(json);
            TestQuestions questions = new TestQuestions();
            questions.setAnsw1(gson.getAnsw1());
            questions.setAnsw2(gson.getAnsw2());
            questions.setAnsw3(gson.getAnsw3());
            questions.setAnsw4(gson.getAnsw4());
            questions.setQuestion(gson.getQuestion());
            questions.setSrcId(gson.getSrcId());

            if (isNullOrEmpty(gson.getId())) {
                questions.setId(getSequenceNextVal());
            } else {
                questions.setId(Integer.parseInt(gson.getId()));
            }
            em.merge(questions);
            return getGsonResult(true, questions);
        } catch (Exception e) {
            e.printStackTrace();
            return getGsonResult(false, e.toString());
        }
    }

    public GsonResult saveTestType(String json) {
        try {
            GsonTestType gson = wrapToGsonTestTypeByJsonString(json);
            TestType testType = new TestType();
            testType.setId(getSequenceNextVal());
            testType.setType(gson.getType());
            testType.setIsPublic(1);
            testType.setName(gson.getName());
            testType.setLevel(0);
            em.merge(testType);
            return getGsonResult(true, testType);
        } catch (Exception e) {
            e.printStackTrace();
            return getGsonResult(false, e.toString());
        }
    }

    public List<GsonIntelectualQuestion> getIntellectualGuestionsList(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 20;
        return wrapToGsonIntelectualQuestionList(
                em.createNamedQuery("IntelectualQuestion.findBySrcId")
                        .setParameter("srcId", srcId)
                        .setFirstResult(start)
                        .setMaxResults(count)
                        .getResultList());
    }

    private GsonResult intelectualQuestionValidate(GsonIntelectualQuestion gson) {
        try {
            for (GsonIntelectualQuestion obj : gson.getData()) {
                String userAnsw = obj.getAnsw();
                String answerDB = obj.getDbAnsw();

                if (isNullOrEmpty(answerDB)) {
                    obj.setDbAnsw("деректер қорында жауап берілмеген");
                } else {

                    obj.setDbAnsw(answerDB);
                    if (isNullOrEmpty(userAnsw)) {
                        obj.setResult(getGsonResult(false, "Сіз жауап бермедіңіз"));
                    } else {
                        obj.setResult(isIntelectualQuestionValidate(userAnsw, answerDB));
                    }
                }

            }
            return getGsonResult(true, gson);

        } catch (Exception e) {

            return getGsonResult(false, e.toString());
        }
    }

    private GsonResult isIntelectualQuestionValidate(String userAnsw, String dBAnswer) throws Exception {

        userAnsw = getReplaceSpecialChars(userAnsw);
        dBAnswer = getReplaceSpecialChars(dBAnswer);

        if (isNullOrEmpty(dBAnswer)) {
            return getGsonResult(false, "Деректер қорында жауап жазылмаған");
        }
        if (isNullOrEmpty(userAnsw)) {
            return getGsonResult(false, "0% <h1 style='color:red;'>Дұрыс емес</h1>");
        }
        if (userAnsw.equals(dBAnswer)) {
            return getGsonResult(true, "100% <h1 style='color:green;'>Дұрыс</h1>");
        }

        String userFirstFormSentence = getFirstFormString(userAnsw);
        String dbFirstFormSentence = getFirstFormString(dBAnswer);


        String vs = validateStrings(userFirstFormSentence, dbFirstFormSentence);
        //return getGsonResult(true, vs);
        return getGsonResult(true, userFirstFormSentence + getNewLine() + getNewLine() + dbFirstFormSentence + getNewLine() + vs);
    }


    private String validateStrings(String userFirstFormSentence, String dbFirstFormSentence) {
        String[] userWordsArr = userFirstFormSentence.toLowerCase().split(" ");
        String[] dbWordsArr = dbFirstFormSentence.toLowerCase().split(" ");

        Integer containsWords = 0;
        for (String dBAnswerWord : dbWordsArr) {
            if (stringContainsItemFromList(dBAnswerWord, userWordsArr)) {
                containsWords++;
            }
        }

        Integer allEq = 0;
        Integer trueIdxCnt = 0;
        Integer lastDBIdx = -1;

        for (String word : userWordsArr) {
            Integer dbWordIdx = getIndexInArray(dbWordsArr, word);
            if (dbWordIdx > 0) {
                allEq++;
                if (dbWordIdx > lastDBIdx) {
                    lastDBIdx = dbWordIdx;
                    trueIdxCnt++;
                }
            }
        }

        float result = 0;
        if (allEq > 0) {
            result = 100 / allEq * trueIdxCnt;
        }
        String resultStr = "";
        if (result > 80) {
            resultStr = "<h1 style='color:green;'>Дұрыс</h1>";
        } else {
            resultStr = "<h1 style='color:red;'>Дұрыс емес</h1>";
        }
        return getNewLine() + "Cөздер саны " + containsWords + ", деректер қорында " + userWordsArr.length + ", сәйкес " + dbWordsArr.length
                + getNewLine()
                + " allEquals = " + allEq
                + ", order true = " + trueIdxCnt
                + " false = " + (allEq - trueIdxCnt)
                + getNewLine()
                + " result = " + result +"%"
                + resultStr;

    }

    private Integer getIndexInArray(String[] arr, String word) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean stringContainsItemFromList(String inputStr, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (isStringEqualsString(inputStr, items[i])) {
                return true;
            }
        }
        return false;
    }

    private String getFirstFormString(String str) {
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split(" ");
        for (String word : arr) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(getWordByRemoveEnding(word));
        }
        return sb.toString();
    }

    private String getNewLine() {
        return "<br />";
    }


    private List<String> allEndingList = null;

    private String getWordByRemoveEnding(String str) {
        if (isNullOrEmpty(str) || str.contains(" ")) {
            return str;
        }

        if (allEndingList == null) {
            allEndingList = getFullEndingList();
        }

        String str2 = removeEdingByEndingList(allEndingList, str);
        if (!str.equals(str2)) {
            str = str2;
        }

        return str;
    }

    private String removeEdingByEndingList(List<String> list, String str) {
        if (list != null) {
            for (String ending : list) {
                if (isStringContainsEnding(str, ending)) {
                    String str2 = str.toLowerCase().replace(ending.toLowerCase(), "");
                    if (str2.length() > 2) {
                        return str2;
                    }
                    return str;
                }
            }
        }
        return str;
    }

    private static boolean isStringContainsEnding(String lStr, String rStr) {
        if (isNullOrEmpty(lStr) || isNullOrEmpty(rStr)) {
            return false;
        }
        lStr = lStr + ".";
        rStr = rStr + ".";
        return lStr.trim().toLowerCase().contains(rStr.trim().toLowerCase());
    }

    private static boolean isStringEqualsString(String lStr, String rStr) {
        if (isNullOrEmpty(lStr) || isNullOrEmpty(rStr)) {
            return false;
        }
        return lStr.trim().toLowerCase().equals(rStr.trim().toLowerCase());
    }

    private String getReplaceSpecialChars(String str) {
        return str
                .replace("\n", " ")
                .replace("-", " ")
                .replace(".", " ")
                .replace(",", " ")
                .replace("?", " ")
                .replace("!", " ")
                .replace("<", " ")
                .replace(">", " ")
                .replace("'", " ")
                .replace("\"", " ")
                .replace("/", " ")
                .replace("+", " ")
                .replace("\n", " ")
                .replace("*", " ")
                .replace("`", " ")
                .replace("                 ", " ")
                .replace("                ", " ")
                .replace("               ", " ")
                .replace("              ", " ")
                .replace("             ", " ")
                .replace("            ", " ")
                .replace("           ", " ")
                .replace("          ", " ")
                .replace("         ", " ")
                .replace("        ", " ")
                .replace("       ", " ")
                .replace("      ", " ")
                .replace("     ", " ")
                .replace("    ", " ")
                .replace("   ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace("  ", " ")
                .trim();

    }


    public List<DTestType> getDTestTypeList() {
        return em.createNamedQuery("DTestType.findAll").getResultList();
    }


    public List<String> getFullEndingList() {
        return em.createNativeQuery(" SELECT e.value  FROM (\n" +
                "         SELECT e.value, length(e.value) l FROM All_Ending e\n" +
                "         UNION\n" +
                "         SELECT e.value, length(e.value) l FROM D_Case_Ending e\n" +
                "         UNION\n" +
                "         SELECT e.value, length(e.value) + 10 l FROM Other_Ending e\n" +
                "         UNION\n" +
                "         SELECT e.value, length(e.value) l FROM All_Suffixes e\n" +
                "       ) e\n" +
                "  ORDER BY l DESC ").getResultList();
    }


    public GsonDatatableData getIntellectTestingListById(Integer srcId, Integer start, Integer count) {
        if (start == null) start = 0;
        if (count == null) count = start + 10;
        GsonDatatableData data = new GsonDatatableData();
        data.setData(getIntellectualGuestionsList(srcId, start, count));
        data.setPos(start);
        data.setTotal_count(getIntellectualQuestionsCount(srcId).intValue());
        return data;
    }


    public Long getIntellectualQuestionsCount(Integer srcId) {
        return (Long) getSingleResultOrNull(em.createQuery("SELECT count(g) FROM IntelectualQuestion g WHERE g.srcId = :srcId").setParameter("srcId", srcId));
    }


    public GsonResult saveIntellectQuestion(String json) {
        try {
            GsonIntelectualQuestion gson = wrapToGsonIntelectualQuestionByJsonString(json);
            IntelectualQuestion questions = new IntelectualQuestion();
            questions.setQuestion(gson.getQuestion());
            questions.setSrcId(gson.getSrcId());
            questions.setAnswer(gson.getDbAnsw());

            if (isNullOrEmpty(gson.getId())) {
                questions.setId(getSequenceNextVal());
                gson.setId(questions.getId().toString());
            } else {
                questions.setId(Integer.parseInt(gson.getId()));
            }
            em.merge(questions);
            return getGsonResult(true, gson);
        } catch (Exception e) {
            e.printStackTrace();
            return getGsonResult(false, e.toString());
        }
    }

    public GsonResult removeIntellectualQuestionById(Integer id) {
        Query q = em.createQuery("delete  FROM IntelectualQuestion g WHERE g.id = :id").setParameter("id", id);
        q.executeUpdate();
        return getGsonResult(true, null);
    }
}
