<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="header.html" %>

 <html xmlns="http://www.w3.org/1999/xhtml">
     <head>
          	<title>Public Page</title>
         	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
			<script type="text/javascript" src="https://www.google.com/jsapi"></script>
			<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
			<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" />
			<link rel="stylesheet" href="/resources/demos/style.css" />
			<script type="text/javascript">
				
			$(function(){
		  		drawChart("line");
		  		$('#ddlName').change(function() {
		  	    	alert($(this).val());
		  	    	drawChart($(this).val());
		  	    	})
			});
			
			 // Load the Visualization API and the piechart package.
		    google.load('visualization', '1', {'packages':['corechart']});
			 
			 
		    function drawChart(chartType) {

		        var jsonData = $.ajax({
		            url: "/RoleAuth/TPSJsonDataServlet",
		            //data: {"uname":uname},
		            dataType:"json",
		            async: false,
		            //timeout:10000,
		            }).responseText;

		       /*  var jsonData = $.ajax({
		            url: "http://10.0.41.231:8080/Rest1.8/api/tps",
		            //data: {"uname":uname},
		            dataType:"json",
		            async: false
		            }).responseText; */
		            
		        // Create our data table out of JSON data loaded from server.
		        var data = new google.visualization.DataTable();
		        var dataArray = [];
		        data.addColumn('string', 'Date');
		        data.addColumn('number', 'TPSCount');
		        //data.addRows(jsonData);
		        $.each(JSON.parse(jsonData), function(i,obj){
		  	    	  
		  	    	  dataArray.push([obj.date,obj.tpsCount]);
		  	      });
		        data.addRows(dataArray);
		        console.log(data);

		        // Instantiate and draw our chart, passing in some options.
		        	if(chartType == "bar"){
		        		  var chart1 = new google.visualization.ColumnChart(document.getElementById('chart_div1_tab'));
		        		  chart1.draw(data, {width: 500, height: 400});	
		            } else if(chartType == "line"){
		          	  var chart2 = new google.visualization.LineChart(document.getElementById('chart_div1_tab'));
		          	  chart2.draw(data, {width: 500, height: 400});
		            } else{
		          	  var chart3 = new google.visualization.PieChart(document.getElementById('chart_div1_tab'));
		          	  chart3.draw(data, {width: 500, height: 400});  				
		            }
		        
		      }
			</script>
     </head>
     <body>
         <h1>Welcome!  <sec:authentication property="principal.username"/> </h1>
         <h4>${message}</h4>
          ${HelloMessage} 
          
        <div id="header"></div>
		<div id="chart_div1_tab"></div>
		<div id="chart_div2_tab"></div>
		<div id="chart_div3_tab"></div>
		<div id="footer"></div>
		
		<select id="ddlName">
			<option value="line">Line Chart</option>
			<option value="bar">Bar Chart</option>
			<option value="pie">Pie Chart</option>
		</select>
        <a href="<c:url value='j_spring_security_logout'/>">Click here to logout</a>
     </body>
 </html>

