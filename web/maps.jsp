<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 3/29/18
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maps</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <%--<script src="js/mapsGraph.js"></script>--%>
</head>
<body>

<h1>My First Google Map</h1>

<div>Country: <span id="country"></span> </div>
<div>State: <span id="state"></span></div>
<div>City: <span id="city"></span></div>
<div>Latitude: <span id="latitude"></span></div>
<div>Longitude: <span id="longitude"></span></div>
<div>IP: <span id="ip"></span></div>

<div id="googleMap" style="width:100%;height:400px;"></div>

<script>

    var lat;
    var lng;
    console.log("2");
    $.ajax({
        url: "https://geoip-db.com/jsonp",
        jsonpCallback: "callback",
        dataType: "jsonp",
        success: function( location ) {
            $('#country').html(location.country_name);
            $('#state').html(location.state);
            $('#city').html(location.city);
            lat = location.latitude;
            // console.log(lat);
            $('#latitude').html(lat);
            lng = location.longitude;
            // console.log(lng);
            $('#longitude').html(lng);
            $('#ip').html(location.IPv4);
            // console.log(lat);
            // console.log(lng);

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

        }
    });

</script>


<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCqq4Qc-3Ysc7yy9miNKpcUzEw_IwDj0dw&callback=myMap"></script>

</body>
</html>
