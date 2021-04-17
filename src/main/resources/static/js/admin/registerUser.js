// Клиент ------------------------------------------
function registerNewClient(btn) {
    if(validateClientForm()) {
        let firstName = $("#ClientFirstNameForm").val();
        let lastName = $("#ClientLastNameForm").val();
        let login = $("#ClientLoginForm").val();
        let password = $("#ClientPasswordForm").val();
        let email = $("#ClientEmailForm").val();
        let hoursLimit = $("#ClientHoursLimitForm").val();
        let instructorID = $("#ClientInstructorForm").val();

        $.ajax({
            method: "POST",
            url: "/registerNewClient",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"firstName" : firstName,
                "lastName" : lastName,
                "login" : login,
                "password" : password,
                "email" : email,
                "hoursLimit" : hoursLimit,
                "instructorID" : instructorID}),
            dataType: 'json',

            success: function(data) {
                $('#RegistrationSuccess').show(100);
                setTimeout(hideMessage, 3000);
                getClientList();
            },
            error: function(er) {
                $('#RegistrationError').show(100);
                setTimeout(hideMessage, 3000);
            }
        });
    }

    else {
        $('#RegistrationError').show(100);
        setTimeout(hideMessage, 3000);
    }

}

function validateClientForm() {
    if($("#ClientFirstNameForm").val() === "") return false;
    if($("#ClientLastNameForm").val() === "") return false;
    if($("#ClientLoginForm").val() === "") return false;
    if($("#ClientPasswordForm").val() === "") return false;
    if($("#ClientEmailForm").val() === "") return false;
    let h = $("#ClientHoursLimitForm").val();
    if(h === "" || h < 0 || !Number.isInteger(+h)) return false;
    if($("#ClientInstructorForm").val() === "false") return false;

    return true;
}


function getInstructorsForRegister() {
    $.ajax({
        type: 'GET',
        url: '/getInstructorListForClient',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#ClientInstructorForm").empty().append("<option value='false' selected disabled>Выберите инструктора</option>");
            $.each(data, function(key, value) {
                $("#ClientInstructorForm").append("<option value='" + key+"'>" + value + "</option>");
            });
        }
    });
}


// Инструктор ---------------------------------------------------

function registerNewInstructor(btn) {
    if(validateInstructorForm()) {
        let firstName = $("#InstructorFirstNameForm").val();
        let lastName = $("#InstructorLastNameForm").val();
        let login = $("#InstructorLoginForm").val();
        let password = $("#InstructorPasswordForm").val();
        let carID = $("#InstructorCarForm").val();

        $.ajax({
            method: "POST",
            url: "/registerNewInstructor",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data:  JSON.stringify({"firstName" : firstName,
                "lastName" : lastName,
                "login" : login,
                "password" : password,
                "carID" : carID}),
            dataType: 'json',

            success: function(data) {
                $('#RegistrationSuccess').show(100);
                setTimeout(hideMessage, 3000);
                // getInstructorList();
            },
            error: function(er) {
                $('#RegistrationError').show(100);
                setTimeout(hideMessage, 3000);
            }
        });
    }

    else {
        $('#RegistrationError').show(100);
        setTimeout(hideMessage, 3000);
    }

}

function validateInstructorForm() {
    if($("#InstructorFirstNameForm").val() === "") return false;
    if($("#InstructorLastNameForm").val() === "") return false;
    if($("#InstructorLoginForm").val() === "") return false;
    if($("#InstructorPasswordForm").val() === "") return false;

    return true;
}

function getCarsForRegister() {
    $.ajax({
        type: 'GET',
        url: '/getCarsForInstructorRegister',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $("#InstructorCarForm").empty().append("<option value='' selected disabled>Выберите машину</option>");
            $.each(data, function(key, value) {
                $("#InstructorCarForm").append("<option value='" + key+ "'>" + value + "</option>");
            });
        }
    });
}


// ----------------------------------------------------
function hideMessage() {
    $('#RegistrationSuccess').hide(100);
    $('#RegistrationError').hide(100);
}



getInstructorsForRegister();
getCarsForRegister();