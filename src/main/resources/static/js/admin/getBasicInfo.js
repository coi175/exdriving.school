function getBasicInfo() {
    $.ajax({
        type: 'GET',
        url: '/adminBasicInfo',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#AdminUsername").text(data);
        }
    });
}

getBasicInfo();

// при клике на вкладку занятия
$(document).ready(function() {
    $('#ClientListButton').click(function(e) {
      getBasicInfo();
        getClientList();
    });
});

$(document).ready(function() {
    $('#InstructorListButton').click(function(e) {
        getBasicInfo();
        getInstructorList();
    });
});