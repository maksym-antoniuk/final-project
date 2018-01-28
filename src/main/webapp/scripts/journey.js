

var mapModal = document.getElementById('map');
var mapIframe = document.getElementById('map_iframe');
var closeModal = document.getElementById('closeModal');

function openMap(from, where) {
    mapModal.style.display = 'block';
    mapIframe.src = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyDEjWn36Ge7gRPc8d28x6bB_3lZWFGDM_I&origin=".concat(from).concat("&destination=").concat(where).concat("&avoid=tolls|highways");
}

closeModal.onclick = function (ev) {
    mapModal.style.display = "none";
}

$(document).ready(function() {
    $('#userName').blur(function(event) {
        var name = $('#userName').val();
        $.get('/Base/portfolio', {
            userName : name
        }, function(responseText) {
            $('#ajaxGetUserServletResponse').text(responseText);
        });
    });
});