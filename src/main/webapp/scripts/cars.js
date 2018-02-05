function removeCar(id) {
    post('/Base/car', {delete:id});
}

function editCar(id, number, type, capacity, volume) {
    post('/Base/car', {edit:id, car_number:number, type_bodywork:type, capacity:capacity, volume:volume});
}