function getInstructorList() {
    $.ajax({
        type: 'GET',
        url: '/adminGetInstructorList',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $('#InstructorListBlock').empty().append("<div class=\"row text-center instructor_list_line\">\n" +
                "                <div class=\"col instructor_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Инструктор:</span>\n" +
                "                </div>\n" +
                "                <div class=\"col instructor_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Кол-во клиентов:</span>\n" +
                "                </div>\n" +
                "                <div class=\"col instructor_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Машина:</span>\n" +
                "                </div>\n" +
                "            </div>");
            $.each(data, function (key, value) {
                let values = value.split('/');
                $('#InstructorListBlock').append("<div onclick=\"instructorClick(this);\" class=\"row text-center instructor_list_line instructor_item\">\n" +
                    "                <div class=\"col instructor_list_line_item\">\n" +
                    "                    <span>" + values[1] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"col instructor_list_line_item\">\n" +
                    "                    <span>" + values[2] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"col instructor_list_line_item\">\n" +
                    "                    <span>" + values[3] + "</span>\n" +
                    "                </div>\n" +
                    " <div style=\"display: none;\" class=\"col instructor_list_line_item\">" + values[0] + "</div>\n" +
                    "            </div>")
            });
        }
    });
}

getInstructorList();