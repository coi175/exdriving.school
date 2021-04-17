function instructorClick(dv) {
    let id = $(dv).children(".instructor_list_line_item:last").text();
    getInstructorFromServer(id);
    getCars();
    openInstructorInfo();
}

function getInstructorFromServer(id) {
    $.ajax({
        method: "POST",
        url: "/openAndGetInstructorInfo",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data:  JSON.stringify({"id" : id}),
        dataType: 'json',

        success: function(data) {
            let map = new Map();
            $.each(data, function(key, value) {
                map.set(key, value);
            });

            // базовая информация
            let baseInfo = map.get("baseInfo");
            $('#InstructorID').text(baseInfo[0]);
            $('#InstructorName').text(baseInfo[1]);
            $('#InstructorCarName').text(baseInfo[2]);
            $('#InstructorStudentsCount').text(baseInfo[3]);
            $('#InstructorLessonsCount').text(baseInfo[4]);

            // Список клиентов
            let clients = map.get("clients")
            $('#InstructorClientsBlock').empty().append(" <div class=\"row mb-4\">\n" +
                "                            <h4>Список клиентов:</h4>\n" +
                "                        </div>\n" +
                "                        <div class=\"row text-center list_block_line\">\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Имя:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Email:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Оставшиеся часы:</span>\n" +
                "                            </div>\n" +
                "                        </div>");
            for(let i = 0; i < clients.length; i++) {
                let values = clients[i].split('/');
                $('#InstructorClientsBlock').append("<div class=\"row text-center list_block_line\">\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + values[0] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span >" + values[1] + "</span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col list_block_line_item\">\n" +
                    "                                <span>" + values[2] + "</span>\n" +
                    "                            </div>\n" +
                    "                        </div>");
            }

            // сертификаты
            let lessons = map.get("lessons")
            $("#InstructorLessonsBlock").empty().append("<div class=\"row mb-4\">\n" +
                "                            <h4>Список занятий: </h4>\n" +
                "                        </div>\n" +
                "                        <div class=\"row text-center list_block_line\">\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Дата:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Место:</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"col list_block_line_item\">\n" +
                "                                <span style=\"font-weight: bold\">Кол-во записавшихся:</span>\n" +
                "                            </div>\n" +
                "                        </div>");
            for(let i = 0; i < lessons.length; i++) {
                let cvalues = lessons[i].split('/');
                $("#InstructorLessonsBlock").append("<div class=\"row text-center list_block_line\">\n" +
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
            closeInstructorInfo();
            console.log(er);
        }
    });
}

function changeCarForInstructor(btn) {
        let instructorID = $('#InstructorID').text();
        let carID = $('#Cars option:selected').val();
        $.ajax({
            method: "POST",
            url: "/changeCarForInstructor",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"instructorID" : instructorID,
                "carID" : carID}),
            dataType: 'json',

            success: function(data) {
                getInstructorFromServer(instructorID);
                getCars();
            },
            error: function(er) {
                alert("Не удалось изменить данные");
                console.log(er);
            }
        });
}

function getCars() {
    $.ajax({
        type: 'GET',
        url: '/getCarsForInstructor',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#Cars").empty().append("<option value='' selected>Выберите машину (удалить)</option>");
            $.each(data, function(key, value) {
                $("#Cars").append("<option value='" + key+"'>" + value + "</option>");
            });
        }
    });
}


function openInstructorInfo() {
    $("#InstructorListBlock").hide(100);
    $("#InstructorInfoBlock").show(100);
}

function closeInstructorInfo() {
    $("#InstructorListBlock").show(100);
    $("#InstructorInfoBlock").hide(100);
    getInstructorList();
}