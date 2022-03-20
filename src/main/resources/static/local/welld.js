$(document).ready(function () {
	$('#deleteall-id').click(function () {
		$.ajax('/deleteall', {type: 'DELETE'});
		$("#msgview-id").empty().append(wdFormatPoint(null));
	});
	$('#viewall-id').click(function () {
		$.ajax({url: "/points/all"})
				.then(function (data) {
					$("#msgview-id").empty().append(wdFormatPoint(data));
				});
	});
	$("#findsegment-id").click(function () {
		let form = $('#form-segment')[0];
		let nrPoint = $('#nrpoint-id').val();
		let restUrl = "/points/segment/" + nrPoint;
		
		let check = form.checkValidity();

		if (!check) {
			form.reportValidity();
		}
		if (check) {
			$.ajax({url: restUrl})
					.then(function (data) {
						$("#msgsegment-id").empty().append(wdFormatSegment(data));
					});
		}
	});
});

function wdFormatSegment(data) {
	console.log(data);
	if (data === null || data.length<=1) {
		return "Inserire almeno due punti";
	}
	let result = "<ul class=\"list-group\">";
	for (let i = 0; i < data.length; ++i) {
		let segment = data[i].segment;
		let points = data[i].points;
		result += "<li style=\"color: green\" class=\"list-group-item\">";
		if (segment.vertical) {
			result += "x = " + segment.constantX;
		}
		if (!segment.vertical) {
			result += "y = " + (segment.m === 1 ? "x" : segment.m===0 ? "" : segment.m + "x ")
							+ (segment.q === 0 ? "" : segment.q > 0 ? (" + " + segment.q) : segment.q);
		}
		result += "</li>"
		result += wdFormatPoint(points);
	}
	result += "</ul>"
	return result;
}
function wdFormatPoint(data) {
	if (data === null || data.length === 0) {
		return"Nessun punto presente"
	}
	let result = "<ul class=\"list-group\">";
	let rowpoint = jQuery.validator.format("<li class=\"list-group-item\">P{0}({1},{2})</li>");
	for (let i = 0; i < data.length; ++i) {
		let p = data[i];
		result += rowpoint(i + 1, p.x, p.y);
	}
	result += "</ul>"
	return result;


}