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
document.getElementById("photo_upload").onchange = function () {
    var reader = new FileReader();
    var image_field = document.getElementById("image");
    reader.onload = function (e) {
        var img = new Image();
        img.src = e.target.result;
        
        if(img.width > img.height){
            image_field.style.width = '80%';
        }else{
            image_field.style.height = '80%';
        }
        image_field.src = e.target.result;
    };
    
    // Read the image
    reader.readAsDataURL(this.files[0]);
    
    //Get the button and hide them
    var label = document.getElementById("reg_label");
    var div_button = document.getElementById("div_button");
    var button = document.getElementById("photo_upload");
    var img_cont = document.getElementById("img_cont");
    label.style.display = 'none';
    div_button.style.display = 'none';
    button.style.display = 'none';
    img_cont.style.backgroundColor = '#121212';
    img_cont.style.marginBottom = '20px';
    img_cont.style.paddingTop = '25px';
    img_cont.style.paddingBottom = '25px';
};
