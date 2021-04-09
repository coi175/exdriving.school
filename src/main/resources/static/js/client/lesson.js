// получаем блок текущего занятия (либо пишем из бд, либо пишем, что на занятие не записан)
function getCurrentLesson() {
    $.ajax({
        type: 'GET',
        url: '/currentLesson',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $('#CurrentLesson').html("<div class=\"current_lesson_block_item\">\n" +
                "                            Дата занятия: <br><span id=\"LessonDate\" class=\"lesson_data\">" + data[0] + "</span>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"current_lesson_block_item\">\n" +
                "                            Количество студентов: <br> <span id=\"LessonStudentsCount\" class=\"lesson_data\">" + data[1] + "/" + data[2] + "</span>\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"current_lesson_block_item\">\n" +
                "                            Место занятия: <br> <span id=\"LessonPlace\" class=\"lesson_data\">" + data[3] + "</span>\n" +
                "                        </div>");
            $('#cancelRecording').show();
        },
        error: function (er) {
            $('#CurrentLesson').html("<span style=\"font-style: italic\">*Вы не записаны на занятие <br>\n" +
                "                            <span style=\"font-style: normal; font-size: 30px\">&#128203;</span></span");
            $('#cancelRecording').hide();
        }
    });
}

// при клике на вкладку занятия
$(document).ready(function() {
    $('#LessonTab').click(function(e) {
        getCurrentLesson();
        getLessonList();
    });
});
// при загрузке страницы (т.к. вкладка занятия автоматически грузится при загрузке)
$(document).ready(function() {
    getCurrentLesson();
    getLessonList();
});

$(document).ready(function() {
    $('#cancelRecording').click(function(e) {
        // Stop form from sending request to server
        e.preventDefault();

        $.ajax({
            method: "POST",
            url: "/cancelRecording",
            dataType: 'json',

            // если запись на занятие отменена успешно, то?
            success: function(data) {
                // обновляем блок текущего занятия (после успешной отмены там будет что вы не записаны)
                getCurrentLesson();
                // обновляем базовую информацию (добавятся оставшиеся часы, убавятся потраченные часы)
                getBasicInfo();
                getLessonList();
            },
            error: function(er) {
                console.log(er);
            }
        });
    })
});