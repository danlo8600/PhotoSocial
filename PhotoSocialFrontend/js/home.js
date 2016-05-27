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

var module = angular.module("home",[])
    /* Home controller for Popular */
    .controller("ngPopCont",function($scope, $http){
        var getPop = "http://localhost:8080/PhotoSocial/getPopularPhotos";
            $http.get(getPop).success(function(data_pop) {
                    var aux_pop = [];
                    for (i = 0; i < 6 && i < data_pop.length; i++) {
                            aux_pop[i] = data_pop[i];
                    }
                    $scope.popular_photos = aux_pop;
            });
    })

    /* Home controller for New */
    .controller("ngNewCont",function($scope, $http){
        var getNew = "http://localhost:8080/PhotoSocial/getNewPhotos";
            $http.get(getNew).success(function(data_new) {
                    var aux_new = [];
                    for (i = 0; i < 6 && i < data_new.length; i++) {
                            aux_new[i] = data_new[i];
                    }
                    $scope.new_photos = aux_new;
            });
    });