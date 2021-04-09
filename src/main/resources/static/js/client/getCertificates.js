$.ajax({
    type: 'GET',
    url: '/certificates',
    data: { get_param: 'value' },
    dataType: 'json',
    success: function (data) {
        $.each(data, function (index, value) {
            let values = value.split('/');
            $('#Certificates').append("<div class=\"row mt-4\">\n" +
                "                <div class=\"col-6 certificate\" style=\"padding-top: 150px\">\n" +
                "                    <div class=\"row text-center m-auto\">\n" +
                "                        <span class=\"m-auto certificate_textline certificate_number\">Номер: " + values[0] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"row text-center m-auto\">\n" +
                "                        <span class=\"m-auto certificate_textline certificate_client\">" + values[1] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"row text-center m-auto\">\n" +
                "                        <span class=\"m-auto certificate_textline certificate_mark\">Оценка:" + values[2] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"row text-center m-auto\">\n" +
                "                        <span class=\"certificate_textline certificate_date\">Дата: " + values[3] + "</span>\n" +
                "                    </div>\n" +
                "                    <div class=\"row\">\n" +
                "                        <a  onclick='downloadCertificate(this)' href = '#' class =\"download_certificate m-auto\">" +
                "                        <div class ='cert_data' hidden='hidden'>" + values[0] + '/' + values[1] + '/' + values[2] + '/' + values[3] + "</div> Скачать</a>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>")
        });

    }
});
