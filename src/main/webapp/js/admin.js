$(document).ready(function () {
    locale();
    form_init();
});

var foldersTreeMenu = [
    {id: "LESSON", icon: "folder-open-o", value: "Добавить урок к уровням"},
    {id: "VIDEO", icon: " fa fa-youtube", value: "Добавить видео к уровням"},
    {id: "GRAMMAR", icon: "floppy-o", value: "Добавить грамматику к уровням"},
    {id: "AUDIO", icon: " fa fa-file-audio-o", value: "Добавить аудио к уровням"},
    {id: "EDITLESSON", icon: "folder-open-o", value: "Изменить урок к уровням"},
    {id: "EDITVIDEO", icon: " fa fa-youtube", value: "Изменить видео к уровням"},
    {id: "EDITGRAMMAR", icon: "floppy-o", value: "Изменить грамматику к уровням"},
    {id: "EDITAUDIO", icon: " fa fa-file-audio-o", value: "Изменить аудио к уровням"},
    {id: "ANTO", icon: " fa fa-bullhorn", value: "Антонимы"},
    {id: "SYN", icon: " fa fa-free-code-camp", value: "Синонимы"},
    {id: "GAMEW", icon: " fa fa-gamepad", value: "Игра слов"}
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

function adViewAddView() {
    $$("addSomeThink").addView({
        id: 'addMainVIew',
        rows: []
    });
}

function reViewRemove() {
    $$('addSomeThink').removeView('addMainVIew');
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
        case "EDITLESSON":
            editOrDropFunction(1);
            break;
        case "EDITVIDEO":
            editOrDropFunction(2);
            break;
        case "EDITGRAMMAR":
            editOrDropFunction(3);
            break;
        case "EDITAUDIO":
            editOrDropFunction(4);
            break;
        case "ANTO":
            addSynOrAnto(1);
            break;
        case "SYN":
            addSynOrAnto(2);
            break;
        case "GAMEW":
            addLessonType(9);
            break;
    }
}

function addSynOrAnto(param) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
        {
            id: 'antView',
            width: 800,
            view: 'form',
            elementsConfig: {
                labelPosition: "top"
            },
            rows: [
                {
                    view: 'text',
                    required: true,
                    name: 'value',
                    label: 'Первое значение'
                },
                {
                    view: 'text',
                    required: true,
                    name: 'secValue',
                    label: 'Второе значение'
                },
                {
                    cols: [
                        {
                            view: "button",
                            value: 'Сохранить',
                            width: 190,
                            on: {
                                onItemClick: function () {
                                    addDataAntOrSynonym(param);
                                }
                            }
                        },
                        {
                            view: "button",
                            value: 'Очистить',
                            width: 190,
                            on: {
                                onItemClick: function () {
                                    clearFormByid('antView');
                                }
                            }
                        }
                    ]
                }
            ]
        }
    );
}

function addDataAntOrSynonym(param) {
    if (!isNullOrEmpty(param)) {
        get_ajax('/study/wr/admin/addDataAntOrSynonym', 'POST',
            {
                paramId: param,
                nameRus: $$('antView').elements['value'].getValue(),
                nameKaz: $$('antView').elements['secValue'].getValue()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('antView');
                    notifyMessage(gson.message, gson.message, notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function editOrDropFunction(paramId) {
    reViewRemove();
    adViewAddView();
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/editAdmin', 'GET', {param: paramId}, function (gson) {
            if (gson && !gson.result) {
                notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
            } else {
                gson.message.forEach(function (e) {
                    $$('addMainVIew').addView(
                        {
                            id: 'editContent',
                            rows: [
                                {
                                    view: 'label',
                                    template: '<a>' + e.id + '   ' + e.nameRus + '</a>',
                                    on: {
                                        onItemClick: function () {
                                            switch (paramId) {
                                                case 1:
                                                    addViewLessonToLesson(e.parentId);
                                                    $$('lessonView').parse(e);
                                                    break;
                                                case 2:
                                                    addViewVideoToLesson(e.paramId);
                                                    $$('videoView').parse(e);
                                                    CKEDITOR.instances.descRus.setData(e.descRus);
                                                    CKEDITOR.instances.descKaz.setData(e.descKaz);
                                                    CKEDITOR.instances.descLan.setData(e.descLan);
                                                    break;
                                                case 3:
                                                    addViewGrammarToLesson(e.paramId);
                                                    $$('grammarView').parse(e);
                                                    CKEDITOR.instances.descRus.setData(e.descRus);
                                                    CKEDITOR.instances.descKaz.setData(e.descKaz);
                                                    CKEDITOR.instances.descLan.setData(e.descLan);
                                                    break;
                                            }
                                        }
                                    }
                                }
                            ]
                        }
                    );
                });
            }
        });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function addLessonType(paramId) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
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
            addViewGrammarToLesson(btnId);
            break;
        case 4:
            addViewAudioToLesson(btnId);
            break;
        case 9:
            addViewGameWToLesson(btnId);
            break;
    }
}

function addViewLessonToLesson(lessonId) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
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
                    hidden: true,
                    name: 'id'
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
                id: $$('lessonView').elements['id'].getValue(),
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
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
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
                                onChange: function () {
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
                            hidden: true,
                            name: 'id'
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
                        {view: 'label', label: "Упражнения на русском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descRus',
                            id: 'descRus',
                            template: '<div id="descRus"></div>'
                        },
                        {view: 'label', label: "Упражнения на казахском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descKaz',
                            id: 'descKaz',
                            template: '<div id="descKaz"></div>'
                        },
                        {view: 'label', label: "Упражнения на латынском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descLan',
                            id: 'descLan',
                            template: '<div id="descLan"></div>'
                        },
                        {
                            cols: [
                                {
                                    view: "button",
                                    value: 'Сохранить',
                                    width: 190,
                                    on: {
                                        onItemClick: function () {
                                            addDataToVideo(lessonId);
                                        }
                                    }
                                },
                                {
                                    view: "button",
                                    value: 'Очистить',
                                    width: 190,
                                    on: {
                                        onItemClick: function () {
                                            clearFormByid('videoView');
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    );
    initSample();
    initSample2();
    initSample3();
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
                id: $$('videoView').elements['id'].getValue(),
                link: $$('videoView').elements['link'].getValue(),
                nameRus: $$('videoView').elements['nameRus'].getValue(),
                nameKaz: $$('videoView').elements['nameKaz'].getValue(),
                nameLan: $$('videoView').elements['nameLan'].getValue(),
                descRus: CKEDITOR.instances.descRus.getData(),
                descKaz: CKEDITOR.instances.descKaz.getData(),
                descLan: CKEDITOR.instances.descLan.getData()
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

function addViewGrammarToLesson(lessonId) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
        {
            id: 'grammarMain',
            rows: [
                {
                    view: 'label',
                    label: 'Добавить грамматику к уроку'
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
                                    $$('grammarView').enable();
                                }
                            }
                        }
                    ]
                },
                {
                    id: 'grammarView',
                    width: 800,
                    disabled: true,
                    view: 'form',
                    elementsConfig: {
                        labelPosition: "top"
                    },
                    rows: [
                        {
                            view: 'text',
                            hidden: true,
                            name: 'id'
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
                        {view: 'label', label: "Упражнения на русском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descRus',
                            id: 'descRus',
                            template: '<div id="descRus"></div>'
                        },
                        {view: 'label', label: "Упражнения на казахском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descKaz',
                            id: 'descKaz',
                            template: '<div id="descKaz"></div>'
                        },
                        {view: 'label', label: "Упражнения на латынском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descLan',
                            id: 'descLan',
                            template: '<div id="descLan"></div>'
                        },
                        {
                            cols: [
                                {
                                    view: "button",
                                    value: 'Сохранить',
                                    width: 190,
                                    on: {
                                        onItemClick: function () {
                                            addDataToGrammar(lessonId);
                                        }
                                    }
                                },
                                {
                                    view: "button",
                                    value: 'Очистить',
                                    width: 190,
                                    on: {
                                        onItemClick: function () {
                                            clearFormByid('videoView');
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    );
    initSample();
    initSample2();
    initSample3();
    loadData(1);
}

function addDataToGrammar(paramId) {
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/addGrammarLessonByPart', 'POST',
            {
                paramId: $$('parentId').getValue(),
                id: $$('grammarView').elements['id'].getValue(),
                nameRus: $$('grammarView').elements['nameRus'].getValue(),
                nameKaz: $$('grammarView').elements['nameKaz'].getValue(),
                nameLan: $$('grammarView').elements['nameLan'].getValue(),
                descRus: CKEDITOR.instances.descRus.getData(),
                descKaz: CKEDITOR.instances.descKaz.getData(),
                descLan: CKEDITOR.instances.descLan.getData()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('grammarView');
                    notifyMessage("Сохранено", "Сохранено", notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function addViewAudioToLesson(lessonId) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
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
                        {view: 'label', label: "Упражнения на русском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descRus',
                            id: 'descRus',
                            template: '<div id="descRus"></div>'
                        },
                        {view: 'label', label: "Упражнения на казахском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descKaz',
                            id: 'descKaz',
                            template: '<div id="descKaz"></div>'
                        },
                        {view: 'label', label: "Упражнения на латынском"},
                        {
                            view: "template",
                            height: 300,
                            name: 'descLan',
                            id: 'descLan',
                            template: '<div id="descLan"></div>'
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
    initSample();
    initSample2();
    initSample3();
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
                descRus: CKEDITOR.instances.descRus.getData(),
                descKaz: CKEDITOR.instances.descKaz.getData(),
                descLan: CKEDITOR.instances.descLan.getData()
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

function addViewGameWToLesson(lessonId) {
    reViewRemove();
    adViewAddView();
    $$('addMainVIew').addView(
        {
            id: 'gameView',
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
                                    $$('gameForm').enable();
                                }
                            }
                        }
                    ]
                },
                {
                    id: 'gameForm',
                    width: 800,
                    disabled: true,
                    view: 'form',
                    elementsConfig: {
                        labelPosition: "top"
                    },
                    rows: [
                        {view: 'text', name: 'question', label: 'Основной вопрос', required: true},
                        {
                            cols: [
                                {view: 'text', name: 'var1', label: 'Вариант 1', required: true},
                                {
                                    view: "checkbox",
                                    name: "ch_var1",
                                    id: "ch_var1",
                                    label: "Отметить как правильный",
                                    value: 0,
                                    on: {
                                        onItemClick: function () {
                                            if (this.getValue() == 1) {
                                                $$("ch_var2").setValue(0);
                                                $$("ch_var3").setValue(0);
                                                $$("ch_var4").setValue(0);
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            cols: [
                                {view: 'text', name: 'var2', label: 'Вариант 2', required: true},
                                {
                                    view: "checkbox",
                                    name: "ch_var2",
                                    id: "ch_var2",
                                    label: "Отметить как правильный",
                                    value: 0,
                                    on: {
                                        onItemClick: function () {
                                            if (this.getValue() == 1) {
                                                $$("ch_var1").setValue(0);
                                                $$("ch_var3").setValue(0);
                                                $$("ch_var4").setValue(0);
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            cols: [
                                {view: 'text', name: 'var3', label: 'Вариант 3', required: true},
                                {
                                    view: "checkbox",
                                    name: "ch_var3",
                                    id: "ch_var3",
                                    label: "Отметить как правильный",
                                    value: 0,
                                    on: {
                                        onItemClick: function () {
                                            if (this.getValue() == 1) {
                                                $$("ch_var2").setValue(0);
                                                $$("ch_var1").setValue(0);
                                                $$("ch_var4").setValue(0);
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            cols: [
                                {view: 'text', name: 'var4', label: 'Вариант 4', required: true},
                                {
                                    view: "checkbox",
                                    name: "ch_var4",
                                    id: "ch_var4",
                                    label: "Отметить как правильный",
                                    value: 0,
                                    on: {
                                        onItemClick: function () {
                                            if (this.getValue() == 1) {
                                                $$("ch_var2").setValue(0);
                                                $$("ch_var3").setValue(0);
                                                $$("ch_var1").setValue(0);
                                            }
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            cols: [
                                {
                                    css: "noBorder",
                                    width: 190,
                                    height: 40,
                                    template: '<button type="button" onclick="saveViewGameWToLesson(' + lessonId + ')" class="btn btn-success">Сохранить</button>'
                                },
                                {
                                    css: "noBorder",
                                    width: 190,
                                    height: 40,
                                    template: '<button type="button" onclick="clearFormByid(\'gameForm\')" class="btn btn-danger">Очистить</button>'
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
}

function saveViewGameWToLesson(paramId) {
    var form = $$('gameForm');
    if (!isNullOrEmpty(paramId)) {
        get_ajax('/study/wr/admin/addDataDGameW', 'POST',
            {
                paramId: $$('parentId').getValue(),
                question: form.elements['question'].getValue(),
                var1: form.elements['var1'].getValue(),
                ch_var1: form.elements['ch_var1'].getValue(),
                var2: form.elements['var2'].getValue(),
                ch_var2: form.elements['ch_var2'].getValue(),
                var3: form.elements['var3'].getValue(),
                ch_var3: form.elements['ch_var3'].getValue(),
                var4: form.elements['var4'].getValue(),
                ch_var4: form.elements['ch_var4'].getValue()
            }, function (gson) {
                if (gson && !gson.result) {
                    notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
                } else {
                    clearFormByid('gameForm');
                    notifyMessage("Сохранено", "Сохранено", notifyType.info)
                }
            });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}