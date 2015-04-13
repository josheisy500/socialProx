// create tab group
var tabGroup = Titanium.UI.createTabGroup();

//this window
var profWin = Titanium.UI.createWindow();

//the second window
var mapWin = Titanium.UI.createWindow({
	url : 'location.js'
});

var tab1 = Titanium.UI.createTab({
	title : 'Profile',
	window : profWin
});

var tab2 = Titanium.UI.createTab({
	title : 'Map',
	window : mapWin
});

/******************      Current Behaviour      ****************** */

profWin.addEventListener('androidback', function(e) {
	//do nothing only navigation is via tabGroup
	var win = Titanium.UI.createWindow({
		url : 'app.js'
	});
	win.open();
	profWin.close();
});

//when window launches event find user  coordinates
profWin.addEventListener('open', function() {

	Titanium.Geolocation.getCurrentPosition(function(e) {
		if (!e.success || e.error) {
			Ti.API.info("Code translation: " + translateErrorCode(e.code));
			alert('error ' + JSON.stringify(e.error));
			return;
		}

		Titanium.Geolocation.accuracy = Titanium.Geolocation.ACCURACY_BEST;
		Titanium.Geolocation.distanceFilter = 10;

		var longitude = e.coords.longitude;
		var latitude = e.coords.latitude;

		Titanium.API.info('geo - current location: ' + ' long ' + longitude + ' lat ' + latitude);

		var web = Ti.UI.createWebView({
			url : 'profile.html',
		});

		web.addEventListener('click', function(e) {
			Ti.App.fireEvent('app:fromTitaniumLat', {
				latitude : latitude
			});
			Ti.App.fireEvent('app:fromTitaniumLong', {
				longitude : longitude
			});
		});
		profWin.add(web);

	});

});

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

// add tabs to the group
tabGroup.addTab(tab1);
tabGroup.addTab(tab2);
// open tab group
tabGroup.open();
