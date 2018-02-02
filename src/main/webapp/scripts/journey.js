var mapModal = document.getElementById('map');
var mapIframe = document.getElementById('map_iframe');
var addNewRoute = document.getElementById('addNewRoute');
var closeModal = document.getElementById('closeModal');
var closeModalAdd = document.getElementById('closeModalAdd');

function openMap(from, where) {
    mapModal.style.display = 'block';
    mapIframe.src = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyDEjWn36Ge7gRPc8d28x6bB_3lZWFGDM_I&origin=".concat(from).concat("&destination=").concat(where).concat("&avoid=tolls|highways");
}

function openModalAdd(ev) {
    addNewRoute.style.display = 'block';
};



closeModal.onclick = function (ev) {
    mapModal.style.display = "none";
};

closeModalAdd.onclick = function (ev) {
    addNewRoute.style.display = "none";
};

$(".buttonAddCar").click(function () {
    var carid = $(this).attr('carid');
    var journeyid = $(this).parent().parent().attr('journeyid');
    post('/Base/car-journey', {addCarToJourney: ';;', journeyid: journeyid, carid: carid});
});

$('#editJourney').click(function () {
    var rows = document.getElementsByClassName('editRow');
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].style.display === 'block') {
            rows[i].style.display = 'none';
        } else {
            rows[i].style.display = 'block';
        }

    }
});

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}

function acceptCar(idJour) {
    document.getElementById('dynamicTable').innerHTML = '';
    $.get('/Base/car-journey', {journey: idJour},
        function (data) {
            var h31 = document.createElement('h3');
            h31.setAttribute('class', 'w3-text-red');
            h31.innerHTML = 'Unfortunately, no cars have subscribed yet';
            document.getElementById('dynamicTable').appendChild(h31);

            for (var i = 0; i < data.cars.length; i++) {
                if(i === 0){
                    document.getElementById('dynamicTable').innerHTML = '';
                }
                var h3 = document.createElement('h3');
                h3.innerHTML = data.cars[i].mark.concat(' ').concat(data.cars[i].model).concat(' ');
                var icon = document.createElement('i');
                icon.setAttribute('class', 'fa fa-plus w3-large w3-text-green');
                icon.setAttribute('idcar', data.cars[i].id);
                icon.setAttribute('idjourney', idJour);
                icon.onclick = function () {
                    post('/Base/car-journey', {
                        acceptCarToJourney: 'kek',
                        carid: icon.getAttribute('idcar'),
                        journeyid: icon.getAttribute('idjourney')
                    })
                };
                document.getElementById('dynamicTable').appendChild(h3);
                h3.appendChild(icon);
                var table = document.createElement('table');
                table.setAttribute('class', 'w3-table');
                document.getElementById('dynamicTable').appendChild(table);

                var row = document.createElement('tr');
                table.appendChild(row);
                var td = document.createElement('td');
                td.innerHTML = 'Number';
                row.appendChild(td);
                td = document.createElement('td');
                td.innerHTML = data.cars[i].number;
                row.appendChild(td);

                row = document.createElement('tr');
                table.appendChild(row);
                td = document.createElement('td');
                td.innerHTML = 'Capacity';
                row.appendChild(td);
                td = document.createElement('td');
                td.innerHTML = data.cars[i].maxWeight;
                row.appendChild(td);

                row = document.createElement('tr');
                table.appendChild(row);
                td = document.createElement('td');
                td.innerHTML = 'Volume';
                row.appendChild(td);
                td = document.createElement('td');
                td.innerHTML = data.cars[i].maxVolume;
                row.appendChild(td);

                row = document.createElement('tr');
                table.appendChild(row);
                td = document.createElement('td');
                td.innerHTML = 'Bodywork';
                row.appendChild(td);
                td = document.createElement('td');
                td.innerHTML = data.cars[i].bodywork;
                row.appendChild(td);

                row = document.createElement('tr');
                table.appendChild(row);
                td = document.createElement('td');
                td.innerHTML = 'Status';
                row.appendChild(td);
                td = document.createElement('td');
                td.innerHTML = data.cars[i].status;
                row.appendChild(td);
            }
        }
    );
    document.getElementById('journeyCars').style.display = 'block';
}

function cancelJourney(id) {
    post('/Base/journey', {cancel:id});
};

function confirmJourney(id) {
    post('/Base/journey', {confirm:id});
}

function showEditPanel() {
    var x = document.getElementById("editPanel");
    if (x.className.indexOf("w3-show") === -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

function closeEditPanel() {
    var x = document.getElementById("editPanel");
    if (x.className.indexOf("w3-show") === -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}