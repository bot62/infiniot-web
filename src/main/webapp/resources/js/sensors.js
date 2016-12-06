//
// Highcharts
// http://www.highcharts.com/docs/working-with-data/live-data
//
//

$(document).ready(function() {
	// Initialize the highchart container N°2
	$('#highcharts-container').highcharts({
		chart : {
			defaultSeriesType : 'spline'
		},
		title : {
			text : 'Realtime data'
		},
		xAxis : {
			type : 'datetime',
			tickPixelInterval : 150,
			maxZoom : 20 * 1000
		},
		yAxis : {
			minPadding : 0.2,
			maxPadding : 0.2,
			title : {
				text : 'Value',
				margin : 80
			}
		},
		exporting : {
			enabled : false
		}
	});
	// get sensor data from AJAX request (auto start)
	requestData();

	// targeted <tr> tags onclick listener
	trClick("#table-sensor tr");
});

/**
 * Get the last sensor data using HTTP request in POST. When executed the first
 * time, this function will be recalled every 1000ms.
 * 
 */
function requestData() {
	$.ajax({
		url : projectName + "/wsn/getSensorData", // action
		method : 'POST',
		data : {
			key : '72cec25cbbd386c3b68f9b73f480057ef55d09b8'
		},
		success : function(json) {
			addSdata(json);
			setTimeout(requestData, 1000);
		},
		cache : false
	});
}

/**
 * This method adds JSON data to existing highchart 
 * container.
 * 
 */
addSdata = function(json) {
	var sensors = $.parseJSON(json);
	var chart = $('#highcharts-container').highcharts();
	if (chart.series.length === 0) {
		$.each(sensors, function(i, sensor) {
			chart.addSeries({
				name : sensor.sid,
				data : []
			});
		});
	}
	// shift if the series is longer than 60s
	var series = chart.series[0], shift = series.data.length > 60;
	$.each(sensors, function(i, sensor) {
		chart.series[i].addPoint([ sensor.timestamp, sensor.value ], true,
				shift);
	});
}

/**
 * Onclick listener for targeted <tr> tag.
 *
 */
trClick = function(trTarget) {

	$(trTarget).each(function() {

		// if target is not the header, not has <th>
		$(this).not(':has(th)').click(function() {

			// cancel previous selection
			cancelSelect(trTarget);
			loadHighchart('#highcharts_ESIG_TEMP_001');

			// start new selection
			var data = $(this).find(".value");
			var content = data.text();
			var chart = $('#highcharts_ESIG_TEMP_001').highcharts();
			chart.setTitle({
				text : $(this).attr('id')
			});
			if ($(this).hasClass('info')) {
				$(this).removeClass('info');
			} else {
				$(this).addClass('info');
			}

		});
	});
};

/**
 * Cancel selected item in the list of targeted <tr>.
 * 
 */
cancelSelect = function(trTarget) {
	$(trTarget).each(function() {
		if ($(this).hasClass("info")) {
			$(this).removeClass("info");
		}
	});
};

/**
 * This method help to get URL parameters using jquery.
 * When sensors.jsp page is requested by an action, URL
 * parameters are resolved by the regular expression, 
 * and return as results.
 *
 * [StackOverflow] Get url parameter jquery
 * http://stackoverflow.com/questions/19491336/get-url-parameter-jquery
 * 
 */
$.urlParam = function(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
	}
}

var mq = window.matchMedia('@media all and (min-width: 700px)');

$(document).ready(function() {
	if (mq.matches) {
		alert('ok')
		$('#highcharts-container').highcharts.yAxis.title.attr({
			enabled : false
		});
	}
});
