function startIntelllectualTest(item) {
    get_ajax('/study/wr/app/getIntellectualGuestions?srcId=' + item.id + "&start=0&count=25", 'GET', null, function (gson) {
        testData = gson;
        createIntellectTempl();
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createIntellectTempl() {
    var obj = testData[activeQuestionIdx];
    if (!$$("answTempl")) {
        $$("testsContainer").addView({
            id: "answTempl",
            rows: [
                {
                    id: "iTestGuestionWrap",
                    cols: [],
                    height: 70
                }, {
                    height: 2
                },
                {

                    cols: [
                        {
                            width: 750,
                            rows: [

                                {
                                    height: 15
                                },
                                {
                                    // readonly: testFinish,
                                    id: "answTextarea",
                                    view: 'textarea',
                                    name: "answ",
                                    height: 140
                                },
                                {
                                    height: 5
                                },
                                {
                                    view: "label",
                                    id: "trueanswer",
                                    label: "<b>" + getResourceName("valuation.trueanswer") + ":</b>"
                                },
                                {
                                    readonly: true,
                                    id: "dbAnswTextarea",
                                    view: 'textarea',
                                    name: "dbAnsw",
                                    height: 140
                                }
                            ]
                        }, {
                            width: 10
                        },
                        {
                            autowidth: true,
                            autoheight: true,
                            width: 390,
                            minheight: 300,
                            id: "iQuestionResultWrap",
                            rows: [{height: 20}]
                        }
                    ]
                }
            ]
        });
    }

    $$("answTextarea").setValue(nvl(obj.answ, ""));
    if (testFinish) {
        $$("dbAnswTextarea").show();
        $$("trueanswer").show();
        $$("dbAnswTextarea").setValue(nvl(obj.dbAnsw, ""));
    } else {
        $$("dbAnswTextarea").hide();
        $$("trueanswer").hide();
    }

    $$("iQuestionResultWrap").removeView("iQuestionResult");
    if (obj.result && obj.result.message) {
        $$("iQuestionResultWrap").addView({
            width: 900,
            autoheight: true,
            id: "iQuestionResult",
            template: "<h5>" + obj.result.message + "</h5>"
        });

    }

    $$("iTestGuestionWrap").removeView("iTestGuestion");
    $$("iTestGuestionWrap").addView({
        id: "iTestGuestion",
        minheight: 300,
        autoheight: true,
        template: "<div class='test-question'>" + obj.question + "</div>"
    });
    setIntellectQuestionPaging();
}


function setIntellectQuestionPaging() {
    var max = testData.length;
    $$("answTempl").removeView("answTemplPaging");
    $$("answTempl").addView({
        id: "answTemplPaging",
        css: 'answTemplPaging',
        rows: [
            {
                cols: [
                    {},
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx <= 0,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + 0 + ')" class=" fa fa-fast-backward" aria-hidden="true"></i>'
                    },
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx <= 0,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx - 1) + ')" class=" fa fa-backward" aria-hidden="true"></i>'
                    },
                    {
                        view: 'label',
                        width: 100,
                        label: "<h4 style='text-align: center'>" + (activeQuestionIdx + 1) + " / " + max + "</h4>"
                    },
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx >= max - 1,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx + 1) + ')" class=" fa fa-forward" aria-hidden="true"></i>'
                    },
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx >= max - 1,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (max - 1) + ')" class=" fa fa-fast-forward" aria-hidden="true"></i>'
                    },
                    {},
                    {}
                ]
            }, {
                // hidden: testFinish,
                cols: [
                    {
                        height: 50,
                        id: "finishTestingBtn",
                        width: 155,
                        css: "noBorder",
                        template: "<button id='fillWordsRequireBtn' style='width: 145px;' onclick=\"finishIntellectTesting()\" class='btn btn-success'>Аяқтау</button>",
                    },
                    {}
                ]
            }
        ]

    });
}

function intellectQPagingClick(idx, nextIdx) {
    if (idx != null) {
        getAnswTextareaVal(idx)
    }

    if (isNullOrEmpty(nextIdx)) {
        nextIdx = 0;
    }
    if (nextIdx < 0 || nextIdx > testData.length - 1)
        return;
    activeQuestionIdx = nextIdx;
    createIntellectTempl();
}

function getAnswTextareaVal(idx) {
    if (idx != null && !isNullOrEmpty($$("answTextarea").getValue())) {
        testData[idx].answ = $$("answTextarea").getValue();
    }
}

function finishIntellectTesting() {
    getAnswTextareaVal(activeQuestionIdx);
    testFinish = true;
    //$$('finishTestingBtn').disable();

    var json = {};
    json.data = testData;
    setGameResult(0, json,
        function (gson) {
            if (gson.result) {
                testData = gson.message.data;
                console.log(gson.message.data);
                createIntellectTempl()
                // messageBox(getResourceName("valuation.answer"), gson.message.message);
            } else {

                messageBox(getResourceName("valuation.answer"), gson.message);
            }
        }
    );
    intellectQPagingClick(0);
}

function intelectualTestAdmin(item) {


    $('#gameResultContainerWrapper').hide();
    $('#userInfo').hide();

    $('.mainwrapper').removeClass(' top80px');
    $('.mainwrapper').addClass(' top20px');
    webix.ui({
        id: "testsContainerAdmin",
        container: "testsContainerAdmin",
        rows: [
            {
                cols: [

                    {
                        view: "label",
                        label: item.name
                    },
                    {
                        height: 50,
                        width: 155,
                        css: "noBorder",
                        template: "<button onclick='editIntellectualQuestion({srcId:" + item.id + "})' style='width: 145px;'  class='btn btn-success'>жаңа сұрақ қосу</button>"
                    }
                ]
            },
            {
                view: "datatable",
                css: "appTable",
                id: "iTestingAdminTable",
                scroll: false,
                header: false,
                footer: false,
                minHeight: 400,
                autoheight: true,
                url: "/study/wr/app/getIntellectTestingListById?srcId=" + item.id,
                rowHeight: 45,
                select: 'row',
                columns: [
                    {id: "index", header: "№пп", width: 60},
                    {
                        id: "question",
                        fillspace: 1
                    },
                    {id: "editBtn", header: " ", width: 60},
                    {id: "removBtn", header: " ", width: 60},
                ],
                pager: {
                    container: "testsContainerAdminPaging",
                    size: 10,
                    group: 15
                },
                scheme: {
                    $init: function (obj) {
                        obj.editBtn = "<button style='width:40px;'  class='editITestQuestion btn btn-primary'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                        obj.removBtn = "<button style='width:40px;'  class='removeITestQuestion btn btn-danger'><i class=\" fa fa-trash\" aria-hidden=\"true\"></i></button>";

                    }
                },
                onClick: {
                    editITestQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            var obj = $$("iTestingAdminTable").getSelectedItem();
                            editIntellectualQuestion(obj);
                        }, 100)
                    },
                    removeITestQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            removeIntellectualQuestion(item.row);
                        }, 100)
                    }
                },
                on: {

                    "data->onStoreUpdated": function () {
                        this.data.each(function (obj, i) {
                            if (!obj) obj = {};
                            if (!obj.index) obj.index = 0;
                            obj.index = i + 1;
                        })
                    }
                }
            },
            {
                view: "template",
                height: 50,
                content: "testsContainerAdminPaging"
            }
        ]
    })
}


function editIntellectualQuestion(item) {

    if (!$$('editIQuestionWin')) {
        webix.ui({
            view: "window",
            id: "editIQuestionWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            width: 700,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", id: "editIQuestionLabel", label: "Сұрақты өзгерту"},
                    {view: "label", id: "addIQuestionLabel", label: "Жаңа сұрақ қосу"},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: " fa fa-times", css: "buttonIcon",
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
                        view: "tabbar",
                        viewResultDocCount: 0,
                        on: {
                            onChange: function (newV, oldV) {

                            }
                        },
                        id: "editIQuestionTabbar", value: 'main', multiview: true,
                        options: [
                            {value: "Басты", id: 'main', width: 130}
                        ]
                    }, {height: 10},
                    {
                        width: 800,
                        cells: [
                            {
                                width: 130,
                                id: 'main',
                                rows: [{
                                    id: "editIQuestionWinForm",
                                    view: "form",
                                    width: 700,
                                    elementsConfig: {
                                        labelPosition: "left",
                                        labelAlign: "left",
                                        inputWidth: 660,
                                        labelWidth: 100
                                    },
                                    elements: [
                                        {
                                            view: "text",
                                            name: "id",
                                            required: true,
                                            hidden: true
                                        },
                                        {
                                            view: "text",
                                            name: "srcId",
                                            required: true,
                                            hidden: true
                                        },
                                        {
                                            view: "text",
                                            name: "question",
                                            label: "Сұрақ",
                                            required: true
                                        },
                                        {
                                            view: "label",
                                            id: "trueanswer",
                                            label: "<b>" + getResourceName("valuation.answer") + ":</b>"
                                        },
                                        {
                                            id: "answTextarea",
                                            view: 'textarea',
                                            name: "dbAnsw",
                                            height: 140
                                        }
                                    ]
                                }]
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
                                template: "<button onclick='saveIntellectQuestion()' style='width: 145px;'  class='btn btn-success'>Сақтау</button>"
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
    if (item.id) {
        $$("editIQuestionLabel").show();
        $$("addIQuestionLabel").hide();
    } else {
        $$("editIQuestionLabel").hide();
        $$("addIQuestionLabel").show();
    }
    $$("editIQuestionWinForm").parse(item);
    $$('editIQuestionWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function saveIntellectQuestion() {
    var form = $$("editIQuestionWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    showAppProgress();
    get_ajax('/study/wr/app/saveIntellectQuestion', 'POST', strJson, function (gson) {
            hideAppProgress();
            if (gson && gson.result) {
                $$("iTestingAdminTable").parse(gson.message);
                $$("editIQuestionWin").hide();
                notifyMessage("Инфо", "Сұрақ сақталды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            hideAppProgress();
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}


function removeIntellectualQuestion(id) {
    get_ajax('/study/wr/app/removeIntellectualQuestionById?id=' + id, 'GET', null, function (gson) {
        if (gson && gson.result) {
            $$("iTestingAdminTable").remove(id);
        } else {
            messageBox("Ошибка", gson.message);
        }
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
