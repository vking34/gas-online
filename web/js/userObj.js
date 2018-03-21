function sendObj() {

    // get inputs

    var user = new Object();

    user.firstName = $('#firstName').val();
    user.lastName = $('#lastName').val();
    user.username = $('#username').val();
    user.password = $('#password').val();
    user.password1 = $('#password1').val();
    user.phoneNumber = $('#phoneNumber').val();
    user.email = $('#email').val();

    var $error = $('#error');

    $.ajax(
        {
            url: 'signup',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(user),
            contentType: 'application/json; charset=UTF-8',
            success: function (error) {
                var $usernameError = $("#usernameError");
                var $phoneNumberError = $("#phoneNumberError");
                var $emailError = $("#emailError");

                $usernameError.append('<li>'+ error.usernameError + '</li>');
                $phoneNumberError.append('<li>' + error.phoneNumberError + '</li>');
                $emailError.append('<li>' + error.emailError + '</li>');

            }
        }
    );

    return false;
}