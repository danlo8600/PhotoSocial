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

import d4n1x.photosocial.DAO.CategoryDAO;
import d4n1x.photosocial.domain.Category;
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
public class CategoryDAOImpl implements CategoryDAO{

    private SessionFactory factory;
    
    @Override
    public void addCategory(Category category) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't insert new category");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public Category getCategoryById(long category_id) throws Exception {
        Category category = null;
        Query query = null;
        String HQL = "FROM Category WHERE category_id = :category_id";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("category_id", category_id);
            category = (Category) query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find category by id");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return category;
    }

    @Override
    public Category getCategoryByName(String category_name) throws Exception {
        Category category = null;
        Query query = null;
        String HQL = "FROM Category WHERE category_name = :category_name";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("category_name", category_name);
            category = (Category) query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find category by name");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return category;
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        ArrayList<Category> categories = null;
        Query query = null;
        String HQL = "FROM Category ORDER BY category_name";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            categories = (ArrayList<Category>) query.list();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find categories");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return categories;
    }

    @Override
    public void modifyCategory(Category category) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't modify category");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void deleteCategory(String category_name) throws Exception {
        Transaction tx = null;
        Category category = getCategoryByName(category_name);
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.delete(category);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't delete category");
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
