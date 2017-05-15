function startAudi() {
    get_ajax('/study/wr/app/getRandom10AudiList', 'GET', {srcId: activeGameId}, function (gson) {
        testData = gson;
        createAudi(gson);
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createAudi() {
    var data = testData[activeQuestionIdx];
    var resultLabel = "";
    if (testFinish) {
        resultLabel = "<h3 style='color:";
        if (data.result) {
            resultLabel += "green' >Дұрыс";
        } else {
            resultLabel += "red' >Дұрыc емес ";
        }
        resultLabel += "<\h3>";
    }
    $$("audioContainer").removeView("audioQuestion");
    $$("audioContainer").addView({
        id: "audioQuestion",
        rows: [
            {
                width: 700,
                cols: [
                    {
                        id: "editAudiQuestionWinFrame",
                        width: 310,
                        height: 210,
                        template: "<iframe width='300' height='200' src='" + data.frame + "'liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false'></iframe>"
                    }, {
                        view: "label",
                        width: 200,
                        label: resultLabel
                    }
                ]
            },
            {
                height: 10
            },
            {
                view: "label",
                label: getResourceName("valuation.tyngdalghanmaetindiengizingiz")
            },
            {
                id: "audiAnswTextarea",
                view: 'textarea',
                value: data.answ,
                height: 70
            },
            {
                height: 10
            },
            {
                hidden: !testFinish,
                view: "label",
                label: getResourceName("valuation.trueanswer")
            },
            {
                hidden: !testFinish,
                view: 'textarea',
                value: testData[activeQuestionIdx].text,
                height: 70
            },
            {
                id: "answTemplRow",
                rows: []
            }
        ]
    });
    setAudiQuestionPaging()
}


function setAudiQuestionPaging() {
    var max = testData.length;
    $$("answTemplRow").addView({
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
                label: '<i onclick="audiQuestionPagingClick(' + activeQuestionIdx + ',' + 0 + ')" class=" fa fa-fast-backward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="audiQuestionPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx - 1) + ')" class=" fa fa-backward" aria-hidden="true"></i>'
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
                label: '<i onclick="audiQuestionPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx + 1) + ')" class=" fa fa-forward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx >= max - 1,
                label: '<i onclick="audiQuestionPagingClick(' + activeQuestionIdx + ',' + (max - 1) + ')" class=" fa fa-fast-forward" aria-hidden="true"></i>'
            },
            {},
            {
                hidden: testFinish,
                height: 50,
                id: "finishTestingBtn",
                width: 155,
                css: "noBorder",
                template: "<button id='finishAudiBtn' style='width: 145px;' onclick=\"finishAudi()\" class='btn btn-success'>" + getResourceName("valuation.finish") + "</button>",
            }
        ]
    });
}


function finishAudi() {

    getAudiAnswTextareaVal(activeQuestionIdx);
    testFinish = true;
    $$('finishAudiBtn').disable();
    var resCount = 0;
    console.log(testData)
    for (var i in testData) {
        console.log(testData[i].answ.trim().toLowerCase(), testData[i].text.trim().toLowerCase())
        if (!isNullOrEmpty(testData[i].answ) && !isNullOrEmpty(testData[i].text) &&
            testData[i].answ.trim().toLowerCase() == testData[i].text.trim().toLowerCase()) {
            resCount++;
        }
    }

    var round = Math.round;
    var total = round(100 / testData.length * resCount);
    var json = {};
    json.data = testData;
    json.total = total;
    setGameResult(total, json,
        function (gson) {

        }
    );
    audiQuestionPagingClick(null,0);
}

function audiQuestionPagingClick(idx, nextIdx) {

    if (idx != null) {
        getAudiAnswTextareaVal(idx)
    }
    if (isNullOrEmpty(nextIdx)) {
        nextIdx = 0;
    }
    if (nextIdx < 0 || nextIdx > testData.length - 1)
        return;
    activeQuestionIdx = nextIdx;
    createAudi();
}


function getAudiAnswTextareaVal(idx) {
    if (idx != null && $$("audiAnswTextarea")) {
        testData[idx].answ = $$("audiAnswTextarea").getValue();
    }
}

function audiAdmin(item) {


    $('#gameResultContainerWrapper').hide();
    $('#userInfo').hide();

    $('.mainwrapper').removeClass(' top80px');
    $('.mainwrapper').addClass(' top20px');
    webix.ui({
        id: "audioContainerAdmin",
        container: "audioContainerAdmin",
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
                        template: "<button onclick='editAudiQuestion({srcId:" + item.id + "})' style='width: 145px;'  class='btn btn-success'>жаңа сұрақ қосу</button>"
                    }
                ]
            },
            {
                view: "datatable",
                css: "appTable",
                id: "audiAdminTable",
                scroll: false,
                header: false,
                footer: false,
                minHeight: 400,
                autoheight: true,
                url: "/study/wr/app/getAudiTestingListBySrcId?srcId=" + item.id,
                rowHeight: 45,
                select: 'row',
                columns: [
                    {id: "index", header: "№пп", width: 60},
                    {
                        id: "text",
                        fillspace: 1
                    },
                    {id: "editBtn", header: " ", width: 60},
                    {id: "removBtn", header: " ", width: 60},
                ],
                pager: {
                    container: "audioContainerAdminPaging",
                    size: 10,
                    group: 15
                },
                scheme: {
                    $init: function (obj) {
                        obj.editBtn = "<button style='width:40px;'  class='editAudiQuestion btn btn-primary'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                        obj.removBtn = "<button style='width:40px;'  class='removeAudiQuestion btn btn-danger'><i class=\" fa fa-trash\" aria-hidden=\"true\"></i></button>";

                    }
                },
                onClick: {
                    editAudiQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            var obj = $$("audiAdminTable").getSelectedItem();
                            editAudiQuestion(obj);
                        }, 100)
                    },
                    removeAudiQuestion: function (e, item, cell) {
                        setTimeout(function () {
                            removeAudiQuestion(item.row);
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
                content: "audioContainerAdminPaging"
            }
        ]
    })
}

function editAudiQuestion(item) {

    if (!$$('editAudiQuestionWin')) {
        webix.ui({
            view: "window",
            id: "editAudiQuestionWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            width: 920,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", id: "editAudiQuestionLabel", label: getResourceName("valuation.editquestion")},
                    {view: "label", id: "addQuestionLabel", label: getResourceName("valuation.addquestion")},
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
                cols: [
                    {
                        rows: [
                            {
                                id: "editAudiQuestionWinForm",
                                view: "form",
                                width: 590,
                                elementsConfig: {
                                    labelPosition: "left",
                                    labelAlign: "left",
                                    inputWidth: 555,
                                    labelWidth: 120
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
                                        name: "frame",
                                        label: "Сілтеме",
                                        required: true
                                    },
                                    {
                                        view: "text",
                                        name: "text",
                                        label: "Дұрыс жауап",
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
                                        template: "<button onclick='saveAudiQuestion()' style='width: 145px;'  class='btn btn-success'>Сақтау</button>"
                                    }
                                ]
                            },
                            {
                                height: 20
                            }
                        ]
                    }, {
                        id: "editAudiQuestionWinFrameWrap",
                        rows: []
                    }
                ]
            }
        }).hide();
    }
    if (item.id) {
        $$("editAudiQuestionLabel").show();
        $$("addQuestionLabel").hide();
    } else {
        $$("editAudiQuestionLabel").hide();
        $$("addQuestionLabel").show();
    }

    $$("editAudiQuestionWinFrameWrap").removeView("editAudiQuestionWinFrame")
    if (!isNullOrEmpty(item.frame)) {
        $$("editAudiQuestionWinFrameWrap").addView({
            id: "editAudiQuestionWinFrame",
            width: 310,
            height: 210,
            template: "<iframe width='300' height='200' src='" + item.frame + "'liking=false&amp;sharing=false&amp;show_artwork=false&amp;color=ff9900&amp;download=false&amp;auto_play=false&amp;hide_related=true&amp;show_comments=false&amp;show_user=false&amp;show_reposts=false'></iframe>"
        })

    }
    $$("editAudiQuestionWinForm").parse(item);
    $$('editAudiQuestionWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function saveAudiQuestion() {
    var form = $$("editAudiQuestionWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    get_ajax('/study/wr/app/saveAudiQuestion', 'POST', strJson, function (gson) {
            if (gson && gson.result) {
                $$("audiAdminTable").parse(gson.message);
                $$("editAudiQuestionWin").hide();
                notifyMessage("Инфо", "Сұрақ сақталды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}

function removeAudiQuestion(id) {
    get_ajax('/study/wr/app/removeAudiQuestionById?id=' + id, 'GET', null, function (gson) {
        if (gson && gson.result) {
            $$("audiAdminTable").remove(id);
        } else {
            messageBox("Ошибка", gson.message);
        }
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
