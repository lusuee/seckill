<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>秒杀详情页</title>
	<%@ include file="common/head.jsp" %>
</head>
<body>

<div class="container">
	<div class="panel panel-default text-center">
		<div class="panel-heading">
			<h2>${secKill.name}</h2>
		</div>
		<div class="panel-body">
			<h2 class="text-danger">
				<!-- 显示time图标 -->
				<span class="glyphicon glyphicon-time"></span>
				<!-- 展示倒计时 -->
				<span class="glyphicon" id="seckill-box"></span>
			</h2>
		</div>
	</div>
</div>

<!-- 登录弹出层，输入电话 -->
<!-- class 不要增加fade，否则在调用modal方法显示模态框时无法正常显示 -->
<div id="killPhoneModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title text-center">
					<span class="glyphicon glyphicon-phone"></span>
				</h3>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2">
						<input type="text" name="killPhone" id="killPhone"
						       placeholder="填手机号^o^" class="form-control">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!-- 验证信息 -->
				<span id="killPhoneMessage" class="glyphicon"></span>
				<button type="button" id="killPhoneBtn" class="btn btn-success">
					<span class="glyphicon glyphicon-phone"></span>
					Submit
				</button>
			</div>
		</div>
	</div>
</div>

</body>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>
<script src="http://cdn.bootcss.com/tether/1.4.0/js/tether.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<!-- jQuery Cookie操作插件 -->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery countdown倒计时插件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<!-- 开始编写交互逻辑 -->
<script src="/resources/script/seckill.js"></script>
<script type="text/javascript">
	$(function () {
		//使用EL表达式传入参数
		seckill.detail.init({
			seckillId : ${secKill.seckillId},
			startTime : ${secKill.startTime.time},  //毫秒
			endTime : ${secKill.endTime.time}
		})
	})
</script>
</html>