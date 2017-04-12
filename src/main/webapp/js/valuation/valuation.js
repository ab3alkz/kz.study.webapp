$(document).ready(function () {
    load();
});

function load() {
    form_init();
}

function form_init() {
    webix.ready(function () {
        if (!isNullOrEmpty(getURLParameters("myuser"))) {
            myuser = getURLParameters("myuser");
        }
        if (isNullOrEmpty(myuser)) {
            createLoginForm();
        } else {
            authSuccess();
        }
        gameResultContainerCreate();
        // startTest("fillWords", {
        //     id: "fillWords",
        //     name: "Сөздерді толықтыр"
        // });
    });
}

function gameResultContainerCreate() {
    $('#gameResultContainerWrapper').show();
    webix.ui(
        {
            id: "gameResultTable",
            container:'gameResultContainerWrapper',
            view: "datatable",
            // css: "sSListPersonTable",
            resizeColumn: true,
            autoheight: true,
            minHeight: 400,
            scroll: true,
            url: '/study/wr/app/getGameResultList',
            tooltip: true,
            rowLineHeight: 30,
            columns: [
                {id: "uName", header: " ", width: 100},
                {id: "gameId", header: " ", width: 120},
                {id: "result", header: " ", width: 120,
                    tooltip:"#info#"}

            ],
            select: "row",
            datafetch: 12,
            pager: {
                container: "gameResultTablePaging",
                size: 10,
                group: 15
            },
            scheme: {
                $init: function (obj) {
                    //  obj.action = "<span onclick=\"suspendWin(" + obj.sicid + ", " + obj.osn + ")\"   class='btn btn-danger' style='width: 70px;padding: 2px;margin-bottom:3px'>Приост.</span>"
                }
            },
            on: {
                onAfterLoad: function () {
                    this.enable();
                    $$("gameResultTablePaging").enable();
                    this.hideProgress();
                    this.hideOverlay();
                    if (!this.count())
                        this.showOverlay("<span class='no_data_found'>" + getResourceName('no.data.found') + "</span>");
                },
                onBeforeLoad: function () {
                    this.disable();
                    $$("gameResultTablePaging").disable();
                    webix.extend($$("gameResultTable"), webix.ProgressBar);
                    this.showProgress();
                }
            }
        });
}

function createLoginForm() {

    $('#userInfo').hide();
    $('#loginForm').show();
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
                                        template: "<button onclick='loginFormSubmit();' style='width: 145px;' class='btn btn-success'>Кіру</button>"
                                    },
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button onclick='viewLoginWin()' style='width: 145px;'  class='btn btn-primary'>Тіркелу</button>"
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
    if (isNullOrEmpty(myuser)) {
        return viewLoginWin();
    }
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
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
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

function viewLoginWin() {
    if (!$$('viewLoginWin')) {
        webix.ui({
            view: "window",
            id: "viewLoginWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Сынақты бастау үшін тіркелуіңіз керек"},
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
                        id: "viewLoginWinForm",
                        view: "form",
                        width: 500,
                        elements: [
                            {
                                view: "text",
                                name: "uname",
                                label: "Логин",
                                required: true
                            },
                            {
                                view: "text",
                                name: "name",
                                label: "Аты жөні",
                                required: true
                            },
                            {
                                view: "text",
                                name: "email",
                                label: "Почтасы",
                                required: true
                            },
                            {
                                view: "text",
                                name: "password",
                                label: "Құпия сөз",
                                type: "password",
                                required: true
                            },
                            {
                                view: "text",
                                name: "password",
                                label: "Құпия сөз",
                                type: "passwordс",
                                required: true
                            }
                        ]
                    },
                    {
                        height: 20
                    }, {
                        cols: [
                            {
                                width: 20
                            },
                            {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button style='width: 145px;'  class='btn btn-success'>Тіркелу</button>"
                            }, {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button onclick='viewSignInWin()'  style='width: 145px;' class='btn btn-primary'>Мен тіркелгенмін</button>"
                            }
                        ]
                    },
                    {
                        height: 20
                    }
                ]
            }
        }).hide();
    }
    $$('viewLoginWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}


function viewSignInWin() {
    if ($$("viewLoginWin")) {
        $$("viewLoginWin").hide();
    }
    if (!$$('viewSignInWin')) {
        webix.ui({
            view: "window",
            id: "viewSignInWin",
            modal: true,
            position: "center",
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Кіру"},
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
                        id: "viewSignInWinForm",
                        view: 'form',
                        width: 450,
                        elements: [
                            {
                                view: "text",
                                name: "uname",
                                label: "Логин",
                                required: true
                            },
                            {
                                view: "text",
                                name: "password",
                                label: "Құпия сөз",
                                type: "password",
                                required: true
                            }
                        ]
                    },
                    {
                        height: 20
                    }, {
                        cols: [
                            {
                                width: 20
                            },
                            {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button onclick='viewSignInWinSubmit()' style='width: 145px;'  class='btn btn-primary'>Кіру</button>"
                            }, {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button onclick='viewLoginWin()'  style='width: 145px;' class='btn btn-success'>Тіркелу</button>"
                            }
                        ]
                    },
                    {
                        height: 20
                    }
                ]
            }
        }).hide();
    }
    $$('viewSignInWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function viewSignInWinSubmit() {
    var username = $$("viewSignInWinForm").elements['uname'].getValue();
    var password = $$("viewSignInWinForm").elements['password'].getValue();
    loginSubmit1(username, password);
}
function loginFormSubmit() {
    var username = $$("loginForm").elements['uname'].getValue();
    var password = $$("loginForm").elements['password'].getValue();
    loginSubmit1(username, password);
}

function loginSubmit1(username, password) {

    if (username.trim().length == 0 || password.trim().length == 0) {
        messageBox("Введите логин и пароль");
        return;
    }

    $.ajax({
        url: "/study/auth",
        type: 'GET',
        data: {j_username: username, j_password: password},
        success: function (gson) {
            if (gson) {
                if (gson.result) {
                    authSuccess();

                    window.location.href = "";
                } else {
                    messageBox("Ошибка", gson.message);
                }
            }
        },
        error: function () {
            messageBox("Ошибка", "Ошибка службы ");
        }
    });
}

function authSuccess() {
    $('#loginForm').hide();

    webix.ui({
        id: "userInfo",
        container: "userInfo",
        rows: [
            {
                cols: [
                    {}, {
                        template: "<img style='width: 100px;height: 100px;margin: 10px;' src='/study/images/no-avatar.jpg'>",
                        height: 120,
                        width: 120
                    },
                    {}
                ]
            },
            {
                view: "label",
                label: "<h3 style='text-align: center;margin-bottom: 0'>" + myuserFio + "</h3><span onclick='logout1()'>Шығу</span>",
                height: 110
            }
        ]
    });
    $('#userInfo').show();
}

function logout1() {
    $.post("/study/auth", function () {
        window.location.href = "";
    });
}
