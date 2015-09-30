<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>text</title> 
</head>
<body>

 <form action="${ctx}/hiveManage.do?method=hiveQuery" method="post">
	sql: <input type="text" value=""   name="querysql" />
	type 1是开发 2是测试:  <input type="text" value="${param.type }"   name="type" />
	db_name:  <input type="text" value="${param.db_name }"   name="db_name" />
	是否加载udf 1是 2不是:  <input type="text" value="${param.isudf }"   name="isudf" />
	<input type="submit">
</form> 

	<table  border="1"  style="border-collapse:collapse">
		<tr>
		<td align="center">num</td>
			<c:forEach items="${columList}" var="info" varStatus="i">
						<td align="center">${info}</td>
			</c:forEach>
		</tr>
		<c:forEach items="${returnlist}" var="info" varStatus="i">
		<tr>
		<td  align="center">${i.count}</td>
			<c:forEach items="${info}" var="info1" varStatus="j">
						<td  align="center">${info1}</td>
			</c:forEach>
		</tr>
		</c:forEach>
		
		<tr>
		
		</tr>

	</table>
</body>
</html>