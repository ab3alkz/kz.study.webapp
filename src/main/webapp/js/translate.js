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
                        css: 'mainAnalizeTitle',
                        label: "Қазақша-латынша аудармашы"
                    },
                    {}
                ]
            },
            {height: 40},
            {
                cols: [
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
                    {
                        height: 270,
                        cols: [
                            {
                                view: "textarea",
                                labelAlign: "right",
                                id: 'resultAre',
                                readonly: true,
                                height: 150
                            }
                        ]
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
                        template: '<button type="button" onclick="btnTypeRec(1)" class="btn btn-primary">Аудару</button>'
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

 }

function btnTypeRec(id) {
    switch (id) {
        case 1:
            getTranslateResult();
            break;
        case 2:
            $$('analizeTxtArea').setValue("");
            $$('resultAre').setValue("");
            break;
    }
}


function getTranslateResult() {
    var text = $$('analizeTxtArea').getValue();
    if (!isNullOrEmpty(text)) {
        get_ajax('/study/wr/anal/getTranslate', 'GET', {text: text}, function (gson) {
            if (gson && !gson.result) {
                notifyMessage("қате", "Сіз мәтінді енгізбедініз", notifyType.danger)
            } else {
                $$('resultAre').setValue(gson.message);
            }
        });
    } else {
        notifyMessage("қате", "Сіз мәтінді енгізбедініз", notifyType.danger)
    }
}