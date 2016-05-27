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
import d4n1x.photosocial.DTO.CategoryDTO;
import d4n1x.photosocial.domain.Category;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
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
public class CategoryRest {
    
    @Autowired
    @Qualifier("CategoryBO")
    CategoryBO categoryBO;
    
    @RequestMapping(value = "addCategory")
    public Category addCategory(@RequestParam String category_name){
        
        Category category = new Category();
        
        category.setCategory_name(category_name);
        
        try {
            categoryBO.addCategory(category);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return category;
    }
    
    @RequestMapping("getCategories")
    public ArrayList<CategoryDTO> getCategories(HttpServletResponse hsr){
        CategoryDTO categoryDTO = null;
        ArrayList<Category> categoriesBO = null;
        ArrayList<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();
        
        hsr.setHeader("Access-Control-Allow-Headers", "Content-Type");
        hsr.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        hsr.setHeader("Access-Control-Allow-Origin", "*");
        
        try {
            categoriesBO = (ArrayList<Category>) categoryBO.getAllCategory();
            System.out.println(categoriesBO);
            for(Category cat : categoriesBO){
                categoryDTO = new CategoryDTO(cat.getCategory_name());
                categoriesDTO.add(categoryDTO);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categoriesDTO;
    }
    
    @RequestMapping("modifyCategory")
    public HttpStatus modifyCategory(@RequestParam String category_name){
        try {
            categoryBO.deleteCategory(category_name);
        } catch (Exception ex) {
            Logger.getLogger(CategoryRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return HttpStatus.OK;
    }
    
    @RequestMapping("deleteCategory")
    public HttpStatus deleteCategory(@RequestParam String category_name){
        try {
            categoryBO.deleteCategory(category_name);
        } catch (Exception ex) {
            Logger.getLogger(AdminRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HttpStatus.OK;
    }
}
