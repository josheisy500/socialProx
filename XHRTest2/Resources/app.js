var win = Ti.UI.createWindow({
	backgroundColor:'#fff'
});
var table = Ti.UI.createTableView();
var tableData = [];
var rowView = Ti.UI.createView ({height : 60, layout: 'horizontal'});

var buttonGet = Titanium.UI.createButton({
	title: 'Get Information',
	top: 10,
	width: 200,
	height: 50
});
win.add(buttonGet);

var buttonSend = Titanium.UI.createButton({
	title: 'Send Information',
	width: 200,
	height: 50
});
win.add(buttonSend);


//retrieve from servlet
buttonGet.addEventListener('click', function(e)
{
	Ti.API.info("Button was clicked");
	retrieveWin = Ti.UI.createWindow({
		url: 'retrieve.js',
		title: 'Pulling From Servlet'
	});	
	retrieveWin.open();
});//end button event


buttonSend.addEventListener('click', function(e)
{
	Ti.API.info("Button Pressed");
	sendData = Ti.UI.createWindow({
		url: 'sendData.js',
		title: 'Sending Data To Servlet'
	});
	sendData.open();
});

win.open();

