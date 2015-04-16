//Create the current window
var win = Titanium.UI.createWindow({
	windowSoftInputMode : Ti.UI.Android.SOFT_INPUT_ADJUST_PAN,
	backgroundColor : 'transparent',
	backgroundImage : 'bg.jpg',
});

//button for guest login 
var button = Ti.UI.createButton({
	title : 'Try As A Guest',
	top : '50dp'
});

//open profile window if user selects try as guest
button.addEventListener('click', function(e) {
	var win = Ti.UI.createWindow({
		url : "profile.js"
	});
	win.open();
});
win.add(button);

var platformName = Titanium.Platform.osname;
var facebook;

if (platformName == 'android' || platformName == 'iphone' || platformName == 'ipad') {
	facebook = require('facebook');
} else {
	facebook = Titanium.Facebook;
}

facebook.appid = '790403037715544';
facebook.permissions = ['publish_stream', 'read_stream'];

//if facebook login is true open profile window
function updateLoginStatus() {
	if (facebook.loggedIn) {
		var win = Ti.UI.createWindow({
			url : "profile.js"
		});
		win.open();
	}

}

win.addEventListener('androidback', function(e) {
	facebook.addEventListener('logout', updateLoginStatus);
	win.close();
});

facebook.addEventListener('login', updateLoginStatus);
facebook.addEventListener('logout', updateLoginStatus);

win.add(facebook.createLoginButton({
	style : facebook.BUTTON_STYLE_WIDE,
	top : 100
}));



win.open();
