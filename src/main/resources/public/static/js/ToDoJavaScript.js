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
	});
});