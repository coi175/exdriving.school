function getClientList() {
    $.ajax({
        type: 'GET',
        url: '/adminGetClientList',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $('#ClientListBlock').empty().append("<div class=\"row text-center client_list_line\">\n" +
                "                <div class=\"col client_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Клиент:</span>\n" +
                "                </div>\n" +
                "                <div class=\"col client_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Почта:</span>\n" +
                "                </div>\n" +
                "                <div class=\"col client_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Часы:</span>\n" +
                "                </div>\n" +
                "                <div class=\"col client_list_line_item\">\n" +
                "                    <span style=\"font-weight: bold\">Инструктор:</span>\n" +
                "                </div>\n" +
                "            </div>");
            $.each(data, function (key, value) {
                let values = value.split('/');
                $('#ClientListBlock').append("<div onclick=\"clientClick(this);\" class=\"row text-center client_list_line client_item\">\n" +
                    "                <div class=\"col client_list_line_item\">\n" +
                    "                    <span>" + values[1] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"col client_list_line_item\">\n" +
                    "                    <span>" + values[2] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"col client_list_line_item\">\n" +
                    "                    <span>" + values[3] + "</span>\n" +
                    "                </div>\n" +
                    "                <div class=\"col client_list_line_item\">\n" +
                    "                    <span>" + values[4] + "</span>\n" +
                    "                </div>\n" +
                    " <div style=\"display: none;\" class=\"col client_list_line_item\">" + values[0] + "</div>\n" +
                    "            </div>")
            });
        }
    });
}

getClientList();