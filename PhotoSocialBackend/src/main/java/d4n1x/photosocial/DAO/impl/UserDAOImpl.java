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
package d4n1x.photosocial.DAO.impl;

import d4n1x.photosocial.DAO.UserDAO;
import d4n1x.photosocial.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Marchegiano Danilo
 */
public class UserDAOImpl implements UserDAO{

    private SessionFactory factory;
    
    @Override
    public void addUser(User user) throws Exception {
        
        Transaction tx = null;
        
        Session session = factory.openSession();
        
        try{
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch(RuntimeException e){
            System.out.println("ERROR_DAO: Can't add new user");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public List<User> getAllUser() throws Exception {
        
        ArrayList<User> users = null;
        Query query = null;
        String HQL = "FROM User ORDER BY user_id";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            users = (ArrayList<User>) query.list();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find users");
            System.out.println(e);
        }finally{
            session.close();
        }
        return users;
    }

    @Override
    public User getUserById(long user_id) throws Exception {
        User user = null;
        Query query = null;
        String HQL = "FROM User where user_id = :user_id";
        
        Session session = factory.openSession();
        try {
            query = session.createQuery(HQL);
            query.setParameter("user_id", user_id);
            user = (User) query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find user by id");
            System.out.println(e);
        }finally{
            session.close();
        }
        return user;
    }

    @Override
    public User getUserByUname(String user_name) throws Exception {
        User user = null;
        Query query = null;
        String HQL = "FROM User where user_name = :user_name";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("user_name", user_name);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            System.out.println("ERROR_DAO: Can't find user by name");
            System.out.println(e);
        }finally{
            session.close();
        }
        return user;
    }

    @Override
    public void modifyUser(User user) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't modify user");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void deleteUser(String user_name) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        User user = getUserByUname(user_name);
        
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't delete user");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
