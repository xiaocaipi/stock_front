<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/Common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--<link rel="stylesheet" href="${ctx}/css/g.base.css" />-->

<link rel="stylesheet" href="${ctx}/css/c.user.css" />
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

        	           "<td>"+item.close+" &nbsp;</td>"+
        	           "<td>"+item.zhangdiefudu+"</td>"+	
        	           "<td>"+item.dealnowShou+"</td>"+	
        	           "<td>" +
                   	"<a href='${ctx}/aboutKnowledge.do?method=isCreateOrShow&kmKnowledgeId="+item.id+"' target='_blank' class='btn-green-small'><span>编辑</span></a> "+
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
                    
                    <!--列表-->
                    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="table-list mb20">
                    	<!--标题-->
                    	<thead>
                            <tr>
                                <th class="aln-l">code</th>
                                <th>nowprice</th>
                                <th>zhangdiefu</th>
                                <th>chengjiaoshou</th>
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
