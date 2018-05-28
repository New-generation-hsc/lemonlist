var app = angular.module('APP', []);

app.controller('Controller', ['$scope', function($scope){

	function Task (content, create_time, deadline) {
		this.content = content;
		this.crt_time = create_time;
		this.deadline = deadline;
		this.tags = []

		this.addTag = function (tag) {
			this.push(tag);
		}

		this.removeTag = function (tag) {
			this.removeTag(tag);
		}

		this.updateContent = function (content) {
			this.content = content;
		}

		this.updateDeadline = function (deadline) {
			this.deadline = deadline;
		}
	}
	
	$scope.tasks = []; // the all tasks that will show in the page

	$scope.postTask = function () {
		var content = $scope.task_text;
		var create_time = new Date();
		var deadline = $scope.datetime;

		$scope.tasks.push(new Task(content, create_time, deadline));
	}

	$scope.editTask = function () {
		$(".main-body").animate({marginLeft : "-268px"}, 1500);
		$(".task-edit").animate({right : "0"}, 2000);
	}
}]);