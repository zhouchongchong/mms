// JavaScript Document
//var apiUrl="http://localhost:8080/mms/";
var apiUrl = "http://118.190.137.113:8080/mms/"
$(function() {
	$('.tablelist tbody tr:odd').addClass('odd');
	$(".tablelist li").click(function() {
		$(".tablelist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})
	$(".hallbtn").on("click", function() {
		if($(this).hasClass("hallhover")) {
			$(this).removeClass("hallhover");
		} else {
			$(this).addClass("hallhover")
		}
	});

});
/*对jquery的ajax再次封装*/
function JQajax(target, param, callback) {
	$.ajax({
		type: "post",
		url: apiUrl + target,
		data: param,
		dataType: 'json',
		timeout: 2000,
		async: true,
		success: callback,
		error: function(e) {

		}
	});
}
//自定义layer tips
function errorTips(par, text) {
	layer.tips(text, par, {
		tipsMore: true
	});
} 

function msg(text){
	layer.msg(text, {time: 2000, icon:6});
}
function msgEr(text){
	layer.msg(text, {time: 2000, icon:5});
}

/*取压缩图*/
function swallImg(target){
	var oindex = target.lastIndexOf('.');
	var newImg = target.substr(0,oindex) + 'small' + target.substr(oindex,target.length);
	return newImg
}