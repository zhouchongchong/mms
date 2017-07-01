const customName = localStorage.getItem('customName');
const validateLogin = function() {
	$.ajax({
		type:'post',
		url:'http://localhost:8080/mms/user/isin',
		data:{
			'userName':customName
		},
		dataType:'json',
		success:function(data){
			if(data.result == 0){
				//window.location.href= "http://localhost:8080/mms/login_index.html"
			}else{
				$('.ifLogin').text('您好：'+customName);
			}
		},
		error:function(e){
			
		}
	});
}
validateLogin()