$(document).ready(function () {
    form_init();
});

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        rows: [
            {
                view: 'label',
                value: getResourceName("menu.analize"),
                css: 'mainAnalizeTitle'
            },
            {height: 40},
            {
                height: 270,
                cols: [
                    {
                        view: "textarea",
                        labelAlign: "right",
                        id: 'analizeTxtArea',
                        required: true,
                        height: 150
                    }
                ]
            },
            {height: 20},
            {
                cols: [
                    {},
                    {
                        css: "noBorder",
                        id: "btnStartAnalize",
                        params: 1,
                        width: 190,
                        height: 40,
                        template: '<button type="button" onclick="btnTypeRec(1)" class="btn btn-primary">' + getResourceName('btn.lbl.check') + '</button>'
                    },
                    {
                        css: "noBorder",
                        id: "btnClearAnalize",
                        params: 2,
                        width: 180,
                        template: '<button type="button" onclick="btnTypeRec(2)" class="btn btn-danger">' + getResourceName('menu.clear.btn') + '</button>'
                    },
                    {}
                ]
            }
        ]
    });
}

function btnTypeRec(id) {
    switch (id) {
        case 1:
            getAnalizeResult();
            break;
        case 2:
            $$('analizeTxtArea').setValue("");
            break;
    }
}

function getAnalizeResult() {
    var text = $$('analizeTxtArea').getValue();
    if (!isNullOrEmpty(text)) {
        get_ajax('/study/wr/anal/getAnalize', 'GET', {text: text}, function (gson) {
            if (gson && !gson.result) {
                notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
            } else {
                openWindowToAnalize(gson.message.symantic.message, gson.message.morph.message);
            }
        });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function openWindowToAnalize(json, morphJson) {

    var cont_h = $(window).height();
    var cont_w = $(window).width();
    if (!$$('analWin')) {
        webix.ui({
            view: "window",
            id: "analWin",
            modal: true,
            scroll: true,
            position: "center",
            height: cont_h,
            width: cont_w,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            head: {
                cols: [
                    {},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {},
                            {
                                view: "icon", icon: "times", css: 'buttonIconRed',
                                click: function () {
                                    this.getTopParentView().hide();
                                    window.onscroll = null;
                                    $$('analizeTable').clearAll();
                                    $$('analizeRepeatTable').clearAll();
                                    $$('synonymDataTable').clearAll();
                                    $$('antonymDataTable').clearAll();
                                }
                            },
                            {width: 5}
                        ]
                    }
                ]
            },
            body: {
                rows: [
                    {
                        view: "accordion",
                        multi: true,
                        scroll: true,
                        css: 'mainAccordion',
                        margin: 20,
                        rows: [
                            {
                                view: "accordionitem",
                                header: "<span class='webix_icon fa-user'></span><span class='wordStyle'>" + getResourceName('main.txt.stat') + "</span>",
                                autoheight: true,
                                collapsed: true,
                                body: {
                                    rows: [
                                        {
                                            view: "accordion",
                                            rows: [
                                                {
                                                    height: 80,
                                                    cols: [
                                                        {},
                                                        {
                                                            width: 850,
                                                            view: "datatable",
                                                            id: 'analizeTable',
                                                            scroll: false,
                                                            columns: [
                                                                {
                                                                    id: "mLength",
                                                                    header: getResourceName("anal.s.count"),
                                                                    width: 200
                                                                },
                                                                {
                                                                    id: "woutMLength",
                                                                    header: getResourceName("anal.s.space.count"),
                                                                    width: 200
                                                                },
                                                                {
                                                                    id: "wordCount",
                                                                    header: getResourceName("anal.w.count"),
                                                                    width: 200
                                                                },
                                                                {
                                                                    id: "uniqueWordCount",
                                                                    header: getResourceName("anal.w.id.count"),
                                                                    fillspace: 1
                                                                }
                                                            ]
                                                        },
                                                        {}
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        view: "accordion",
                        multi: true,
                        scroll: true,
                        css: 'mainAccordion',
                        margin: 20,
                        rows: [
                            {
                                view: "accordionitem",
                                header: "<span class='webix_icon fa-user'></span><span class='wordStyle'>" + getResourceName('anal.w.id.main.label') + "</span>",
                                autoheight: true,
                                collapsed: true,
                                body: {
                                    rows: [
                                        {
                                            view: "accordion",
                                            rows: [
                                                {
                                                    height: 500,
                                                    cols: [
                                                        {},
                                                        {
                                                            width: 850,
                                                            view: "datatable",
                                                            id: 'analizeRepeatTable',
                                                            scroll: true,
                                                            columns: [
                                                                {
                                                                    id: "value",
                                                                    header: getResourceName("words"),
                                                                    fillspace: 1
                                                                },
                                                                {
                                                                    id: "addValue",
                                                                    header: getResourceName("counts"),
                                                                    width: 200
                                                                }
                                                            ]
                                                        },
                                                        {}
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        view: "accordion",
                        multi: true,
                        css: 'mainAccordion',
                        margin: 20,
                        rows: [
                            {
                                view: "accordionitem",
                                header: "<span class='webix_icon fa-user'></span><span class='wordStyle'>" + getResourceName('main.antonym') + "</span>",
                                autoheight: true,
                                collapsed: true,
                                body: {
                                    rows: [
                                        {
                                            view: "accordion",
                                            rows: [
                                                {
                                                    width: 850,
                                                    view: 'label',
                                                    id: 'antlabel',
                                                    css: 'wordStyle'
                                                },
                                                {
                                                    width: 850,
                                                    view: "datatable",
                                                    id: 'antonymDataTable',
                                                    scroll: true,
                                                    columns: [
                                                        {
                                                            id: "value",
                                                            header: getResourceName("words"),
                                                            fillspace: 1
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        view: "accordion",
                        multi: true,
                        css: 'mainAccordion',
                        margin: 20,
                        rows: [
                            {
                                view: "accordionitem",
                                header: "<span class='webix_icon fa-user'></span><span class='wordStyle'>" + getResourceName('main.synonym') + "</span>",
                                autoheight: true,
                                collapsed: true,
                                body: {
                                    rows: [
                                        {
                                            view: "accordion",
                                            rows: [
                                                {
                                                    width: 850,
                                                    view: 'label',
                                                    id: 'synlabel',
                                                    css: 'wordStyle'
                                                },
                                                {
                                                    width: 850,
                                                    view: "datatable",
                                                    id: 'synonymDataTable',
                                                    scroll: true,
                                                    columns: [
                                                        {
                                                            id: "value",
                                                            header: getResourceName("words"),
                                                            fillspace: 1
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        view: "accordion",
                        multi: true,
                        scroll: true,
                        css: 'mainAccordion',
                        margin: 20,
                        rows: [
                            {
                                view: "accordionitem",
                                header: "<span class='webix_icon fa-user'></span><span class='wordStyle'>Морфологический разбор</span>",
                                autoheight: true,
                                scroll: true,
                                collapsed: true,
                                body: {
                                    rows: [
                                        {
                                            view: "accordion",
                                            scroll: true,
                                            rows: [
                                                {
                                                    view: "scrollview",
                                                    id: "scrollview",
                                                    scroll: "y",
                                                    height: 400,
                                                    width: 150,
                                                    body: {
                                                        rows: [
                                                            {
                                                                width: 1200,
                                                                view: 'label',
                                                                id: 'morphLabel',
                                                                css: 'wordStyle'
                                                            },
                                                            {
                                                                cols: [
                                                                    {
                                                                        view: 'label',
                                                                        id: 'sentenceType',
                                                                        css: 'wordStyle'
                                                                    },
                                                                    {
                                                                        view: 'label',
                                                                        id: 'aLetter'
                                                                    },
                                                                    {
                                                                        view: 'label',
                                                                        id: 'bLetter'
                                                                    }
                                                                ]
                                                            },
                                                            {
                                                                view: 'label',
                                                                label: 'Есімдік',
                                                                css: 'wordStyle'
                                                            },
                                                            {
                                                                id: 'esimdikList',
                                                                cols: []
                                                            },
                                                            {
                                                                view: 'label',
                                                                label: 'Қалау',
                                                                css: 'wordStyle'
                                                            },
                                                            {
                                                                id: 'qalauList',
                                                                cols: []
                                                            },
                                                            {
                                                                view: 'label',
                                                                label: 'Тұйық',
                                                                css: 'wordStyle'
                                                            },
                                                            {
                                                                id: 'tuyqList',
                                                                cols: []
                                                            },
                                                            {
                                                                view: 'label',
                                                                label: 'Негiзгi талдау',
                                                                css: 'wordStyle'
                                                            },
                                                            {
                                                                id: 'mainAnalize',
                                                                cols: []
                                                            }
                                                        ]
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        ]
                    }
                ]
            }
        }).hide();
    }
    $$('analWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };

    $$('analizeTable').parse(json);

    var items = [];
    for (var n in json.repeatedWord) {
        var object = {};
        object['value'] = n;
        object['addValue'] = json.repeatedWord[n];
        items.push(object)
    }
    $$('analizeRepeatTable').parse(items);

    $$('antlabel').setValue(morphJson.antonym.antonymTitle);
    $$('synlabel').setValue(morphJson.synonym.synonymTitle);

    if (morphJson.antonym.antonymResult) {
        var items2 = [];
        morphJson.antonym.antonymResult.forEach(function (e) {
            var object2 = {};
            object2['value'] = e;
            items2.push(object2)
        });

        $$('antonymDataTable').parse(items2);
    }
    if (morphJson.synonym.synonymResult) {
        var items3 = [];
        morphJson.synonym.synonymResult.forEach(function (e) {
            var object3 = {};
            if (e != null) {
                object3['value'] = e;
            } else {
                object3['value'] = "Сөйлемде 0 синоним табылды :";
            }

            items3.push(object3)
        });

        $$('synonymDataTable').parse(items3);
    }

    $$('morphLabel').setValue(morphJson.mainAnalyze.qLabel);
    $$('bLetter').setValue(morphJson.mainAnalyze.consonant);
    $$('aLetter').setValue(morphJson.mainAnalyze.vowel);
    $$('sentenceType').setValue(morphJson.mainAnalyze.sentenceType);

    $$('esimdikList').removeView('esimdikListV');
    $$("esimdikList").addView({
        id: 'esimdikListV',
        rows: []
    });
    morphJson.mainAnalyze.esimdikList.forEach(function (e) {
        $$('esimdikListV').addView(
            {
                rows: [
                    {
                        view: 'label',
                        label: e
                    }
                ]
            }
        );
    });
    $$('qalauList').removeView('qalauListV');
    $$("qalauList").addView({
        id: 'qalauListV',
        rows: []
    });
    morphJson.mainAnalyze.qalauList.forEach(function (e) {
        $$('qalauListV').addView(
            {
                rows: [
                    {
                        view: 'label',
                        label: e
                    }
                ]
            }
        );
    });
    $$('tuyqList').removeView('tuyqListV');
    $$("tuyqList").addView({
        id: 'tuyqListV',
        rows: []
    });
    morphJson.mainAnalyze.tuyqList.forEach(function (e) {
        $$('tuyqListV').addView(
            {
                rows: [
                    {
                        view: 'label',
                        label: e
                    }
                ]
            }
        );
    });
    $$('mainAnalize').removeView('mainAnalizeV');
    $$("mainAnalize").addView({
        id: 'mainAnalizeV',
        rows: []
    });
    morphJson.mainAnalyze.mainAnalize.forEach(function (e) {
        $$('mainAnalizeV').addView(
            {
                rows: [
                    {
                        view: 'label',
                        label: e
                    }
                ]
            }
        );
    });
}