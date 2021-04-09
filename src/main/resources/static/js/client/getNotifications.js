$.ajax({
    type: 'GET',
    url: '/notifications',
    data: { get_param: 'value' },
    dataType: 'json',
    success: function (data) {
        if(data.length === 0) {
            $('#NotificationTab').text("Уведомления");
        }
        else {
            $('#NotificationTab').html("Уведомления  <span>&#128276</span>")
        }
    }
});

$(document).ready(function() {
    $('#NotificationTab').click(function(e) {
        notificationsLoad();
    });
});

function notificationsLoad() {
        $.ajax({
            type: 'GET',
            url: '/notifications',
            data: { get_param: 'value' },
            dataType: 'json',
            success: function (data) {
                if(data.length === 0) {
                    $('#NotificationTab').text("Уведомления");
                    $('#NotificationList').empty().append("<span style='margin: 0 auto; font-style: italic;'>*Всё прочитано, уведомлений нет.</span>");
                }
                else {
                    $('#NotificationTab').html("Уведомления  <span>&#128276</span>")
                    notificationUpdate(data);
                }
            }
        });

        function notificationUpdate(data) {
            $('#NotificationList').empty();
            $.each(data, function (index, value) {
                let values = value.split('/');
                $('#NotificationList').append("<div class=\"row notification_item\">\n" +
                    "                    <div class=\"col\">\n" +
                    "                        <div class=\"row\">\n" +
                    "                            <span class=\"notification_title\">" + values[0] + "</span>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"row notification_text\">\n" + values[1] +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>")
            });
        }
}

