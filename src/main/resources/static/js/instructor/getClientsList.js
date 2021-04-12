$.ajax({
    type: 'GET',
    url: '/clientsList',
    data: { get_param: 'value' },
    dataType: 'json',
    success: function (data) {
        $.each(data, function (key, value) {
            let values = value.split('/');
            $('#clientListBlock').append("<div class=\"row text-center client_list_line\">\n" +
                "                    <div class=\"col client_list_line_item\">\n" +
                "                        <span style=\"font-weight: bold\">" + values[0] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"col client_list_line_item\">\n" +
                "                        <span style=\"font-weight: bold\">" + values[1] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"col client_list_line_item\">\n" +
                "                        <span style=\"font-weight: bold\">" + values[2] + "</span>\n" +
                "                    </div>\n" +
                "                </div>")
        });
    }
});