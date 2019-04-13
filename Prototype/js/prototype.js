class Hotspot {
	/**
	* @param {number} x
	* @param {number} y
	* @param {number} width
	* @param {number} height
	*/
	constructor(x, y, width, height) {
		/** @type {number} */
		this.x = x;
		/** @type {number} */
		this.y = y;
		/** @type {number} */
		this.width = width;
		/** @type {number} */
		this.height = height;
	}
}

/** @type {!Element} */
const screen = document.getElementsByClassName("screen")[0];
/** @type {!Map<!Hotspot, function()>} */
const hotspotHandlers = new Map();
let soundIsPlaying = false;

function showMainScreen() {
	screen.style.background = `no-repeat center url("./image/main_screen_1.svg")`;
	hotspotHandlers.clear();
	hotspotHandlers.set(new Hotspot(88, 29, 60, 60), () => {
		showMainScreen2();
	});
	hotspotHandlers.set(new Hotspot(196, 115, 167, 40), () => {
		showMainScreen3();
	});
	hotspotHandlers.set(new Hotspot(19, 344, 167, 60), () => {
		playSound("./sound/sound1.wav");
	});
	hotspotHandlers.set(new Hotspot(196, 485, 167, 60), () => {
		playSound("./sound/sound2.wav");
	});
}

function showMainScreen2() {
	screen.style.background = `no-repeat center url("./image/main_screen_2.svg")`;
	hotspotHandlers.clear();
	hotspotHandlers.set(new Hotspot(160, 29, 60, 60), () => {
		showMainScreen();
	});
}

function showMainScreen3() {
	screen.style.background = `no-repeat center url("./image/main_screen_3.svg")`;
	hotspotHandlers.clear();
	hotspotHandlers.set(new Hotspot(19, 115, 167, 40), () => {
		showMainScreen();
	});
}

/**
 * @param {string} source
 */
function playSound(source) {
	if (!soundIsPlaying)
	{
		soundIsPlaying = true;
		const audio = new Audio(source);
		audio.onended = () => soundIsPlaying = false;
		audio.play();
	}
}

/**
 * @param {number} x
 * @param {number} y
 * @param {!Hotspot} hotspot
 * @returns {boolean}
 */
function intersect(x, y, hotspot)
{
	return 	(x >= hotspot.x) &&
			(x <= hotspot.x + hotspot.width) &&
			(y >= hotspot.y) &&
			(y <= hotspot.y + hotspot.height);
}


function addTooltips() {
	for (const [hotspot] of hotspotHandlers.entries())
	{
		const tooltip = document.createElement('div');

		tooltip.className = "tooltip";
		tooltip.style.position = "absolute";
		tooltip.style.left = `${hotspot.x}px`;
		tooltip.style.top = `${hotspot.y}px`;
		tooltip.style.width = `${hotspot.width}px`;
		tooltip.style.height = `${hotspot.height}px`;

		screen.appendChild(tooltip);
	}
}

function removeTooltips() {
	while (screen.lastChild) {
		screen.removeChild(screen.lastChild);
	}
}

/**
 * @param {!MouseEvent} e
 */
function onClickScreen(e) {
	removeTooltips();

	for (const [hotspot, handler] of hotspotHandlers.entries())
	{
		if (intersect(e.layerX, e.layerY, hotspot))
		{
			handler();
			return;
		}
	}

	addTooltips();
}

function start() {
	screen.addEventListener("click", onClickScreen);
	showMainScreen();
}

start();