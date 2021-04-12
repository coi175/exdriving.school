function getLessonsForMark() {
    $.ajax({
        type: 'GET',
        url: '/getLessonsForMark',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#LessonToMark").empty();
            $.each(data, function (key, value) {

                $("#LessonToMark").append("<option value= \"" + key + "\">" + value +  "</option>");
            });
        },
        error: function (er) {
            console.log(er);
        }
    });
}

function getStudentsForMark() {
    $.ajax({
        type: 'GET',
        url: '/getStudentsForMark',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#StudentForMark").empty();
            $.each(data, function (key, value) {

                $("#StudentForMark").append("<option value= \"" + key + "\">" + value +  "</option>");
            });
        },
        error: function (er) {
            console.log(er);
        }
    });
}

function addMark(btn) {
    let lessonId = $('#LessonToMark option:selected').val();
    let clientId = $('#StudentForMark option:selected').val();
    let mark = $('#Mark option:selected').text();

    $.ajax({
        method: "POST",
        url: "/addMark",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data:  JSON.stringify({"lessonID" : lessonId,
            "clientID" : clientId,
            "mark" : mark}),
        dataType: 'json',

        success: function(data) {
            $('#AddMarkSuccess').show(100);
            setTimeout(hideMarkMessages, 4000);
        },
        error: function(er) {
            $('#AddMarkFault').show(100);
            setTimeout(hideMarkMessages, 4000);
        }
    });
}

function hideMarkMessages() {
    $('#AddMarkSuccess').hide(100);
    $('#AddMarkFault').hide(100);
}

$(document).ready(function() {
    $('#MarksTab').click(function(e) {
        getLessonsForMark();
        getStudentsForMark()
    });
});