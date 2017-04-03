/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author админ
 */
public class AccessManager {
    /*
    public static Boolean checkPermission(HttpSession session, Integer pageId) {
        try {
            Users user = (Users) session.getAttribute("user");
            if (user != null) {
                Collection<UsersRole> roles = user.getUsersRoleCollection();
                if (!roles.isEmpty()) {
                    for (UsersRole role : roles) {
                        Collection<RolePageAccess> rpa = role.getDRoleId().getRolePageAccessList();
                        return getUserPage(rpa, pageId) != null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static DPage getUserPage(Collection<RolePageAccess> rpa, Integer page) {
        try {
            if (!rpa.isEmpty()) {
                for (RolePageAccess item : rpa) {
                    if (item.getDPageId().getId().equals(page.longValue())) {
                        return item.getDPageId();
                    }

                }
            }
        } catch (Exception e) {
        }

        return null;
    }
    */
}
