<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />

<%-- import scripts --%>
<script src="<%=request.getContextPath()%>/ext/Highcharts-4/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/ext/Highcharts-4/js/modules/exporting.js"></script>

<%-- set project name for external js --%>
<script>var projectName = "<%=request.getContextPath()%>";</script>

<div class="page-header">
  <h1>Sensor Tracker</h1>
  <button id="start" class="btn btn-default hidden">start</button>
</div>
<div class="row">
  <div id="highcharts-div" class="col-md-12">
    <div id="highcharts-container" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto"></div>
  </div>
</div>
<hr>
<form>
  <div class="input-group col-xs-1">
    <span class="input-group-addon" id="search-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span> <input id="sensor-finder" type="text" class="form-control"
      placeholder="Search sensors" aria-describedby="search-addon" style="width: 200px">
  </div>
</form>
<table id="table-sensor" class="table table-striped">
  <thead>
    <tr>
      <th>SId <span class="filter fa fa-caret-up pull-right"></span></th>
      <th class="hidden-xs">Building <span class="filter fa fa-caret-down pull-right"></span></th>
      <th class="hidden-xs">Floor <span class="filter fa fa-caret-down pull-right"></span></th>
      <th>Room <span class="filter fa fa-caret-down pull-right"></span></th>
      <th class="hidden-xs">Type <span class="filter fa fa-caret-down pull-right"></span></th>
      <th>Value <span class="filter filtered fa fa-caret-down pull-right"></span></th>
      <th class="hidden-xs">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr id="tr-ESIG_TEMP_001">
      <td>ESIG_TEMP_001 <span class="fa fa-exclamation-triangle"></span></td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">200°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
    <tr id="tr-ESIG_TEMP_002">
      <td>ESIG_TEMP_002</td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">90°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
    <tr id="tr-ESIG_TEMP_003">
      <td>ESIG_TEMP_003</td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">90°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
    <tr id="tr-ESIG_TEMP_004">
      <td>ESIG_TEMP_004</td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">90°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
    <tr id="tr-ESIG_TEMP_005">
      <td>ESIG_TEMP_005</td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">90°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
    <tr id="tr-ESIG_TEMP_006">
      <td>ESIG_TEMP_006</td>
      <td class="hidden-xs">Building A</td>
      <td class="hidden-xs">1</td>
      <td>B1 255</td>
      <td class="hidden-xs">Temperature</td>
      <td class="value">90°C</td>
      <td class="hidden-xs">Libelium Temperature sensor <a href="#">LIBE9025</a></td>
    </tr>
  </tbody>
</table>

<%-- Highchart implementation --%>
<script src="<%=request.getContextPath()%>/resources/js/sensors.js"></script>

<jsp:include page="/WEB-INF/utils/footer.jsp" />
