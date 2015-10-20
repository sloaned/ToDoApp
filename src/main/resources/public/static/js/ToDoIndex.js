$(document).ready(function(){
	
	$.ajax({
		url: '/todo',
		method: 'GET'
	}).then(function(toDoList){
		//var obj = JSON.parse(JSON.stringify(toDoList));
		var obj = toDoList;
		var item;
		console.log(toDoList);
		for(var i = 0; i < obj.length; i++){
			item = obj[i];
			$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
		}
	});
});