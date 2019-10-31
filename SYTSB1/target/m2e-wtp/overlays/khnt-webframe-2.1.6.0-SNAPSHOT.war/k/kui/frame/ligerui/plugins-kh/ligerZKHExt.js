//必填项检查 2013-5-25 下午4:34 lybide
function ligerElementRequired(element, value, g, p) {
	var ti = element;
	required = ti.attr("required");
	if (required && !value) {
		ti.addClass("requiredstar");
		//ti.after('<div class="l-requiredstar"></div>')
		//ti.parents(".l-text").add
	} else {
		ti.removeClass("requiredstar");
		//ti.next().remove();
		ti.parents(".l-text").removeClass("l-text-invalid");
	}
};