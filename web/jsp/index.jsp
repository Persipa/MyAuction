<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>拍卖系统-Welcome</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/WebCalendar.js" type="text/javascript"></script>
</head>

<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="sale">
			<h1 class="lf">在线拍卖系统</h1>
			<div class="logout right">
				<a href="${pageContext.request.contextPath}/user/doLogout" title="注销">注销</a>
			</div>
		</div>
		<div class="forms">
		<form id="form_query" action="${pageContext.request.contextPath}/auction/findAuctions" method="post">
			<input id="page" name="pageNum" type="hidden" value="1">
			<label for="name">名称</label> 
			<input name="auctionname" value="${condition.auctionname }" type="text" class="nwinput" id="name" />
			<label for="names">描述</label> 
			<input name="auctiondesc" value="${condition.auctiondesc }" type="text" id="names" class="nwinput" /> 
			<label for="time">开始时间</label>      
        	<input name="auctionstarttime" 
            value="<fmt:formatDate value='${condition.auctionstarttime}' pattern='yyyy-MM-dd HH:mm:ss' />"
        	type="text" id="time" class="nwinput" readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
			<label for="end-time">结束时间</label> 
			<input name="auctionendtime" 
			value="<fmt:formatDate value="${condition.auctionendtime }" pattern="yyyy-MM-dd HH:mm:ss"/>" 
			type="text" id="end-time"  class="nwinput" readonly="readonly"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/> 
			<label for="price">起拍价</label> 
			<input name="auctionstartprice" value="${condition.auctionstartprice }" type="text" id="price" class="nwinput" /> 
			<input name="" type="submit" value="查询" class="spbg buttombg f14  sale-buttom" />
		</form>
			<c:if test="${sessionScope.user.userisadmin==1 }">
				<input type="button" value="发布" onclick="location='${pageContext.request.contextPath}/jsp/addAuction.jsp'"
					class="spbg buttombg f14  sale-buttom buttomb" />
			</c:if>
		</div>
		<div class="items">
			<ul class="rows even strong">
				<li>名称</li>
				<li class="list-wd">描述</li>
				<li>开始时间</li>
				<li>结束时间</li>
				<li>起拍价</li>
				<li class="borderno">操作</li>
			</ul>


			<c:forEach var="auction" items="${auctionList }" varStatus="state">
				<ul class="rows <c:if test="${state.index%2!=0 }">even</c:if>">
					<li><a href="${pageContext.request.contextPath}/auction/toAuctionDetail/${auction.auctionid}" title="">${auction.auctionname }</a></li>
					<li class="list-wd">${auction.auctiondesc }</li>
					<li><fmt:formatDate value="${auction.auctionstarttime }"
							pattern="yyyy.MM.dd HH:mm:ss" /></li>
					<li><fmt:formatDate value="${auction.auctionendtime }"
							pattern="yyyy.MM.dd HH:mm:ss" /></li>
					<li>${auction.auctionstartprice }</li>
					<li class="borderno red">
					<c:if test="${sessionScope.user.userisadmin==1 }">
						<a href="${pageContext.request.contextPath}/auction/toUpdateDetail?auctionId=${auction.auctionid}" title="竞拍" onclick="dele();">修改</a>|
          				<a href="${pageContext.request.contextPath}/auction/deleteAuction?auctionId=${auction.auctionid}" title="竞拍" onclick="abc();">删除</a>
					</c:if> 
					<c:if test="${sessionScope.user.userisadmin==0 }">
						<a href="${pageContext.request.contextPath}/auction/toAuctionDetail/ ${auction.auctionid}" title="竞拍">竞拍</a>
					</c:if>
					</li>
				</ul>

			</c:forEach>

			<div class="page">
				<span class="red">第${pageInfo.pageNum }/${pageInfo.pages }页</span> <a
					href="javascript:jumpPage(1)"
					title="">首页</a> <a
					href="javascript:jumpPage(${pageInfo.prePage})"
					title="">上一页</a> <a
					href="javascript:jumpPage(${pageInfo.nextPage})"
					title="">下一页</a> <a
					href="javascript:jumpPage(${pageInfo.pages})"
					title="">尾页</a>
			</div>
		</div>
		<script>
			function abc() {

				if (confirm("你真的确认要删除吗？请确认")) {

					return true;
				} else {
					return false;
				}

			};
			function dele() {
				if (confirm("你真的确认要修改吗？请确认")) {
					return true;
				} else {
					return false;
				}
			}
			function jumpPage(pageNum){
				
				document.getElementById("page").value=pageNum;
				document.getElementById("form_query").submit();
			}
		</script>
		<!-- main end-->
	</div>
</body>

</html>