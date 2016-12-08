<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Cloud Life (beta)</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ext/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ext/font-awesome-4.3.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/ext/font-awesome/font-awesome.min.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/ext/bootstrap-sidebar/simple-sidebar.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ext/jquery/jquery-1.11.2.min.js"></script>
<!-- JS - bootstrap.min.js contain all plugins in a single file. Include only one. -->
<script src="${pageContext.request.contextPath}/ext/bootstrap/js/bootstrap.min.js"></script>
<!-- JS - bootstrap progressbar -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ext/bootstrap-progressbar-0.8.4/bootstrap-progressbar.min.js"></script>
<script>
$(document).ready(function() {
  $('.progress .progress-bar')
      .progressbar({display_text: 'fill', use_percentage: false});
});
</script>
</head>
<body>
  <p>Veuillez vous connecter.</p>
</body>
</html>
