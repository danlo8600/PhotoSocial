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

import d4n1x.photosocial.BO.RoleBO;
import d4n1x.photosocial.DAO.RoleDAO;
import d4n1x.photosocial.domain.Role;
import java.util.List;

/**
 *
 * @author Marchegiano Danilo
 */
public class RoleBOImpl implements RoleBO{

    private RoleDAO roleDAO;
    
    @Override
    public void addRole(Role role) throws Exception {
        try {
            roleDAO.addRole(role);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public void deleteRole(String role_name) throws Exception {
        try {
            roleDAO.deleteRole(role_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public Role getRoleById(long role_id) throws Exception {
        Role role = null;
        try {
            role = roleDAO.getRoleById(role_id);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() throws Exception {
        List<Role> allRoles = null;
        try {
            allRoles = roleDAO.getAllRoles();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return allRoles;
    }

    @Override
    public Role getRoleByName(String role_name) throws Exception {
        Role role = null;
        try {
            role = roleDAO.getRoleByName(role_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return role;
    }

    @Override
    public void modifyRole(Role role) throws Exception {
        try {
            roleDAO.modifyRole(role);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}
