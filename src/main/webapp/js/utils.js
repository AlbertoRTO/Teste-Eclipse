function showHide(shID) {
	if (document.getElementById(shID)) {

		if (document.getElementById(shID+'-show').style.display != 'none' && document.getElementById(shID).style.display != 'block' && document.getElementById(shID).style.display != ""  ) {
			document.getElementById(shID).style.display = 'block';
			document.getElementById(shID).className = "aberto";
			document.getElementById(shID+'-show').className = "aberto";
		} else {
			document.getElementById(shID).style.display = 'none';
			document.getElementById(shID+'-show').className = "fechado";
		}
	}
} 

function showHideChild(shID) {
	if (document.getElementById(shID)) {
		if (document.getElementById(shID+'-show').style.display != 'none' && document.getElementById(shID).style.display != 'block' && document.getElementById(shID).style.display != "" ) {
			document.getElementById(shID).style.display = 'block';
			document.getElementById(shID).className = "aberto";
		} else {
			document.getElementById(shID).style.display = 'none';
		}
	}
} 

function showGrid(shID) {
	if (document.getElementById(shID)) {
		if (document.getElementById(shID).style.display != 'block' && document.getElementById(shID).style.display != "" ) {
			document.getElementById(shID).style.display = 'block';
		} else {
			document.getElementById(shID).style.display = 'none';
		}
	}
} 

function changeStyle()
{
	if (document.getElementById("iceModalFrame")){
		
		var x=document.getElementById("iceModalFrame");
		var y=x.contentWindow;
		if (y.document)y=y.document;
		y.body.style.backgroundColor="#000";
		
	}

}





