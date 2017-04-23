$(document).ready(function () {
    form_init();
});

function form_init() {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        css: 'blueW',
        height: 600,
        rows: [
            {
                cols: [
                    {},
                    {
                        width: 500,
                        view: 'label',
                        id: 'mainAnalizeTitle',
                        css: 'mainAnalizeTitle'
                    },
                    {}
                ]
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
                        template: '<button type="button" onclick="btnTypeRec(1)" class="btn btn-primary">'+getResourceName('btn.lbl.check')+'</button>'
                    },
                    {
                        css: "noBorder",
                        id: "btnClearAnalize",
                        params: 2,
                        width: 180,
                        template: '<button type="button" onclick="btnTypeRec(2)" class="btn btn-danger">'+getResourceName('menu.clear.btn')+'</button>'
                    },
                    {}
                ]
            }
        ]
    });

    setTitle();
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

function setTitle() {
    var title ="";
    switch (getLocalStorage("analize")) {
        case "1":
            title = getResourceName('menu.analize.sem');
            break;
        case "2":
            title = getResourceName('menu.analize.morph');
            break;
        case "3":
            title = getResourceName('menu.analize.syntax');
            break;
        case "4":
            title = getResourceName('menu.analize.lex');
            break;
    }
    $$('mainAnalizeTitle').setValue(title);
}

function getAnalizeResult() {
    var id = getLocalStorage("analize");
    var text = $$('analizeTxtArea').getValue();
    if (!isNullOrEmpty(text)) {
        get_ajax('/study/wr/anal/getAnalize', 'GET', {id: id, text: text}, function (gson) {
            if (gson && !gson.result) {
                notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
            } else {
                openWindowToAnalize(gson.message);
            }
        });
    } else {
        notifyMessage(getResourceName("error.txt"), getResourceName("error.txt.mess"), notifyType.danger)
    }
}

function openWindowToAnalize(json) {

    var cont_h = $(window).height() - 70;
    if (!$$('analWin')) {
        webix.ui({
            view: "window",
            id: "analWin",
            modal: true,
            scroll: true,
            position: "center",
            height: cont_h,
            width: 900,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            head: {
                cols: [
                    {width: 10},
                    {view: 'label', label: getResourceName('main.txt.stat'), css: 'windowLabel'},
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
                        height: 80,
                        cols: [
                            {},
                            {
                                width: 850,
                                view: "datatable",
                                id: 'analizeTable',
                                scroll: false,
                                columns: [
                                    {id: "mLength", header: getResourceName("anal.s.count"), width: 200},
                                    {id: "woutMLength", header: getResourceName("anal.s.space.count"), width: 200},
                                    {id: "wordCount", header: getResourceName("anal.w.count"), width: 200},
                                    {id: "uniqueWordCount", header: getResourceName("anal.w.id.count"), fillspace: 1}
                                ]
                            },
                            {}
                        ]
                    },
                    {
                        cols: [
                            {},
                            {
                                width: 850,
                                view: 'label',
                                css: 'wordStyle',
                                label: getResourceName('anal.w.id.main.label')
                            },
                            {}
                        ]
                    },
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
                                    {id: "value", header: getResourceName("words"), fillspace: 1},
                                    {id: "addValue", header: getResourceName("counts"), width: 200}
                                ]
                            },
                            {}
                        ]
                    },
                    {height: 30}
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
    for(var n in json.repeatedWord) {
        var object = {};
        object['value'] = n;
        object['addValue'] = json.repeatedWord[n];
        items.push(object)
    }
    $$('analizeRepeatTable').parse(items);
}