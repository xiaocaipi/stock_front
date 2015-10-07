<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/Common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--<link rel="stylesheet" href="${ctx}/css/g.base.css" />-->
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${ctx}/js/jquery1.8.2.js"></script>
   <script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
/*
 * 创建社团页面  （初始化）
 */

	$(document).ready(function(){ 
	   
		dogetList(2);
	});

	function dogetList(status){
		
		 $("#status").attr("value",status);
		getList(1);
	}


function getList(pageIndex){

		
		$.ajax({ 
			url: '${ctx}/stockrt.do?method=getStockRT&pageIndex='+pageIndex+"&"+new Date(),
	            type: 'POST',
	            dataType: 'json', 
	            data:$("#form").serialize(),
	            success: function(data) { 
				$("tr.removeClass").remove();
				 $("#tab1").empty();
				   var htmlStr='';
	               $.each(data,function(idx,item){ 
		            
	              	 htmlStr+="<tr class='removeClass'>"+
	      	  			"<td>"+item.code+"</td>"+

        	           "<td>"+item.overprice+" &nbsp;</td>"+
        	           "<td>"+item.lowprice+"</td>"+	
        	           "<td>"+item.zhangfu+"</td>"+	
        	           "<td>"+item.diefu+"</td>"+	
        	           "<td>"+item.huanshoulv+"</td>"+	
        	           "<td>"+item.status+"</td>"+	
        	           "<td>" +
                   	"<a href='${ctx}/stockrt.do?method=editAlert&code="+item.code+"' target='_blank' class=''><span>编辑</span></a> "+
                        	"<a href='###' onclick='deleteword("+item.id+")' class='btn-green-small'><span>删除</span></a> "+
        	           "</td>"+	
        	           "</tr>";		
         	           
	               });
	              
	               $("#tab1").append(htmlStr);

	               
	               setPage(data,'getList');///设置分页
	            }
		});	 
	
	  
  }

function paixucreate(status){
	
	 $("#createTime").attr("value",status);
	 $("#modifyTime").attr("value",null);
	 getList(1);
}
function paixuUpdate(status){
	
	 $("#modifyTime").attr("value",status);
	 $("#createTime").attr("value",null);
	 getList(1);
}

function addalert(){
	document.getElementById("form").action ="${ctx}/stockrt.do?method=addAlert";
	document.getElementById("form").submit();
}
</script>


<!--header begin-->

    <body>
<%-- <%@ include file="/Common/globaltop.jsp"%>   --%>
	

   
    <!--content begin-->
    <div class="wrap">
    	<div class="user clr-fn">
            <form id="form" method="post" name="form">  
            	<input type="hidden" name="status" id="status"/>
            	<input type="hidden"" name="createTime" id="createTime"/>
            	<input type="hidden" name="modifyTime" id="modifyTime"/>
            	<input type="hidden" name="isdelete" id="isdelete"/>
            	<input type="hidden" name="wordid" id="wordid"/>
            	
            	
            </form> 
            <!--主体区开始-->
            <div class="section">
            
                <div class="inner clr-fn">
                    <input type="button" value="add" onclick="addalert()"/>
                    <!--列表-->
                    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="table-list mb20">
                    	<!--标题-->
                    	<thead>
                            <tr>
                                <th class="aln-l">code</th>
                                <th>大于价格</th>
                                <th>小于价格</th>
                                <th>大于涨幅</th>
                                <th>小于跌幅</th>
                                <th>换手率</th>
                                <th>状态（1，是预警过 2，是没有）</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <!--内容-->
                    	<tbody id="tab1">
                          
                        </tbody>
                    </table>
                    <!--列表结束-->
                    
                    <div class="aln-c">
                        <div class="paging clr-fn" id="pageBar">
                          
                        </div>
                    </div>
                    
                </div>
            </div>
            <!--主体区结束-->
        </div>
        
    </div>
    <!--content end-->
     

    

    

    
    <!--footer begin-->
<%--     <%@ include file="/Common/footer.jsp"%> --%>
    <!--footer end-->
</body>

</html>
