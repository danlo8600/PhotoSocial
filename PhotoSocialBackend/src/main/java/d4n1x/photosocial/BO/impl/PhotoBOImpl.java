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

import d4n1x.photosocial.BO.PhotoBO;
import d4n1x.photosocial.DAO.PhotoDAO;
import d4n1x.photosocial.domain.Category;
import d4n1x.photosocial.domain.Photo;
import java.util.List;

/**
 *
 * @author Marchegiano Danilo
 */
public class PhotoBOImpl implements PhotoBO{

    private PhotoDAO photoDAO;
    
    @Override
    public Photo getPhotoByID(long photo_id) throws Exception {
        Photo photo = null;
        try {
            photo = photoDAO.getPhotoByID(photo_id);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return photo;
    }

    @Override
    public List<Photo> getPhotoByName(String photo_name) throws Exception {
        List<Photo> photoName = null;
        try {
            photoName = photoDAO.getPhotoByName(photo_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return photoName;
    }

    @Override
    public void deletePhoto(long photo_id) throws Exception {
        try {
            photoDAO.deletePhoto(photo_id);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public void modifyPhoto(Photo photo) throws Exception {
        try {
            photoDAO.modifyPhoto(photo);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    public PhotoDAO getPhotoDAO() {
        return photoDAO;
    }

    public void setPhotoDAO(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    @Override
    public int getLastPhotoId() throws Exception {
        int big_id = 0;
        try {
            big_id = photoDAO.getLastPhotoId();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return big_id;
    }

    @Override
    public void addPhoto(Photo photo) throws Exception {
        try {
            photoDAO.addPhoto(photo);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public List<Photo> getAllPhotos() throws Exception {
        List<Photo> all_photos = null;
        try {
            all_photos = photoDAO.getAllPhotos();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return all_photos;
    }

    @Override
    public List<Photo> getNewPhotos() throws Exception {
        List<Photo> new_photos = null;
        try {
            new_photos = photoDAO.getNewPhotos();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return new_photos;
    }

    @Override
    public List<Photo> getPopularPhotos() throws Exception {
        List<Photo> popular_photos = null;
        try {
            popular_photos = photoDAO.getPopularPhotos();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return popular_photos;
    }

    @Override
    public List<Photo> getPhotosFromCategory(Category cat) throws Exception {
        List<Photo> ph_fromCategory = null;
        try{
            ph_fromCategory = photoDAO.getPhotosFromCategory(cat);
        }catch(Exception e){
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return ph_fromCategory;
    }
    
}
