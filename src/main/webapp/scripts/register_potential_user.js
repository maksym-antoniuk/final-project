function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
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

function addManager(path, id) {
    var sal = document.getElementById(id.concat('_salary')).value;
    post(path, {addManager: id, salary: sal});
}

function addDriver(path, id) {
    post(path, {addDriver: id});
}

function cancelUser(path, id) {
    post(path, {cancel: id});
}

function hide(id) {
    var str = id.concat('_man');
    var elem = document.getElementById(str);
    if(elem.style.display === 'none'){
        elem.style.display = 'block';

    } else if(elem.style.display === 'block'){
        elem.style.display = 'none';
    }

}

function salaryManager(id) {
    var str = id.concat('_man');
    var elem = document.getElementById(str);
    if (elem.className.indexOf("w3-show") === -1) {
        elem.className += " w3-show";
    } else {
        elem.className = elem.className.replace(" w3-show", "");
    }
}
