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
package d4n1x.photosocial.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marchegiano Danilo
 */
public class UserDTO implements Serializable{
    
    private String name;
    
    private String surname;
    
    private String country;
    
    private String language;
    
    private Date birthday;
    
    private Date reg_data;
    
    private String user_name;
    
    private String email;
    
    private String profile_picture;

    private List<RoleDTO> roles;
    
    private List<PhotoDTO> photos;

    
    /* Set all data */
    public UserDTO(String name, String surname, String country, String language, 
                   Date birthday, Date reg_data, String user_name, String email, 
                   String profile_picture, List<RoleDTO> roles, 
                   List<PhotoDTO> photos) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
        this.reg_data = reg_data;
        this.user_name = user_name;
        this.email = email;
        this.profile_picture = profile_picture;
        this.roles = roles;
        this.photos = photos;
    }

    /* Only user data no roles and photos */

    public UserDTO(String name, String surname, String country, String language, 
                   Date birthday, Date reg_data, String user_name, String email, 
                   String profile_picture, List<RoleDTO> roles) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
        this.reg_data = reg_data;
        this.user_name = user_name;
        this.email = email;
        this.profile_picture = profile_picture;
        this.roles = roles;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getReg_data() {
        return reg_data;
    }

    public void setReg_data(Date reg_data) {
        this.reg_data = reg_data;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }
}
