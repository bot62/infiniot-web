<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />

<%-- import scripts --%>
<script src="${pageContext.request.contextPath}/ext/Highcharts-4/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/ext/Highcharts-4/js/modules/exporting.js"></script>

<%-- set project name for external js --%>
<script>
  var projectName = "${pageContext.request.contextPath}";
</script>

<div class="page-header">
  <h1 id="title">Bienvenue, admininistrateur !</h1>
  <p>Vous pouvez avoir accès à différentes données sur les capteurs</p>
  <button id="getdata" class="btn btn-default">add JSON data</button>
  <button id="addserie" class="btn btn-default">add serie</button>
  <button id="adddata" class="btn btn-default">add fake data</button>
</div>

<div class="row">
  <select name="filtre" size="1" onChange="changeDesc(this.value)">
    <option value=""></option>
    <option value="Annualy">Annualy</option>
    <option value="Monthly">Monthly</option>
    <option value="Weekly">Weekly</option>
  </select>
  <div id="sdata-container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</div>

<table id="sdata-table" class="table table-striped">
  <%-- Waiting JS action for the implementation... --%>
</table>

<%-- Highchart implementation --%>
<script>
/*
 * This script aims to load data for the highchart container. It
 * contains several steps:
 * 1. listen the action and request data (AJAX)
 * 2. display sensor data in the table
 *
 */
$(document).ready(function() {
  // get sensor data from AJAX request
  $('#getdata').each(function() {
    $(this).click(function() {
      $.post(
          projectName + '/wsn/getSensorData',  // action
          {
            // params
            manufacturer: 'LIBELIUM',
            type: 'TCA',
            before_ts: '1446128731'
          },
          function(json) {  // callback
            showSdata(json);
            addSdata(json);
          });
    });
  });
  // add serie
  $('#addserie').click(function() {
    var chart = $('#sdata-container').highcharts();
    chart.setTitle({text: 'Monthly Average Temperature (updated)'});
    if (chart.series.length === 2) {
      chart.addSeries({
        name: 'B1 202',
        data: [
          94.1, 95.6, 54.4, 29.9, 71.5, 6.4, 29.2, 44.0, 76.0, 35.6, 48.5, 16.4
        ]
      });
    }
  });
  // add data
  $('#adddata').click(function() {
    var chart = $('#sdata-container').highcharts();
    chart.setTitle({text: 'Monthly Average Temperature (updated 2)'});
    chart.series[0].addPoint(5, true, true);
    chart.series[1].addPoint(10, true, true);
    if (chart.series.length === 3) {
      chart.series[2].addPoint(30, true, true);
    }
  });
});
/*
 * Initialize the highchart container
 */
$('#sdata-container').highcharts({
  chart: {type: 'line'},
  title: {text: 'Sensor Temperature'},
  subtitle: {text: 'Provided by: Libelium'},
  xAxis: {
    categories: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11']
  },
  yAxis: {title: {text: 'Temperature (°C)'}},
  plotOptions:
      {line: {dataLabels: {enabled: true}, enableMouseTracking: false}},
  series: [
    {
      name: 'B1 200',
      data:
          [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
    },
    {
      name: 'B1 201',
      data: [
        3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8
      ]
    }
  ]
});
/**
 * show sensor data
 */
showSdata = function(json) {
  var sdata = $.parseJSON(json);
  var content = '';
  // set table head
  content += '<thead>';
  content += '<tr>';
  content += '<th>Sid</th>';
  content += '<th>Type</th>';
  content += '<th>Value</th>';
  content += '<th>Timestamp</th>';
  content += '</tr>';
  content += '</thead>';
  // set table body
  content += '<tbody>';
  $.each(sdata, function() {
    content += '<tr>';
    content += '<td>' + this.sid + '</td>';
    content += '<td>' + this.type + '</td>';
    content += '<td>' + this.value + '</td>';
    content += '<td>' + this.timestamp + '</td>';
    content += '</tr>';
  });
  content += '</tbody>';
  // set target
  $('#sdata-table').html(content);
};
/**
 * add json data
 */
addSdata = function(json) {
  var sdata = $.parseJSON(json);
  var chart = $('#sdata-container').highcharts();
  chart.setTitle({text: 'Monthly Average Temperature (updated 2 From JSON)'});
  $.each(sdata, function() {
    chart.series[0].addPoint(parseFloat(this.value), true, true);
    chart.series[1].addPoint(parseFloat(this.value) * 0.9, true, true);
    // if (chart.series.length === 3) {
    //	chart.series[2].addPoint(this.value * 3, true, true);
    //}
  });
}
</script>
<%-- footer --%>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
