angular.module("sprang.services", ["ngResource"]).
    factory('Story', function ($resource) {
        var Story = $resource('/api/v1/stories/:storyId', {storyId: '@id'});
        Story.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return Story;
    });

angular.module("sprang", ["sprang.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/stories', {templateUrl: 'views/stories/list.html', controller: StoryListController})
            .when('/stories/new', {templateUrl: 'views/stories/create.html', controller: StoryCreateController})
            .when('/stories/:storyId', {templateUrl: 'views/stories/detail.html', controller: StoryDetailController});
    });

function StoryListController($scope, Story) {
    $scope.stories = Story.query();
    
}

function StoryCreateController($scope, $routeParams, $location, Story) {

    $scope.story = new Story();

    $scope.save = function () {
    	$scope.story.$save(function (story, headers) {
    		toastr.success("Created");
            $location.path('/stories');
        });
    };
}


function StoryDetailController($scope, $routeParams, $location, Story) {
    var storyId = $routeParams.storyId;
    
    $scope.story = Story.get({storyId: storyId});

}
