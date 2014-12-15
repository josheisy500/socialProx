var win = Ti.UI.createWindow({
	title: 'Adding Map!',
	backgroundColour: '#000#'
});

var mapview = Ti.Map.createView({
	//top: 110,
	//height: 350,
	mapType: Ti.Map.STANDARD_TYPE,
	region: {
		//latitude: 52.82729,
		//longitude: -6.936106,
		latitudeDelta: 0.5,
		longitudeDelta: 0.5
	},
	animate: true,
	regionFit: true,
	userLocation: true    
});


win.add(mapview);

win.open();
