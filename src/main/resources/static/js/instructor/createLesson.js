function generateDates() {
    var now = new Date();
    let list =  $('#LessonDate');
    list.empty();
    now.setDate(now.getDate()+1);
    for(let i = 0; i < 30; i++) {
        let dNum = (now.getDate() < 9) ? ("0" + (now.getDate())) : (now.getDate());
        let mNum = (now.getMonth()+1 < 9) ? ("0" + (now.getMonth()+1)) : (now.getMonth()+1);
        let dateStr = now.getDate() + "." + mNum + "." + now.getFullYear();
        now.setDate(now.getDate()+1);
        list.append("<option>" + dateStr + "</option>")
    }
}

function getLessonPlaces() {
    $.ajax({
        type: 'GET',
        url: '/getLessonPlacesForInstructor',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#LessonPlace").empty();
            $.each(data, function (index, value) {

                $("#LessonPlace").append("<option>"+value+"</option>")
            });
        },
        error: function (er) {
            console.log(er);
        }
    });
}

$(document).ready(function() {
    $('#CreateLessonTab').click(function(e) {
        generateDates();
        getLessonPlaces()
    });
});

function createLesson(btn) {
    let date = $('#LessonDate option:selected').text();
    let time = $('#LessonTime option:selected').text();
    let address = $('#LessonPlace option:selected').text();
    let studentsLimit = $('#LessonStudentsLimit option:selected').text();

    $.ajax({
        method: "POST",
        url: "/createLesson",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data:  JSON.stringify({"date" : date,
                "time" : time,
                "address" : address,
                "studentsLimit" : studentsLimit}),
        dataType: 'json',

        success: function(data) {
            $('#CreateLessonSuccess').show(100);
            setTimeout(hideMessage, 4000);
        },
        error: function(er) {
            $('#CreateLessonFault').show(100);
            setTimeout(hideMessage, 4000);
        }
    });
}

function hideMessage() {
    $('#CreateLessonSuccess').hide(100);
    $('#CreateLessonFault').hide(100);
}