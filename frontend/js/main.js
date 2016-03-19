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
    .when("/blog/add", {templateUrl: "partials/blog_add.html", controller: "BlogPostAddCtrl"})
    .when("/blog/post/:id", {templateUrl: "partials/blog_item.html", controller: "BlogViewCtrl"})
    .when("/blog/post/edit/:id", {templateUrl: "partials/blog_edit.html", controller: "BlogEditCtrl"})
    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function ($scope, $location, $http, $filter) {
  console.log("Accessing "); console.log(location.hash);
  $http.defaults.useXDomain = true;

  $http.get('http://127.0.0.1\:4567/api/v1/posts')
    .success(function (data) {
      $scope.posts = data;
    }).error(function (data, status) {
      console.log('Error ' + data)
    })
});

app.controller('BlogPostAddCtrl', function ($scope, $location, $http) {
  console.log("Accessing "); console.log(location.hash);
  $http.defaults.useXDomain = true;

  $scope.createPost = function () {
    $http.defaults.headers.post = {};
    $http.post('http://127.0.0.1\:4567/api/v1/posts', $scope.post)
      .success(function (data) {
        $location.path('/');
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
  }
});

app.controller('BlogViewCtrl', function ($scope, $routeParams, $location, $http) {
  $http.get('http://127.0.0.1\:4567/api/v1/posts/' + $routeParams.id)
      .success(function(data) {
        $scope.post = data;
      }).error(function(data, status) {
        console.log('Error' + data + ' status: ' + status)
      })

  $http.get('http://127.0.0.1\:4567/api/v1/comments/' + $routeParams.id)
    .success(function(data) {
      $scope.comments = data;
    }).error(function(data, status) {
      console.log('Error' + data + ' status: ' + status)
    })

  $scope.addComment = function() {
    $http.defaults.headers.post = {};
    $scope.comment.postId = $routeParams.id;
    $http.post('http://127.0.0.1\:4567/api/v1/comments', $scope.comment)
      .success(function (data) {
        $scope.$apply();
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
  }

  $scope.go = function ( path ) {
    $location.path( path + $routeParams.id );
  }
});

app.controller('BlogEditCtrl', function ($scope, $routeParams, $http, $location) {
  $http.defaults.useXDomain = true;

  $http.get('http://127.0.0.1\:4567/api/v1/posts/' + $routeParams.id)
    .success(function(data) {
      $scope.post = data;
    }).error(function(data, status) {
      console.log('Error' + data + ' status: ' + status)
    })

  $scope.editPost = function () {
    $http.defaults.headers.post = {};
    $http.post('http://127.0.0.1\:4567/api/v1/posts/' + $routeParams.id, $scope.post)
      .success(function (data) {
        $location.path('/blog/post/' + $routeParams.id );
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
  }
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