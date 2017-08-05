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
			if(data.result == 1){
				console.log(localStorage.getItem('customName'))
				var stR = "用戶："+localStorage.getItem('customName')+"&nbsp;&nbsp;<a class='text' href='javascript:void(0)'>已登录</a><span class='line'>|</span><a class='text' href='javascript:void(0)' onclick='logout()'>退出</a></span>";
				$('#ifLogin').html(stR);
			}
		},
		error:function(e){
			
		}
	});
}

function logout(){
	window.location.href="login_index.html";
	localStorage.removeItem('customName')
}
validateLogin()