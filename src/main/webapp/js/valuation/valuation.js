$(document).ready(function () {
    load();
});

var activeGameId;
var activeGameType;
var testData;
var activeQuestionIdx = 0;
var testFinish = false;
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
            gameResultContainerCreate();
        }
        // startTest("test", {
        //     id: "test",
        //     name: "Дұрыс жауабын тап"
        // });
    });
}

function gameResultContainerCreate() {
    var refreshgameResultCounter = 0;
    $('#gameResultContainerWrapper').show();
    webix.ui({
        container: 'gameResultContainerWrapper',
        cols: [
            {
                rows: [
                    {
                        id: "gameResultTable",
                        view: "datatable",
                        width: 800,
                        // css: "sSListPersonTable",
                        resizeColumn: true,
                        autoheight: true,
                        minHeight: 300,
                        url: '/study/wr/app/getGameResultList',
                        rowLineHeight: 30,
                        columns: [
                            {id: "gameId", header: " ", width: 150},
                            {id: "uName", header: " ", fillspace: 1},
                            {
                                id: "result", header: " ", width: 120
                            }

                        ],
                        select: "row",
                        datafetch: 12,
                        scheme: {
                            $init: function (obj) {
                                refreshgameResultCounter++;
                                if (refreshgameResultCounter == 1) {
                                    setGameResultInfo(obj);
                                }
                                //  obj.action = "<span onclick=\"suspendWin(" + obj.sicid + ", " + obj.osn + ")\"   class='btn btn-danger' style='width: 70px;padding: 2px;margin-bottom:3px'>Приост.</span>"
                            }
                        },
                        on: {
                            onAfterLoad: function () {
                                this.enable();
                                this.hideProgress();
                                this.hideOverlay();
                                if (!this.count())
                                    this.showOverlay("<span class='no_data_found'></span>");
                            },
                            onBeforeLoad: function () {
                                this.disable();
                                webix.extend($$("gameResultTable"), webix.ProgressBar);
                                this.showProgress();
                            },
                            onItemClick: function () {
                                var obj = this.getSelectedItem();
                                setGameResultInfo(obj);
                            }
                        }
                    }, {
                        height: 20
                    }
                ]
            }, {
                id: "gameResultInfo",
                width: 360,
                rows: []
            }
        ]
    });
}

function createLoginForm() {

    $('#navbar').hide();
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
                                label: "<h3 style='margin: 0;padding: 0'>" + getResourceName("valuation.registration") + "</h3>",
                                height: 80
                            },
                            {
                                view: "text",
                                name: "uname",
                                label: getResourceName("valuation.login")
                            },
                            {
                                view: "text",
                                name: "password",
                                label: getResourceName("valuation.password"),
                                type: "password"
                            }, {
                                height: 20
                            }, {
                                cols: [
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button onclick='loginFormSubmit();' style='width: 145px;' class='btn btn-success'>" + getResourceName("valuation.kiru") + "</button>"
                                    },
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button onclick='registrationWin()' style='width: 145px;'  class='btn btn-primary'>" + getResourceName("valuation.registration") + "</button>"
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

function getTestTypeList(isPublic_) {
    if (isNullOrEmpty(myuser)) {
        return viewSignInWin();
    }
    get_ajax('/study/wr/app/getTestTypeList', 'GET', {
        isPublic: isPublic_,
        lang: lang
    }, function (gson) {
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
                    {view: "label", label: getResourceName("valuation.changetest")},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                disabled: true,
                                id: "editTestTypeBtn",
                                hidden: !isAdmin,
                                view: "icon", icon: " fa fa-pencil-square-o", css: "buttonIcon",
                                click: function () {
                                    editTreeElement($$("viewTestTypeListTable").getSelectedItem());
                                }
                            },
                            {
                                view: "icon",
                                hidden: !isAdmin,
                                icon: " fa fa-plus-square-o",
                                css: "buttonIcon",
                                tooltip: "Жаңа деңгей қосу",
                                click: function () {
                                    editLevelWin(null);
                                }
                            },
                            {
                                view: "icon", icon: "  fa fa-times", css: "buttonIcon",
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
                        view: "treetable",
                        css: "appTable",
                        id: "viewTestTypeListTable",
                        scroll: 'y',
                        header: false,
                        footer: false,
                        height: 500,
                        rowHeight: 45,
                        select: 'row',
                        columns: [

                            {id: "startBtn", header: " ", width: 120},
                            {

                                template: "{common.treetable()} <b style='font-size: 16px; color:#317eac'>#name#</b>",
                                header: " ",
                                fillspace: 1
                            },
                            {id: "editBtn", header: " ", width: 60, hidden: !isAdmin}
                        ],
                        on: {
                            onAfterSelect: function () {
                                $$("editTestTypeBtn").enable();
                            }
                        },
                        scheme: {
                            $init: function (obj) {
                                if (obj.parent) {
                                    obj.startBtn = "<button style='width:100px;'  class='startTest btn btn-primary'>" + getResourceName("valuation.start") + "</button>";

                                    if (obj.type == 'test') {
                                        obj.editBtn = "<button style='width:40px;'  title='Сынақ тапсырмаларын өңдеу'  class='editTesting btn btn-danger'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                                    } else if (obj.type == 'intelectualTest') {
                                        obj.editBtn = "<button style='width:40px;' title='Сынақ тапсырмаларын өңдеу'   class='editIntelectualTest btn btn-danger'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                                    } else if (obj.type == 'audi') {
                                        obj.editBtn = "<button style='width:40px;' title='Сынақ тапсырмаларын өңдеу'   class='editAudiTest btn btn-danger'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                                    } else if (obj.type == 'fillWords') {
                                        obj.editBtn = "<button style='width:40px;' title='Сынақ тапсырмаларын өңдеу'   class='editFillWords btn btn-danger'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                                    }
                                } else {
                                    obj.editBtn = "<button style='width:40px;'  title='Жаңа сынақ түрын қосу'  class='addTesting btn btn-primary'><i class=\" fa fa-plus\" aria-hidden=\"true\"></i></button>";
                                }
                            }
                        },
                        onClick: {
                            startTest: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    startTest(item.row, obj);
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            },
                            addTesting: function (e, item, cell) {
                                setTimeout(function () {
                                    editAddTestTypeWin({parent: item.row});
                                }, 100)
                            },
                            editTesting: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    testingAdmin(obj);
                                    $("#mainContainer").hide();
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            },
                            editAudiTest: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    audiAdmin(obj);
                                    $("#mainContainer").hide();
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            },
                            editIntelectualTest: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    intelectualTestAdmin(obj);
                                    $("#mainContainer").hide();
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            },
                            editFillWords: function (e, item, cell) {
                                setTimeout(function () {
                                    var obj = $$("viewTestTypeListTable").getSelectedItem();
                                    editFillWords(obj);
                                    $("#mainContainer").hide();
                                    $$("viewTestTypeListWin").hide();
                                }, 100)
                            }
                        }
                    },
                    {
                        height: 20
                    }
                ]
            }
        }).hide();
    }
    loadTreeData("viewTestTypeListTable", getTreeData(gson, 'id', 'parent'));
    console.log(getTreeData(gson, 'id', 'parent'))
    $$('viewTestTypeListWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function startTest(id, item) {
    $("#mainContainer").hide();
    $('#gameResultContainerWrapper').hide();
    activeGameId = id;
    activeGameType = item.type;
    switch (item.type) {
        case "fillWords":
            createFillWordsContainer(id, item);
            break;
        case "test":
            createTestsContainer(id, item);
            break;
        case "audi":
            createAudioContainer(id, item);
            break;
        case "intelectualTest":
            createIntelectualTestContainer(id, item);
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
                    autowidth: true,
                    label: "<h2>" + item.name + "</h2>"
                }, {
                    height: 50
                }
            ]
        }
    );

    startFillWords(id);
}

function createTestsContainer(id, item) {
    webix.ui(
        {
            id: "testsContainer",
            container: "testsContainer",
            rows: [
                {
                    view: 'label',
                    height: 60,
                    autowidth: true,
                    label: "<h3 style='margin: 0'>" + item.name + "</h3>"
                }
            ]
        }
    );

    $('#userInfo').hide();

    $('.mainwrapper').removeClass(' top80px');
    $('.mainwrapper').addClass(' top20px');
    startTesting(item);
}
function createAudioContainer(id, item) {
    webix.ui(
        {
            id: "audioContainer",
            container: "fillWordsContainer",
            rows: [
                {
                    view: 'label',
                    height: 60,
                    autowidth: true,
                    label: "<h2>" + item.name + "</h2>"
                }, {
                    height: 50
                }
            ]
        }
    );

    startAudi();
}

function createIntelectualTestContainer(id, item) {
    webix.ui(
        {
            id: "testsContainer",
            container: "testsContainer",
            rows: [
                {
                    view: 'label',
                    height: 60,
                    autowidth: true,
                    label: "<h3 style='margin: 0'>" + item.name + "</h3>"
                }
            ]
        }
    );


    $('#userInfo').hide();

    $('.mainwrapper').removeClass(' top80px');
    $('.mainwrapper').addClass(' top20px');
    startIntelllectualTest(item);
}

function viewSignInWin() {
    if ($$("registrationWin")) {
        $$("registrationWin").hide();
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
                    {view: "label", label: getResourceName("valuation.kiru")},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "  fa fa-times", css: "buttonIcon",
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
                                label: getResourceName("valuation.login"),
                                required: true
                            },
                            {
                                view: "text",
                                name: "password",
                                label: getResourceName("valuation.password"),
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
                                template: "<button onclick='viewSignInWinSubmit()' style='width: 145px;'  class='btn btn-primary'>" + getResourceName("valuation.kiru") + "</button>"
                            }, {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button onclick='registrationWin()'  style='width: 145px;' class='btn btn-success'>" + getResourceName("valuation.registration") + "</button>"
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
            }
        ]
    });
    webix.ui({
        id: "navbarContainer",
        container: "navbarContainer",
        autoheight: true,
        cols: [
            {
                view: "label",
                label: "<a href='' class=' fa fa-home'></a>",
                width: 60
            },
            {},
            {},
            {
                view: "label",
                autowidth: true,
                label: "<span class='myuser-Fio'>" + myuserFio + "</span>",
            },
            {
                view: "label",
                label: "<span onclick='logout1()' class=' fa fa-sign-out'></span>",
                width: 60
            }
        ]
    });
    $('#userInfo').show();
    $('#navbar').show();
}

function logout1() {
    $.post("/study/auth", function () {
        window.location.href = "";
    });
}


function setGameResultInfo(obj) {
    return;
    $$("gameResultInfo").removeView("gameResultInfoW");
    $$("gameResultInfo").addView({
        id: "gameResultInfoW",
        template: "<h4>" + obj.uName + "</h4>" + getFillWordsResultByJson(JSON.parse(obj.info)),
        width: 360,
        height: 404
    });
}

function setGameResult(result, json, callBack) {
    get_ajax('/study/wr/app/setGameResult', 'GET', {
        gameId: activeGameId,
        type: activeGameType,
        uName: myuser,
        result: result,
        json: JSON.stringify(json)
    }, callBack, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function editLevelWin(obj) {
    if (!$$('editLevelWin')) {
        webix.ui({
            view: "window",
            id: "editLevelWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            head: {
                cols: [
                    {width: 10},
                    {view: "label", id: "valuationaddnewlevel", label: getResourceName("valuation.addnewlevel")},
                    {view: "label", id: "valuationeditlevel", label: getResourceName("valuation.editlevel")},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "  fa fa-times", css: "buttonIcon",
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
                        view: "form",
                        id: "editLevelWinForm",
                        width: 500,
                        elementsConfig: {
                            labelPosition: "left",
                            labelWidth: 140,
                            width: 500
                        },
                        elements: [
                            {
                                required: true,
                                name: "name",
                                label: getResourceName("valuation.levelname"),
                                view: "text"
                            },
                            {
                                hidden: true,
                                name: "lang",
                                view: "text"
                            },
                            {
                                height: 20
                            },
                            {
                                cols: [
                                    {
                                        width: 320
                                    },
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button onclick='saveLevel()' style='width: 145px;'  class='btn btn-success'>" + getResourceName("valuation.save") + "</button>"
                                    }
                                ]
                            }
                        ]
                    }

                ]
            }
        }).hide();
    }
    if (obj) {
        $$("valuationaddnewlevel").hide();
        $$("valuationeditlevel").show();
    } else {
        $$("valuationaddnewlevel").show();
        $$("valuationeditlevel").hide();
    }
    $$('editLevelWinForm').parse(obj);
    $$('editLevelWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}
function editAddTestTypeWin(obj) {
    if (!$$('addTestTypeWin')) {
        webix.ui({
            view: "window",
            id: "addTestTypeWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            head: {
                cols: [
                    {width: 10},
                    {view: "label", id: "valuationaddnewtest", label: getResourceName("valuation.addnewtest")},
                    {view: "label", id: "valuationedittest", label: getResourceName("valuation.edittest")},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "  fa fa-times", css: "buttonIcon",
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
                        view: "form",
                        id: "addTestTypeWinForm",
                        width: 500,
                        elementsConfig: {
                            labelPosition: "left",
                            labelWidth: 140,
                            width: 500
                        },
                        elements: [
                            {
                                label: getResourceName("valuation.uroven"),
                                view: "richselect",
                                name: "parent",
                                required: true,
                                suggest: {
                                    css: "auto_width_popup",
                                    body: {
                                        template: "#name#",
                                        url: "/study/wr/app/getUrovenList"
                                    }
                                }
                            },
                            {
                                label: getResourceName("valuation.testtype"),
                                view: "richselect",
                                name: "type",
                                required: true,
                                suggest: {
                                    css: "auto_width_popup",
                                    body: {
                                        template: "#name#",
                                        url: "/study/wr/app/getDTestTypeList"
                                    }
                                }
                            },
                            {
                                required: true,
                                name: "name",
                                label: getResourceName("valuation.testname"),
                                view: "text"
                            },
                            {
                                hidden: true,
                                name: "lang",
                                value: lang,
                                view: "text"
                            },
                            {
                                height: 20
                            },
                            {
                                cols: [
                                    {
                                        width: 320
                                    },
                                    {
                                        height: 50,
                                        width: 155,
                                        css: "noBorder",
                                        template: "<button onclick='saveTestType()' style='width: 145px;'  class='btn btn-success'>" + getResourceName("valuation.save") + "</button>"
                                    }
                                ]
                            }
                        ]
                    }

                ]
            }
        }).hide();
    }
    if (obj && obj.id) {
        $$("valuationaddnewtest").hide();
        $$("valuationedittest").show();
    } else {
        $$("valuationaddnewtest").show();
        $$("valuationedittest").hide();
    }
    $$('addTestTypeWinForm').parse(obj);
    $$('addTestTypeWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function saveTestType() {
    var form = $$("addTestTypeWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    get_ajax('/study/wr/app/saveTestType', 'POST', strJson, function (gson) {
            if (gson && gson.result) {

                loadTreeData("viewTestTypeListTable", getTreeData(gson.message, 'id', 'parent'));
                $$("addTestTypeWin").hide();
                notifyMessage("Инфо", "Жаңа сынақ қосылды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}

function saveLevel() {
    var form = $$("editLevelWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    get_ajax('/study/wr/app/saveLevel', 'POST', strJson, function (gson) {
            if (gson && gson.result) {
                loadTreeData("viewTestTypeListTable", getTreeData(gson.message, 'id', 'parent'));
                $$("editLevelWin").hide();
                notifyMessage("Инфо", "Жаңа деңгей қосылды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}


function showAppProgress() {
    if (!$$("mainprogress"))
        createMainProgress();

    $$("mainprogress").show();
    $$("mainprogress").showProgress({
        position: 0,
        delay: 30000,
        type: "icon",
        icon: "refresh"
    });
}


function hideAppProgress() {
    if ($$("mainprogress")) {
        $$("mainprogress").hideProgress();
        $$("mainprogress").hide();
    }
}

function createMainProgress() {
    var cont_h = $(window).height();
    var mainprogress = webix.ui({
        id: "mainprogress",
        css: "mainprogress",
        autowidth: true,
        autoheight: true,
        hidden: true,
        container: "mainprogress",
        rows: [
            {
                height: cont_h
            }
        ]
    });
    webix.event(window, "resize", function () {
        mainprogress.adjust();
    });
    webix.extend($$("mainprogress"), webix.ProgressBar);
}

function editTreeElement(item) {
    if (item.parent) {
        editAddTestTypeWin(item);
    } else {
        editLevelWin(item)
    }
}
