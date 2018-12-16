// эта функция принимает на вход ID HTML элемента и JSON массив данных. 
// она вставялет новые опции для селекта в текущий компонент. здесь она лишь объявляется (как метод в классе) - вызываться она будет позже
function initSelectElement(htmlElementId, jsonArray) {
	$('#' + htmlElementId).find('option').remove().end(); // удалить все
	// старые опции

	// вставить пустую опцию. в случае edit формы при начальной загрузке надо
	// будет подправить код, чтобы не перетиралоась выбранная опция. текущий
	// вариант только для нового объекта
	$('#' + htmlElementId).append($("<option></option>").attr({
		"disabled" : '',
		"selected" : '',
		"value" : ''
	}).text(' -- select a station -- '));

	// вставляет новые опции в элемент
	$.each(jsonArray, function(key, value) {
		$('#' + htmlElementId).append($("<option></option>").attr("value", value.id).text(value.name));
	});
	$('select').formSelect();
}

function initComboboxes(contextUrl) {
	var fromSelect = $('#from');
	fromSelect.change(function() {
		var selectedId = fromSelect.val();
		var routeId = $('#id').val();
		$.get(contextUrl + "/passengerRoute/toStations?routeId=" + routeId + "&selectedName=" + selectedId, function(
				stationsTo) {
			initSelectElement('to', stationsTo);
		})
	});
	
	
	var toSelect = $('#to');
	toSelect.change(function() {
		var selectedTo = toSelect.val();
		var routeId = $('#id').val();
		var selectedFrom = $('#from').val();
		
		
		
		$.get(contextUrl + "/passengerRoute/getPrice?routeId=" + routeId + "&selectedFrom=" + selectedFrom + "&selectedTo=" + selectedTo, function(
				price) {
			
			
			$('#price').text(price);
		})
		
		
	});
}