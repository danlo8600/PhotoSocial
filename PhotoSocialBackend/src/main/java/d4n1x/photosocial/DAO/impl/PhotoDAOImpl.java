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

import d4n1x.photosocial.DAO.PhotoDAO;
import d4n1x.photosocial.domain.Category;
import d4n1x.photosocial.domain.Photo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marchegiano Danilo
 */
public class PhotoDAOImpl implements PhotoDAO{

    private SessionFactory factory;
    
    @Override
    public Photo getPhotoByID(long photo_id) throws Exception {
        Photo photo = null;
        Query query = null;
        String HQL = "FROM Photo WHERE photo_id = :photo_id";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("photo_id", photo_id);
            photo = (Photo) query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find photo by id");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return photo;
    }

    @Override
    public List<Photo> getPhotoByName(String photo_name) throws Exception {
        ArrayList<Photo> photos = null;
        Query query = null;
        String HQL = "FROM Photo WHERE photo_name = :photo_name";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            query.setParameter("photo_name", photo_name);
            photos = (ArrayList<Photo>) query.uniqueResult();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find photo by name");
            System.out.println(e);
        }finally{
            session.close();
        }
        
        return photos;
    }

    @Override
    public void deletePhoto(long photo_id) throws Exception {
        Transaction tx = null;
        Photo photo = getPhotoByID(photo_id);
        
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.delete(photo);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't delete photo");
            System.out.println(e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void modifyPhoto(Photo photo) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(photo);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't modify photo");
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

    @Override
    public int getLastPhotoId() throws Exception {
        int big_id = 0;
        Query query = null;
        String HQL = "SELECT MAX(photo_id) FROM Photo";
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            big_id = new Integer(query.uniqueResult().toString());
        } catch(ClassCastException cce){
            System.out.println("WARNING_DAO: Can't find the calimum. Return 0");
            System.out.println("WARNING_DAO: " + cce);
            return 0;
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find the calimum");
            System.out.println("ERROR_DAO: " + e);
        } finally{
            session.close();
        }
        return big_id;
    }

    @Override
    public void addPhoto(Photo photo) throws Exception {
        Transaction tx = null;
        Session session = factory.openSession();
        
        try {
            tx = session.beginTransaction();
            session.save(photo);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't add photo");
            System.out.println("ERROR_DAO: " + e);
            tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public List<Photo> getAllPhotos() throws Exception {
        Query query = null;
        List<Photo> all_photos = null;
        String HQL = "FROM Photo";
        
        Session session = factory.openSession();
        
        try {
            query = session.createQuery(HQL);
            all_photos = (List<Photo>) query.list();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: Can't find all photos");
            System.out.println("ERROR_DAO: " + e);
        }finally{
            session.close();
        }
        return all_photos;
    }
    
    @Override
    public List<Photo> getNewPhotos() throws Exception {
        List<Photo> new_photos = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -12);
        
        Session session = factory.openSession();
        
        try {
            Criteria cr = session.createCriteria(Photo.class);
            cr.add(Restrictions.gt("upload_data", cal.getTime()));
            cr.setMaxResults(10);
            new_photos = (List <Photo>) cr.list();
        } catch (RuntimeException e) {
            System.out.println("ERROR_DAO: " + e.getMessage());
        } finally{
            session.close();
        }
        return new_photos;
    }

    @Override
    public List<Photo> getPopularPhotos() throws Exception {
        Query query = null;
        List<Photo> new_photos = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -24);
        String HQL = "FROM Photo p WHERE p.upload_data >= :cal ORDER BY p.photo_likes DESC";
        Session session = factory.openSession();
       
        try {
            query = session.createQuery(HQL);
            query.setCalendarDate("cal", cal);
            new_photos = (List<Photo>) query.list();
        } catch (RuntimeException e) {
            
        }finally{
            session.close();
        }
        return new_photos;
    }

    @Override
    public List<Photo> getPhotosFromCategory(Category cat) throws Exception {
        List<Photo> ph_from_category = null;
        Query query = null;
        String HQL = "FROM Photo WHERE category_category_id = :category_id ORDER BY upload_data DESC";
                
        Session session = factory.openSession();
        
        try{
            query = session.createQuery(HQL);
            query.setParameter("category_id", cat.getCategory_id());
            ph_from_category = (List<Photo>) query.list();
        }catch(RuntimeException e){
            System.out.println("ERROR_DAO: Can't find photo form " + cat.getCategory_name());
            System.out.println("ERROR_DAO: " + e.getMessage());
        }finally{
            session.close();
        }
        return ph_from_category;
    }
}
