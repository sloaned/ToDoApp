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

	    // Check the ranges of month and year
	    if(year < 1000 || year > 3000 || month == 0 || month > 12)
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
		var item = {};
		item.task = $("#addTask").val();
		item.assignedUser = $("#addName").val();
		item.inProgress = $("#addInProgress").val();
		item.dueDate = $("#addDate").val();
		item.description = $("#addDescription").val();
		item.complete = "false";
		console.log(item.task + ' ' + item.assignedUser + ' ' + item.inProgress + ' ' + item.dueDate);
		console.log(JSON.stringify(item));
		$.ajax({
			url: '/todo',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(item)
		}).then(function(){
			window.location.href = "/todo/index";
		},
		function(error){
			console.log("A big ol error occurred");
			console.log(JSON.stringify(error));
		});
	
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
					$("#updateInProgress").val(item.inProgress);
					$("#updateComplete").val(item.complete);
					/*if(emp.isActive === true)
					{
						$("#isActiveInput").prop('checked', true);
					}
					else
					{
						$("#isActiveInput").prop('checked', false);
					}*/
				}
			}
		});
	});
	$("#updateSubmit").click(function(event){
		event.preventDefault();
		var idNum = $("#updateIdNum").html();
		var item = {};
		item.task = $("#updateTask").val();
		item.assignedUser = $("#updateName").val();
		item.inProgress = $("#updateInProgress").val();
		item.dueDate = $("#updateDate").val();
		item.description = $("#updateDescription").val();
		item.complete = $("#updateComplete").val();
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
	
	});
});