// map 基本參數設置

var zoomSize = 13;
var place = {lat: 25.042131, lng: 121.525672};

// 回傳用參數

var locationArray ;
var overviewPolylineArray ;
var overviewPolyline ;
var locationFormatArray ;
//

var decodeString ;
var mapGlobal ;
//map 初始

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: zoomSize,
    center: place
  });
}

// 路程規劃
function planningMap(){
  var directionsDisplay = new google.maps.DirectionsRenderer;
  var directionsService = new google.maps.DirectionsService;
  var geocoder = new google.maps.Geocoder();

  var map = new google.maps.Map(document.getElementById('map'), {
	zoom: zoomSize,
	center: place
  });
  directionsDisplay.setMap(map);



  $("#send").click(function(){
	 // setTimeout(calculateAndDisplayRoute(directionsService, directionsDisplay,map),2000)
	  pushWayPoints(directionsService)
  })
}

function pushWayPoints(directionsService) {
	locationArray = []
	overviewPolylineArray = []
    var waypts = [];
    var wayPointsArray = $("input[name='wayPoints']");
    locationArray.push($("#start").val())
    locationArray.push($("#destination").val())
    for (var i = 0; i < wayPointsArray.length; i++) {
    	locationArray.push(wayPointsArray[i].value)
        waypts.push({
          location: wayPointsArray[i].value,
          stopover: true
        });
    }
    directionsService.route({
        origin:  $("#start").val(),  // Haight.
        destination:  $("#destination").val(),  // Ocean Beach.
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: "DRIVING",
        provideRouteAlternatives: false,
        avoidHighways :true,
        avoidTolls :true
      }, function(response, status) {
        if (status == 'OK') {
      	  var map = new google.maps.Map(document.getElementById('map'), {
      		    zoom: zoomSize,
      		    center: place
      		  });
      	  
            for (var i = 0, len = response.routes.length; i < len; i++) {
            	overviewPolylineArray.push(response.routes[i].overview_polyline)
            	overviewPolyline = response.routes[0].overview_polyline;
            	decodeOverviewPolyline(overviewPolyline)
               var directionsDisplay = new google.maps.DirectionsRenderer({
                    map: map,
                    directions: response,
                    draggable: true,
                    //panel: document.getElementById('groupJourneyContent'),
                    routeIndex: i
                });
                directionsDisplay.addListener('directions_changed', function() {
                    resetLocation(directionsDisplay.getDirections());
                  });
            }
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });    
}


function calculateAndDisplayRoute(directionsService, directionsDisplay,map) {
    directionsService.route({
      origin:  $("#start").val(),  // Haight.
      destination:  $("#destination").val(),  // Ocean Beach.
      travelMode: "DRIVING",
      provideRouteAlternatives: true,
      optimizeWaypoints: true,
      avoidHighways :true,
      avoidTolls :true
    }, function(response, status) {
      if (status == 'OK') {
    	  var map = new google.maps.Map(document.getElementById('map'), {
    		    zoom: zoomSize,
    		    center: place
    		  });
          for (var i = 0, len = response.routes.length; i < len; i++) {
              new google.maps.DirectionsRenderer({
                  map: map,
                  directions: response,
                  routeIndex: i
              });
          }
//    	  console.log(response)
//        directionsDisplay.setDirections(response);
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }

// 位置定址

function geocodeAddress(address) {
  var geocoder = new google.maps.Geocoder();
  geocoder.geocode({'location': address}, function(results, status) {
    if (status === 'OK') {
      locationFormatArray.push(results[0].formatted_address)
    } else {
      console.log('Geocode was not successful for the following reason: ' + status);
      locationFormatArray.push(address)
    }
  });
}

// 中途點新增輸入inputtext

function addWayPointField(i){
  var wpHTML = "<div  name =\"wayPoint\" class=\"row\">"
	wpHTML += "<div class=\"input-field col s10\">"
	wpHTML += "<input class=\"validate\"  name=\"wayPoints\" type=\"text\"> <label for=\"start\">中途點(請輸入地點)</label>"
	wpHTML += "</div>"
	wpHTML += "<div class=\"col s1 right-align\">"
	wpHTML += "<a id= \"add\" class=\"btn-floating btn-large waves-effect waves-light\" onclick = \"addWayPointField()\"><i class=\"material-icons\">add</i></a>"
	wpHTML += "</div>"
	wpHTML += "<div class=\"col s1 right-align\">"
	wpHTML += "<a id= \"remove\" class=\"btn-floating btn-large waves-effect waves-light\"  onclick = \"removeWayPointField()\"><i class=\"material-icons\" >remove</i></a>"
	wpHTML += "</div>"
	wpHTML += "</div>"
  $( "body" ).find( "div[name='wayPoint']" ).eq(i).after(wpHTML).ready(function(){ adjustWayPointNo()})
}
function removeWayPointField(i){
	$( "body" ).find( "div[name='wayPoint']" ).eq(i).remove()
	adjustWayPointNo();
}
function adjustWayPointNo(){
	for(var i = 1 ; i < ($( "body" ).find( "div[name='wayPoint']").length -1) ; i++){
		$( "body" ).find( "div[name='wayPoint'] #add" ).eq(i).attr("onclick","addWayPointField("+i+")")
		$( "body" ).find( "div[name='wayPoint'] #remove" ).eq(i).attr("onclick","removeWayPointField("+i+")")
	}
}

// marker 重設

function resetLocation(directions){
	console.log(directions)
	for(var i = 0 ; i < directions.routes[0].legs.length ; i ++){
		if(i==0){
			$("#start").val(directions.routes[0].legs[i].start_address)
			$("body").find("input[name='wayPoints']").eq(0).val(directions.routes[0].legs[i].end_address)
			
		}else if (i==( directions.routes[0].legs.length-1)){
			$("body").find("input[name='wayPoints']").eq(i-1).val(directions.routes[0].legs[i].start_address)
			$("#destination").val(directions.routes[0].legs[i].end_address)
			
		}else{
			$("body").find("input[name='wayPoints']").eq(i).val(directions.routes[0].legs[i].start_address)
			
		}
	}
	overviewPolyline = directions.routes[0].overview_polyline
}

// 等高線圖顯示

function plotElevation(elevations, status) {
	
	$("#canvas").empty()
	var  elevationArr = [];
    var chartDiv = document.getElementById('view');
    if (status !== 'OK') {
      // Show the error code inside the chartDiv.
      chartDiv.innerHTML = 'Cannot show elevation: request failed because ' +
          status;
      return;
    }
    // Create a new chart in the elevation_chart DIV.
    for (var i = 0; i < elevations.length; i++) {
        //console.log(elevations[i].elevation)
    	elevationArr.push(elevations[i].elevation)
    }
    var ctx = document.getElementById("canvas").getContext("2d");
    
    var config = {
            type: 'line',
            data: {
                labels: locationFormatArray,
                datasets: [{
                    label: $("#start").val()+" 到 "+$("#destination").val(),
                    backgroundColor: window.chartColors.blue,
                    borderColor: window.chartColors.blue,
                    data:  elevationArr,
                    fill: false,
                }]
            },
            options: {
                responsive: true,
                title:{
                    display:true,
                    text:'路線海拔'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '位置'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '海拔'
                        }
                    }]
                }
            }
        };
    window.myLine = new Chart(ctx, config);
  }

// decode path
function decodeOverviewPolyline(val){
  decodeString = new google.maps.geometry.encoding.decodePath(val);
  locationFormatArray = [];
  var jGolbal = 0
  var len = decodeString.length-1
  for(var i = 0 ; len > 4 && i < (len/4) ; i ++){
	setTimeout(function() {
	  for(j = 0  ; j <  4 ; j++ ,jGolbal++){
		geocodeAddress(decodeString[jGolbal])
	  }
	}, 4000*i)
  }
  var elevator = new google.maps.ElevationService;
  elevator.getElevationAlongPath({
    'path': decodeString,
    'samples': 500
  }, plotElevation);
}



// read journey  func 


function drawPolyline(){
	decodeString = google.maps.geometry.encoding.decodePath(overviewPolyline);
	mapGlobal = new google.maps.Map(document.getElementById('map'), {
	  zoom: zoomSize,
	  center: place
	});
    var path = new google.maps.Polyline({
        path: decodeString,
        geodesic: true,
        strokeColor: '#6699ff',
        strokeOpacity: 1,
        strokeWeight: 4
    });
    path.setMap(mapGlobal);
    var geocoder = new google.maps.Geocoder();
    var no = [];
    for(var i = locationArray.length ; i > 0 ; i --){
    	no.push(i)
    }
    for(var i = 0 ; i < locationArray.length ; i ++){
    	geocoder.geocode({'address': locationArray[i]}, function(results, status) {
    		if (status === 'OK') {
    			var marker = new google.maps.Marker({
    				map: mapGlobal,
    				label: String(no.pop()) ,
    				position: results[0].geometry.location
    			});
    		} else {
    			console.log('Geocode was not successful for the following reason: ' + status);
    		}
    	});
    }
    decodeOverviewPolylineForJourneyRead()
}

//decode path
function decodeOverviewPolylineForJourneyRead(){
  var elevator = new google.maps.ElevationService;
  elevator.getElevationAlongPath({
    'path': decodeString,
    'samples': 500
  }, plotElevation);
}

function plotElevation(elevations, status) {
	$("#canvas").empty()
	var  elevationArr = [];
    var chartDiv = document.getElementById('view');
    if (status !== 'OK') {
      // Show the error code inside the chartDiv.
      chartDiv.innerHTML = 'Cannot show elevation: request failed because ' +
          status;
      return;
    }
    // Create a new chart in the elevation_chart DIV.
    for (var i = 0; i < elevations.length; i++) {
        //console.log(elevations[i].elevation)
    	elevationArr.push(elevations[i].elevation)
    }
    var ctx = document.getElementById("canvas").getContext("2d");
    
    var config = {
            type: 'line',
            data: {
                labels: decodeString,
                datasets: [{
                    label: $("#start").val()+" 到 "+$("#destination").val(),
                    backgroundColor: window.chartColors.blue,
                    borderColor: window.chartColors.blue,
                    data:  elevationArr,
                    fill: false,
                }]
            },
            options: {
                responsive: true,
                title:{
                    display:true,
                    text:'路線海拔'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '位置'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: '海拔'
                        }
                    }]
                }
            }
        };
    window.myLine = new Chart(ctx, config);
  }


