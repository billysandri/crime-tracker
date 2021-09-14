let map;
let mapMarkers = [];

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 41.85, lng: -87.65 },
    zoom: 10,
    mapId: '6a20fe312aa3d2af',
    streetViewControl: false,
    fullscreenControl: false,
    mapTypeControl: false,
  });

  const card = document.getElementById("ac-card");
  const input = document.getElementById("autocomplete");
  const options =     {
    types: ["address"],
    fields: ["formatted_address", "geometry", "name"],
  };

  map.controls[google.maps.ControlPosition.TOP_LEFT].push(card);
  autocomplete = new google.maps.places.Autocomplete(input, options);

  autocomplete.addListener('place_changed', onPlaceChanged);
}

function onPlaceChanged() {
  const place = autocomplete.getPlace();
  if (place.geometry.viewport) {
    map.fitBounds(place.geometry.viewport);
  } else {
    map.setCenter(place.geometry.location);
    map.setZoom(17);
  }

  
}

function addMarkers(markers) {
  for (let i = 0; i < markers.length; i++) {
    mapMarkers[i] =
    new google.maps.Marker({
      position: markers[i],
      map: map,
    });
  }
}

function removeMarkers() {
  for (let i = 0; i < mapMarkers.length; i++) {
    mapMarkers[i].setMap(null);
  }
}