// выводим модальное окно при клике на занятие
function openModalWindow(btn) {
    var modal = $(btn).next();
    modal.show();
}

// при клике на крестик скрываем его
function closeModalSpan(span) {
    $(".modal").hide();
}

// получаем список занятий с сервера
function getLessonList() {
    $.ajax({
        type: 'GET',
        url: '/lessonList',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#lessonList").empty();
            if(data[0] === "empty") {
                $("#lessonList").append("<span style=\"font-style: italic\">*Занятий не найдено <br><span style=\"font-style: normal; font-size: 30px\">&#128203;</span></span>");
            }
            else {
                $.each(data, function (key, value) {
                    let values = value.split('/');

                    $("#lessonList").append("<button onclick=\"openModalWindow(this);\" class=\"lesson_list_item\">" + values[1] + "</button>" +
                        "<div class=\"modal\">\n" +
                        "                            <div class=\"modal-content\">\n" +
                        "                                <span onclick =\"closeModalSpan(this)\" class=\"close\">&times;</span>\n" +
                        "                                <div class = \"modal-content-item\">\n" +
                        "                                    Дата занятия: <br><span class=\"modal_content_item_data\">" + values[1] + "</span>\n" +
                        "                                </div>\n" +
                        "\n" +
                        "                                <div class = \"modal-content-item\">\n" +
                        "                                    Место занятия: <br><span class=\"modal_content_item_data\">" + values[2] + "</span>\n" +
                        "                                </div>\n" +
                        "\n" +
                        "                                <div class = \"modal-content-item\">\n" +
                        "                                    Количество студентов: <br><span class=\"modal_content_item_data\">" + values[4] + "/" + values[3] + "</span>\n" +
                        "                                </div>\n" +
                        "                                <div class = \"modal-content-item\" style=\"display: none\">" + values[0] + "</div>\n" +
                        "\n" +
                        "                                <button onclick =\"recordToLesson(this);\" class=\"record_to_lesson_button\">Записаться</button>\n" +
                        "                            </div>\n" +
                        "                        </div>");
                });
            }
        },
        error: function (er) {
            console.log(er);
        }
    });
}

// записываемся на занятие
function recordToLesson(btn) {
    let id = $(btn).prev().text();
    $.ajax({
        method: "POST",
        url: "/recordToLesson",
        data: id,
        dataType: 'json',

        success: function(data) {
            getBasicInfo();
            getCurrentLesson();
            getLessonList();
        },
        error: function(er) {

        }
    });
}
