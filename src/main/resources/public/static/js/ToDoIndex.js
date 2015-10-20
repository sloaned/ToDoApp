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
			var filtered = [];
			switch(choice)
			{
				case("All statuses"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						filtered.push(item);
					}
					break;
				case("Incomplete"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.complete === false)
						{
							filtered.push(item);
						}
					}
					break;
				case("Complete"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.complete === true)
						{
							filtered.push(item);
						}
					}
					break;
				case("In Progress"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.inProgress === true)
						{
							filtered.push(item);
						}
					}
					break;
				case("Not In Progress"):
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						if(item.inProgress === false)
						{
							filtered.push(item);
						}
					}
					break;
				case("Past Due"):
					var today = new Date();
					for(var i = 0; i < obj.length; i++){
						item = obj[i];
						var date = item.dueDate;
						var month = date.substring(0, 2);
						var day = date.substring(3, 5);
						var year = date.substring(6, 10);
						month = parseInt(month);
						day = parseInt(day);
						year = parseInt(year);
						month--;
						var taskDate = new Date();
						taskDate.setFullYear(year, month, day);
						if(taskDate < today && item.complete === false)
						{
							filtered.push(item);
						}
					}
					break;
				default:
					break;
			}
			if($("#nameFilter").val() != "")
			{
				var name = $("#nameFilter").val();
				for(var i = 0; i < filtered.length; i++)
				{
					item = filtered[i];
					if(item.assignedUser === name)
					{
						$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');
					}
				}
			}
			else
			{
				for(var i = 0; i < filtered.length; i++)
				{
					item = filtered[i];
					$("#toDoTable").append('<tr><td>' + item.taskNum + '</td><td>' + item.assignedUser + '</td><td>' + item.task + '</td><td>' + item.dueDate + '</td><td>' + item.description + '</td><td>' + item.inProgress + '</td><td>' + item.complete + '</td></tr>');	
				}
			}
			
		});
	});
});