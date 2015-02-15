var win = Titanium.UI.createWindow({
	backgroundColor : '#fff',
	layout:'vertical'
});

//textfields
var textInterest = Ti.UI.createTextField({
	borderStyle : Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
	color : '#336699',
	top : 50,
	hintText : 'Current Interest',
	left : 10,
	width : 250,
	height : 60

});

var textType = Ti.UI.createTextField({
	borderStyle : Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
	color : '#336699',
	top : 100,
	hintText : 'Interest Type',
	left : 10,
	width : 250,
	height : 60
});

var button = Ti.UI.createButton({
	title : 'Send Information',
	top : 200,
	width : 200,
	height : 50
});

button.addEventListener('click', function(e) {

	var params = {
		"places" : {
			Country : textInterest.getValue(),
			Capital : textType.getValue()
		}
	};

	var xhr = Ti.Network.createHTTPClient({});
	
	// function to deal with errors
	xhr.onerror = function() {
		Ti.API.info('error, HTTP status = ' + this.status);
		alert('Error Sending Data');
	};

	// function to deal with response
	xhr.onload = function() {
		console.log('success, HTTP status = ' + this.status);
	};
		
	xhr.open("POST", 'http://130.206.127.43:8080/Test');

	//set enconding
	xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");

	xhr.send(JSON.stringify(params));

});

win.add(textInterest);
win.add(textType);
win.add(button);
win.open();
