<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<style>

input[type=submit] {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 20px 34px;
	text-decoration: none;
	margin: 4px 2px; 
	cursor: pointer;
}

select {
	width: 20%;
	padding: 18px 22px;
	border: none;
	border-radius: 5px;
	background-color: #f1f1f1;
}

table {
	width: 50%;
	height:45%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 80px;
	text-align: center;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: #4CAF50;
	color: white;
	text-align: center;
}
</style>

<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
</head>
<script type="text/javascript">
	onunload = function() {
		var foo = document.getElementById('foo');
		self.name = 'fooidx' + foo.selectedIndex;
	}

	onload = function() {
		var idx, foo = document.getElementById('foo');
		foo.selectedIndex = (idx = self.name.split('fooidx')) ? idx[1] : 0;
	}
</script>

<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Newyork Subway Stations</a>
			</div>
		</div>
	</nav>

	<div align="center">
		<form method="GET" action="/loadData">
			<!-- action="/action_page.php"> -->
			Source: <select>
				<option value="34street">34 Street</option>
			</select> <br> <br> Destination: <select id="foo"
				name="destinationName"
				onchange="options[selectedIndex].value&&self.location.reload(true)">
				<option value="" selected="selected"></option>
				<option value="42streetportauthority">42 Street Port
					Authority</option>
				<option value="rockfellercenter">Rockfeller Center</option>
				<option value="worldtradecenter">World trade center</option>
				<option value="brooklyn">Brooklyn</option>
				<option value="coneyIsland">Coney Island</option>
			</select> <br>
			<br> <input type="submit" name="submit" id="submit"
				value="Calculate Shortest Path">

			<%
				String destinationName = request.getParameter("destinationName");
			%>
		</form>
		<%
			List nodeList = (List) session.getAttribute("nodeList");
		%>
		<br> <br>
		<table id="t01">
			<tr>
				<th>Source</th>
				 <th>Route</th>
				<th>Destination</th>
				<th>Distance</th>
				<th>Average waiting time</th>
			</tr>

			<%-- <%=nodeList%> --%>
			<c:forEach items="${nodeList}" var="node">
				<tr>
					<td><c:out value="34 Street" /></td>
					<td><c:out value="${node.routeName}" /></td> 
					<td><c:out value="${node.name}" /></td>
					<td><c:out value="${node.distance} minutes" /></td>
					<td><c:out value="${node.avgWaitingTime} minutes" /></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>