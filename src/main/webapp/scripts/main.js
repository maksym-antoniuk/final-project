var showcaseButton = document.getElementById('showcaseButton');
var modal = document.getElementById('start-modal');
var loginModal = document.getElementById('login-modal')
var closeModal = document.getElementById('closeModal');
var signInButton = document.getElementById('sign_in');
var closeLoginModal = document.getElementById('close-login-modal');

signInButton.onclick = function (ev) {
    loginModal.style.display = 'block';
}

closeLoginModal.onclick = function (ev) {
    loginModal.style.display = "none";
}

showcaseButton.onclick = function (ev) {
    modal.style.display = 'block';
    document.getElementById('driver').style.display = 'none';
}

closeModal.onclick = function (ev) {
    openRole(ev, 'manager');
    modal.style.display = 'none';
}

function accFunction(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

function openRole(evt, roleName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("role");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = 'none';
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
    }
    document.getElementById(roleName).style.display = "block";
    evt.currentTarget.className += " w3-red";
}

//document.getElementById('driverBt').onclick = openRole(document.getElementById('driverBt').onclick, 'driver');
//document.getElementById('managerBt').onclick = openRole(document.getElementById('managerBt').onclick, 'manager');