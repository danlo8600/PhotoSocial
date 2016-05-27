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

import d4n1x.photosocial.BO.RoleBO;
import d4n1x.photosocial.BO.UserBO;
import d4n1x.photosocial.DTO.RoleDTO;
import d4n1x.photosocial.DTO.UserDTO;
import d4n1x.photosocial.domain.Role;
import d4n1x.photosocial.domain.User;
import d4n1x.photosocial.utility.PasswordEncrypter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marchegiano Danilo
 */

@RestController
public class UserRest {
    
    @Autowired
    @Qualifier("UserBO")
    private UserBO userBO;
    
    @Autowired
    @Qualifier("RoleBO")
    private RoleBO roleBO;
    
    @Autowired
    @Qualifier("passwordEncrypter")
    private PasswordEncrypter encrypter;
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping("addUser")
    public void addUser(@RequestParam String uname, @RequestParam String pwd, 
                        @RequestParam String email){
        
        User usr = new User();
        
        ArrayList<Role> roles = new ArrayList<Role>();
        try {
            roles.add(roleBO.getRoleByName("ROLE_USER"));
            usr.setUser_name(uname);
            usr.setPassword(encrypter.pwdcrypt(pwd+uname));
            usr.setEmail(email);
            usr.setRoles(roles);
            userBO.addUser(usr);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping("modifyUser")
    public User modifyUser(@RequestParam String uname,
                           @RequestParam String pwd,
                           @RequestParam String email,
                           @RequestParam Date birthday,
                           @RequestParam String country,
                           @RequestParam String language,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String profile_picture){
        
        User usr = new User();
        
        ArrayList<Role> roles = new ArrayList<Role>();
        try {
            roles.add(roleBO.getRoleByName("ROLE_USER"));
            usr.setUser_name(uname);
            usr.setPassword(encrypter.pwdcrypt(pwd+uname));
            usr.setEmail(email);
            usr.setRoles(roles);
            usr.setBirthday(birthday);
            usr.setCountry(country);
            usr.setLanguage(language);
            usr.setName(name);
            usr.setSurname(surname);
            usr.setProfile_picture(profile_picture);
            userBO.modifyUser(usr);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    
    @RequestMapping("deleteUser")
    public void deleteUser(String user_name){
        try {
            userBO.deleteUser(user_name);
        } catch (Exception ex) {
            Logger.getLogger(UserRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping("getAllUsers")
    public List<UserDTO> getAllUsers(){
        UserDTO userDTO = null;
        RoleDTO roleDTO = null;
        List<User> users = null;
        ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
        
        try {
            users = userBO.getAllUser();
            for(User usr : users){
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
                
                usersDTO.add(userDTO);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersDTO;
    }
    
    @RequestMapping("getUserByUname")
    public UserDTO getUserByUname(String user_name){
        RoleDTO roleDTO = null;
        ArrayList<RoleDTO> roles = null;
        UserDTO userDTO = null;
        User user = null;
        
        try {
            user = userBO.getUserByUname(user_name);
            roles = new ArrayList<RoleDTO>();
            for(Role r : user.getRoles()){
                roleDTO = new RoleDTO(r.getRole_name());
                roles.add(roleDTO);
            }
            userDTO = new UserDTO(user.getName(), user.getSurname(),
                                  user.getCountry(), user.getLanguage(),
                                  user.getBirthday(), user.getReg_data(),
                                  user.getUser_name(), user.getEmail(),
                                  user.getProfile_picture(), roles);
        } catch (Exception ex) {
            Logger.getLogger(UserRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userDTO;
    }
}
