<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% String projectName = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<title>Cloud Life (beta)</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%= projectName %>/ext/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= projectName %>/ext/font-awesome-4.3.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="<%= projectName %>/ext/font-awesome/font-awesome.min.css"> -->
<link rel="stylesheet" href="<%= projectName %>/ext/bootstrap-sidebar/simple-sidebar.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<%= projectName %>/ext/jquery/jquery-1.11.2.min.js"></script>
<!-- JS - bootstrap.min.js contain all plugins in a single file. Include only one. -->
<script src="<%= projectName %>/ext/bootstrap/js/bootstrap.min.js"></script>
<!-- JS - bootstrap progressbar -->
<script type="text/javascript" src="<%= projectName %>/ext/bootstrap-progressbar-0.8.4/bootstrap-progressbar.min.js"></script>
<script>
    $(document).ready(function() {
        $('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: false});
    });
  </script>
</head>
<body>
  <p>Veuillez vous connecter.</p>
</body>
</html>
