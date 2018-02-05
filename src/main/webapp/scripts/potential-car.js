function addCar(id){
    post('/Base/potential-car', {add:id});
}

function removePotentialCar(id) {
    post('/Base/potential-car', {remove:id});
}