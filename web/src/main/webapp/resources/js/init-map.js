
function init() {

	var myMap = new ymaps.Map("map", {
		center : [ 53.90, 27.56 ],

		zoom : 7
	});
	var myGeoObject = new ymaps.GeoObject({
		geometry : {
			type : "Point",
			coordinates : [ 55.8, 37.8 ]
		}
	});
	var myPlacemark = new ymaps.Placemark([ 55.8, 37.6 ]);

	myMap.geoObjects.add(myGeoObject);
	myMap.geoObjects.add(myPlacemark);
}
ymaps.ready(init);