
 var x = document.getElementById("notif");
  var y = 0;
  
  test();
  
  function test() {
	  if (y == 0) {
	 	 document.getElementById("myChart").style.display ='none';
	  }
  }
  
  x.onclick = function cacache() {
	  
	  if (y == 0) {
		  document.getElementById("myChart").style.display ='block';
		  y = 1;
	  }
	  else {
		  document.getElementById("myChart").style.display ='none';
		  y = 0;
	  }
	  
  }