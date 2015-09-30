<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/Common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highstock Example</title>

<script type="text/javascript"
	src="${ctx}/js/stock/jquery1.8.2.js"></script>
<style type="text/css">
${
demo
.css
}
</style>
<script type="text/javascript">
$(function () {
    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?a=e&filename=aapl-ohlc.json&callback=?', function (data) {

        // create the chart
        $('#container').highcharts('StockChart', {


            rangeSelector : {
                inputEnabled: $('#container').width() > 480,
                selected : 1
            },

            title : {
                text : 'AAPL Stock Price'
            },

            series : [{
                type : 'candlestick',
                name : 'AAPL Stock Price',
                data : data,
                dataGrouping : {
                    units : [
                        [
                            'week', // unit name
                            [1] // allowed multiples
                        ], [
                            'month',
                            [1, 2, 3, 4, 6]
                        ]
                    ]
                }
            }]
        });
    });
});

		</script>
</head>
<body>
	<script src="${ctx}/js/stock/highstock.src.js"></script>
<!-- 	<script src="../../js/modules/exporting.js"></script> -->

	<div id="container" style="height: 400px; min-width: 310px"></div>
</body>
</html>

