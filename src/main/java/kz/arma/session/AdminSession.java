package kz.study.session;

import kz.study.entity.*;
import kz.study.gson.GsonGames;
import kz.study.gson.GsonGroups;
import kz.study.gson.GsonResult;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static kz.study.util.Crypt.MD5;
import static kz.study.util.DateUtil.stringToDate;
import static kz.study.util.DateUtil.stringToSqlDate;
import static kz.study.util.Util.*;
import static kz.study.wrapper.Wrapper.wrapToGsonGroupsList;

/**
 * Created by a.amanzhol on 17.11.2016.
 */
@Stateless
public class AdminSession {

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    @EJB
    UserSession userSession;

    public List<GsonGroups> getGroups() {

        List<GsonGroups> result;
        try {
            List<Groups> list = em.createNamedQuery("Groups.findAll")
                    .getResultList();
            result = wrapToGsonGroupsList(list);
        } catch (NoResultException e) {
            result = new ArrayList<>();
        }

        return result;
    }


    public GsonResult createUser(String uName, String newPass, String confirmPass, String uDesc) {

        if (isNullOrEmpty(uName)) {
            return getGsonResult(false, "Вы не ввели логин");
        } else if (isNullOrEmpty(newPass)) {
            return getGsonResult(false, "Вы не ввели пароль");
        } else if (isNullOrEmpty(confirmPass)) {
            return getGsonResult(false, "Вы не ввели пароль подтверждение");
        } else if (!newPass.equals(confirmPass)) {
            return getGsonResult(false, "Пароли не совпадают");
        }
        try {
            Users user = (Users) getSingleResultOrNull(em.createNamedQuery("Users.findByUName").setParameter("uName", uName));
            if (user != null) {
                return getGsonResult(false, "Пользователь с таким логином существует");
            }
            user = new Users();
            user.setuName(uName);
            user.setuPassword(MD5(newPass));
            user.setuDescription(getValueOrEmpty(uDesc));

            UserDetail detail = new UserDetail();
            detail.setuName(user);
            detail.setLocked(0);

            em.persist(user);
            em.persist(detail);
            return getGsonResult(true, uName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getGsonResult(false, "Ошибка при сохранений");
    }

    public GsonResult lockUser(String uName, Integer lock) {
        try {

            UserDetail userDetail = (UserDetail) getSingleResultOrNull(em.createNamedQuery("UserDetail.findByUName").setParameter("uName", new Users(uName)));
            if (userDetail == null) {
                return getGsonResult(false, "Пользователь не найден");
            }
            userDetail.setLocked(lock);
            //em.remove(users);
            return getGsonResult(true, null);
        } catch (NoResultException e) {
            e.printStackTrace();
            return getGsonResult(false, "Ошибка");
        }

    }

    public GsonResult resetPassword(String uName, String oldPass, String newPass, String confirmPass) {
        try {

            Users user = (Users) getSingleResultOrNull(em.createNamedQuery("Users.findByUName").setParameter("uName", uName));
            if (user == null) {
                return getGsonResult(false, "Пользователь " + uName + " не найден");
            }
            if (!user.getuPassword().equals(MD5(oldPass))) {
                return getGsonResult(false, "Ввели не правильный пароль");
            }
            if (!newPass.equals(confirmPass)) {
                return getGsonResult(false, "Пароли не совпадают");
            }
            user.setuPassword(MD5(newPass));
            return getGsonResult(true, null);
        } catch (NoResultException e) {
            e.printStackTrace();
            return getGsonResult(false, "Ошибка");
        }

    }

    public GsonResult addGame(GsonGames gson) {
        try {
            Games oldGame = getLastGame();
            if (oldGame != null) {
                oldGame.setArchive(1);
                em.merge(oldGame);
            }

            Games game = new Games();
            game.setId(createGuid());
            game.setName(gson.getName());
            game.setDateGame(stringToSqlDate(gson.getDateGame()));
            game.setDateAdd(new Date(new java.util.Date().getTime()));
            game.setArchive(0);
            em.persist(game);
            return getGsonResult(true, game.getId());
        } catch (Exception e) {
            return getGsonResult(false, e.toString());
        }
    }


    public GsonResult getLastGsonGame() {
        return getGsonResult(true, getLastGame());
    }

    public Games getLastGame() {
        return (Games) getSingleResultOrNull(em.createQuery("select g from Games g where g.archive != 1"));
    }

    public List<Games> getArchiveGameList() {
        return em.createQuery("select g from Games g where g.archive = 1").getResultList();
    }

    public List<Slide> getSlidesList(String gameId) {
        return em.createNamedQuery("Slide.findByIdGame").setParameter("idGame", gameId).getResultList();
    }


    public GsonResult createSlide(String slideType, String game) {
        try {
            Integer ord = (Integer) getSingleResultOrNull(
                    em.createQuery(" SELECT max(s.ord) FROM Slide s where s.idGame = :idGame ")
                            .setParameter("idGame", game)
            );
            if (ord == null) {
                ord = 1;
            }
            Slide slide = new Slide();
            slide.setId(createGuid());
            slide.setType(slideType);
            slide.setIdGame(game);
            slide.setOrd(ord);
            em.persist(slide);
            return getGsonResult(true, slide.getId());
        } catch (Exception e) {
            return getGsonResult(true, e.toString());
        }
    }
}
