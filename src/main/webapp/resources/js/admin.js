function changeDesc(filtre) {
	if (filtre == 'Weekly') {
		$('#container').highcharts(
				{
					chart : {
						type : 'line'
					},
					title : {
						text : 'Weekly Average Temperature'
					},
					subtitle : {
						text : 'Source: Sensor 1 & Sensor 2 - PING32'
					},
					xAxis : {
						categories : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri',
								'Sat', 'Sun' ]
					},
					yAxis : {
						title : {
							text : 'Temperature (°C)'
						}
					},
					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							},
							enableMouseTracking : false
						}
					},
					series : [
							{
								name : 'Sensor 1',
								data : [ 317.0, 386.9, 319.5, 264.5, 218.4,
										221.5, 225.2 ]
							},
							{
								name : 'Sensor 2',
								data : [ 343.9, 394.2, 425.7, 388.5, 311.9,
										315.2, 317.0, ]
							} ]
				});

	} else if (filtre == 'Monthly') {
		document.getElementById('container').innerHTML = "Monthly OK";
		$('#container')
				.highcharts(
						{
							chart : {
								type : 'line'
							},
							title : {
								text : 'Monthly Average Temperature'
							},
							subtitle : {
								text : 'Source: Sensor 1 & Sensor 2 - PING32'
							},
							xAxis : {
								categories : [ '01', '02', '03', '04', '05',
										'06', '07', '08', '09', '10', '11',
										'12', '14', '15', '16', '17', '18',
										'19', '20', '21', '22', '23', '24',
										'25', '26', '27', '28', '29', '30',
										'31' ]
							},
							yAxis : {
								title : {
									text : 'Temperature (°C)'
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : true
									},
									enableMouseTracking : false
								}
							},
							series : [
									{
										name : 'Sensor 1',
										data : [ 7.0, 6.9, 9.5, 14.5, 18.4,
												21.5, 25.2, 26.5, 23.3, 18.3,
												13.9, 9.6, 7.0, 6.9, 9.5, 14.5,
												18.4, 21.5, 25.2, 26.5, 23.3,
												18.3, 13.9, 9.6, 6.9, 9.5,
												14.5, 18.4, 21.5, 25.2, 26.5,
												18.3 ]
									},
									{
										name : 'Sensor 2',
										data : [ 3.9, 4.2, 5.7, 8.5, 11.9,
												15.2, 17.0, 16.6, 14.2, 10.3,
												6.6, 4.8, 3.9, 4.2, 5.7, 8.5,
												11.9, 15.2, 17.0, 16.6, 14.2,
												10.3, 6.6, 4.8, 3.9, 4.2, 5.7,
												8.5, 11.9, 15.2, 17.0, 16.6 ]
									} ]
						});
	} else if (filtre == 'Annualy') {
		document.getElementById('container').innerHTML = "Annualy OK";
		$('#container')
				.highcharts(
						{
							chart : {
								type : 'line'
							},
							title : {
								text : 'Annualy Average Temperature'
							},
							subtitle : {
								text : 'Source: Sensor 1 & Sensor 2 - PING32'
							},
							xAxis : {
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct', 'Nov', 'Dec' ]
							},
							yAxis : {
								title : {
									text : 'Temperature (°C)'
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : true
									},
									enableMouseTracking : false
								}
							},
							series : [
									{
										name : 'Sensor 1',
										data : [ 7.0, 6.9, 9.5, 14.5, 18.4,
												21.5, 25.2, 26.5, 23.3, 18.3,
												13.9, 9.6 ]
									},
									{
										name : 'Sensor 2',
										data : [ 3.9, 4.2, 5.7, 8.5, 11.9,
												15.2, 17.0, 16.6, 14.2, 10.3,
												6.6, 4.8 ]
									} ]
						});
	}

	else {
		document.getElementById('container').innerHTML = "";
	}
}
