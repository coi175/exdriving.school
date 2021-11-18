/**
 * Посылает Post запрос на сервер, чтобы создать новое место занятий
 */
function createPlace() {
    // т.к. тут пользователь сам вводит данные, проверяем их на правильность и если правильные, посылаем запрос
    if(validatePlaceInfo()) {
        // получаем значение input по ID
        let city = $("#CityPlace").val();
        let street = $("#StreetPlace").val();
        let house = $("#HousePlace").val();

        // запрос POST на сервер
        $.ajax({
            method: "POST", // запрос POST
            url: "/addNewPlace", // URL на который пойдет запрос (в контроллере @PostMapping("/addNewPlace"/)
            headers: { // указываем что посылаем JSON, иначе контроллер на разберется
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            // сами данные которые посылаем
            data:  JSON.stringify({"city" : city,
                "street" : street,
                "house" : house}),
            dataType: 'json',

            // после того как от сервер придет ответ его нужно обработать.
            // Если пришел ответ, что запрос прошел успешно, вызвается success функция
            success: function(data) {
                // показываем блок div с сообщением об успехе
                $('#DataAddSuccess').show(100);
                // через 3 секунды скрываем его
                setTimeout(hideMessage, 3000);
            },
            // если запрос не удался
            error: function(er) {
                // показываем ошибку
                $('#DataAddError').show(100);
                // закрываем через 3 секунды
                setTimeout(hideMessage, 3000);
            }
        });
    }

    // если введенные данные были не правильные, запрос не посылается, просто показывается сообщение об ошибке
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
