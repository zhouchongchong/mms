const userName = localStorage.getItem('userName');
if(!userName){
	parent.window.location.href= "http://118.190.137.113:8080/mms/login.html"
}
const validateLogin = function() {
	$.ajax({
		type:'post',
		url:'http://118.190.137.113:8080/mms/user/isin',
		data:{
			'userName':userName
		},
		dataType:'json',
		success:function(data){
			if(data.result == 0){
				parent.window.location.href= "http://118.190.137.113:8080/mms/login.html"
			}else{
				$('.dl-log-user').text(userName)
			}
		},
		error:function(e){
			
		}
	});
}
validateLogin()