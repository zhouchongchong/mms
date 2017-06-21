const userName = localStorage.getItem('userName');
if(!userName){
	window.location.href = "../login.html"
}
const validateLogin = function() {
	$.ajax({
		type:'post',
		url:'http://localhost:8080/mms/user/isin',
		data:{
			'userName':userName
		},
		dataType:'json',
		success:function(data){
			if(data == 0){
				window.location.href = "../login.html"
			}
		},
		error:function(e){
			
		}
	})
}