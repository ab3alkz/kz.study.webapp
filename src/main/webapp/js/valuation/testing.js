function startTesting(item) {
    get_ajax('/study/wr/app/getRandom25Guestions?srcId=' + item.id + "&start=0&count=25&lang=" + lang, 'GET', null, function (gson) {

        testData = gson;
        createAnswTempl();
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createAnswTempl() {
    var obj = testData[activeQuestionIdx];
    $$("testsContainer").removeView("answTempl");
    $$("testsContainer").addView({
        id: "answTempl",
        rows: [
            {
                autoheight: true,
                width: 900,
                template: "<div class='test-question'>" + obj.question + "</div>"
            },
            {
                height: 15
            }
        ]
    });

    setTemplAnsw(obj);
    setQuestionPaging();
}

function setTemplAnsw(obj) {
    var randomIdx = createRandPositionIdx(1, 4);
    for (var idx in  randomIdx) {
        var i = randomIdx[idx];
        var resultCss = "";
        if (testData[activeQuestionIdx].answIdx == i) {
            resultCss = "test-answ-" + testData[activeQuestionIdx].result;
        } else if (testFinish && i == 1) {
            resultCss = "test-answ-not-find-true";
        }
        $$("answTempl").addView({
            autoheight: true,
            width: 900,
            template: "<div onclick='answerClick(" + i + ")' class='test-answ test-answ" + i + " " + resultCss + "'>" + obj['answ' + i] + "</div>"
        });
        $$("answTempl").addView({
            height: 12
        });
    }
}

function answerClick(answIdx) {
    var result = answIdx == 1;
    if (!isNullOrEmpty(testData[activeQuestionIdx].answIdx) || testFinish) {
        return;
    }
    testData[activeQuestionIdx].answIdx = answIdx;
    testData[activeQuestionIdx].result = result;

    $('.test-answ' + answIdx).addClass(' test-answ-' + result);
}

function setQuestionPaging() {
    var max = testData.length;
    $$("answTempl").addView({
        css: 'answTemplPaging',
        cols: [
            {
                hidden: testFinish,
            },
            {},
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + 0 + ')" class=" fa fa-fast-backward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx - 1) + ')" class=" fa fa-backward" aria-hidden="true"></i>'
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
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx + 1) + ')" class=" fa fa-forward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx >= max - 1,
                label: '<i onclick="questionPagingClick(' + (max - 1) + ')" class=" fa fa-fast-forward" aria-hidden="true"></i>'
            },
            {},
            {
                hidden: testFinish,
                height: 50,
                id: "finishTestingBtn",
                width: 155,
                css: "noBorder",
                template: "<button id='fillWordsRequireBtn' style='width: 145px;' onclick=\"finishTesting()\" class='btn btn-success'>"+getResourceName("valuation.finish")+"</button>",
            }
        ]
    });
}

function questionPagingClick(idx) {
    if (isNullOrEmpty(idx)) {
        idx = 0;
    }
    if (idx < 0 || idx > testData.length - 1)
        return;
    activeQuestionIdx = idx;
    createAnswTempl();
}

function createRandPositionIdx(min, max) {
    var data = [];
    for (var i = min; i <= max; i++) {
        var rand = getRandomInt(min, max);
        if (!myContins(data, rand)) {
            data.push(rand);
        } else {
            i--;
        }
    }
    return data;
}

function myContins(arr, val) {
    for (var i in arr) {
        if (arr[i] == val) {
            return true;
        }
    }
    return false;
}

function finishTesting() {
    testFinish = true;
    $$('finishTestingBtn').disable();
    var resCount = 0;
    for (var i in testData) {
        if (testData[i].result) {
            resCount++;
        }
    }

    var round = Math.round;
    var total = round(100 / testData.length * resCount);
    var json = {};
    json.data = testData;
    json.total = total;
    setGameResult(total, json,
        function () {

        }
    );
    questionPagingClick(0);
}

function testingAdmin(item) {


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
                        template: "<button onclick='editQuestion({srcId:" + item.id + "})' style='width: 145px;'  class='btn btn-success'>жаңа сұрақ қосу</button>"
                    }
                ]
            },
            {
                view: "datatable",
                css: "appTable",
                id: "testingAdminTable",
                scroll: false,
                header: false,
                footer: false,
                minHeight: 400,
                autoheight: true,
                url: "/study/wr/app/getTestingListById?srcId=" + item.id,
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
                        obj.editBtn = "<button style='width:40px;'  class='editQuestion btn btn-primary'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                        obj.removBtn = "<button style='width:40px;'  class='removeQuestion btn btn-danger'><i class=\" fa fa-trash\" aria-hidden=\"true\"></i></button>";

                    }
                },
                onClick: {
                    editQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            var obj = $$("testingAdminTable").getSelectedItem();
                            editQuestion(obj);
                        }, 100)
                    },
                    removeQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            removeQuestion(item.row);
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

function editQuestion(item) {

    if (!$$('editQuestionWin')) {
        webix.ui({
            view: "window",
            id: "editQuestionWin",
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
                    {view: "label", id: "editQuestionLabel", label: "Сұрақты өзгерту"},
                    {view: "label", id: "addQuestionLabel", label: "Жаңа сұрақ қосу"},
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
                        id: "editQuestionWinForm",
                        view: "form",
                        width: 700,
                        elementsConfig: {labelPosition: "left", labelAlign: "left", inputWidth: 660, labelWidth: 150},
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
                                view: "text",
                                name: "answ1",
                                label: "Дұрыс жауап",
                                required: true
                            },
                            {
                                view: "text",
                                name: "answ2",
                                label: "Дұрыс емес жауап",
                                required: true
                            },
                            {
                                view: "text",
                                name: "answ3",
                                label: "Дұрыс емес жауап",
                                required: true
                            },
                            {
                                view: "text",
                                name: "answ4",
                                label: "Дұрыс емес жауап",
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
                                template: "<button onclick='saveQuestion()' style='width: 145px;'  class='btn btn-success'>Сақтау</button>"
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
        $$("editQuestionLabel").show();
        $$("addQuestionLabel").hide();
    } else {
        $$("editQuestionLabel").hide();
        $$("addQuestionLabel").show();
    }
    $$("editQuestionWinForm").parse(item);
    $$('editQuestionWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function saveQuestion() {
    var form = $$("editQuestionWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    get_ajax('/study/wr/app/saveQuestion', 'POST', strJson, function (gson) {
            if (gson && gson.result) {
                $$("testingAdminTable").parse(gson.message);
                $$("editQuestionWin").hide();
                notifyMessage("Инфо", "Сұрақ сақталды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}

function removeQuestion(id) {
    get_ajax('/study/wr/app/removeQuestionById?id=' + id, 'GET', null, function (gson) {
        if (gson && gson.result) {
            $$("testingAdminTable").remove(id);
        } else {
            messageBox("Ошибка", gson.message);
        }
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
