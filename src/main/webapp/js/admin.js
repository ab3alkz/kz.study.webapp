$(document).ready(function () {
    locale();
    form_init();
});

var foldersTreeMenu = [
    {id: "LESSON", icon: "folder-open-o", value: "Добавить урок к уровням"},
    {id: "VIDEO", icon: " fa fa-youtube", value: "Добавить видео к уровням"},
    {id: "GRAMMAR", icon: "floppy-o", value: "Добавить грамматику к уровням"},
    {id: "AUDIO", icon: " fa fa-file-audio-o", value: "Добавить аудио к уровням"}
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
    $$('addSomeThink').removeView('videoMain');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioMain');
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
    $$('addSomeThink').removeView('videoMain');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioMain');
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
                    required: true,
                    name: 'nameRus',
                    label: 'Наименование на русском'
                },
                {
                    view: 'text',
                    required: true,
                    name: 'nameKaz',
                    label: 'Наименование на казахском'
                },
                {
                    view: 'text',
                    required: true,
                    name: 'nameLan',
                    label: 'Наименование на латынском'
                },
                {
                    cols: [
                        {
                            css: "noBorder",
                            width: 190,
                            height: 40,
                            template: '<button type="button" onclick="addDataToLesson(' + lessonId + ')" class="btn btn-success">Сохранить</button>'
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

function addDataToLesson(paramId) {
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/addLessonByPart', 'POST',
            {
                paramId: paramId,
                nameRus: $$('lessonView').elements['nameRus'].getValue(),
                nameKaz: $$('lessonView').elements['nameKaz'].getValue(),
                nameLan: $$('lessonView').elements['nameLan'].getValue()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('lessonView');
                    notifyMessage(gson.message, gson.message, notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function addViewVideoToLesson(lessonId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoMain');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioMain');
    $$('addSomeThink').addView(
        {
            id: 'videoMain',
            rows: [
                {
                    view: 'label',
                    label: 'Добавить видео к уроку'
                },
                {
                    cols: [
                        {
                            view: "richselect",
                            label: 'Выберите уровень',
                            labelPosition: "top",
                            id: "dUroven",
                            inputHeight: 37,
                            height: 70,
                            suggest: {
                                body: {template: "#nameRus#"}
                            },
                            on: {
                                onChange: function (newV) {
                                    if (!isNullOrEmpty(newV)) {
                                        $$('parentId').enable();
                                        loadData(2, newV);
                                    }
                                }
                            }
                        },
                        {
                            view: "richselect",
                            label: 'Выберите родительский элемент',
                            labelPosition: "top",
                            name: "parentId",
                            id: "parentId",
                            disabled: true,
                            inputHeight: 37,
                            height: 70,
                            suggest: {
                                body: {template: "#nameRus#"}
                            },
                            on: {
                                onChange: function (newV, oldV) {
                                    $$('videoView').enable();
                                }
                            }
                        }
                    ]
                },
                {
                    id: 'videoView',
                    width: 800,
                    view: 'form',
                    disabled: true,
                    elementsConfig: {
                        labelPosition: "top"
                    },
                    rows: [
                        {
                            view: 'text',
                            name: 'link',
                            required: true,
                            label: 'Ссылка в ютюбе'
                        },
                        {
                            view: 'text',
                            name: 'nameRus',
                            required: true,
                            label: 'Наименование на русском'
                        },
                        {
                            view: 'text',
                            name: 'nameKaz',
                            required: true,
                            label: 'Наименование на казахском'
                        },
                        {
                            view: 'text',
                            name: 'nameLan',
                            required: true,
                            label: 'Наименование на латынском'
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descRus',
                            required: true,
                            label: "Упражнения на русском",
                            labelPosition: "top"
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descKaz',
                            required: true,
                            label: "Упражнения на казахском",
                            labelPosition: "top"
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descLan',
                            required: true,
                            label: "Упражнения на латынском",
                            labelPosition: "top"
                        },
                        {
                            cols: [
                                {
                                    css: "noBorder",
                                    width: 190,
                                    height: 40,
                                    template: '<button type="button" onclick="addDataToVideo(' + lessonId + ')" class="btn btn-success">Сохранить</button>'
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
            ]
        }
    );
    loadData(1);
}

function loadData(param, newV) {
    var url = "";
    var form = "";
    if (param == 1) {
        url = '/study/wr/dic?name=dUrovenList&newV=' + newV;
        form = 'dUroven';
    } else if (param == 2) {
        url = '/study/wr/dic?name=dParentList&newV=' + newV;
        form = 'parentId';
    }

    get_ajax(url, 'GET', {}, function (gson) {
        if (gson) {
            loadFormRichDictionaryById(form, gson);
        }
    });
}

function loadFormRichDictionaryById(viewId, data) {
    var list = $$(viewId).getPopup().getList();
    list.clearAll();
    list.parse(data);
}

function addDataToVideo(paramId) {
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/addVideoLessonByPart', 'POST',
            {
                paramId: $$('parentId').getValue(),
                link: $$('videoView').elements['link'].getValue(),
                nameRus: $$('videoView').elements['nameRus'].getValue(),
                nameKaz: $$('videoView').elements['nameKaz'].getValue(),
                nameLan: $$('videoView').elements['nameLan'].getValue(),
                descRus: $$('videoView').elements['descRus'].getValue(),
                descKaz: $$('videoView').elements['descRus'].getValue(),
                descLan: $$('videoView').elements['descRus'].getValue()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('videoView');
                    notifyMessage(gson.message, gson.message, notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function addViewAudioToLesson(lessonId) {
    $$('addSomeThink').removeView('btnView');
    $$('addSomeThink').removeView('videoMain');
    $$('addSomeThink').removeView('lessonView');
    $$('addSomeThink').removeView('audioMain');
    $$('addSomeThink').addView(
        {
            id: 'audioMain',
            rows: [
                {
                    view: 'label',
                    label: 'Добавить аудио к уроку'
                },
                {
                    cols: [
                        {
                            view: "richselect",
                            label: 'Выберите уровень',
                            labelPosition: "top",
                            id: "dUroven",
                            inputHeight: 37,
                            height: 70,
                            suggest: {
                                body: {template: "#nameRus#"}
                            },
                            on: {
                                onChange: function (newV) {
                                    if (!isNullOrEmpty(newV)) {
                                        $$('parentId').enable();
                                        loadData(2, newV);
                                    }
                                }
                            }
                        },
                        {
                            view: "richselect",
                            label: 'Выберите родительский элемент',
                            labelPosition: "top",
                            name: "parentId",
                            id: "parentId",
                            disabled: true,
                            inputHeight: 37,
                            height: 70,
                            suggest: {
                                body: {template: "#nameRus#"}
                            },
                            on: {
                                onChange: function (newV, oldV) {
                                    $$('audioView').enable();
                                }
                            }
                        }
                    ]
                },
                {
                    id: 'audioView',
                    width: 800,
                    disabled: true,
                    view: 'form',
                    elementsConfig: {
                        labelPosition: "top"
                    },
                    rows: [
                        {
                            view: 'text',
                            name: 'link',
                            required: true,
                            label: 'Ссылка в SoundCloud'
                        },
                        {
                            view: 'text',
                            name: 'nameRus',
                            required: true,
                            label: 'Наименование на русском'
                        },
                        {
                            view: 'text',
                            name: 'nameKaz',
                            required: true,
                            label: 'Наименование на казахском'
                        },
                        {
                            view: 'text',
                            name: 'nameLan',
                            required: true,
                            label: 'Наименование на латынском'
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descRus',
                            required: true,
                            label: "Упражнения на русском",
                            labelPosition: "top"
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descKaz',
                            required: true,
                            label: "Упражнения на казахском",
                            labelPosition: "top"
                        },
                        {
                            view: "richtext",
                            height: 300,
                            name: 'descLan',
                            required: true,
                            label: "Упражнения на латынском",
                            labelPosition: "top"
                        },
                        {
                            cols: [
                                {
                                    css: "noBorder",
                                    width: 190,
                                    height: 40,
                                    template: '<button type="button" onclick="addDataToAudio(' + lessonId + ')" class="btn btn-success">Сохранить</button>'
                                },
                                {
                                    css: "noBorder",
                                    width: 190,
                                    height: 40,
                                    template: '<button type="button" onclick="clearFormByid(\'videoView\')" class="btn btn-danger">Очистить</button>'
                                }
                            ]
                        },
                        {
                            id: 'avaRows',
                            cols: []
                        }
                    ]
                }
            ]
        }
    );
    loadData(1);
    addProfilePhotoBlock();
}

function addDataToAudio(paramId) {
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/addAudioLessonByPart', 'POST',
            {
                paramId: $$('parentId').getValue(),
                link: $$('audioView').elements['link'].getValue(),
                nameRus: $$('audioView').elements['nameRus'].getValue(),
                nameKaz: $$('audioView').elements['nameKaz'].getValue(),
                nameLan: $$('audioView').elements['nameLan'].getValue(),
                descRus: $$('audioView').elements['descRus'].getValue(),
                descKaz: $$('audioView').elements['descRus'].getValue(),
                descLan: $$('audioView').elements['descRus'].getValue()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('audioView');
                    $$('lessonId').setValue(gson.message);
                    notifyMessage("Сохранено", "Сохранено", notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
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
    $$('avaRows').removeView('ava');
    var avatar;
    if (!ava) {
        avatar = '/study/plugin/img/ava4.png';
    } else {
        avatar = ava;
    }
    $$('avaRows').addView(
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
                    view: 'text',
                    label: 'Ссылка на картинку',
                    id: 'audioLink'
                },
                {
                    cols: [
                        {
                            view: 'text',
                            hidden: true,
                            id: 'lessonId'
                        },
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
    )
}