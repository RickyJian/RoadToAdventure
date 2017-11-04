// map 基本參數設置

var zoomSize = 13;
var place = {lat: 25.042131, lng: 121.525672};

// 回傳用參數

var locationArray ;
var overviewPolylineArray ;
var overviewPolyline ;

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
	  pushWayPoints(directionsService, directionsDisplay)
  })
}

function pushWayPoints(directionsService, directionsDisplay) {
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
        provideRouteAlternatives: true,
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
                new google.maps.DirectionsRenderer({
                    map: map,
                    directions: response,
                    draggable: true,
                    //panel: document.getElementById('groupJourneyContent'),
                    routeIndex: i
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

function geocodeAddress(resultsMap,address,callback ) {
	var geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': address}, function(results, status) {
      if (status === 'OK') {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
        startEndPlace.push(results[0].geometry.location.lat())
        startEndPlace.push(results[0].geometry.location.lng())
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
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
