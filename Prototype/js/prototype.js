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
/** @type {!Array<!Element>} */
const hotspotTooltips = [];

function showMainScreen() {
	hotspotHandlers.clear();
	hotspotHandlers.set(new Hotspot(20, 20, 50, 50), () => {
		console.log("Ok, hotspot!")
	});

	screen.style.background = `no-repeat center url("./image/main_screen_1.svg")`;
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