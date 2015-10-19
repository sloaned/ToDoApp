$(document).ready(function(){
	
	$.ajax({
		url: '/todo',
		method: 'GET'
	}).then(function(todo){
		var obj = JSON.parse(JSON.stringify(todo));
		var item;
		for(var i = 0; i < obj.length; i++){
			item = obj[i];
			$("#toDoTable").append('<tr><td>' + (i+1) + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
		}
	});
});