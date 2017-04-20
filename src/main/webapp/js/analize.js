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
                        height: 150,
                        on: {
                            onChange: function (newValue) {
                                var v = "" + newValue;
                                console.log(v.length)
                            },
                            onTimedKeyPress: function (newValue) {
                                var v = "" + newValue;
                                console.log(v.length)
                            }
                        }
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
                        template: '<button type="button" onclick="btnTypeRec(1)" class="btn btn-primary">Тексеру</button>'
                    },
                    {
                        css: "noBorder",
                        id: "btnClearAnalize",
                        params: 2,
                        width: 180,
                        template: '<button type="button" onclick="btnTypeRec(2)" class="btn btn-danger">Тазарту</button>'
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
            title = "Семантикалық талдау";
            break;
        case "2":
            title = "Морфологиялық талдау";
            break;
        case "3":
            title = "Синтаксистық талдау";
            break;
        case "4":
            title = "Лексикалық талдау";
            break;
        default:
            title = "Семантикалық талдау";
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
                notifyMessage("қате", "Сіз мәтінді енгізбедініз", notifyType.danger)
            } else {
                openWindowToAnalize(gson.message);
            }
        });
    } else {
        notifyMessage("қате", "Сіз мәтінді енгізбедініз", notifyType.danger)
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
                    {view: 'label', label: 'Текст статистикасы', css: 'windowLabel'},
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
                                    {id: "mLength", header: "таңбалар саны", width: 200},
                                    {id: "woutMLength", header: "Пробелсыз таңбалар саны", width: 200},
                                    {id: "wordCount", header: "сөздердің саны", width: 200},
                                    {id: "uniqueWordCount", header: "Бірегей сөздердің саны", fillspace: 1}
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
                                label: 'Сөздер мен олардың аталған рет саны'
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
                                    {id: "value", header: "сөздер", fillspace: 1},
                                    {id: "addValue", header: "Саны", width: 200}
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