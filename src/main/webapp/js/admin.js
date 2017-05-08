$(document).ready(function () {
    locale();
    form_init();
});

var foldersTreeMenu = [
    {id: "LESSON", icon: "folder-open-o", value: "Добавить урок к уровням"},
    {id: "VIDEO", icon: "fa fa-youtube", value: "Добавить видео к уровням"},
    {id: "GRAMMAR", icon: "floppy-o", value: "Добавить грамматику к уровням"},
    {id: "AUDIO", icon: "fa fa-file-audio-o", value: "Добавить аудио к уровням"}
];

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        rows: [
            {
                cols: [{}, {view: 'label', label: 'Панель администрирования'}, {}]
            },
            {
                cols: [
                    {
                        id: "foldersTree",
                        autoheight: true,
                        height: 500,
                        view: "sidebar",
                        data: foldersTreeMenu,
                        on: {
                            onAfterSelect: function (id) {
                                getApps(id);
                            }
                        }
                    },
                    {
                        rows: [
                            {
                                id: 'addSomeThink',
                                cols: []
                            }
                        ]
                    },
                    {}
                ]
            }
        ]
    });


}

function getApps(id) {
    switch (id) {
        case "LESSON":
            addLessonType(1);
            break;
        case "VIDEO":
            addLessonType(2);
            break;
        case "GRAMMAR":
            addLessonType(3);
            break;
        case "AUDIO":
            addLessonType(4);
            break;
    }
}

function addLessonType(paramId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoView');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioView');
    $$('addSomeThink').addView(
        {
            id: 'btnView',
            cols: [
                {
                    css: "noBorder",
                    width: 190,
                    height: 40,
                    template: '<button type="button" onclick="addInfoByParam(' + paramId + ', 1)" class="btn btn-primary">A1 Уровень</button>'
                },
                {
                    css: "noBorder",
                    width: 190,
                    height: 40,
                    template: '<button type="button" onclick="addInfoByParam(' + paramId + ', 2)" class="btn btn-primary">A2 Уровень</button>'
                },
                {
                    css: "noBorder",
                    width: 190,
                    height: 40,
                    template: '<button type="button" onclick="addInfoByParam(' + paramId + ', 3)" class="btn btn-primary">B1 Уровень</button>'
                },
                {
                    css: "noBorder",
                    width: 190,
                    height: 40,
                    template: '<button type="button" onclick="addInfoByParam(' + paramId + ', 4)" class="btn btn-primary">B2 Уровень</button>'
                },
                {
                    css: "noBorder",
                    width: 190,
                    height: 40,
                    template: '<button type="button" onclick="addInfoByParam(' + paramId + ', 5)" class="btn btn-primary">C1 Уровень</button>'
                }
            ]
        }
    );
}

function addInfoByParam(partNum, btnId) {
    switch (partNum) {
        case 1:
            addViewLessonToLesson(btnId);
            break;
        case 2:
            addViewVideoToLesson(btnId);
            break;
        case 3:
            break;
        case 4:
            addViewAudioToLesson(btnId);
            break;
    }
}

function addViewLessonToLesson(lessonId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoView');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioView');
    $$('addSomeThink').addView(
        {
            id: 'lessonView',
            width: 800,
            view: 'form',
            elementsConfig: {
                labelPosition: "top"
            },
            rows: [
                {
                    view: 'label',
                    label: 'Добавить урок к уровням'
                },
                {
                    view: 'text',
                    label: 'Наименование на русском'
                },
                {
                    view: 'text',
                    label: 'Наименование на казахском'
                },
                {
                    view: 'text',
                    label: 'Наименование на латынском'
                },
                {
                    cols: [
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="addInfoByParam(' + lessonId + ', 4)" class="btn btn-success">Сохранить</button>'
                        },
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="clearFormByid(\'videoView\')" class="btn btn-danger">Очистить</button>'
                        }
                    ]
                }
            ]
        }
    );
}

function addViewVideoToLesson(lessonId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoView');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioView');
    $$('addSomeThink').addView(
        {
            id: 'videoView',
            width: 800,
            view: 'form',
            elementsConfig: {
                labelPosition: "top"
            },
            rows: [
                {
                    view: 'label',
                    label: 'Добавить видео к уроку'
                },
                {
                    view: 'text',
                    label: 'Ссылка в ютюбе'
                },
                {
                    view: 'text',
                    label: 'Наименование на русском'
                },
                {
                    view: 'text',
                    label: 'Наименование на казахском'
                },
                {
                    view: 'text',
                    label: 'Наименование на латынском'
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextRu",
                    label: "Упражнения на русском",
                    labelPosition: "top"
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextKz",
                    label: "Упражнения на казахском",
                    labelPosition: "top"
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextLn",
                    label: "Упражнения на латынском",
                    labelPosition: "top"
                },
                {
                    cols: [
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="addInfoByParam(' + lessonId + ', 4)" class="btn btn-success">Сохранить</button>'
                        },
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="clearFormByid(\'videoView\')" class="btn btn-danger">Очистить</button>'
                        }
                    ]
                }
            ]
        }
    );
}

function addViewAudioToLesson(lessonId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoView');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioView');
    $$('addSomeThink').addView(
        {
            id: 'audioView',
            width: 800,
            view: 'form',
            elementsConfig: {
                labelPosition: "top"
            },
            rows: [
                {
                    view: 'label',
                    label: 'Добавить аудио к уроку'
                },
                {
                    view: 'text',
                    label: 'Ссылка в SoundCloud'
                },
                {
                    view: 'text',
                    label: 'Наименование на русском'
                },
                {
                    view: 'text',
                    label: 'Наименование на казахском'
                },
                {
                    view: 'text',
                    label: 'Наименование на латынском'
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextRu",
                    label: "Упражнения на русском",
                    labelPosition: "top"
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextKz",
                    label: "Упражнения на казахском",
                    labelPosition: "top"
                },
                {
                    view: "richtext",
                    height: 300,
                    id: "richtextLn",
                    label: "Упражнения на латынском",
                    labelPosition: "top"
                },
                {
                    cols: addProfilePhotoBlock()
                },
                {
                    view: 'text',
                    label: 'Ссылка на картинку'
                },
                {
                    cols: [
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="addInfoByParam(' + lessonId + ', 4)" class="btn btn-success">Сохранить</button>'
                        },
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="clearFormByid(\'videoView\')" class="btn btn-danger">Очистить</button>'
                        }
                    ]
                }
            ]
        }
    );
}

function clearFormByid(id) {
    $$(id).clear();
}

function uploadUserPhoto() {
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
    if (!$$('uploadUserPhoto')) {
        webix.ui({
            view: "window",
            id: "uploadUserPhoto",
            modal: true,
            position: "center",
            height: 400,
            width: 700,
            head: {
                view: "toolbar", cols: [
                    {view: "label", label: "Фото профиля"},
                    {
                        view: "button", label: 'Закрыть', width: 100, height: 34, align: 'right',
                        click: function () {
                            this.getTopParentView().hide();
                            window.onscroll = null;
                        }
                    }
                ]
            },
            body: {view: "template", content: "uploadCropContainer", height: 500}
        }).hide();
    }

    $$('uploadUserPhoto').show();
}

function removeUserPhoto() {
    get_ajax('/xdoc/ws/profile/removeProfile', 'POST', {}, function (gson) {
        if (!gson.result) {
            notifyMessage('danger', 'Ошибка! ', gson.message);
            return;
        }
        $$('avaRows').removeView('ava');
        var ava = '/xdoc/plugin/img/ava4.png';
        addProfilePhotoBlock(ava);
        notifyMessage('info', 'Редактирование! ', 'Профиль пользователя удалено');
    });
}

function addProfilePhotoBlock(ava) {
    var avatar;
    console.log(ava)
    if (!ava) {
        avatar = '/study/plugin/img/ava4.png';
    } else {
        avatar = ava;
    }
    return [
        {
            id: 'ava',
            rows: [
                {
                    cols: [
                        {},
                        {
                            width: 200,
                            minHeight: 180,
                            id: "person_template",
                            css: "header_personAva",
                            borderless: true,
                            data: {id: 3, name: ""},
                            template: function () {
                                var html = "<div style='height:100%;width:100%;'>";
                                html += "<img class='photo' src='" + avatar + "'/></span>";
                                html += "</div>";
                                return html;
                            }
                        },
                        {}
                    ]
                },
                {
                    cols: [
                        {
                            view: "button",
                            icon: "cloud-upload",
                            label: "Загрузить",
                            type: "iconButton",
                            align: 'left',
                            on: {
                                onItemClick: function (pid, e) {
                                    uploadUserPhoto();
                                }
                            }
                        },
                        {
                            view: "button",
                            icon: "file-o",
                            label: "Очистить",
                            type: "iconButton",
                            align: 'left',
                            on: {
                                onItemClick: function (pid, e) {
                                    removeUserPhoto();
                                }
                            }
                        }
                    ]
                }
            ]
        }
    ]
}