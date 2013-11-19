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
            .when('/stories/:storyId', {templateUrl: 'views/stories/detail.html', controller: StoryDetailController});
    });

function StoryListController($scope, Story) {
    $scope.stories = Story.query();
    
}

function StoryDetailController($scope, $routeParams, $location, Story) {
    var storyId = $routeParams.storyId;

    if (storyId === 'new') {
        $scope.story = new Story();
    } else {
        $scope.story = Story.get({storyId: storyId});
    }

    $scope.save = function () {
        if ($scope.story.isNew()) {
            $scope.story.$save(function (story, headers) {
                toastr.success("Created");
                var location = headers('Location');
                var id = location.substring(location.lastIndexOf('/') + 1);
                $location.path('/stories/' + id);
            });
        } else {
            $scope.story.$update(function() {
                toastr.success("Updated");
            });
        }
    };
}
