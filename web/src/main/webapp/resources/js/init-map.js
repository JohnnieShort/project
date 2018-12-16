function initMap(points, avgLat, avgLong) {
	ymaps.ready(function() {

		var myMap = new ymaps.Map("map", {
			center : [ avgLat, avgLong ],
			zoom : 8
		}, {
			searchControlProvider : 'yandex#search'
		});

		// Создаем ломаную, используя класс GeoObject.
		
		var myGeoObject = new ymaps.GeoObject({
			// Описываем геометрию геообъекта.
			geometry : {
				// Тип геометрии - "Ломаная линия".
				type : "LineString",
				// Указываем координаты вершин ломаной.
				coordinates : points
			},
			// Описываем свойства геообъекта.
			properties : {
				// Содержимое хинта.
				hintContent : "I route",
				// Содержимое балуна.
				balloonContent : "Look map"
			}
		}, {
			// Задаем опции геообъекта.
			// Включаем возможность перетаскивания ломаной.
			draggable : false,
			// Цвет линии.
			strokeColor : "#FF0000",
			// Ширина линии.
			strokeWidth : 5
		});
		// Добавляем линии на карту.
		myMap.geoObjects.add(myGeoObject);
		for (var i = 0; i < points.length; i++) {
			var coord = points[i];
			var myPoint = new ymaps.Placemark(coord);
    		myMap.geoObjects.add(myPoint);
	}
		
		
	});

}