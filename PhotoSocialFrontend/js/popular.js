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

var module = angular.module("popular",[])
    /* Popular Photos controller */
    .controller('ngPopular', function ($scope, $http){
            var getPop = "http://localhost:8080/PhotoSocial/getPopularPhotos";
            $http.get(getPop).success(function(data_pop) {
                    $scope.popular_photos = data_pop;
            });
            var getCat = "http://localhost:8080/PhotoSocial/getCategories";
            $http.get(getCat).success(function(data_cat) {
                    $scope.categories = data_cat;
            });
    });