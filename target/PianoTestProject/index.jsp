<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.nikita.fedorov.piano.searchapp.bean.QuestionBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Question discover</title>
<!-- <script src="resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/datatable/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script src="resources/datatable/dataTables.bootstrap.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/datatable/dataTables.bootstrap.min.js"> -->

<script src="https://code.jquery.com/jquery-1.12.4.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css">

</head>
<body>
	<div class="container" style="top: 20px;">
		<h1>Search for the questions...</h1>
		<form id="searchForm" method="post" action="search">
			<div class="container">
				<div class="row">
					<div class="col-md-10">
						<input class="form-control input-sm" type="text" name="param"
							style="width: 100%;" />
					</div>
					<div class="col-md-2">
						<input type="submit" id="submit-btn" class="btn btn-info" />
					</div>
				</div>
				<c:choose>
					<c:when test="${success=='false'}">
						<c:set value="" var="displayError" />
					</c:when>
					<c:otherwise>
						<c:set value="style='display:none;'" var="displayError" />
					</c:otherwise>
				</c:choose>
				<div class="row">
					<div ${displayError}>
						<div class="col-md-10">
							<div class="alert alert-danger">
								<strong>Bad request!</strong> Please try with other search string.
							</div>
						</div>
					</div>
				</div>
			</div>
			<br />
			<div class=container>
				<table class="table table-hover" id="resultTable">
					<thead>
						<tr>
							<th class="col-md-4">Title</th>
							<th class="col-md-4">Author</th>
							<th class="col-md-4">Creation Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${questions}" var="question">
							<c:choose>
								<c:when test="${question.isAnswered=='true'}">
									<c:set value="success" var="rowClass" />
								</c:when>
								<c:otherwise>
									<c:set value="" var="rowClass" />
								</c:otherwise>
							</c:choose>
							<tr class="${rowClass} clickable-row" style="cursor: pointer" url="${question.link}">
								<td><c:out value="${question.title}" /></td>
								<td><c:out value="${question.author}" /></td>
								<td><c:out value="${question.creationDate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#resultTable').DataTable();
		$('#resultTable_filter').css("display", "none");
		
		$(".clickable-row").click(function() {
	        window.location = $(this).attr("url");
	    });
	});	
</script>
</html>