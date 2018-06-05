function myMap(lat, lng) {
    // var mapOptions= {
    //     center:new google.maps.LatLng(21.0227788,105.8194541),
    //     zoom:14,
    // };
    // var myCenter = new google.maps.LatLng(21.0227788,105.8194541);
    console.log("1");
    var myCenter = new google.maps.LatLng(lat, lng);
    var mapOptions = {
        center: myCenter,
        zoom: 14,
        mapTypeControl: false,
        zoomControl: true,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.SMALL
        }
        // streetViewControl: false,
        // rotateControl: false
    };
    var map=new google.maps.Map(document.getElementById("googleMap"),mapOptions);
    var marker = new google.maps.Marker({
        position: myCenter,
        animation: google.maps.Animation.BOUNCE
    });
    marker.setMap(map);

    google.maps.event.addListener(marker,'click', function () {
        var infowindow = new google.maps.InfoWindow({
            content:"You're here!"
        });
        infowindow.open(map, marker);
    });

    return false;
}