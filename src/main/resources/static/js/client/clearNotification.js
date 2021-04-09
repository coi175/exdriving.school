$(document).ready(function() {
    $('#NotificationClearButton').click(function(e) {
        // Stop form from sending request to server
        e.preventDefault();

        var btn = $(this);

        $.ajax({
            method: "POST",
            url: "/clearNotifications",
            dataType: 'json',

            success: function(data) {
                $('#NotificationList').html('').append("<span style='margin: 0 auto; font-style: italic;'>*Всё прочитано, уведомлений нет.</span>");
                $('#NotificationTab').text("Уведомления");
            },
            error: function(er) {
                console.log(er);
            }
        });
    })
});