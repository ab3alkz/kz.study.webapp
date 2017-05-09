$(document).ready(function () {
    form_init();
    getLessonValue();
});

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        css: 'blueW',
        autoheight: true,
        rows: []
    });
}

function getLessonValue() {
    var part = getLocalStorage("part");
    get_ajax('/study/wr/lrn/getLessonValue', 'GET', {part: part}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        gson.message.forEach(function (e) {
            $$('mainlayot').addView(
                {
                    id: "content",
                    css: 'boxLetter',
                    rows: [
                        {
                            view: "button",
                            id: e.id,
                            value: e.value,
                            width: 190,
                            on: {
                                onItemClick: function () {
                                    this.hide();
                                    addViewLocal(this.data.id);
                                }
                            }
                        },
                        {height: 30}
                    ]
                }
            );
        })
    });
}

function addVideoViewByID() {
    $$("mainlayot").removeView('videocontent');
    $$("mainlayot").removeView('grammarContent');
    $$("mainlayot").removeView('audioContent');
    get_ajax('/study/wr/lrn/getVideoFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        gson.message.forEach(function (e) {
            $$("mainlayot").addView({
                id: "videocontent",
                css: 'boxLetter',
                rows: [
                    {
                        cols: [
                            {},
                            {
                                view: 'label',
                                css: 'mainAnalizeTitle',
                                label: e.value
                            },
                            {}
                        ]
                    },
                    {
                        cols: [
                            {
                                height: 500,
                                width: 700,
                                template: '<iframe width="700" height="500" src="' + e.link + '" frameborder="0" allowfullscreen></iframe>'
                            },
                            {
                                view: 'template',
                                autoheight: true,
                                template: e.descValue
                            }
                        ]
                    },
                    {height: 30}
                ]
            });
        })
    });
}

function addAudioViewByID() {
    $$("mainlayot").removeView('videocontent');
    $$("mainlayot").removeView('grammarContent');
    $$("mainlayot").removeView('audioContent');
    get_ajax('/study/wr/lrn/getAudioFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        gson.message.forEach(function (e) {
            $$("mainlayot").addView({
                id: "audioContent",
                css: 'boxLetter',
                rows: [
                    {
                        cols: [
                            {},
                            {
                                view: 'label',
                                css: 'mainAnalizeTitle',
                                label: e.value
                            },
                            {}
                        ]
                    },
                    {
                        cols: [
                            {
                                height: 150,
                                width: 250,
                                template: '<iframe width="250" height="150" scrolling="no" frameborder="no" src="' + e.link + 'liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false"></iframe>'
                            },
                            {
                                view: 'template',
                                autoheight: true,
                                template: e.descValue
                            },
                            {
                                data: {title: "Image One", src: e.img},
                                template: function (obj) {
                                    return '<img src="' + obj.src + '" width="300" height="150"/>'
                                }
                            },
                            {
                                view: 'label',
                                template: '<a href="' + e.audioLink + '">Ссылка</a>'
                            }
                        ]
                    }
                ]
            });
        })
    });
}

function addGrammarViewByID() {
    $$("mainlayot").removeView('videocontent');
    $$("mainlayot").removeView('grammarContent');
    $$("mainlayot").removeView('audioContent');
    get_ajax('/study/wr/lrn/getGrammarFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        gson.message.forEach(function (e) {
            $$("mainlayot").addView({
                id: "grammarContent",
                css: 'boxLetter',
                rows: [
                    {
                        height: 60,
                        cols: [
                            {},
                            {
                                view: 'label',
                                css: 'mainAnalizeTitle',
                                template: e.value
                            },
                            {}
                        ]
                    },
                    {height: 10},
                    {
                        cols: [
                            {
                                view: 'label',
                                template: e.descValue
                            }
                        ]
                    },
                    {height: 80},
                ]
            });
        })
    });
}