function getBasicInfo() {
    $.ajax({
        type: 'GET',
        url: '/instructorBasicInfo',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            let map = new Map();
            $.each(data, function (key, value) {
                map.set(key, value);
            });
            $('#instructorName').text(map.get("instructorName"));
            $('#instructorCar').text(map.get("instructorCar"));
            $('#studentsCount').text(map.get("studentsCount"));
        }
    });
}

getBasicInfo();