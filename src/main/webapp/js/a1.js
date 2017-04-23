var value;

$(document).ready(function () {
    form_init();
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

function addVideoViewById() {
    $$("mainlayot").removeView('content');
    var id = getLocalStorage("lesson_1");
    switch (id) {
        case "1":
            getVideoLessonId(13, id);
            break;
        case "2":
            getVideoLessonId(14, id);
            break;
        case "3":
            getVideoLessonId(14, id);
            break;
        case "4":
            getVideoLessonId(14, id);
            break;
        case "5":
            getVideoLessonId(14, id);
            break;
        case "6":
            getVideoLessonId(14, id);
            break;
        case "7":
            getVideoLessonId(14, id);
            break;
        case "8":
            getVideoLessonId(14, id);
            break;
        case "9":
            getVideoLessonId(14, id);
            break;
        case "10":
            getVideoLessonId(14, id);
            break;
    }
}

function getVideoLessonId(id, resId) {
    get_ajax('/study/wr/lrn/getVideoById', 'GET', {id: id}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        addVideo(gson.message.value, resId);
    });
}

function addVideo(value, resId) {
    $$("mainlayot").addView({
        id: "content",
        css: 'boxLetter',
        rows: [
            {
                cols: [
                    {},
                    {
                        view: 'label',
                        label: getResourceName('vide.aone.title.lesson')
                    },
                    {}
                ]
            },
            {
                cols: [
                    {},
                    {
                        height: 500,
                        width: 700,
                        template: '<iframe width="700" height="500" src="' + value + '" frameborder="0" allowfullscreen></iframe>'
                    },
                    {}
                ]
            },
            {height: 30},
            {
                cols: [
                    {},
                    {
                        view: 'template',
                        autoheight: true,
                        template: getResourceName('video.aone.one.lesson' + resId)
                    },
                    {}
                ]
            },
            {height: 30}
        ]
    });
}

function addAudioViewById() {
    $$("mainlayot").removeView('content');
    var id = getLocalStorage("lesson_1");
    switch (id) {
        case "1":
            getAudioLessonId(id);
            break;
        case "2":
            getAudioLessonId(id);
            break;
        case "3":
            getAudioLessonId(id);
            break;
        case "4":
            getAudioLessonId(id);
            break;
        case "5":
            getAudioLessonId(id);
            break;
        case "6":
            getAudioLessonId(id);
            break;
        case "7":
            getAudioLessonId(id);
            break;
        case "8":
            getAudioLessonId(id);
            break;
        case "9":
            getAudioLessonId(id);
            break;
        case "10":
            getAudioLessonId(id);
            break;
    }
}

function getAudioLessonId(id) {
    get_ajax('/study/wr/lrn/getAudioById', 'GET', {id: id}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        for (var i in gson.message) {
            addAudi(gson.message[i].value, i);
        }
    });
}

function addAudi(value, i) {
    $$("mainlayot").addView({
        id: "content",
        rows: [
            {
                cols: [
                    {},
                    {
                        view: 'label',
                        label: getResourceName('audio.lesson.one.title' + i)
                    },
                    {}
                ]
            },
            {
                cols: [
                    {},
                    {
                        height: 150,
                        width: 250,
                        template: '<iframe width="250" height="150" scrolling="no" frameborder="no" src="' + value + 'liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false"></iframe>'
                    },
                    {}
                ]
            },
            {height: 30},
            {
                cols: [
                    {},
                    {
                        view: 'template',
                        autoheight: true,
                        template: getResourceName('audio.lesson.one.vorzal.text' + i)
                    },
                    {}
                ]
            }
        ]
    });
}

function addGrammarViewById() {
    $$("mainlayot").removeView('content');
    var viewId = [];
    var id = getLocalStorage("lesson_1");
    switch (id) {
        case "1":
            viewId = cons;
            break;
        case "2":
            viewId = cons;
            break;
        case "3":
            viewId = cons;
            break;
        case "4":
            viewId = cons;
            break;
        case "5":
            viewId = cons;
            break;
        case "6":
            viewId = cons;
            break;
        case "7":
            viewId = cons;
            break;
        case "8":
            viewId = cons;
            break;
        case "9":
            viewId = cons;
            break;
        case "10":
            viewId = cons;
            break;
    }
    $$("mainlayot").addView({
        id: "content",
        css: 'boxLetter',
        rows: viewId
    });
}