var zoom = 7;
var markers = [];
var user_marker_flag = 0;
var count = 1;
var initial_location = new google.maps.LatLng(23.6974161, 120.9244374);
var marker_words;
var marker_pic, scence_ptr, tMode, form;
var arr = new Array(10);
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var area;
// var iconLevel = new google.maps.Size(zoom*2,zoom*3);
var gDrag = {
	jq : {},
	item : {},
	status : 0,
	y : 0,
	x : 0,
	info : ""
}
var infowindow;
var info_control = 0;
$(function() {
	directionsDisplay = new google.maps.DirectionsRenderer();
	directionsDisplay.setPanel(document.getElementById('infoBar'));
	infowindow = new google.maps.InfoWindow();
	var mapOptions = {
		center : initial_location,
		zoom : zoom,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	directionsDisplay.setMap(map);
	// here is the important
	if (scenes) {
		gDrag.jq = $('#gmarker'); // ???

		for (i = 0; i < 343; i++) {
			scence_ptr = new google.maps.LatLng(scenes[i]["lat"],
					scenes[i]["lng"]);

			if (scenes[i]["type"] == "美食")
				marker_pic = "flag_orange.png"; // orange
			else if (scenes[i]["type"] == "溫泉")
				marker_pic = "flag_darkblue.png"; // dark blue
			else if (scenes[i]["type"] == "風景區")
				marker_pic = "flag_blue.png"; // blue
			else if (scenes[i]["type"] == "博物館")
				marker_pic = "flag_purple.png"; // purple
			else if (scenes[i]["type"] == "休閒農場")
				marker_pic = "flag_green.png"; // green
			else if (scenes[i]["type"] == "主題遊樂園")
				marker_pic = "flag_yellow.png"; // yellow
			else if (scenes[i]["type"] == "森林遊樂區")
				marker_pic = "flag_darkgreen.png"; // dark green
			else if (scenes[i]["type"] == "其他")
				marker_pic = "flag_grey.png"; // grey
			else if (scenes[i]["type"] == "其他人文景點")
				marker_pic = "flag_pink.png"; // pink
			else
				marker_pic = "flag_white.png"; // white
			marker_words = scenes[i]["name"] + "@" + i.toString();

			markers.push(new google.maps.Marker({
				position : scence_ptr,
				map : null,
				draggable : false,
				raiseOnDrag : false,
				animation : google.maps.Animation.DROP,
				icon : marker_pic,
				title : marker_words,
			}));

			// Block mouse with our invisible gmarker
			google.maps.event.addListener(markers[i], 'mouseover', function(e) {

				if (!gDrag.jq.hasClass('ui-draggable-dragging')) {

					gDrag.item = this;
					gDrag.jq.offset({
						top : gDrag.y - 10,
						left : gDrag.x - 10
					});

				}
				title = this.getTitle();
				scenery_name = title.substr(0, title.indexOf('@'));
				scenery_num = title
						.substr(title.indexOf('@') + 1, title.length);
				info_content = scenery_name + "<div>景点位置："
						+ scenes[scenery_num]["location"] + "</div><div>景点类型："
						+ scenes[scenery_num]["type"] + "</div>"+"<div>"+"<a onclick=\"detail('"+scenery_num +"')\">查看详情</a>"+"</div>";
				infowindow.setContent(info_content);
				infowindow.open(map, this);

			});
		/*	google.maps.event.addListener(markers[i], 'dblclick', function(e) {
				title = this.getTitle();
				scenery_name = title.substr(0, title.indexOf('@'));
				scenery_num = title
						.substr(title.indexOf('@') + 1, title.length);
				// $("#rightBar").text("use java servlet ajax here to get the
				// info from server");

				$(".rightBar").load(
						"http://localhost:8080/WDTourism//servlet/AttractionDetailsServlet", {
							web1 : scenes[scenery_num]["web1"],
							web2 : scenes[scenery_num]["web2"],
							name : scenery_name
						}

				);

			});*/
		}

		gDrag.jq.draggable({
			start : function(event, ui) {
				// console.log(gDrag.item.getIcon())
				gDrag.jq.html('<img src="' + gDrag.item.getIcon()
						+ '" height="60"/>');
				gDrag.item.setVisible(true);
				gDrag.info = gDrag.item.getTitle();
			},

			stop : function(event, ui) {

				gDrag.jq.html('');
				gDrag.item.setVisible(true);

				// alert(gDrag.info);
			}
		});

		$(document).mousemove(function(event) {
			gDrag.x = event.pageX;
			gDrag.y = event.pageY;
		});

		$("#dropzone1")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num1 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="1" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest1").innerHTML = num1[0];
								arr[0] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest1").innerHTML = "";
								gDrag.status = 0;
								arr[0] = undefined;
							}
						});
		$("#dropzone2")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num2 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="2" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest2").innerHTML = num2[0];
								arr[1] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest2").innerHTML = "";
								gDrag.status = 0;
								arr[1] = undefined;
							}
						});
		$("#dropzone3")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num3 = gDrag.info.split("@");

								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="3" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');
								document.getElementById("dest3").innerHTML = num3[0];
								arr[2] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest3").innerHTML = "";
								gDrag.status = 0;
								arr[2] = undefined;
							}
						});
		$("#dropzone4")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num4 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="4" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');
								document.getElementById("dest4").innerHTML = num4[0];
								arr[3] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest4").innerHTML = "";
								gDrag.status = 0;
								arr[3] = undefined;
							}
						});
		$("#dropzone5")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num5 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img  class="marker" id="5" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest5").innerHTML = num5[0];
								arr[4] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest5").innerHTML = "";
								gDrag.status = 0;
								arr[4] = undefined;
							}
						});
		$("#dropzone6")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num6 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="6" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest6").innerHTML = num6[0];
								arr[5] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest6").innerHTML = "";
								gDrag.status = 0;
								arr[5] = undefined;
							}
						});
		$("#dropzone7")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num7 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="7" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest7").innerHTML = num2[0];
								arr[6] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest7").innerHTML = "";
								gDrag.status = 0;
								arr[6] = undefined;
							}
						});
		$("#dropzone8")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num8 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="8" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest8").innerHTML = num8[0];
								arr[7] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest8").innerHTML = "";
								gDrag.status = 0;
								arr[7] = undefined;
							}
						});
		$("#dropzone9")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num9 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="9" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest9").innerHTML = num9[0];
								arr[8] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest9").innerHTML = "";
								gDrag.status = 0;
								arr[8] = undefined;
							}
						});
		$("#dropzone10")
				.droppable(
						{
							accept : "#gmarker",
							activeClass : "drophere",
							hoverClass : "dropaccept",
							drop : function(event, ui, item) {
								gDrag.status = 1;
								num10 = gDrag.info.split("@");
								$(this)
										.addClass("ui-state-highlight")
										.html(
												'<img class="marker" id="10" draggable="true"  ondragend="drag_end(event)" src="'
														+ gDrag.item.getIcon()
														+ '"/>');

								document.getElementById("dest10").innerHTML = num10[0];
								arr[9] = gDrag.info.substr(gDrag.info
										.indexOf('@') + 1, gDrag.info.length);
							},
							out : function(event, ui, item) {
								$(this).addClass("ui-state-highlight").html("");
								document.getElementById("dest10").innerHTML = "";
								gDrag.status = 0;
								arr[9] = undefined;
							}
						});
		

	}

	$("#sortable").sortable();
	$("#sortable").disableSelection();
	/*
	 * var route = [ new google.maps.LatLng(23.6974161-0.2, 120.9244374-0.2),
	 * new google.maps.LatLng(23.6974161-0.2, 120.9244374+0.2), new
	 * google.maps.LatLng(23.6974161+0.2, 120.9244374+0.2), new
	 * google.maps.LatLng(23.6974161+0.2, 120.9244374-0.2) ];
	 */

	var route = {
		fillColor : '#000',
		fillOpacity : 0.35,
		strokeColor : "#fff",
		strokeOpacity : 1.0,
		strokeWeight : 2,
		map : null,
		center : initial_location,
		radius : 10,
	};
	area = new google.maps.Circle(route);

	google.maps.event.addListener(map, 'click', function(event) {
		var lat = event.latLng.lat();
		var lng = event.latLng.lng();
		var area_r = Math.sqrt(20 - map.getZoom()) * 4000;
		google.maps.event.addListener(map, 'zoom_changed', function() {
			area_r = Math.sqrt(20 - map.getZoom()) * 4000;
		});
		area.setMap(null);
		var route = {
			fillColor : '#000',
			fillOpacity : 0.35,
			strokeColor : "#fff",
			strokeOpacity : 1.0,
			strokeWeight : 2,
			map : map,
			center : new google.maps.LatLng(lat, lng),
			radius : area_r,
		};
		area = new google.maps.Circle(route);

		if (markers) {
		
			for (i in markers) {
				if (area.getBounds().contains(
						new google.maps.LatLng(scenes[i]["lat"],
								scenes[i]["lng"]))){
				//	
					markers[i].setMap(map);
				}
				else {
					markers[i].setMap(null);
				}
			}
		}
	});

	// when the map zoom changes, resize the icon based on the zoom level so the
	// marker covers the same geographic area
	/*
	 * google.maps.event.addListener(map, 'zoom_changed', function() {
	 * 
	 * var pixelSizeAtZoom0 = 8; //the size of the icon at zoom level 0 var
	 * maxPixelSize = 350; //restricts the maximum size of the icon, otherwise
	 * the browser will choke at higher zoom levels trying to scale an image to
	 * millions of pixels
	 * 
	 * zoom = map.getZoom(); var relativePixelSize =
	 * Math.round(pixelSizeAtZoom0*Math.pow(2,zoom)); // use 2 to the power of
	 * current zoom to calculate relative pixel size. Base of exponent is 2
	 * because relative size should double every time you zoom in
	 * 
	 * if(relativePixelSize > maxPixelSize) //restrict the maximum size of the
	 * icon relativePixelSize = maxPixelSize; for (i in markers) { //change the
	 * size of the icon markers[i].setIcon( new google.maps.MarkerImage(
	 * markers[i].getIcon().url, //marker's same icon graphic null,//size
	 * null,//origin null, //anchor //new google.maps.Size(zoom*2,zoom*3)
	 * //changes the scale new google.maps.Size(relativePixelSize,
	 * relativePixelSize) ) ); } });
	 */
});

$(document).ready(function() {
	$(".rightBar").bind("mouseenter", function() {
		$(".rightBar").css("overflow-y", "scroll");
	});
	$(".rightBar").bind("mouseleave", function() {
		$(".rightBar").scrollTop(0).css("overflow-y", "hidden");
	});
	
});
function search(){
	 $.post("http://localhost:8080/WDTourism//servlet/SearchServlet",
			  {
			    searchWord:$("#address-input").val(),
			   
			  },
			  function(data,status){
				  // data is the array of spot name
				  for (i in markers) {
					   markers[i].setMap(null);
				   }
				  var spots = JSON.parse(data);
				  console.log(spots);
				  $.each(spots,function(index,obj){
					   if (markers) {
						 
							for (i in markers) {
								if (scenes[i]["name"]==obj){
									markers[i].setAnimation(google.maps.Animation.DROP);
									markers[i].setMap(map);
								}
								else {
									
								}
							}
						}
				  });
			  }
	 );
}
function drag_end(e) {
	var zone = document.getElementById(e.target.id);
	zone.remove();
	$("#dest" + e.target.id).text("");
	arr[e.target.id - 1] = undefined;

}

// a practice we user for our PJ
function Try() {
	var del_button = $(".route");
	for (var i = 0; i < del_button.length; i++) {
		$(del_button[i]).remove();
	}

	var new_arr = [];
	for (var i = 0; i < 10; i++) {
		if (arr[i] != undefined) {
			new_arr.push(arr[i]);

		}

	}
	if (new_arr.length == 1 || new_arr.length == 0) {
		return;
	}
	for (var i = 0; i < new_arr.length - 1; i++) {
		var button = document.createElement("input");
		button.setAttribute('type', 'button');
		button.setAttribute('value', "交通資訊");
		button.setAttribute('class', 'route');
		func = "show(" + new_arr[i] + "," + new_arr[i + 1] + ")";

		button.setAttribute('onclick', func);
		document.getElementById("z" + i + "_" + (i + 1)).appendChild(button);

	}

}
function show(index1, index2) {
	if (!info_control) {
		var neighborhoods = [
				new google.maps.LatLng(scenes[index1]["lat"],
						scenes[index1]["lng"]), // A
				new google.maps.LatLng(scenes[index2]["lat"],
						scenes[index2]["lng"]) // B
		];
		form = document.getElementById("choose_mode");
		if (document.getElementById('driving').checked)
			tMode = google.maps.TravelMode.DRIVING;
		else if (document.getElementById('walking').checked)
			tMode = google.maps.TravelMode.WALKING;
		else if (document.getElementById('transit').checked)
			tMode = google.maps.TravelMode.TRANSIT;
		var request = {
			origin : neighborhoods[0],
			destination : neighborhoods[1],
			travelMode : tMode
		};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
				var route = response.routes[0];
				for (var i = 0; i < route.legs.length; i++) {
					// we can't obtain the time for the panel but we can get the
					// time from this one
					// temp_time = route.legs[i].duration.text;
					// temp_distance = route.legs[i].distance.text;
				}

			}
		});

		$("#infoBar").animate({
			left : '0px'
		});
		info_control = 1;

	} else {
		$("#infoBar").animate({
			left : '-300px'
		});
		info_control = 0;
	}
}

function changeMode(x, check) {
	if ((x == "driving") && (check == true)) {
		document.getElementById('mp').src = "driving.png";
	} else if ((x == "walking") && (check == true)) {
		document.getElementById('mp').src = "walking.png";
	} else if ((x == "transit") && (check == true)) {
		document.getElementById('mp').src = "transit.png";
	}
}
function detail(num){
	 console.log(num);
	 $.post("http://localhost:8080/WDTourism//servlet/AttractionDetailsServlet",
			  {    
			   
			  },
			  function(data,status){
				
			  }
	 );

}
function Finish() {

	var new_arr = [];
	for (var i = 0; i < 10; i++) {
		if (arr[i] != undefined) {
			new_arr.push(arr[i]);

		}

	}
	if (new_arr.length == 0) {
		return;
	}
	var content = "";
	var dis_time_content = "";
	var temp_time = "";
	var temp_distance = "";
	for (var i = 0; i < new_arr.length; i++) {
		alert(scenes[new_arr[i]]["location"] + "," + scenes[new_arr[i]]["type"]
				+ "," + scenes[new_arr[i]]["name"] + ","
				+ scenes[new_arr[i]]["lng"] + "," + scenes[new_arr[i]]["lat"]
				+ "," + scenes[new_arr[i]]["rec"] + ","
				+ scenes[new_arr[i]]["web1"] + "," + scenes[new_arr[i]]["web2"]
				+ "," + "" + scenes[new_arr[i]]["time"] + ","
				+ scenes[new_arr[i]]["pic"]);
		var tmp = scenes[new_arr[i]]["location"] + ","
				+ scenes[new_arr[i]]["type"] + "," + scenes[new_arr[i]]["name"]
				+ "," + scenes[new_arr[i]]["lng"] + ","
				+ scenes[new_arr[i]]["lat"] + "," + scenes[new_arr[i]]["rec"]
				+ "," + scenes[new_arr[i]]["web1"] + ","
				+ scenes[new_arr[i]]["web2"] + "," + scenes[new_arr[i]]["time"]
				+ "," + scenes[new_arr[i]]["pic"];
		content += tmp + "&";

	}

	for (var i = 0; i < new_arr.length - 1; i++) {
		var neighborhoods = [
				new google.maps.LatLng(scenes[new_arr[i]]["lat"],
						scenes[new_arr[i]]["lng"]), // A
				new google.maps.LatLng(scenes[new_arr[i + 1]]["lat"],
						scenes[new_arr[i + 1]]["lng"]) // B
		];
		form = document.getElementById("choose_mode");
		if (document.getElementById('driving').checked)
			tMode = google.maps.TravelMode.DRIVING;
		else if (document.getElementById('walking').checked)
			tMode = google.maps.TravelMode.WALKING;
		else if (document.getElementById('transit').checked)
			tMode = google.maps.TravelMode.TRANSIT;
		var request = {
			origin : neighborhoods[0],
			destination : neighborhoods[1],
			travelMode : tMode
		};
		// alert(neighborhoods[0]+"&"+neighborhoods[1]);

		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				// directionsDisplay.setDirections(response);
				var route = response.routes[0];

				for (var j = 0; j < route.legs.length; j++) {
					// we can't obtain the time for the panel but we can get the
					// time from this one
					temp_time = route.legs[j].duration.text;
					temp_distance = route.legs[j].distance.text;

					dis_time_content += temp_distance + "," + temp_time + "&";
					// alert(dis_time_content);
					$("#Time").val(dis_time_content);
				}

			} else {
				alert("WHY");
			}

		});

	}
	// alert(dis_time_content);

	$("#Go").val(content);
	// $("#hide").submit();

}

// rightbar
$(function() {
	// 預設顯示第一個 Tab
	var _showTab = 0;
	var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');
	$($defaultLi.find('a').attr('href')).siblings().hide();

	// 當 li 頁籤被點擊時...
	// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
	$('ul.tabs li').click(function() {
		// 找出 li 中的超連結 href(#id)
		var $this = $(this), _clickTab = $this.find('a').attr('href');
		// 把目前點擊到的 li 頁籤加上 .active
		// 並把兄弟元素中有 .active 的都移除 class
		$this.addClass('active').siblings('.active').removeClass('active');
		// 淡入相對應的內容並隱藏兄弟元素
		$(_clickTab).stop(false, true).fadeIn().siblings().hide();

		return false;
	}).find('a').focus(function() {
		this.blur();
	});
});
