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


/**
 *
 * @author Marchegiano Danilo
 */
public class PhotoDTO implements Serializable{
    
    private long photo_id;
    
    private String photo_name;
    
    private String photo_description;
    
    private long photo_likes;
    
    private long photo_views;
    
    private Date upload_data;
    
    private UserDTO userDTO;
    
    private String category;
    
    private String photo_file;
    
    public PhotoDTO(long photo_id, String photo_name, String photo_description, 
                    long photo_likes, long photo_views, Date upload_data, 
                    UserDTO userDTO, String category, String photo_file) {
        this.photo_id = photo_id;
        this.photo_name = photo_name;
        this.photo_description = photo_description;
        this.photo_likes = photo_likes;
        this.photo_views = photo_views;
        this.upload_data = upload_data;
        this.userDTO = userDTO;
        this.category = category;
        this.photo_file = photo_file;
    }

    public long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(long photo_id) {
        this.photo_id = photo_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getPhoto_description() {
        return photo_description;
    }

    public void setPhoto_description(String photo_description) {
        this.photo_description = photo_description;
    }

    public long getPhoto_likes() {
        return photo_likes;
    }

    public void setPhoto_likes(long photo_likes) {
        this.photo_likes = photo_likes;
    }

    public long getPhoto_views() {
        return photo_views;
    }

    public void setPhoto_views(long photo_views) {
        this.photo_views = photo_views;
    }

    public Date getUpload_data() {
        return upload_data;
    }

    public void setUpload_data(Date upload_data) {
        this.upload_data = upload_data;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto_file() {
        return photo_file;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }
}
