$(document).ready(function () {
    load();
});

function load() {
    form_init();
}

window.onresize = function (e) {
    slideEditWinResize();
}

function form_init() {
    webix.ready(function () {
        createLoginForm();
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
        hideAppProgress();
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
                        columns: [
                            {id: "startBtn", header: " ", width: 120},
                            {template: "<b style='font-size: 16px; color:#317eac'>#name#</b>", header: " ", fillspace: 1}
                        ],
                        scheme: {
                            $init: function (obj) {
                                obj.startBtn = "<button style='width:100px;'  class='startTest btn btn-primary'>Бастау</button>";
                            }
                        },
                        onClick: {
                            startTest: function (e, item, cell) {
                                startTest(item.row);
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

function startTest(id) {
    console.log(id)
}
