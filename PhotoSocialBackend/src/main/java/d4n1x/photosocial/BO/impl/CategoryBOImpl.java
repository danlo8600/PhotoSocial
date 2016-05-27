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

import d4n1x.photosocial.BO.CategoryBO;
import d4n1x.photosocial.DAO.CategoryDAO;
import d4n1x.photosocial.domain.Category;
import java.util.List;

/**
 *
 * @author Marchegiano Danilo
 */
public class CategoryBOImpl implements CategoryBO{

    private CategoryDAO categoryDAO;
    
    @Override
    public void addCategory(Category category) throws Exception {
        try {
            categoryDAO.addCategory(category);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public Category getCategoryById(long category_id) throws Exception {
        Category category = null;
        try {
            category = categoryDAO.getCategoryById(category_id);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String category_name) throws Exception {
        Category category = null;
        try {
            category = categoryDAO.getCategoryByName(category_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        List<Category> allCategory = null;
        try {
            allCategory = categoryDAO.getAllCategory();
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
        return allCategory;
    }

    @Override
    public void modifyCategory(Category category) throws Exception {
        try {
            categoryDAO.modifyCategory(category);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    @Override
    public void deleteCategory(String category_name) throws Exception {
        try {
            categoryDAO.deleteCategory(category_name);
        } catch (Exception e) {
            System.out.println("ERROR_BO: " + e.getMessage());
        }
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
}
