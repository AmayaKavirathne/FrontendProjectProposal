
$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateProposalForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		// If valid------------------------
		//$("#formproposal").submit();
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			 url : "ProposalAPI",
			 type : type,
			 data : $("#formproposal").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 	onProposalSaveComplete(response.responseText, status);
			 }
	});
	});
function onProposalSaveComplete(response, status)
		{
		
		if (status == "success") 
		{
			
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		$("#divProposalGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") 
		{
				
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
			
		}else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
		}
		
		$("#hidIDSave").val("");
		$("#formproposal")[0].reset();
	}
	
	
		
//UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
	$("#hidIDSave").val($(this).data("proposal_ID"));
	$("#ProjectName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#budget").val($(this).closest("tr").find('td:eq(1)').text());
	$("#CompletionDate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#productCategory").val($(this).closest("tr").find('td:eq(3)').text());
	$("#sellOrNot").val($(this).closest("tr").find('td:eq(4)').text());
	$("#description").val($(this).closest("tr").find('td:eq(5)').text());
	$("#status").val($(this).closest("tr").find('td:eq(6)').text());
	$("#userID").val($(this).closest("tr").find('td:eq(7)').text());

	
});

function onProposalDeleteComplete(response, status)
{
	if (status == "success")
	 {
	 var resultSet = JSON.parse(response);
	 
	 if (resultSet.status.trim() == "success")
	 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 
			 $("#divProposalGrid").html(resultSet.data);
	 } else if (resultSet.status.trim() == "error")
	 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
	 }
	 } else if (status == "error")
	 {
			 $("#alertError").text("Error while deleting.");
			 $("#alertError").show();
	 } else
	 {
			 $("#alertError").text("Unknown error while deleting..");
			 $("#alertError").show();
	 }
}

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
		$.ajax(
		{
		 url : "ProposalAPI",
		 type : "DELETE",
		 data : "proposal_ID=" + $(this).data("proposal_ID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 		onProposalDeleteComplete(response.responseText, status);
		 }
		});
});

// CLIENT-MODEL================================================================
function validateProposalForm()
{
// PROPOSAL NAME
if ($("#ProjectName").val().trim() == "")
{
return "Insert Project Name.";
}
//PRICE-------------------------------
if ($("#budget").val().trim() == "") {
	return "Insert Project Budget.";
}
//is numerical value
var tmpPrice = $("#budget").val().trim();
if (!$.isNumeric(tmpPrice)) {
	return "Insert a numerical value for Project Budget.";
}

// convert to decimal price
$("#budget").val(parseFloat(tmpPrice).toFixed(2));

// CompletionDate-------------------------------
if ($("#CompletionDate").val().trim() == "")
{
return "Insert Completion date.";
}
// productCategory
if ($("#productCategory").val().trim() == "")
{
return "Insert Project category.";
}
// sellOrNot
if ($("#sellOrNot").val().trim() == "")
{
return "Insert selling status.";
}
// description
if ($("#description").val().trim() == "")
{
return "Insert description.";
}
//status
if ($("#status").val().trim() == "")
{
return "Insert status.";
}

// UserID
if ($("#userID").val().trim() == "") {
	return "Insert User ID.";
}

	return true;
}
	



