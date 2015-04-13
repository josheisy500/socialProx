var mapWin = Titanium.UI.createWindow();

/**************************  current window behaviour   ***************************/

Ti.Geolocation.preferredProvider = "gps";
var result;

mapWin.addEventListener('androidback', function(e) {
	var win = Titanium.UI.createWindow({
		url : 'profile.js'
	});
	win.open();
	mapWin.close();
	//only 1 map activity allowed - close when finished

});

//SHOW CUSTOM ALERT IF DEVICE HAS GEO TURNED OFF
if (Titanium.Geolocation.locationServicesEnabled === false) {
	Titanium.UI.createAlertDialog({
		title : 'Social Prox',
		message : 'Your device has location turned off - please turn it on.'
	}).show();
}

//when window launches event find user  coordinates
mapWin.addEventListener('open', function() {

	Titanium.Geolocation.getCurrentPosition(function(e) {
		if (!e.success || e.error) {
			Ti.API.info("Code translation: " + translateErrorCode(e.code));
			alert('error ' + JSON.stringify(e.error));
			return;

		}

		Titanium.Geolocation.accuracy = Titanium.Geolocation.ACCURACY_BEST;
		Titanium.Geolocation.distanceFilter = 10;

		longitude = e.coords.longitude;
		latitude = e.coords.latitude;

		Titanium.API.info('geo - current location: ' + ' long ' + longitude + ' lat ' + latitude);

		var map = Ti.Map.createView({
			mapType : Titanium.Map.STANDARD_TYPE,
			region : {
				longitude : e.coords.longitude,
				latitude : e.coords.latitude,
				latitudeDelta : 0.5,
				longitudeDelta : 0.5,
			},
			regionFit : true,
			animate : true,
		});

		var userLoc = Titanium.Map.createAnnotation({
			latitude : e.coords.latitude,
			longitude : e.coords.longitude,
			title : 'Your Location',
			subtitle : 'your current location: ' + ' long ' + longitude + ' lat ' + latitude,
			animate : true,
			image : "marker.png"
		});
		map.addAnnotation(userLoc);
		mapWin.add(map);

	});

});

//}//end else
function translateErrorCode(code) {
	if (code == null) {
		return null;
	}
	switch (code) {
	case Ti.Geolocation.ERROR_LOCATION_UNKNOWN:
		return "Location unknown";
	case Ti.Geolocation.ERROR_DENIED:
		return "Access denied";
	case Ti.Geolocation.ERROR_NETWORK:
		return "Network error";
	case Ti.Geolocation.ERROR_HEADING_FAILURE:
		return "Failure to detect heading";
	case Ti.Geolocation.ERROR_REGION_MONITORING_DENIED:
		return "Region monitoring access denied";
	case Ti.Geolocation.ERROR_REGION_MONITORING_FAILURE:
		return "Region monitoring access failure";
	case Ti.Geolocation.ERROR_REGION_MONITORING_DELAYED:
		return "Region monitoring setup delayed";
	}
}

mapWin.open();
