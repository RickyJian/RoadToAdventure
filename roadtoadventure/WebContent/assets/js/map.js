var zoomSize = 13;
var place = {lat: 25.042131, lng: 121.525672};

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: zoomSize,
    center: place
  });
}

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
	  setTimeout(calculateAndDisplayRoute(directionsService, directionsDisplay,map),2000)
  })
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


