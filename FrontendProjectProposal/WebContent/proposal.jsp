<%@page import="com.Proposal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<title>Project Proposal Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="jQuery-3.2.1.min.js"></script>
<script src="proposal.js"></script>
</head>

<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Project Proposal Management</h1>
					<form id="formproposal" name="formproposal" method="post" action="proposal.jsp">
 						 ProjectName:
						<input id="ProjectName" name="ProjectName" type="text"
 						class="form-control form-control-sm">
						<br> budget:
						<input id="budget" name="budget" type="text"
 						class="form-control form-control-sm">
						<br> CompletionDate:
						<input id="CompletionDate" name="CompletionDate" type="text"
 						class="form-control form-control-sm">
						<br> productCategory:
						<input id="productCategory" name="productCategory" type="text"
						 class="form-control form-control-sm">
						<br> sellOrNot:
						<input id="sellOrNot" name="sellOrNot" type="text"
 						class="form-control form-control-sm">
						<br> description:
						<input id="description" name="description" type="text"
 						class="form-control form-control-sm">
						<br> status:
						<input id="status" name="status" type="text"
 						class="form-control form-control-sm">
						<br>userID:
						<input id="userID" name="userID" type="text"
 						class="form-control form-control-sm">
						<br>
						
 						


						<input id="btnSave" name="btnSave" type="button" value="Save"
 						class="btn btn-primary">
						<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProposalGrid">
					<%
									Proposal proposal = new Proposal();
								    out.print(proposal.viewProposals());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>