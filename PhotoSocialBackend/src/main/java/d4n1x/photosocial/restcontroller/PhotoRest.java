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
package d4n1x.photosocial.restcontroller;

import d4n1x.photosocial.BO.CategoryBO;
import d4n1x.photosocial.BO.PhotoBO;
import d4n1x.photosocial.BO.UserBO;
import d4n1x.photosocial.DTO.PhotoDTO;
import d4n1x.photosocial.DTO.RoleDTO;
import d4n1x.photosocial.DTO.UserDTO;
import d4n1x.photosocial.domain.Category;
import d4n1x.photosocial.domain.Photo;
import d4n1x.photosocial.domain.Role;
import d4n1x.photosocial.domain.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Marchegiano Danilo
 */
@RestController
public class PhotoRest {
    
    @Autowired
    @Qualifier("PhotoBO")
    private PhotoBO photoBO;
    
    @Autowired
    @Qualifier("CategoryBO")
    private CategoryBO categoryBO;
    
    @Autowired
    @Qualifier("UserBO")
    private UserBO userBO;
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(value = "addPhoto")
    public long addPhoto(@RequestParam MultipartFile photo_file,
                         @RequestParam String photo_name,
                         @RequestParam String photo_descrp,
                         @RequestParam String uname,
                         @RequestParam String category){
        
        Photo photo = null;
        
        
        try {
            photo = new Photo();
            photo.setPhoto_name(photo_name);
            photo.setPhoto_description(photo_descrp);
            photo.setPhoto_file("data:image/jpeg;base64," + Base64.getEncoder().
                                encodeToString(photo_file.getBytes()));
            photo.setCategory(categoryBO.getCategoryByName(category));
            photo.setUser(userBO.getUserById(1));
            photoBO.addPhoto(photo);
        } catch (IOException ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo.getPhoto_id();
    }
    
    @RequestMapping(value = "modifyPhoto")
    public long modifyPhoto(@RequestParam MultipartFile photo_file,
                            @RequestParam String photo_name,
                            @RequestParam String photo_descrp,
                            @RequestParam String uname,
                            @RequestParam String category,
                            @RequestParam long photo_id){
        
        Photo photo = null;
        
        try {
            Photo old_photo = photoBO.getPhotoByID(photo_id);
            photo = new Photo();
            photo.setPhoto_id(photo_id);
            photo.setPhoto_name(photo_name);
            photo.setPhoto_description(photo_descrp);
            photo.setPhoto_file("data:image/jpeg;base64," + Base64.getEncoder().
                                encodeToString(photo_file.getBytes()));
            photo.setCategory(categoryBO.getCategoryByName(category));
            photo.setUser(userBO.getUserByUname(uname));
            photoBO.modifyPhoto(photo);
        } catch (IOException ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo.getPhoto_id();
    }
    
    @RequestMapping("deletePhoto")
    public HttpStatus deletePhoto(long photo_id){
        try {
            photoBO.deletePhoto(photo_id);
        } catch (Exception ex) {
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HttpStatus.OK;
    }
    
    @RequestMapping(value="getPopularPhotos")
    public List<PhotoDTO> getPopularPhotos(HttpServletResponse hsr){
        List<Photo> popular_photos = null;
        ArrayList<PhotoDTO> popular_photosDTO = new ArrayList<PhotoDTO>();
        RoleDTO roleDTO = null;
        UserDTO userDTO = null;
        PhotoDTO photoDTO = null;
        
        hsr.setHeader("Access-Control-Allow-Headers", "Content-Type");
        hsr.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        hsr.setHeader("Access-Control-Allow-Origin", "*");
        
        try {
            popular_photos = photoBO.getPopularPhotos();
            for(Photo photo : popular_photos){
                User usr = photo.getUser();
                ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
                for(Role r : usr.getRoles()){
                    roleDTO = new RoleDTO(r.getRole_name());
                    roles.add(roleDTO);
                }
                userDTO = new UserDTO(usr.getName(), usr.getSurname(),
                                      usr.getCountry(), usr.getLanguage(),
                                      usr.getBirthday(), usr.getReg_data(),
                                      usr.getUser_name(), usr.getEmail(),
                                      usr.getProfile_picture(), roles);
            
                photoDTO = new PhotoDTO(photo.getPhoto_id(), 
                                        photo.getPhoto_name(), 
                                        photo.getPhoto_description(), 
                                        photo.getPhoto_likes(), 
                                        photo.getPhoto_views(), 
                                        photo.getUpload_data(), userDTO, 
                                        photo.getCategory().getCategory_name(),
                                        photo.getPhoto_file());
                
                popular_photosDTO.add(photoDTO);
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return popular_photosDTO;
    }
    
    @RequestMapping("getNewPhotos")
    public List<PhotoDTO> getNewPhots(HttpServletResponse hsr){
        List<Photo> new_photos = null;
        ArrayList<PhotoDTO> new_photosDTO = new ArrayList<PhotoDTO>();
        RoleDTO roleDTO = null;
        UserDTO userDTO = null;
        PhotoDTO photoDTO = null;
        
        hsr.setHeader("Access-Control-Allow-Headers", "Content-Type");
        hsr.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        hsr.setHeader("Access-Control-Allow-Origin", "*");
        
        try {
            new_photos = photoBO.getNewPhotos();
            for(Photo photo : new_photos){
                User usr = photo.getUser();
                ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
                for(Role r : usr.getRoles()){
                    roleDTO = new RoleDTO(r.getRole_name());
                    roles.add(roleDTO);
                }
                userDTO = new UserDTO(usr.getName(), usr.getSurname(),
                                      usr.getCountry(), usr.getLanguage(),
                                      usr.getBirthday(), usr.getReg_data(),
                                      usr.getUser_name(), usr.getEmail(),
                                      usr.getProfile_picture(), roles);
            
                photoDTO = new PhotoDTO(photo.getPhoto_id(), 
                                        photo.getPhoto_name(), 
                                        photo.getPhoto_description(), 
                                        photo.getPhoto_likes(), 
                                        photo.getPhoto_views(), 
                                        photo.getUpload_data(), userDTO, 
                                        photo.getCategory().getCategory_name(),
                                        photo.getPhoto_file());
                
                new_photosDTO.add(photoDTO);
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new_photosDTO;
    }
    
    @RequestMapping("getPhotoId")
    public PhotoDTO getPhotoId(long userID){
        
        Photo photo = null;
        UserDTO userDTO = null;
        PhotoDTO photoDTO = null;
        RoleDTO roleDTO = null;
        
        try {
            photo = photoBO.getPhotoByID(userID);
            User usr = photo.getUser();
            ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
            for(Role r : usr.getRoles()){
                    roleDTO = new RoleDTO(r.getRole_name());
                    roles.add(roleDTO);
                }
                userDTO = new UserDTO(usr.getName(), usr.getSurname(),
                                      usr.getCountry(), usr.getLanguage(),
                                      usr.getBirthday(), usr.getReg_data(),
                                      usr.getUser_name(), usr.getEmail(),
                                      usr.getProfile_picture(), roles);
            
            photoDTO = new PhotoDTO(photo.getPhoto_id(), 
                                        photo.getPhoto_name(), 
                                        photo.getPhoto_description(), 
                                        photo.getPhoto_likes(), 
                                        photo.getPhoto_views(), 
                                        photo.getUpload_data(), userDTO, 
                                        photo.getCategory().getCategory_name(), 
                                        photo.getPhoto_file());
        } catch (Exception ex) {
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return photoDTO;
    }
    
    @RequestMapping("getPhotos")
    public List<PhotoDTO> getPhotos(){
        List<Photo> all_photos = null;
        ArrayList<PhotoDTO> all_photosDTO = new ArrayList<PhotoDTO>();
        RoleDTO roleDTO = null;
        UserDTO userDTO = null;
        PhotoDTO photoDTO = null;
        
        try {
            all_photos = photoBO.getAllPhotos();
            for(Photo photo : all_photos){
                User usr = photo.getUser();
                ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
                for(Role r : usr.getRoles()){
                    roleDTO = new RoleDTO(r.getRole_name());
                    roles.add(roleDTO);
                }
                userDTO = new UserDTO(usr.getName(), usr.getSurname(),
                                      usr.getCountry(), usr.getLanguage(),
                                      usr.getBirthday(), usr.getReg_data(),
                                      usr.getUser_name(), usr.getEmail(),
                                      usr.getProfile_picture(), roles);
            
                photoDTO = new PhotoDTO(photo.getPhoto_id(), 
                                        photo.getPhoto_name(), 
                                        photo.getPhoto_description(), 
                                        photo.getPhoto_likes(), 
                                        photo.getPhoto_views(), 
                                        photo.getUpload_data(), userDTO, 
                                        photo.getCategory().getCategory_name(),
                                        photo.getPhoto_file());
                
                all_photosDTO.add(photoDTO);
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all_photosDTO;
    }
    
    @RequestMapping("getPhotosFromCategory")
    public List<PhotoDTO> getPhotosFromCategory(String cat){
        List<Photo> photosInCategory = null;
        ArrayList<PhotoDTO> photosInCategoryDTO = new ArrayList<PhotoDTO>();
        RoleDTO roleDTO = null;
        UserDTO userDTO = null;
        PhotoDTO photoDTO = null;
        
        try{
            Category category = categoryBO.getCategoryByName(cat);
            photosInCategory = photoBO.getPhotosFromCategory(category);
            ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
            for(Photo photo : photosInCategory){
                User usr = photo.getUser();
               for(Role r : usr.getRoles()){
                    roleDTO = new RoleDTO(r.getRole_name());
                    roles.add(roleDTO);
                }
                userDTO = new UserDTO(usr.getName(), usr.getSurname(),
                                      usr.getCountry(), usr.getLanguage(),
                                      usr.getBirthday(), usr.getReg_data(),
                                      usr.getUser_name(), usr.getEmail(),
                                      usr.getProfile_picture(), roles);
            
                photoDTO = new PhotoDTO(photo.getPhoto_id(), 
                                        photo.getPhoto_name(), 
                                        photo.getPhoto_description(), 
                                        photo.getPhoto_likes(), 
                                        photo.getPhoto_views(), 
                                        photo.getUpload_data(), userDTO, 
                                        photo.getCategory().getCategory_name(),
                                        photo.getPhoto_file());
                
                photosInCategoryDTO.add(photoDTO);
            }
        }catch(Exception ex){
            Logger.getLogger(PhotoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photosInCategoryDTO;
    }
}
