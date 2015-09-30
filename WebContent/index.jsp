<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>main</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
     <script src=""../js/jquery1.8.2.js""></script>
    <script src="../js/bootstrap.min.js"></script>
	<base href="<%=basePath%>">
  </head>
  <body>
    <h1>你好，世界！</h1>
    <button type="button" class="btn btn-xs btn-default" onclick="goPage(1)">hbase管理</button>
    
    <button type="button" class="btn btn-xs btn-default"  onclick="goPage(2)">预警管理</button>
    
    <button type="button" class="btn btn-xs btn-default" onclick="goPage(3)">hive查询</button>
    
  </body>
  
  <script type="text/javascript">
  function goPage(status){
	  if(status == 1){
		  window.location.href="<%=basePath%>hbasequery.do?method=hbaseManageInt"; 
	  }else if(status ==2){
		  window.location.href="<%=basePath%>stockrt.do?method=alterManager"; 
	  }else if(status ==3){
		  window.location.href="<%=basePath%>hiveManage.do?method=hiveQueryInt"; 
	  }
	}
  </script>
</html>
