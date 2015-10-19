$(document).ready(function(){
	$("#addSubmit").click(function(event){
		event.preventDefault();
		var item = {};
		item.task = $("#addTask").val();
		item.assignedUser = $("#addName").val();
		item.inProgress = $("#addInProgress").val();
		item.dueDate = $("#addDueDate").val();
		item.description = $("#addDescription").val();
		
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
		/*var name = $("#nameInput").val();

		employee.firstName = name.substr(0,name.indexOf(' '));
		employee.lastName = name.substr(name.indexOf(' ')+1);
		employee.age = $("#ageInput").val();
		if($("#isActiveInput").is(':checked'))
		{
			employee.isActive = true;
		}
		else
		{
			employee.isActive = false;
		}
		$.ajax({
			url: '/employees',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(employee)	
		}).then(function(){
			window.location.href = "/employee/index";
		},
		function(error){
			console.log(JSON.stringify(error));
		});*/
	});
});