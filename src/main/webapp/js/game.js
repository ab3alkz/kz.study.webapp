$(document).ready(function () {
    addGame1ViewByID()
});

function addGame1ViewByID() {
    get_ajax('/study/wr/lrn/getGame2FormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        addGme(gson.message);
    })
}