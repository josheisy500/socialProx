var win = Titanium.UI.createWindow({
	backgroundColor : '#fff'
});

var json,
    places,
    place,
    i,
    row,
    countryLabel,
    capitalLabel;
var table = Ti.UI.createTableView();
var tableData = [];

var xhr = Ti.Network.createHTTPClient({
	onload : function() {
		Ti.API.info('Received text: ' + this.responseText);

		json = JSON.parse(this.responseText);
		for ( i = 0; i < json.places.length; i++) {
			place = json.places[i];
			row = Ti.UI.createTableViewRow({
				height : 'auto'
			});
			countryLabel = Ti.UI.createLabel({
				text : place.Country,
				font : {
					fontSize : '24dp'
				},
				height : 'auto',
				left : '10dp',
				top : '5dp',
				color : '#000',
				touchEnabled : false
			});
			capitalLabel = Ti.UI.createLabel({
				text : place.Capital,
				font : {
					fontSize : '24dp'
				},
				height : 'auto',
				left : '150dp',
				top : '5dp',
				color : '#000',
				touchEnabled : false
			});

			row.add(countryLabel);
			row.add(capitalLabel);
			//row.add(rowView);
			tableData.push(row);
		}//end for

		table.setData(tableData);
	},

	onerror : function() {
		Ti.API.info('error, HTTP status = ' + this.status);
		alert('Error Reading Data');
	},
	timeout : 5000
});

xhr.open("GET", 'http://130.206.127.43:8080/Test');
xhr.send();
win.add(table);

win.open();
