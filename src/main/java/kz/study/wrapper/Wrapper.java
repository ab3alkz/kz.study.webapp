package kz.study.wrapper;

import kz.study.entity.*;
import kz.study.gson.*;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

import static kz.study.util.DateUtil.dateToString;


/**
 * Created by a.amanzhol.
 */
public class Wrapper {

    public static GsonUsers wrapToGsonUsers(Users user) {
        if (user != null) {
            GsonUsers gson = new GsonUsers();
            gson.setuName(user.getuName());
            gson.setUserDetail(wrapToGsonUserDetail(user.getUserDetail()));
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

        for(AudioLessons s :list) {
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


    public static GsonIntelectualQuestion wrapToGsonIntelectualQuestion(IntelectualQuestion obj) {
        if (obj != null) {
            GsonIntelectualQuestion gson = new GsonIntelectualQuestion();
            gson.setQuestion(obj.getQuestion());
            gson.setSrcId(obj.getSrcId());
            gson.setId(obj.getId());
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

    public static GsonTestType wrapToGsonTestType(TestType obj) {
        if (obj != null) {
            GsonTestType gson = new GsonTestType();
            gson.setLevel(obj.getLevel());
            gson.setName(obj.getName());
            gson.setIsPublic(obj.getIsPublic());
            gson.setId(obj.getId());
            gson.setType(obj.getType());
            return gson;
        }

        return null;
    }

    public static List<GsonTestType> wrapToGsonTestTypeList(List<TestType> list) {
        List<GsonTestType> gsonList = new ArrayList<>();
        for (TestType o : list) {
            gsonList.add(wrapToGsonTestType(o));
        }
        return gsonList;
    }
}
