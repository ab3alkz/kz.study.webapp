package kz.study.session;

import kz.study.entity.Groupmembers;
import kz.study.entity.UserDetail;
import kz.study.entity.Users;
import kz.study.gson.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

import static kz.study.util.Util.*;
import static kz.study.wrapper.Serialization.wrapToGsonRegistrationByJsonString;
import static kz.study.wrapper.Serialization.wrapToGsonUserDetailByJsonString;
import static kz.study.wrapper.Wrapper.*;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Stateless
public class UserSession {

    @PersistenceContext(unitName = "study")
    private EntityManager em;

    public GsonUsers getGsonUser(String uName) {
        try {
            Users user = getUser(uName);
            return wrapToGsonUsers(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users getUser(String uName) {
        try {
            return (Users) getSingleResultOrNull(em.createNamedQuery("Users.findByUName").setParameter("uName", uName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GsonDatatableData getUsersDetailList(Integer start, Integer limit) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 15;
        }
        GsonDatatableData data = new GsonDatatableData();
        try {
            List<UserDetail> list = em.createNamedQuery("UserDetail.findAll")
                    .setFirstResult(start)
                    .setMaxResults(limit)
                    .getResultList();
            data.setData(wrapToGsonUserDetailList(list));
            data.setPos(start);
            Query q = em.createQuery(" SELECT count(u)  FROM UserDetail u ");
            Long recordSize = (Long) q.getSingleResult();
            data.setTotal_count(recordSize.intValue());

        } catch (NoResultException e) {
        }
        return data;
    }

    public List<GsonGroupmembers> getGroupMembersByUName(String uName) {
        List<GsonGroupmembers> result;
        try {
            List<Groupmembers> list = em.createNamedQuery("Groupmembers.findByGMember")
                    .setParameter("gMember", uName)
                    .getResultList();
            result = wrapToGsonGroupmembersList(list);
        } catch (NoResultException e) {
            result = new ArrayList<>();
        }

        return result;
    }

    public GsonResult editUser(MultivaluedMap<String, String> formParams) {
        GsonUserDetail gsonUserDetail = wrapToGsonUserDetailByJsonString(formParams.getFirst("json"));

        UserDetail userDetail = wrapToGsonUserDetail(gsonUserDetail);
        em.merge(userDetail);

        Query q = em.createQuery("DELETE FROM Groupmembers g where g.groupmembersPK.gMember  = :gMember")
                .setParameter("gMember", gsonUserDetail.getuName());
        q.executeUpdate();
        List<Groupmembers> list = wrapToGroupmembersList(gsonUserDetail.getRoles(), gsonUserDetail.getuName());
        for (Groupmembers g : list) {
            em.persist(g);
        }
        em.flush();
        return getGsonResult(true, null);
    }


    public GsonResult registration(MultivaluedMap<String, String> formParams) {
        try {
            String json = formParams.getFirst("json");
            GsonRegistration gson = wrapToGsonRegistrationByJsonString(json);
            if (!gson.getPassword().equals(gson.getPasswordс())) {
                return getGsonResult(false, "Құпия сөздер сәйкес келмейді");
            }
            if (isNullOrEmpty(gson.getuName())) {
                return getGsonResult(false, "Логинды енгізіңіз");
            }
            Users user = getUser(gson.getuName());
            if (user != null) {
                return getGsonResult(false, gson.getuName()+ " Логин жүйеде бар, басқасын енгізіңіз");
            }
            user = new Users(gson.getuName());
            user.setuPassword(gson.getPassword());
            UserDetail uDet = new UserDetail(new Users(gson.getuName()));
            uDet.setEmail(gson.getEmail());
            uDet.setFirstname(gson.getfName());
            uDet.setLastname(gson.getlName());
            user.setUserDetail(uDet);
            em.persist(user);
            return getGsonResult(true, null);
        } catch (Exception e) {
            return getGsonResult(false, e.toString());
        }
    }
}
