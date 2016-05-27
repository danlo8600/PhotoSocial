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

var main = angular.module("routingApp",["ngRoute", "home", "popular", "new", "categories"]);

main.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/home', {
        templateUrl: 'partials/home.html',
    }).
    when('/popular', {
        templateUrl: 'partials/popular.html',
    }).
    when('/new', {
        templateUrl: 'partials/new.html',
    }).
    when('/admin', {
        templateUrl: 'partials/admin.html',
    }).
    when('/account', {
        templateUrl: 'partials/account.html',
    }).
    when('/upload', {
        templateUrl: 'partials/upload.html',
    }).
    when('/registration', {
        templateUrl: 'partials/registration.html',
    }).
    when('/login', {
        templateUrl: 'partials/login.html',
    }).
    otherwise({
        redirectTo: '/home'
    });
}]);