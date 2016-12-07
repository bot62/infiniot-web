<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />

<%-- import scripts --%>
<script>
  var projectName = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/nodes.js"></script>
<script src="${pageContext.request.contextPath}/ext/Highcharts-4/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/ext/Highcharts-4/js/modules/exporting.js"></script>

<%-- content --%>
<div class="page-header">
  <h1 id="title">Tracker de nœud</h1>
</div>
<div class="row">
  <div id="highcharts-div" class="col-md-12 hidden col-xs-10" style="margin-bottom: 30px">
    <div id="highcharts-container" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto"></div>
  </div>
  <c:forEach items="${nodes}" var="node">
    <div id="${node.nid}" class="col-md-3 nd-div">
      <img class="center-block" src="${pageContext.request.contextPath}/resources/image/waspmote-150x150.jpg" alt="node 2">
      <p class="h4 text-center">${node.nid}</p>
      <ul class="li-style-none">
        <li>
          <c:choose>
            <c:when test="${node.battery >= 0.8}">
              <span style="width: 25px;" class="text-center fa fa-battery-4"></span>
            </c:when>
            <c:when test="${node.battery >= 0.6 && node.battery < 0.8}">
              <span style="width: 25px;" class="text-center fa fa-battery-3"></span>
            </c:when>
            <c:when test="${node.battery >= 0.4 && node.battery < 0.6}">
              <span style="width: 25px;" class="text-center fa fa-battery-2"></span>
            </c:when>
            <c:when test="${node.battery >= 0.05 && node.battery < 0.4}">
              <span style="width: 25px;" class="text-center fa fa-battery-1"></span>
            </c:when>
            <c:when test="${node.battery < 0.05}">
              <span style="width: 25px;" class="text-center fa fa-battery-0"></span>
            </c:when>
          </c:choose>
          <fmt:formatNumber type="percent" maxIntegerDigits="3" value="${node.battery}" />
        </li>
        <li><span class="fa fa-signal text-center" style="width: 25px;"></span> ${node.type}</li>
        <li><span class="fa fa-clock-o text-center" style="width: 25px;"></span> 1j 23h+</li>
        <li><span class="fa fa-map-marker text-center" style="width: 25px"></span> ${node.room}, ${node.floor}, ${node.building}</li>
        <li><span class="fa fa-map-o text-center" style="width: 25px;"></span> (<fmt:formatNumber type="NUMBER" maxIntegerDigits="4" value="${node.longitude}" />, <fmt:formatNumber type="NUMBER"
            maxIntegerDigits="4" value="${node.latitude}" />)</li>
      </ul>
    </div>
  </c:forEach>
</div>
<hr>
<table id="sr-table" class="table table-striped">
  <%-- Waiting AJAX to load... --%>
</table>

<%-- footer --%>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
