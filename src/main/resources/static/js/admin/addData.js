/**
 * Посылает Post запрос на сервер, чтобы создать новое место занятий
 */
function createPlace() {
    if(validatePlaceInfo()) {
        let city = $("#CityPlace").val();
        let street = $("#StreetPlace").val();
        let house = $("#HousePlace").val();

        $.ajax({
            method: "POST",
            url: "/addNewPlace",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"city" : city,
                "street" : street,
                "house" : house}),
            dataType: 'json',

            success: function(data) {
                $('#DataAddSuccess').show(100);
                setTimeout(hideMessage, 3000);
            },
            error: function(er) {
                $('#DataAddError').show(100);
                setTimeout(hideMessage, 3000);
            }
        });
    }

    else {
        $('#DataAddError').show(100);
        setTimeout(hideMessage, 3000);
    }
}

/**
 * Проверяет правильность введенной информации в форму создания места занятий
 */
function validatePlaceInfo() {
    if($("#CityPlace").val() === "") return false;
    if($("#StreetPlace").val() === "") return false;
    if($("#HousePlace").val() === "") return false;

    return true;
}

/**
 * Посылает POST запрос на сервер, чтобы создать новую машину
 */
function createCar() {
    if(validateCarInfo()) {
        let brand = $("#CarBrand").val();
        let model = $("#CarModel").val();
        let number = $("#CarNumber").val();

        $.ajax({
            method: "POST",
            url: "/addNewCar",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"brand" : brand,
                "model" : model,
                "number" : number}),
            dataType: 'json',

            success: function(data) {
                $('#DataAddSuccess').show(100);
                setTimeout(hideMessage, 3000);
            },
            error: function(er) {
                $('#DataAddError').show(100);
                setTimeout(hideMessage, 3000);
            }
        });
    }

    else {
        $('#DataAddError').show(100);
        setTimeout(hideMessage, 3000);
    }
}

/**
 * Проверяет правильность введенной информации в форму для создания машины
 */
function validateCarInfo() {
    if($("#CarBrand").val() === "") return false;
    if($("#CarModel").val() === "") return false;
    if($("#CarNumber").val() === "" || $("#CarNumber").val().length > 9) return false;

    return true;
}

/**
 * Скрывает сообщение об ошибке и успехе
 */
function hideMessage() {
    $('#DataAddSuccess').hide(100);
    $('#DataAddError').hide(100);
}
