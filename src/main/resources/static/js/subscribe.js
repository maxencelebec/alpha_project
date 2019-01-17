

var x = document.getElementById('inscription');
	
		x.onsubmit = function validerInscription () {
        	var a = document.forms["inscription"]["mail"].value;
        	var b = document.forms["inscription"]["confirmMail"].value;
        	var c = document.forms["inscription"]["name"].value;
        	var d = document.forms["inscription"]["password"].value;
        	var e = document.forms["inscription"]["confirmPassword"].value;
        	var f = document.forms["inscription"]["surname"].value;
        	var g = document.forms["inscription"]["username"].value;

        	if (a == "" || b == "" || c == "" || d == "" || e == "" || f == "" || g == "") {
				document.getElementsByClassName('error')[0].innerHTML = "Champ(s) Manquant(s)";
				return false;
        	}
        	else {
        		if (a == b) {
        			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        		    if (re.test(String(a).toLowerCase())) {
        		    	if (d == e) {
	        				return true;
	        			}
	        			else {
	        				document.getElementsByClassName('error')[0].innerHTML = "Mots de passe différents";
	        				return false;
	        			}
        		    }
        		    else {
        		    	document.getElementsByClassName('error')[0].innerHTML = "Mail non valide";
        				return false;
        		    }
	        			
        		}
        		else {
        			document.getElementsByClassName('error')[0].innerHTML = "Mails différents";
        			return false;
        		}
        		
        	}
		}