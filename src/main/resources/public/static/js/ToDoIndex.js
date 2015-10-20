$(document).ready(function(){
	
	$.ajax({
		url: '/todo',
		method: 'GET'
	}).then(function(toDoList){
		var obj = JSON.parse(JSON.stringify(toDoList));
		//var obj = toDoList;
		var item;
		for(var i = 0; i < obj.length; i++){
			item = obj[i];
			$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
		}
	});
	
	$("#filterButton").click(function(event){
		event.preventDefault();
		$.ajax({
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			var choice = $("#filterSelect").val();
			$("#toDoTable tr").remove();
			switch(choice)
			{
				case("All Statuses"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
					}
					break;
				case("Incomplete"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.complete === false)
						{
							$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
						}
					}
					break;
				case("Complete"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.complete === true)
						{
							$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
						}
					}
					break;
				case("In Progress"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.inProgress === true)
						{
							$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
						}
					}
					break;
				case("Not In Progress"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.inProgress === false)
						{
							$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
						}
					}
					break;
				default:
					break;
			}
			
		});
	});
});