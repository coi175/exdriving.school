function getBasicInfo() {
    $.ajax({
        type: 'GET',
        url: '/clientBasicInfo',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            let map = new Map();
            $.each(data, function (key, value) {
                map.set(key, value);
            });
            $('#clientName').text(map.get("clientName"));
            $('#remainingHours').text(map.get("remainingHours"));
            $('#spentHours').text(map.get("spentHours"));
            $('#instructorName').text(map.get("instructorName"));
            $('#studentsCount').text(map.get("studentsCount"));
            $('#instructorCar').text(map.get("instructorCar"));
        }
    });
}

getBasicInfo();