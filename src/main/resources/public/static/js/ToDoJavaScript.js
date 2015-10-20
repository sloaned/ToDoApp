$(document).ready(function(){
	
	function isValidDate(dateString)
	{
	    // First check for the pattern
	    if(!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
	        return false;

	    // Parse the date parts to integers
	    var parts = dateString.split("/");
	    var day = parseInt(parts[1], 10);
	    var month = parseInt(parts[0], 10);
	    var year = parseInt(parts[2], 10);

	    // Check the ranges of month
	    if(month == 0 || month > 12)
	        return false;

	    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	    // Adjust for leap years
	    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
	        monthLength[1] = 29;

	    // Check the range of the day
	    return day > 0 && day <= monthLength[month - 1];
	};
	
	$("#addSubmit").click(function(event){
		event.preventDefault();
		var date = $("#addDate").val();
		var task = $("#addTask").val();
		var user = $("#addName").val();
		var unique = true;
		$.ajax({
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			for(var i = 0; i < obj.length; i++){
				item = obj[i];
				if(item.task === task)
				{
					unique = false;
					break;
				}
			}
		});
		if( $.trim(task) === '')
		{
			console.log("Task name cannot be empty.");
		}
		else if($.trim(name) === '')
		{
			console.log("User name cannot be empty.");
		}
		else if(!unique)
		{
			console.log("That task has been entered already.");
		}	
		else if(!isValidDate(date))
		{
			console.log("Invalid date.");
		}
		else
		{
			var item = {};
			item.task = $("#addTask").val();
			item.assignedUser = $("#addName").val();
			item.inProgress = $("#addInProgress").val();
			item.dueDate = $("#addDate").val();
			item.description = $("#addDescription").val();
			item.complete = false;
			$.ajax({
				url: '/todo',
				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(item)
			}).then(function(){
				window.location.href = "/todo/index";
			},
			function(error){
				console.log(JSON.stringify(error));
			});
		}
		
	
	});
	
	
	$.ajax({
		url: '/todo',
		method: 'GET'
	}).then(function(toDoList){
		var obj = JSON.parse(JSON.stringify(toDoList));
		var item;
		for(var i = 0; i < obj.length; i++){
			item = obj[i];
			$("#updateTaskSelect").append('<option>' + item.taskNum + ' ' + item.task + '</option>');
			$("#removeTaskSelect").append('<option>' + item.taskNum + ' ' + item.task + '</option>');
		}
	});
	
	$("#updateTaskSelect").on("change", function() {
		var selected = $("#updateTaskSelect").val();
		var idNum = selected.substr(0,selected.indexOf(' '));
		$.ajax({
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			for(var i = 0; i < obj.length; i++){
				item = obj[i];
				if(item.taskNum == idNum)
				{
					$("#updateIdNum").html(item.taskNum);
					$("#updateName").val(item.assignedUser);
					$("#updateTask").val(item.task);
					$("#updateDate").val(item.dueDate);
					$("#updateDescription").val(item.description);
					//$("#updateInProgress").val(item.inProgress);
					//$("#updateComplete").val(item.complete);
					if(item.inProgress === true)
					{
						$("#updateInProgress").prop('checked', true);
					}
					else
					{
						$("#updateInProgress").prop('checked', false);
					}
					if(item.complete === true)
					{
						$("#updateComplete").prop('checked', true);
					}
					else
					{
						$("#updateComplete").prop('checked', false);
					}
				}
			}
		});
	});
	$("#updateSubmit").click(function(event){
		event.preventDefault();
		var date = $("#updateDate").val();
		var task = $("#updateTask").val();
		var user = $("#updateName").val();
		var unique = true;
		$.ajax({
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			for(var i = 0; i < obj.length; i++){
				item = obj[i];
				if(item.task === task)
				{
					unique = false;
					break;
				}
			}
		});
		if( $.trim(task) === '')
		{
			console.log("Task name cannot be empty.");
		}
		else if($.trim(name) === '')
		{
			console.log("Assigned user cannot be empty.");
		}
		else if(!unique)
		{
			console.log("That task has been entered already.");
		}	
		else if(!isValidDate(date))
		{
			console.log("Invalid date.");
		}
		else
		{
			var idNum = $("#updateIdNum").html();
			var item = {};
			item.task = $("#updateTask").val();
			item.assignedUser = $("#updateName").val();
			item.dueDate = $("#updateDate").val();
			item.description = $("#updateDescription").val();
			if($("#updateInProgress").is(':checked'))
			{
				item.inProgress = true;
			}
			else
			{
				item.inProgress = false;
			}
			if($("#updateComplete").is(':checked'))
			{
				item.complete = true;
			}
			else
			{
				item.complete = false;
			}
			$.ajax({
				url: '/todo/'+idNum,
				method: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(item)
			}).then(function(){
				window.location.href = "/todo/index";
			},
			function(error){
				console.log(JSON.stringify(error));
			});
		}
		
	
	});
	
	$("#removeSubmit").click(function(event){
		event.preventDefault();
		if($("#removeTaskSelect").val() != "Select a Task")
		{
			var selected = $("#removeTaskSelect").val();
			var idNum = selected.substr(0,selected.indexOf(' '));
			$.ajax({
				url: '/todo/'+idNum,
				method: 'DELETE'
			}).then(function(){
				window.location.href = "/todo/index";
			},
			function(error){
				console.log(JSON.stringify(error));
			});
		}
	
	});
});