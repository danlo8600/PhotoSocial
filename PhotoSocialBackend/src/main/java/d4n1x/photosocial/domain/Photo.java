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
package d4n1x.photosocial.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Marchegiano Danilo
 */

@Entity
@Table(name = "photo")
public class Photo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photo_id;
    
    private String photo_name;
    
    private String photo_description;
    
    private long photo_likes;
    
    private long photo_views;
    
    private Date upload_data = Calendar.getInstance().getTime();
    
    @Lob
    @Column(nullable = false, unique = true)
    private String photo_file;
    
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private User user;
    
    @ManyToOne
    private Category category;

    public Photo() {
    }

    public void setPhoto_id(long photo_id) {
        this.photo_id = photo_id;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public void setPhoto_description(String photo_description) {
        this.photo_description = photo_description;
    }

    public void setPhoto_likes(long photo_likes) {
        this.photo_likes = photo_likes;
    }

    public void setPhoto_views(long photo_views) {
        this.photo_views = photo_views;
    }

    public void setUpload_data(Date upload_data) {
        this.upload_data = upload_data;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getPhoto_id() {
        return photo_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public String getPhoto_description() {
        return photo_description;
    }

    public long getPhoto_likes() {
        return photo_likes;
    }

    public long getPhoto_views() {
        return photo_views;
    }

    public Date getUpload_data() {
        return upload_data;
    }

    public String getPhoto_file() {
        return photo_file;
    }
    
    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.photo_id ^ (this.photo_id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.photo_name);
        hash = 59 * hash + Objects.hashCode(this.photo_description);
        hash = 59 * hash + (int) (this.photo_likes ^ (this.photo_likes >>> 32));
        hash = 59 * hash + (int) (this.photo_views ^ (this.photo_views >>> 32));
        hash = 59 * hash + Objects.hashCode(this.upload_data);
        hash = 59 * hash + Objects.hashCode(this.photo_file);
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Photo other = (Photo) obj;
        if (this.photo_id != other.photo_id) {
            return false;
        }
        if (this.photo_likes != other.photo_likes) {
            return false;
        }
        if (this.photo_views != other.photo_views) {
            return false;
        }
        if (!Objects.equals(this.photo_name, other.photo_name)) {
            return false;
        }
        if (!Objects.equals(this.photo_description, other.photo_description)) {
            return false;
        }
        if (!Objects.equals(this.photo_file, other.photo_file)) {
            return false;
        }
        if (!Objects.equals(this.upload_data, other.upload_data)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
}
