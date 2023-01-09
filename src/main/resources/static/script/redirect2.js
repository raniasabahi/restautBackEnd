$('a').removeClass('active');
$('a:contains(Restaurants)').addClass('active');
$("#main-content").load("page/restaurant.html");

function show(page) {
	if (page == 'restaurants') {
		$('a').removeClass('active');
		$('a:contains(Restaurants)').addClass('active');
		$("#main-content").load("page/restaurant.html");
		event.preventDefault();
	}
	
if (page == "photos") {
		$('a').removeClass('active');
		$('a:contains(Photos)').addClass('active');
		$("#main-content").load("page/photo.html");
		event.preventDefault();
	}
if (page == "restoSpec") {
		$('a').removeClass('active');
		$('a:contains(Affecter les specialites)').addClass('active');
		$("#main-content").load("page/RstoSpeci.html");
		event.preventDefault();
	}

}
