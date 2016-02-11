/**
 * AngularJS Tutorial 1
 * @author Nick Kaye <nick.c.kaye@gmail.com>
 */

/**
 * Main AngularJS Web Application
 */
var app = angular.module('tutorialWebApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/blog.html", controller: "BlogCtrl"})
    // Pages
    .when("/about", {templateUrl: "partials/about.html", controller: "PageCtrl"})
    .when("/contact", {templateUrl: "partials/contact.html", controller: "PageCtrl"})
    // Blog
    .when("/blog", {templateUrl: "partials/blog.html", controller: "BlogCtrl"})
    .when("/blog/add", {templateUrl: "partials/blog_add.html", controller: "BlogCtrl"})
    .when("/blog/post/:id", {templateUrl: "partials/blog_item.html", controller: "BlogViewCtrl"})
    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function ($scope, $location, $http) {
  console.log("Accessing "); console.log(location.hash);
  $http.defaults.useXDomain = true;

  $http.get('http://127.0.0.1\:4567/api/v1/posts').success(function (data) {
      $scope.posts = data;
    }).error(function (data, status) {
      console.log('Error ' + data)
    })

    $scope.createPost = function () {
      $http.post('http://127.0.0.1\:4567/api/v1/posts', $scope.post)
        .success(function (data) {
          $location.path('/');
      }).error(function (data, status) {
          console.log('Error ' + data)
      })
    }
});

app.controller('BlogViewCtrl', function ($scope, $routeParams, $http) {
  $http.get('http://127.0.0.1\:4567/api/v1/posts/' + $routeParams.id)
      .success(function(data) {
        $scope.post = data;
      }).error(function(data, status) {
        console.log('Error' + data + ' status: ' + status)
      })
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Accessing "); console.log(location.hash);

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});