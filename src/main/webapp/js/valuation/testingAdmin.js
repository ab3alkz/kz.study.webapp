
function startTesting1() {
    get_ajax('/study/wr/app/getTestsList', 'GET', null, function (gson) {

    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
