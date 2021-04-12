function getLessons() {
    $.ajax({
        type: 'GET',
        url: '/instructorGetLessons',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#LessonsBlock").empty();
            $.each(data, function (key, value) {
               let blockOfValues = value.split('&');
               let basicValues = blockOfValues[0].split('/');
               let students = blockOfValues[1].split('/');
                $("#LessonsBlock").append("<div class=\"lesson_item\">\n" +
                    "                <div class=\"lesson_item_line\">\n" +
                    "                    Дата:<br> <span class=\"basic_info_data\">" +  basicValues[1] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"lesson_item_line\">\n" +
                    "                    Адрес:<br> <span class=\"basic_info_data\">" +  basicValues[2] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"lesson_item_line\">\n" +
                    "                    Количество студентов:<br> <span class=\"basic_info_data\">" +  basicValues[3] + "/" + basicValues[4] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"lesson_item_line\">\n" +
                    "                    Список студентов:\n" +
                    "                    <div class=\"lesson_item_line_students\">" + "</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"lesson_item_line\" style=\"display: none\">" + basicValues[0] + "</div>\n" +
                    "                <a  onclick=\"deleteLesson(this);\" href = \"#\" class =\"delete_lesson m-auto\">Удалить</a>\n" +
                    "            </div>");
                if(students.length > 1) {
                    for(let i = 0; i < students.length; i++) {
                        $(".lesson_item_line_students:last").append("<div class=\"lesson_item_line_students_item\">" + students[i]  + "</div>");
                    }
                }
                else {
                    $(".lesson_item_line_students:last").append("<div class=\"lesson_item_line_students_item\">" + "Записавшихся нет" + "</div>");
                }

            });
        },
        error: function (er) {
            getLessons();
            console.log(er);
        }
    });
}

getLessons();

$(document).ready(function() {
    $('#LessonTab').click(function(e) {
        getLessons();
    });
});

function deleteLesson(btn) {
    let id = $(btn).prev().text();
    $.ajax({
        method: "POST",
        url: "/deleteLesson",
        data: id,
        dataType: 'json',

        success: function(data) {
            setTimeout(getLessons, 500)
        },
        error: function(er) {
            console.log(er);
        }
    });
}
