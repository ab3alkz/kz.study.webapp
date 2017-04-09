$(document).ready(function () {
    load();
});

function load() {
    form_init();
}

function form_init() {
    webix.ready(function () {
        createLoginForm();
        startTest("fillWords", {
            id: "fillWords",
            name: "Сөздерді толықтыр"
        });
    });
}

function createLoginForm() {
    webix.ui({
        id: "loginForm",
        container: "loginForm",
        view: "form",
        borderless: true,
        elementsConfig: {
            labelPosition: "left",
            labelWidth: 100,
            width: 300
        },
        elements: [
            {
                cols: [
                    {
                        rows: [
                            {
                                view: "label",
                                label: "<h3 style='margin: 0;padding: 0'>Тегін тіркелу</h3>",
                                height: 80
                            },
                            {
                                view: "text",
                                name: "uname",
                                label: "Логин"
                            },
                            {
                                view: "text",
                                name: "password",
                                label: "Құпия сөз",
                                type: "password"
                            }, {
                                height: 20
                            }, {
                                cols: [
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button style='width: 145px;' class='btn btn-success'>Кіру</button>"
                                    },
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button style='width: 145px;'  class='btn btn-primary'>Тіркелу</button>"
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    });

}

function getTestTypeList() {
    get_ajax('/study/wr/app/getTestTypeList', 'GET', null, function (gson) {
        if (gson)
            viewTestTypeListWin(gson);
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function viewTestTypeListWin(gson) {
    if (!$$('viewTestTypeListWin')) {
        webix.ui({
            view: "window",
            id: "viewTestTypeListWin",
            modal: true,
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Сынақты таңдаңыз"},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "fa fa-times", css: "buttonIcon",
                                click: function () {
                                    this.getTopParentView().close();
                                    window.onscroll = null;
                                }
                            }
                        ]
                    }
                ]
            },
            body: {
                rows: [
                    {
                        height: 20
                    },
                    {
                        view: "datatable",
                        css: "appTable",
                        id: "viewTestTypeListTable",
                        scroll: false,
                        header: false,
                        footer: false,
                        rowHeight: 45,
                        select: 'row',
                        columns: [
                            {id: "startBtn", header: " ", width: 120},
                            {
                                template: "<b style='font-size: 16px; color:#317eac'>#name#</b>",
                                header: " ",
                                fillspace: 1
                            }
                        ],
                        scheme: {
                            $init: function (obj) {
                                obj.startBtn = "<button style='width:100px;'  class='startTest btn btn-primary'>Бастау</button>";
                            }
                        },
                        onClick: {
                            startTest: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    startTest(item.row, obj);
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            }
                        }
                    }
                ]
            }
        }).hide();
    }
    $$("viewTestTypeListTable").parse(gson);
    $$('viewTestTypeListWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function startTest(id, item) {
    console.log(id);
    $("#mainContainer").hide();
    switch (id) {
        case "fillWords":
            createFillWordsContainer(id, item);
            break;
        default:
            createDefContainer(id, item);
    }

}


function createDefContainer(id, item) {
    webix.ui(
        {
            id: "defContainer",
            container: "defContainer",
            rows: [
                {
                    view: 'label',
                    height: 60,
                    label: "<h2>" + item.name + "</h2>"
                }
            ]
        }
    );
}

function createFillWordsContainer(id, item) {
    webix.ui(
        {
            id: "fillWordsContainer",
            container: "fillWordsContainer",
            rows: [
                {
                    view: 'label',
                    height: 60,
                    label: "<h2>" + item.name + "</h2>"
                }, {
                    height: 50
                }
            ]
        }
    );
    startFillWords();
}
