<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />
<jsp:include page="/WEB-INF/utils/import-highcharts.jsp" />

<div class="page-header">
  <h1 id="title">Admin</h1>
  <p class="h3">
    Serveur est en mode
    <c:choose>
      <c:when test="${mode == '0'}">
        <span id="running-mode" class="label label-warning">OFF</span>
      </c:when>
      <c:when test="${mode == '1'}">
        <span id="running-mode" class="label label-danger">Fire Test Random</span>
      </c:when>
      <c:when test="${mode == '2'}">
        <span id="running-mode" class="label label-danger">Fire Test I</span>
      </c:when>
      <c:when test="${mode == '3'}">
        <span id="running-mode" class="label label-danger">Fire Test II</span>
      </c:when>
      <c:when test="${mode == '4'}">
        <span id="running-mode" class="label label-success">From Ping32M</span>
      </c:when>
    </c:choose>
    depuis ${runtime} ms
  </p>
</div>
<div class="row">
  <form method="post" action="${pageContext.request.contextPath}/wsn/setRunningMode" class="form-horizontal">
    <div class="col-md-3">
      <select name="mode" class="form-control input-sm col-md-2">
        <option value="0" <c:if test="${mode == '0'}">selected</c:if>>OFF</option>
        <option value="1" <c:if test="${mode == '1'}">selected</c:if>>Fire Test Random</option>
        <option value="2" <c:if test="${mode == '2'}">selected</c:if>>Fire Test I</option>
        <option value="3" <c:if test="${mode == '3'}">selected</c:if>>Fire Test II</option>
        <option value="4" <c:if test="${mode == '4'}">selected</c:if>>From Ping32M</option>
      </select>
    </div>
    <div>
      <button type="submit" class="btn btn-primary btn-sm">Changer</button>
    </div>
  </form>
</div>

<%-- Highchart implementation --%>
<script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
<%-- footer --%>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
