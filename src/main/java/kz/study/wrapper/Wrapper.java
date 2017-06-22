package kz.study.wrapper;

import kz.study.entity.*;
import kz.study.gson.*;
import org.jetbrains.annotations.Contract;

import javax.ws.rs.core.MultivaluedMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static kz.study.session.AnalizeSession.getTranslateString;
import static kz.study.util.DateUtil.dateToString;
import static kz.study.util.Util.getInscriptionByLang;
import static kz.study.util.Util.isNullOrEmpty;


/**
 * Created by a.amanzhol.
 */
public class Wrapper {

    public static GsonUsers wrapToGsonUsers(Users user) {
        if (user != null) {
            GsonUsers gson = new GsonUsers();
            gson.setuName(user.getuName());
            gson.setUserDetail(wrapToGsonUserDetail(user.getUserDetail()));
            gson.setRole(user.getGroupmembers().getGroups().getGName());
            return gson;
        }
        return null;
    }

    public static List<GsonUserDetail> wrapToGsonUserDetailList(List<UserDetail> list) {
        List<GsonUserDetail> result = new ArrayList<>();
        for (UserDetail detail : list) {
            result.add(wrapToGsonUserDetail(detail));
        }
        return result;
    }

    private static GsonUserDetail wrapToGsonUserDetail(UserDetail user) {
        if (user != null) {
            GsonUserDetail gson = new GsonUserDetail();
            gson.setuName(user.getuName().getuName());
            gson.setFirstname(user.getFirstname());
            gson.setLastname(user.getLastname());
            gson.setMiddlename(user.getMiddlename());
            gson.setEmail(user.getEmail());
            gson.setLocked(user.getLocked());
            return gson;
        }
        return null;
    }

    public static UserDetail wrapToGsonUserDetail(GsonUserDetail gson) {
        if (gson != null) {
            UserDetail userDetail = new UserDetail();
            userDetail.setuName(new Users(gson.getuName()));
            userDetail.setFirstname(gson.getFirstname());
            userDetail.setLastname(gson.getLastname());
            userDetail.setMiddlename(gson.getMiddlename());
            userDetail.setEmail(gson.getEmail());
            userDetail.setLocked(gson.getLocked() == null ? 0 : gson.getLocked());
            return userDetail;
        }
        return null;
    }

    public static List<GsonGroupmembers> wrapToGsonGroupmembersList(List<Groupmembers> list) {
        List<GsonGroupmembers> result = new ArrayList<>();
        for (Groupmembers groupmembers : list) {
            result.add(wrapToGsonGroupmembers(groupmembers));
        }
        return result;
    }

    private static GsonGroupmembers wrapToGsonGroupmembers(Groupmembers g) {
        if (g != null) {
            GsonGroupmembers gson = new GsonGroupmembers();
            gson.setgMember(g.getGroupmembersPK().getGMember());
            gson.setgName(g.getGroupmembersPK().getGName());
            return gson;
        }
        return null;
    }

    public static List<GsonGroups> wrapToGsonGroupsList(List<Groups> list) {
        List<GsonGroups> result = new ArrayList<>();
        for (Groups group : list) {
            result.add(wrapToGsonGroup(group));
        }
        return result;
    }

    private static GsonGroups wrapToGsonGroup(Groups group) {
        if (group != null) {
            GsonGroups gson = new GsonGroups();
            gson.setGDescription(group.getGDescription());
            gson.setGName(group.getGName());
            return gson;
        }
        return null;
    }

    public static List<Groupmembers> wrapToGroupmembersList(List<GsonGroupmembers> gsonList, String uName) {
        List<Groupmembers> result = new ArrayList<>();
        for (GsonGroupmembers gson : gsonList) {
            result.add(wrapToGroupmembers(gson, uName));
        }
        return result;
    }

    private static Groupmembers wrapToGroupmembers(GsonGroupmembers g, String uName) {
        if (g != null) {
            Groupmembers gson = new Groupmembers();
            gson.setGroupmembersPK(new GroupmembersPK(g.getgName(), uName));
            return gson;
        }
        return null;
    }

    public static List<GsonAllDic> wrapToGsonAlphLinksList(List<AlphLinks> list) {
        List<GsonAllDic> result = new ArrayList<>();
        for (AlphLinks s : list) {
            result.add(wrapToGsonAlphLinks(s));
        }
        return result;
    }

    public static GsonAllDic wrapToGsonAlphLinks(AlphLinks obj) {
        if (obj != null) {
            GsonAllDic gson = new GsonAllDic();
            gson.setId(obj.getId());
            gson.setValue(obj.getValue());
            gson.setAddValue(obj.getLetter());
            return gson;
        }
        return null;
    }

    public static List<GsonAllDic> wrapToGsonVideoLessonsList(List<VideoLessons> list) {
        List<GsonAllDic> result = new ArrayList<>();

        for (VideoLessons s : list) {
            result.add(wrapToGsonVideoLessons(s));
        }
        return result;
    }

    @Contract("null -> null")
    public static GsonAllDic wrapToGsonVideoLessons(VideoLessons obj) {
        if (obj != null) {
            GsonAllDic gson = new GsonAllDic();
            gson.setId(obj.getId());
            gson.setValue(obj.getValue());
            gson.setAddValue(obj.getDescription());
            return gson;
        }
        return null;
    }

    @Contract("null -> null")
    private static GsonAllDic wrapToGsonAudioLessons(AudioLessons obj) {
        if (obj != null) {
            GsonAllDic gson = new GsonAllDic();
            gson.setId(obj.getId());
            gson.setValue(obj.getValue());
            gson.setAddDopValue(obj.getParamId());
            return gson;
        }
        return null;
    }

    public static List<GsonAllDic> wrapToGsonAudioLessonsList(List<AudioLessons> list) {
        List<GsonAllDic> result = new ArrayList<>();

        for (AudioLessons s : list) {
            result.add(wrapToGsonAudioLessons(s));
        }
        return result;
    }

    public static List<GsonGameResult> wrapToGsonGameResultList(List<GameResult> list) {
        List<GsonGameResult> result = new ArrayList<>();

        for (GameResult s : list) {
            result.add(wrapToGsonGameResult(s));
        }
        return result;
    }

    public static GsonGameResult wrapToGsonGameResult(GameResult obj) {
        if (obj != null) {
            GsonGameResult gson = new GsonGameResult();
            gson.setId(obj.getId());
            UserDetail uDet = obj.getuName().getUserDetail();
            gson.setuName("");
            if (uDet != null) {
                gson.setuName(uDet.getLastname() + " " + uDet.getFirstname());
            }
            gson.setGameId(obj.getGameId().getName());
            gson.setgDate(dateToString(obj.getgDate()));
            //gson.setInfo(obj.getInfo());
            gson.setResult(obj.getResult());
            return gson;
        }
        return null;
    }


    public static GsonAdminValue wrapToGsonAdminValue(MultivaluedMap<String, String> formParams) {
        if (formParams != null) {
            GsonAdminValue gson = new GsonAdminValue();
            if (!isNullOrEmpty(formParams.getFirst("paramId"))) {
                gson.setParamId(formParams.getFirst("paramId"));
            }
            if (!isNullOrEmpty(formParams.getFirst("id"))) {
                gson.setId(formParams.getFirst("id"));
            }
            gson.setNameRus(formParams.getFirst("nameRus"));
            gson.setNameKaz(formParams.getFirst("nameKaz"));
            gson.setNameLan(formParams.getFirst("nameLan"));
            gson.setLink(formParams.getFirst("link"));
            gson.setDescRus(formParams.getFirst("descRus"));
            gson.setDescKaz(formParams.getFirst("descKaz"));
            gson.setDescLan(formParams.getFirst("descLan"));
            gson.setAudioLessonId(formParams.getFirst("audioLessonId"));
            gson.setImg(formParams.getFirst("img"));
            gson.setAudioLink(formParams.getFirst("audioLink"));
            gson.setQuestion(formParams.getFirst("question"));
            gson.setVar1(formParams.getFirst("var1"));
            gson.setVar2(formParams.getFirst("var2"));
            gson.setVar3(formParams.getFirst("var3"));
            gson.setVar4(formParams.getFirst("var4"));
            gson.setCh_var1(formParams.getFirst("ch_var1"));
            gson.setCh_var2(formParams.getFirst("ch_var2"));
            gson.setCh_var3(formParams.getFirst("ch_var3"));
            gson.setCh_var4(formParams.getFirst("ch_var4"));

            return gson;
        }

        return null;
    }

    public static GsonIntelectualQuestion wrapToGsonIntelectualQuestion(IntelectualQuestion obj) {
        if (obj != null) {
            GsonIntelectualQuestion gson = new GsonIntelectualQuestion();
            gson.setQuestion(obj.getQuestion());
            gson.setSrcId(obj.getSrcId());
            gson.setId(obj.getId().toString());
            gson.setDbAnsw(obj.getAnswer());

            return gson;
        }

        return null;
    }

    public static List<GsonIntelectualQuestion> wrapToGsonIntelectualQuestionList(List<IntelectualQuestion> list) {
        List<GsonIntelectualQuestion> gsonList = new ArrayList<>();
        for (IntelectualQuestion o : list) {
            gsonList.add(wrapToGsonIntelectualQuestion(o));
        }
        return gsonList;
    }

    public static GsonTreeDictionary wrapToGsonTreeDictionary(DLesson obj) {
        GsonTreeDictionary gson = new GsonTreeDictionary();
        gson.setId(obj.getId());
        gson.setNameRus(obj.getNameRus());
        gson.setNameKaz(obj.getNameKaz());
        gson.setNameLan(obj.getNameLan());
        if (obj.getdUroven() != null) {
            gson.setParentId(obj.getdUroven().getId().toString());
        }
        return gson;
    }

    public static List<GsonTreeDictionary> wrapToGsonDLessonList(List<DLesson> list) {
        List<GsonTreeDictionary> gsonList = new ArrayList<>();
        for (DLesson o : list) {
            gsonList.add(wrapToGsonTreeDictionary(o));
        }
        return gsonList;
    }

    private static GsonAllDic wrapToGsonGsonAllDic(DLesson obj) {
        GsonAllDic gson = new GsonAllDic();
        gson.setId(obj.getId());
        gson.setValue(getInscriptionByLang(obj.getNameRus(), obj.getNameKaz(), obj.getNameLan()));
        return gson;
    }

    public static List<GsonAllDic> wrapToGsonAllDicList(List<DLesson> list) {
        List<GsonAllDic> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToGsonGsonAllDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDVideoLessonDic(DVideoLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setValue(getInscriptionByLang(obj.getNameRus(), obj.getNameKaz(), obj.getNameLan()));
        gson.setDescValue(getInscriptionByLang(obj.getDescRus(), obj.getDescKaz(), obj.getDescLan()));
        gson.setLink(obj.getLink());
        return gson;
    }

    public static List<GsonAdminValue> wrapToDVideoLessonList(List<DVideoLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDVideoLessonDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDVideoLessonEditDic(DVideoLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setNameRus(obj.getNameRus());
        gson.setNameKaz(obj.getNameKaz());
        gson.setNameLan(obj.getNameLan());
        gson.setLink(obj.getLink());
        gson.setDescRus(obj.getDescRus());
        gson.setDescKaz(obj.getDescKaz());
        gson.setDescLan(obj.getDescLan());
        gson.setParamId(obj.getdLesson().getId());

        return gson;
    }

    public static List<GsonAdminValue> wrapToDVideoLessonEditList(List<DVideoLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDVideoLessonEditDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDAudioLessonDic(DAudioLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setValue(obj.getNameRus());
        gson.setDescValue(getInscriptionByLang(obj.getDescKaz(), obj.getDescKaz(), obj.getDescLan()));
        gson.setLink(obj.getLink());

        return gson;
    }

    public static List<GsonAdminValue> wrapToDAudioLessonEditList(List<DAudioLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDAudioEditLessonDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDAudioEditLessonDic(DAudioLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setNameRus(obj.getNameRus());
        gson.setDescKaz(obj.getDescKaz());
        gson.setDescLan(obj.getDescLan());
        gson.setParamId(obj.getdLesson().getId());
        gson.setLink(obj.getLink());
        return gson;
    }

    public static List<GsonAdminValue> wrapToDAudioLessonList(List<DAudioLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDAudioLessonDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDGrammarLessonDic(DGrammarLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setValue(obj.getNameRus());
        gson.setDescValue(obj.getDescRus());
        return gson;
    }

    public static List<GsonAdminValue> wrapToDGrammarLessonList(List<DGrammarLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDGrammarLessonDic(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDGrammarLessonEditDic(DGrammarLesson obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setNameRus(obj.getNameRus());
        gson.setDescRus(obj.getDescRus());
        gson.setParamId(obj.getdLessonId().getId());
        return gson;
    }

    public static List<GsonAdminValue> wrapToDGrammarLessonEditList(List<DGrammarLesson> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDGrammarLessonEditDic(s)));
        return gsonList;
    }

    public static GsonTreeDictionary wrapToGsonTreeDictionary(DUroven obj) {
        GsonTreeDictionary gson = new GsonTreeDictionary();
        gson.setId(obj.getId().toString());
        gson.setNameRus(obj.getNameRus());
        gson.setNameKaz(obj.getNameKaz());
        gson.setNameLan(obj.getNameLan());
        return gson;
    }

    public static List<GsonTreeDictionary> wrapToGsonDUrovenList(List<DUroven> list) {
        List<GsonTreeDictionary> gsonList = new ArrayList<>();
        for (DUroven o : list) {
            gsonList.add(wrapToGsonTreeDictionary(o));
        }
        return gsonList;
    }

    public static GsonTestType wrapToGsonTestType(TestType obj, String lang) {
        if (obj != null) {
            GsonTestType gson = new GsonTestType();
            gson.setLevel(obj.getLevel());
            if (lang != null && lang.equals("ln")) {
                gson.setName(getTranslateString(obj.getName()));
            } else {
                gson.setName(obj.getName());
            }
            gson.setIsPublic(obj.getIsPublic());
            gson.setParent(obj.getParent());
            gson.setId(obj.getId());
            gson.setType(obj.getType());
            return gson;
        }

        return null;
    }

    public static List<GsonTestType> wrapToGsonTestTypeList(List<TestType> list, String lang) {
        List<GsonTestType> gsonList = new ArrayList<>();
        for (TestType o : list) {
            gsonList.add(wrapToGsonTestType(o, lang));
        }
        return gsonList;
    }

    public static GsonTestQuestions wrapToGsonTestQuestions(TestQuestions obj, String lang) {
        if (obj != null) {
            GsonTestQuestions gson = new GsonTestQuestions();
            if (lang != null && lang.equals("ln")) {
                gson.setQuestion(getTranslateString(obj.getQuestion()));
                gson.setAnsw1(getTranslateString(obj.getAnsw1()));
                gson.setAnsw2(getTranslateString(obj.getAnsw2()));
                gson.setAnsw3(getTranslateString(obj.getAnsw3()));
                gson.setAnsw4(getTranslateString(obj.getAnsw4()));
            } else {
                gson.setQuestion(obj.getQuestion());
                gson.setAnsw1(obj.getAnsw1());
                gson.setAnsw2(obj.getAnsw2());
                gson.setAnsw3(obj.getAnsw3());
                gson.setAnsw4(obj.getAnsw4());
            }
            gson.setLink(obj.getLink());
            gson.setSrcId(obj.getSrcId());
            gson.setId(obj.getId().toString());
            return gson;
        }

        return null;
    }

    public static List<GsonTestQuestions> wrapToGsonTestQuestionsList(List<TestQuestions> list, String lang) {
        List<GsonTestQuestions> gsonList = new ArrayList<>();
        for (TestQuestions o : list) {
            gsonList.add(wrapToGsonTestQuestions(o, lang));
        }
        return gsonList;
    }

    private static GsonAdminValue wrapToDGameWord(DGameWord obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setQuestion(obj.getQuestion());
        gson.setVar1(obj.getdGameWordAnswer().getVar1());
        gson.setVar2(obj.getdGameWordAnswer().getVar2());
        gson.setVar3(obj.getdGameWordAnswer().getVar3());
        gson.setVar4(obj.getdGameWordAnswer().getVar4());
        gson.setAnswer(obj.getAnswer());

        return gson;
    }

    public static List<GsonAdminValue> wrapTDGameWordList(List<DGameWord> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDGameWord(s)));
        return gsonList;
    }

    private static GsonAdminValue wrapToDGameBrainGson(DGameBrain obj) {
        GsonAdminValue gson = new GsonAdminValue();
        gson.setId(obj.getId());
        gson.setDescRus(obj.getDescRus());

        return gson;
    }

    public static List<GsonAdminValue> wrapToDGameBrainList(List<DGameBrain> list) {
        List<GsonAdminValue> gsonList = new ArrayList<>();
        list.forEach(s -> gsonList.add(wrapToDGameBrainGson(s)));
        return gsonList;
    }
}