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
import d4n1x.photosocial.BO.RoleBO;
import d4n1x.photosocial.BO.UserBO;
import d4n1x.photosocial.domain.Role;
import d4n1x.photosocial.utility.PasswordEncrypter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Marchegiano Danilo
 */
@RestController
public class AdminRest {
    
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
    @Qualifier("CategoryBO")
    private CategoryBO categoryBO;
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(value = "addRole")
    public HttpStatus addRole(@RequestParam String role_name){
        
        Role role = new Role();
        
        role.setRole_name(role_name);
        
        try {
            roleBO.addRole(role);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return HttpStatus.OK;
    }
    
    @RequestMapping("deleteRole")
    public HttpStatus deleteRole(@RequestParam String role_name){
        try {
            roleBO.deleteRole(role_name);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
    
    @RequestMapping("initSys")
    private HttpStatus initSys(){
        /*try {
            
        } catch (Exception e) {
            
        }*/
        
        return HttpStatus.OK;
    }
}
