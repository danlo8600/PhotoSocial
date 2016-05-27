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

import d4n1x.photosocial.DAO.RoleDAO;
import d4n1x.photosocial.domain.Role;
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
public class RoleDAOImpl implements RoleDAO{

    private SessionFactory factory;
    
    @Override
    public void addRole(Role role) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't add new role");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void deleteRole(String role_name) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        Role role = getRoleByName(role_name);
        
        try {
            tx = session.beginTransaction();
            session.delete(role);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't delete role");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public Role getRoleById(long role_id) throws Exception {
        Role role = null;
        Query query = null;
        String HQL = "FROM Role WHERE role_id = :role_id";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("role_id", role_id);
            role = (Role) query.uniqueResult();
        } catch (Exception e) {
            System.out.println("ERROR_DAO: Can't find role by id");
            System.out.println(e);
        }finally{
            session.close();
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() throws Exception {
        ArrayList<Role> roles = null;
        Query query = null;
        String HQL = "FROM Role ORDER BY role_id";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            roles = (ArrayList<Role>) query.list();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find roles");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return roles;
    }

    @Override
    public Role getRoleByName(String role_name) throws Exception {
        Role role = null;
        Query query = null;
        String HQL = "FROM Role WHERE role_name = :role_name";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("role_name", role_name);
            role = (Role) query.uniqueResult();
        } catch (Exception e) {
            System.out.println("ERROR_DAO: Can't find role by name");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return role;
    }

    @Override
    public void modifyRole(Role role) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(role);
            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR_DAO: Can't modify role");
            System.out.println(e);
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
