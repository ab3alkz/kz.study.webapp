
function startTesting() {
    get_ajax('/study/wr/app/getRandom25Guestions', 'GET', null, function (gson) {
        createFillWords(gson);
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
