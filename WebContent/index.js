$(document).ready(function(){
	
	$.get( "api/hello/testing", function( data, status) {
		alert("Data is:" + data + "\nStatus :" +  status);
		$("#message").text(data);
		AlchemyApi_params
		  $( ".result" ).html( data );
		  console.log("ur here");
		  console.log(data);
		  alert( "Load was performed." );  
	});
	
});
