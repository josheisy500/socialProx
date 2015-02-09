var win = Ti.UI.createWindow({
	backgroundColor:'#fff'
});
var table = Ti.UI.createTableView();
var tableData = [];
var rowView = Ti.UI.createView ({height : 60, layout: 'horizontal'});
var json, places, place, i, row, countryLabel, capitalLabel;

//textfields 
var textCountry = Ti.UI.createTextField({
	borderStyle: Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
	color: '#336699',
	top: 10, left: 10, width: 250, height: 60
});
var textCapital = Ti.UI.createTextField({
	borderStyle: Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
	color: '#336699',
	top: 10, left: 10, width: 250, height: 60
});

var buttonGet = Titanium.UI.createButton({
	title: 'Get Information',
	top: 10,
	width: 100,
	height: 50
});
win.add(buttonGet);

var buttonSend = Titanium.UI.createButton({
	title: 'Send Information',
	top: 10,
	width: 100,
	height: 50
});
win.add(buttonSend);


//retrieve from servlet
buttonGet.addEventListener('click', function(e)
{
	var xhr = Ti.Network.createHTTPClient({
		onload : function(){
		
		
		Ti.API.info('Received text: ' + this.responseText);
		
        json = JSON.parse(this.responseText);
        for (i=0; i < json.places.length; i++)
        {
        	place = json.places[i];
			row = Ti.UI.createTableViewRow({
				height: '60dp'
			});
			countryLabel = Ti.UI.createLabel({
				text: place.Country,
				font:{
					fontSize:'16dp'
				},
				height: 'auto',
				left: '10dp',
				top: '5dp',
				color: '#000',
				touchEnabled:false
			});
			/*capitalLabel = Ti.UI.createLabel({
				text:'"' + place.Capital + '"',
				font:{
					fontSize:'4dp'
				},
				height: 'auto',
				left: '15dp',
				top: '5dp',
				color: '#000',
				touchEnabled:false
			});
			*/
			row.add(countryLabel);
			//row.add(capitalLabel);
			//row.add(rowView);
			tableData.push(row);
        }//end for
        
        table.setData(tableData);    
	},
	
	onerror: function() {
		Ti.API.info('error, HTTP status = ' + this.status);
		alert('Error Reading Data');
	},
	timeout:5000
	});

	xhr.open("GET", 'http://130.206.127.43:8080/Test');
	xhr.send();
	win.add(table);
});//end button event

//post servlet
buttonSend.addEventListener('click', function(e)
{
	win.add(textCountry);	
	win.add(textCapital);
	
	var params = {
		Country: textCountry.value,
		Capital: textCapital.value
	};
	//send to servlet
	var xhrSend = Ti.Network.createHTTPClient({
		onload : function(){
			Ti.API.info('Received text: ' + this.responseText);
		},
			
		onerror: function(){
			Ti.API.info('error, HTTP status = ' + this.status);
			alert('Error Reading Data');
		},
		timeout:5000
	});
	xhr.open("POST", 'http://130.206.127.43:8080/Test');
	xhr.send(params);
});//end button event

win.open();
