$.ajax({
    type: 'GET',
    url: '/marks',
    data: { get_param: 'value' },
    dataType: 'json',
    success: function (data) {
        let sum = 0;
        let i = 0;
        $.each(data, function (key, value) {
            let dtime = value.split(' ');
            $('#markListBlock').append("<div class='row text-center mark_list_line'>" +
                "<div class=\"col mark_list_item\">\n" + dtime[0] + "</div>\n" +
                "<div class=\"col mark_list_item\">\n" + dtime[1] + "</div>\n" +
                "<div class=\"col mark_list_item\">\n" + dtime[2] + "</div>" + "</div>")
            sum += parseInt(dtime[2]);
            i++;
        });

        let average = sum / i;
        $('#markAverage').text(average.toFixed(2) + "/" + "5");
    }
});