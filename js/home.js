
var themes = $("#list_theme > li");
var circles = $(".theme-circle");

const len = themes.length;
var index = 0;

const clearActive = lists => {
	for (var i = 0; i < len; i++) {
		$($(lists)[i]).removeClass('active');
	}
}

const setActive = index => {
	clearActive(themes);
	$(themes[index % len]).addClass('active');
	clearActive(circles);
	$(circles[index % len]).addClass('active');
}

const autoPlay = () => {
	clearActive();
	setActive(index);
	index = (index + 1) % len;
	setTimeout(autoPlay, 2000);
}

autoPlay();