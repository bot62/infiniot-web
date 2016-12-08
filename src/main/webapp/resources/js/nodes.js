//
// Highcharts
// http://www.highcharts.com/docs/working-with-data/live-data
//
//
var nid = 'unknown'
$(document).ready(function() {
  $('.nd-div').each(function() {
    $(this).click(function() {
      $.post(
          projectName + '/wsn/getSensors',       // action
          {nid: $(this).attr('id')},             // parameter
          function(json) { showSensors(json); }  // callback
          );
      $('#highcharts-div').removeClass('hidden');
      // Initialize the highchart container N°2
      $('#highcharts-container').highcharts({
        chart: {defaultSeriesType: 'spline'},
        title: {text: $(this).attr('id')},
        plotOptions: {line: {marker: {enabled: false}}},
        credits: {enabled: false},
        xAxis: {type: 'datetime', tickPixelInterval: 150, maxZoom: 20 * 1000},
        yAxis: {
          minPadding: 0.2,
          maxPadding: 0.2,
          title: {text: 'Value', margin: 10}
        },
        exporting: {enabled: false}
      });
      // get sensor data from AJAX request (auto start)
      nid = $(this).attr('id');
      requestLiveData();  // request live data
    });
  });
});

/**
 * Get the last sensor data using HTTP request in POST. When executed the first
 * time, this function will be recalled every 1000ms.
 *
 */
function requestLiveData() {
  $.ajax({
    url: projectName + '/wsn/getSensorData',  // action
    method: 'POST',
    data: {'key': '72cec25cbbd386c3b68f9b73f480057ef55d09b8', 'nid': nid},
    success: function(json) {
      addLiveData(json);
      setTimeout(requestLiveData, 1000);
    },
    cache: false
  });
}

/**
 * Get the live statistic data using HTTP request in POST.
 *
 */
function requestLiveStats() {
  console.log('requestLiveStats called');
  $('tbody > tr').each(function() {
    var sid = $(this).attr('id');
    console.log('sid=' + sid + ',nid=' + nid);
    $.ajax({
      url: projectName + '/wsn/getStats',
      method: 'POST',
      data: {
        'key': '72cec25cbbd386c3b68f9b73f480057ef55d09b8',
        'nid': nid,
        'sid': sid
      },
      success: function(json) { addLiveStats(json, sid); },
      cache: false
    })
  });
  setTimeout(requestLiveStats, 1000);
}

/**
 * This method adds JSON data to existing highchart container.
 *
 * @param json sensor data in JSON
 */
addLiveData =
    function(json) {
  var sensors = $.parseJSON(json);
  var chart = $('#highcharts-container').highcharts();
  // console.log('chart.series.length=' + chart.series.length);
  if (chart.series.length === 0) {
    // console.log('adding series...');
    $.each(sensors, function(i, sensor) {
      chart.addSeries({
        id: sensor.sid,
        name: sensor.sid,
        data: [],
        marker: {enabled: false}
      });
      // console.log('Serie ' + sensor.sid + 'added');
    });
  }
  // shift if the series is longer than 60s
  var series = chart.series[0], shift = series.data.length > 60;
  $.each(sensors, function(i, sensor) {
    // console.log('sid=' + sensor.sid + ', t=' + sensor.timestamp + ' added');
    // chart.series[i].addPoint([sensor.timestamp, sensor.value], true, shift);
    chart.series[i].addPoint([sensor.timestamp, sensor.value], true, false);
  });
}

/**
 * This method adds JSON data to existing highchart container.
 *
 * @param json sensor data in JSON
 * @param sname sensor name
 */
addLiveStats =
    function(json, sname) {
  var stats = $.parseJSON(json);
  console.log(
      'addLiveStats: ' + sname + ',' + stats.min + ',' + stats.max + ',' +
      stats.avg + ',' + stats.speed);
  $('#' + sname).children('td.min').html(stats.min.toFixed(1));
  $('#' + sname).children('td.max').html(stats.max.toFixed(1));
  $('#' + sname).children('td.avg').html(stats.avg.toFixed(1));
  $('#' + sname).children('td.speed').html(stats.speed.toFixed(1));
}

/**
 * Show sensor devices info, received in JSON format
 *
 * @param json sensor info in JSON
 */
function showSensors(json) {
  var sensors = $.parseJSON(json);
  var content = '';
  // set table head
  content += '<thead>';
  content += '<tr>';
  content += '<th>Sid</span></th>';
  content += '<th>Building</th>';
  content += '<th>Floor</th>';
  content += '<th>Room</th>';
  content += '<th>Type</th>';
  content += '<th>Speed</th>';
  content += '<th>Max</th>';
  content += '<th>Min</th>';
  content += '<th>Avg</th>'
  content += '<th>Description</th>';
  content += '</tr>';
  content += '</thead>';
  // set table body
  content += '<tbody>';
  $.each(sensors, function() {
    content += '<tr id="' + this.name + '">';
    content += '<td><a href="' + projectName + '\/sensor?sid=' + this.sid +
        '">' + this.name + '</a></td>';
    content += '<td>' + this.building + '</td>';
    content += '<td>' + this.floor + '</td>';
    content += '<td>' + this.room + '</td>';
    content += '<td>' + this.type + '</td>';
    content += '<td class="speed">' + this.speed + '</td>';
    content += '<td class="max">' + this.max + '</td>';
    content += '<td class="min">' + this.min + '</td>';
    content += '<td class="avg">' + this.avg + '</td>';
    content += '<td>' + this.description + '</td>';
    content += '</tr>';
  });
  content += '</tbody>';
  // set target
  $('#sr-table').html(content);
  requestLiveStats();  // request live statistics
}
