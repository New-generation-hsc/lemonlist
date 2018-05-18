
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

$("#editTask").click(function () {
	$(".main-body").animate({marginLeft : "-268px"}, 1500);
	$(".task-edit").animate({right : "0"}, 2000);
});

$(".edit-close").click(function () {
	$(".task-edit").animate({right : "-425px"}, 1500);
	$(".main-body").animate({marginLeft : "160px"}, 2000);
})

autoPlay();