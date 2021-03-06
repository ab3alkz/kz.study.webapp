$(document).ready(function () {
    form_init();
    getLessonValue();
    // addGame1ViewByID()
});

var k = 0;

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        css: 'blueW',
        autoheight: true,
        rows: [{
            id: 'addSomeThink',
            cols: []
        }]
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
            $$('addSomeThink').addView(
                {
                    css: 'boxLetter',
                    cols: [
                        {
                            view: "button",
                            id: e.id,
                            value: e.value,
                            width: 190,
                            height: 60,
                            on: {
                                onItemClick: function () {
                                    addViewLocal(this.data.id);
                                    reViewRemove();
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

function reViewRemove() {
    $$('mainlayot').removeView('addSomeThink');
}

function adViewAddView() {
    $$("mainlayot").addView({
        id: 'addSomeThink',
        rows: []
    });
}

function addVideoViewByID() {
    get_ajax('/study/wr/lrn/getVideoFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        reViewRemove();
        adViewAddView();
        gson.message.forEach(function (e) {
            $$("addSomeThink").addView({
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
    get_ajax('/study/wr/lrn/getAudioFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        reViewRemove();
        adViewAddView();
        gson.message.forEach(function (e) {
            $$("addSomeThink").addView({
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
                            }
                        ]
                    }
                ]
            });
        })
    });
}

function addGrammarViewByID() {
    get_ajax('/study/wr/lrn/getGrammarFormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        reViewRemove();
        adViewAddView();
        gson.message.forEach(function (e) {
            $$("addSomeThink").addView({
                css: 'boxLetter',
                rows: [
                    {
                        height: 90,
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
                        height: 6000,
                        view: 'label',
                        template: e.descValue
                    },
                    {height: 80}
                ]
            });
        })
    });
}

function addGame1ViewByID() {
    get_ajax('/study/wr/lrn/getGame1FormById', 'GET', {param: getLocalStorage("btnParam")}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        reViewRemove();
        adViewAddView();
        gson.message.forEach(function (e) {
            $$("addSomeThink").addView({
                css: 'boxLetter',
                rows: [
                    {height: 10},
                    {
                        height: 6000,
                        view: 'label',
                        template: e.descRus
                    },
                    {height: 80}
                ]
            });
        })
    });
}

function gameData() {
    $('#mainContainer2').css("visibility", "visible");
    $('#mainContainer2').css("display", "block");
    $('#mainContainer').css("display", "none");
    webix.ui({
        id: "mainlayot2",
        container: "mainContainer2",
        css: 'blueW',
        autoheight: true,
        rows: [{
            id: 'addSomeThink',
            cols: []
        }]
    });
}