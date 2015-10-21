$(document).ready(function(){
	
	//taken from Stack Overflow
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
		$("#addNameError").empty();
		$("#addDateError").empty();
		$("#addTaskError").empty();
		var date = $("#addDate").val();
		var taskname = $("#addTask").val();
		var user = $("#addName").val();
		$.ajax({
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			var valid = true;

			for(var i = 0; i < obj.length; i++){
				item = obj[i];
				if(item.task === taskname)
				{
					valid = false;
					$("#addTaskError").html("That task has been entered already.");
					break;
				}
			}
		
			if( $.trim(taskname) === '')
			{
				valid = false;
				$("#addTaskError").html("Task name is required.");
			}
			if($.trim(user) === '')
			{
				valid = false;
				$("#addNameError").html("Assigned person is required.");
			}
			if(!isValidDate(date))
			{
				valid = false;
				$("#addDateError").html("Invalid date.");
			}
			if(valid)
			{
				var item = {};
				item.task = $("#addTask").val();
				item.assignedUser = $("#addName").val();
				item.dueDate = $("#addDate").val();
				item.description = $("#addDescription").val();
				item.complete = false;
				if($("#addInProgress").is(':checked'))
				{
					item.inProgress = true;
				}
				else
				{
					item.inProgress = false;
				}
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
	
	$("#updateComplete").click(function() {
	    var $this = $(this);
	    if($this.is(':checked')) 
	    {
	    	$("#updateInProgress").prop('checked', false);
	    } 
	});
	
	$("#updateInProgress").click(function(){
		var $this = $(this);
		if($this.is(':checked'))
		{
			$("#updateComplete").prop('checked', false);
		}
	});
	
	$("#updateSubmit").click(function(event){
		event.preventDefault();
		$("#updateNameError").empty();
		$("#updateDateError").empty();
		$("#updateTaskError").empty();
		var date = $("#updateDate").val();
		var task = $("#updateTask").val();
		var user = $("#updateName").val();
		
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
		$(".removeBtn").on("click", "#response table td a.del_button", function(e) {
		    e.returnValue = false;
		    var clickedID = this.id.split('-'); //Split string (Split works as PHP explode)
		    var DbNumberID = clickedID[1]; //and get number from array
		    var myData = 'recordToDelete='+ DbNumberID; //build a post data structure   
		    //console.log(myData); 

		    var $tr = $(this).closest('tr'); //here we hold a reference to the clicked tr which will be later used to delete the row

		    $("#delete_this_user").dialog({
		        resizable: false,
		        height:140,
		        modal: true,
		        buttons: {
		            "Yes": function() {
		                //$row.remove();
		                $(this).dialog( "close" );

		                $.ajax({
		                    type: "POST", // HTTP method POST or GET
		                    url: "process.php", //Where to make Ajax calls
		                    dataType:"text", // Data type, HTML, json etc.
		                    data:myData, //Form variables
		                    success:function(response){
		                        //on success, hide  element user wants to delete.
		                        $tr.find('td').fadeOut(1000,function(){ 
		                            $tr.remove();                    
		                        }); 
		                    },
		                    error:function (xhr, ajaxOptions, thrownError){
		                        //On error, we alert user
		                        alert(thrownError);
		                    }
		                });
		            },
		            "no": function() {
		                $(this).dialog( "close" );
		            }
		        }
		    });      


		});
			url: '/todo',
			method: 'GET'
		}).then(function(toDoList){
			var obj = JSON.parse(JSON.stringify(toDoList));
			var item;
			var valid = true;
			for(var i = 0; i < obj.length; i++){
				item = obj[i];
				if(item.task === task)
				{
					valid = false;
					$("#updateTaskError").html("That task has been entered already.");
					break;
				}
			}
			if( $.trim(task) === '')
			{
				valid = false;
				$("#updateTaskError").html("Task name is required.");
			}
			if($.trim(user) === '')
			{
				valid = false;
				$("#updateNameError").html("Assigned person is required.");
			}
			if(!isValidDate(date))
			{
				valid = false;
				$("#updateDateError").html("Invalid date.");
			}
			if(valid)
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