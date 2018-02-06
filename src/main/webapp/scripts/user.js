function nameFilter() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInputName");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableUser");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function surnameFilter() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInputSurname");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableUser");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}