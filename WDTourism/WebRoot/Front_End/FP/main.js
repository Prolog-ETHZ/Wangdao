zoom = 7;		 markers = [];		user_marker_flag = 0;
initial_locattion = new google.maps.LatLng(23.6974161,120.9244374); 
	var		marker_words;
	var marker_pic;
	var scence_ptr;
	var lat,lng;

function initialize() {
	//Define & Create map									
	var mapOptions = {
		center: initial_locattion,
		zoom: zoom,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	dropMarker();
	for( i=0;i<34;i++)
	google.maps.event.addListener(markers[i], 'click', function(event) { 
		for( j=0;j<34;j++){
			lat = event.latLng.lat();
			lng = event.latLng.lng();
			//if((lat==scene[j]["lat"])&&(lng==scene[j]["lng"]))
			console.log(lat);
			console.log(lng);
				
		}
    }); 
			
}	/*end initial function*/


function dropMarker(){

	for( i=0;i<34;i++){       
		scence_ptr = new google.maps.LatLng(scenes[i]["lat"],scenes[i]["lng"]);
	
		if(scenes[i]["type"]=="美食")				marker_pic="flag_orange.png";   	//orange
		else if(scenes[i]["type"]=="溫泉")			marker_pic="flag_darkblue.png";		//dark blue 
		else if(scenes[i]["type"]=="風景區")		marker_pic="flag_blue.png";			//blue
		else if(scenes[i]["type"]=="博物館")		marker_pic="flag_purple.png";  		//purple
		else if(scenes[i]["type"]=="休閒農場")		marker_pic="flag_green.png";   		//green
		else if(scenes[i]["type"]=="主題遊樂園")	marker_pic="flag_yellow.png";		//yellow
		else if(scenes[i]["type"]=="森林遊樂區")	marker_pic="flag_darkgreen.png";	//dark green
		else if(scenes[i]["type"]=="其他")			marker_pic="flag_grey.png";			//grey
		else if(scenes[i]["type"]=="其他人文景點")	marker_pic="flag_pink.png";			//pink
		else 										marker_pic="flag_white.png";		//white
	marker_words = scenes[i]["name"];
	markers.push(
        new google.maps.Marker({
        position:scence_ptr,
        map: map, 
        draggable: false, 
        animation: google.maps.Animation.DROP,
        icon: marker_pic,
        title: marker_words
		})
    );
	}
}



		