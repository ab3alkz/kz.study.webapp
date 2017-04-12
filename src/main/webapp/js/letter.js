$(document).ready(function () {
    form_init();
});

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        css: 'blueW',
        scroll: true,
        cols: [
            {
                id: "body",
                rows: []
            }
        ]
    });
    getAllLetters();
}

function createAudio(json, i) {
    $$("body").addView({
        id: "content" + i,
        css: 'boxLetter',
        rows: [
            {
                height: 150,
                cols: [
                    {
                        view: 'label',
                        label: json.addValue,
                        css: 'pLetter',
                        width: 300,
                        height: 60
                    },
                    {
                        id: "textNoticeCont" + i,
                        hidden: json.value == "",
                        width: 300,
                        template: '<iframe width="100%" height="166" scrolling="no" frameborder="no" src="' + json.value + 'liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false"></iframe>'
                    },
                    {}
                ]
            }
        ]
    });
}

function getAllLetters() {
    get_ajax('/study/wr/lrn/getAllLetters', 'GET', {}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        for (var i in gson.message) {
            createAudio(gson.message[i], i);
        }

    });
}