var canvas = document.querySelector('canvas');

var ctx = canvas.getContext("2d");


function CountDown (x, y, radius, speed) {
	this.posx = x;
	this.posy = y;
	this.radius = radius;
	this.speed = speed;
	this.angle =  - Math.PI / 2;

	this.draw = function () {
		ctx.clearRect(0, 0, 200, 200);
		ctx.beginPath();
		ctx.arc(this.posx, this.posy, this.radius, - Math.PI / 2, this.angle, false);
		ctx.lineWidth = 15;
		ctx.strokeStyle = "rgba(232, 227, 59, 0.7)";
		ctx.stroke();

		ctx.beginPath();
		ctx.arc(this.posx, this.posy, this.radius, this.angle, Math.PI * 3 / 2, false);
		ctx.lineWidth = 15;
		ctx.strokeStyle = "rgba(120, 220, 114, 0.9)";
		ctx.stroke();
	}

	this.update = function () {
		this.angle += this.speed;
		this.draw();
	}

	this.final = function () {
		ctx.clearRect(0, 0, 200, 200);
		ctx.beginPath();
		ctx.arc(this.posx, this.posy, this.radius, 0, Math.PI * 2, false);
		ctx.lineWidth = 15;
		ctx.strokeStyle = "rgba(232, 227, 59, 0.7)";
		ctx.stroke();
	}
}

var requestId;
var countDown = new CountDown(150, 80, 40, 0.01);

function loop () {
	requestId = undefined;
	countDown.update();
	start();
	if(countDown.angle > Math.PI * 3 / 2){
		stop();
		countDown.final();
	}
}

function start () {
	if (!requestId) {
       requestId = window.requestAnimationFrame(loop);
    }
}

function stop () {
	if (requestId) {
       window.cancelAnimationFrame(requestId);
       requestId = undefined;
    }
}

start();