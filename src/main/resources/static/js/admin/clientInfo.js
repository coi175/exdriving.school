// функция вызывается при клике на пользователя
function clientClick(dv) {
    // получаем ID этого пользователя
    let id = $(dv).children(".client_list_line_item:last").text();
    // получаем информацию о пользователе с сервера по ID
    getClientInfoFromServer(id);
    // получаем список инструкторов, который вставляем в список выбора
    getInstructors();
    // делаем блок с инфой видимым
    openClientInfo();
}

function getClientInfoFromServer(id) {
    // Post запрос на сервер
    $.ajax({
        method: "POST",
        url: "/openAndGetClientInfo",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data:  JSON.stringify({"id" : id}),
        dataType: 'json',

        //Если он удачный
        success: function(data) {
            // создаем новый MAP, в который пихаем полученную информацию
            let map = new Map();
            $.each(data, function(key, value) {
                map.set(key, value);
            });
            //Сейчас наш Map = это {"ключ" : ["значение", "значение", "значение"], "ключ2" : [...], ...}
            // то есть значения - это списки строк

            // базовая информация
            let baseInfo = map.get("baseInfo");
            $('#ClientID').text(baseInfo[0]); // в элемент с ID #ClientId устанавливаем текст
            $('#ClientName').text(baseInfo[1]); // и т.д.
            $('#ClientEmail').text(baseInfo[2]);
            $('#ClientInstructorName').text(baseInfo[3]);
            $('#ClientRemainingHours').text(baseInfo[4]);
            $('#ClientSpentHours').text(baseInfo[5]);
            $('#RemainingHours').val(baseInfo[4]);

            // получаем список оценок
            let marks = map.get("marks")
            let sum = 0;
            let i = 0
            // добавляем на страницу html
            $('#clientMarksBlock').empty().append(" <div class=\"row mb-4\">\n" +
                "                            <h4>Средняя оценка: <span id=\"averageMark\" class=\"client_basic_info_block_data\"></span></h4>\n" +
                "                        </div>\n" +
                "                        <div class=\"row text-center list_block_line\">\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Дата:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Время:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Оценка:</span>\n" +
                "                            </div>\n" +
                "                        </div>");
            // перебираем все оценки какие у нас есть
            for(i; i < marks.length; i++) {
                // плюсуем оценку в общую сумму, чтобы потом посчитать среднее
                sum += parseInt(marks[i].split('/')[1]);
                // достаем очередную оценку из списка
                let values = marks[i].split('/');
                // разделяем значения, то есть дата оценки, занятие, сама оценка
                let tm = values[0].split(" ");
                // добавляем новый блок (линию списка)
                $('#clientMarksBlock').append("<div class=\"row text-center list_block_line\">\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + tm[0] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span >" + tm[1] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + values[1] + "</span>\n" +
                    "                            </div>\n" +
                    "                        </div>");
            }
            // считаем среднее - сумма делить на кол-во оценок
            let average = sum / i;
            // если оценки были, то среднее тоже есть
            if(average) {
                $("#averageMark").text(average.toFixed(2)); // устанавливаем текст оценки
            }
            else {
                $("#averageMark").text("?"); // ставим знак вопроса вместо средней оценки
            }

            // сертификаты
            let certificates = map.get("certificates")
            $("#clientCertificatesBlock").empty().append("<div class=\"row mb-4\">\n" +
                "                            <h4>Сертификаты: </h4>\n" +
                "                        </div>\n" +
                "                        <div class=\"row text-center list_block_line\">\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Номер:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Оценка:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Дата:</span>\n" +
                "                            </div>\n" +
                "                        </div>");
            for(let i = 0; i < certificates.length; i++) {
                let cvalues = certificates[i].split('/');
                $("#clientCertificatesBlock").append("<div class=\"row text-center list_block_line\">\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + cvalues[0] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + cvalues[1] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span >" + cvalues[2] + "</span>\n" +
                    "                            </div>\n" +
                    "                        </div>");
            }
        },
        error: function(er) {
            alert("Произошла ошибка");
            console.log(er);
        }
    });
}

function openClientInfo() {
    $("#ClientListBlock").hide(100);
    $("#ClientInfoBlock").show(100);
}

function closeClientInfo() {
    $("#ClientListBlock").show(100);
    $("#ClientInfoBlock").hide(100);
    getClientList();
}

function getInstructors() {
    $.ajax({
        type: 'GET',
        url: '/getInstructorListForClient',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#Instructors").empty().append("<option value='false' selected disabled>Выберите инструктора</option>");
            $.each(data, function(key, value) {
                $("#Instructors").append("<option value='" + key+"'>" + value + "</option>");
            });
        }
    });
}

function changeClientData(btn) {
    if(validateData()) {
        let clientId = $('#ClientID').text();
        let instructorId = $('#Instructors option:selected').val();
        let remainingHours = $('#RemainingHours').val();
        $.ajax({
            method: "POST",
            url: "/changeClientData",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"clientID" : clientId,
                "instructorID" : instructorId,
                "remainingHours" : remainingHours}),
            dataType: 'json',

            success: function(data) {
                getClientInfoFromServer(clientId);
            },
            error: function(er) {
                alert("Не удалось изменить данные");
                console.log(er);
            }
        });
    }
    else {
        alert("Ошибка, неверные данные. Выберите инструктора и введите количество часов в виде положительного числа.")
    }
}

function giveCertForClient(btn) {
    if(validateCertData()) {
        let clientId = $('#ClientID').text();
        let certificateNumber = $("#CertNumber").val();
        let certificateMark = $("#CertMark").val();
        $.ajax({
            method: "POST",
            url: "/giveCertForClient",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"clientID" : clientId,
                "certificateNumber" : certificateNumber,
                "certificateMark" : certificateMark}),
            dataType: 'json',

            success: function(data) {
                getClientInfoFromServer(clientId);
            },
            error: function(er) {
                alert("Не удалось изменить данные");
                console.log(er);
            }
        });
    }
    else {
        alert("Ошибка, неверные данные. Введите положительные целые числа в поля ввода.")
    }
}

function validateData() {
    let selected = $("#Instructors option:selected")
    if(selected.val() === 'false') {
        return false;
    }
    let rHours = $("#RemainingHours").val();
    if (!Number.isInteger(+rHours) || rHours < 0) {
        return false
    }

    return true;
}

function validateCertData() {
    let certNumber = $("#CertNumber").val();
    if(!Number.isInteger(+certNumber) || certNumber < 0) {
        return false;
    }
    let certMark = $("#CertMark").val();
    if (!Number.isInteger(+certMark) || certMark < 0 || certMark > 5) {
        return false
    }

    return true;
}