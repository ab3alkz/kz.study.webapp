$(document).ready(function () {
    form_init();
});

function form_init() {
    webix.ready(function () {
        var layout = webix.ui({
            id: "mainlayot",
            container: "mainContainer",
            cols: [
                {width: 10},
                {id: "body", rows: [{id: "content"}]},
                {width: 10}
            ]
        });

    });
    createAudio();
}

function createAudio() {
    getAllLetters();
    $$("body").addView({
        id: "content",
        rows: [
            {
                view: 'label',
                label: 'Кандай арип'
            },
            {
                cols: [
                    {},
                    {
                        id: "textNoticeCont",
                        width: 200,
                        height: 200,
                        template: '<iframe width="100%" height="166" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/316222556&amp;liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false"></iframe>'
                    },
                    {}
                ]
            }
        ]

    })
}

function getAllLetters() {
    get_ajax('/study/wr/lrn/getAllLetters', 'GET', {}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        $$("body").removeView("content");
        createLastGameForm(gson.message, data.name)
        $$("addGameWin").hide();
        notifyMessage('Информация! ', 'Игра добавлена!', notifyType.info);
    });
}