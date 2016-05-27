/*
 * Copyright (C) 2016 Marchegiano Danilo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package d4n1x.photosocial.BO.impl;

import d4n1x.photosocial.BO.UserBO;
import d4n1x.photosocial.DAO.UserDAO;
import d4n1x.photosocial.domain.User;
import java.util.List;

/**
 *
 * @author Marchegiano Danilo
 */
public class UserBOImpl implements UserBO{

    private UserDAO userDAO;
    
    @Override
    public void addUser(User user) throws Exception {
        try {
            userDAO.addUser(user);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> allUser = null;
        try {
            allUser = userDAO.getAllUser();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return allUser;
    }

    @Override
    public User getUserById(long user_id) throws Exception {
        User user = null;
        try {
            user = userDAO.getUserById(user_id);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getUserByUname(String user_name) throws Exception {
        User user = null;
        try {
            user = userDAO.getUserByUname(user_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: getUserByUname");
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void modifyUser(User user) throws Exception {
        try {
            userDAO.modifyUser(user);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String user_name) throws Exception {
        try {
            userDAO.deleteUser(user_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }
    
    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
}
