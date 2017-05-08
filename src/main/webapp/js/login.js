$(document).ajaxStart(function () {
    alertError('');
    $(".loadingContainer").show();
}).ajaxStop(function () {
    $(".loadingContainer").hide();
});

function alertError(msg) {
    $('.errmsg').html(msg);
}

function login() {

    var username = $('#username').val();
    var password = $('#password').val();

    loginSubmit(username, password);
}
function loginSubmit(username, password) {

    if (username.trim().length == 0 || password.trim().length == 0) {
        alertError("Введите логин и пароль");
        return;
    }

    $.ajax({
        url: "/study/auth",
        type: 'GET',
        data: {j_username: username, j_password: password},
        success: function (gson) {
            if (gson) {
                if (gson.result) {
                    window.location.href = "/study/pages/index.jsp";
                }
            }
        },
        error: function () {
        }
    });
}
