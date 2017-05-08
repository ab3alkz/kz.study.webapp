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
    if (obj.dbAnsw) {
        $$("dbAnswTextarea").show();
        $$("trueanswer").show();
        $$("dbAnswTextarea").setValue(obj.dbAnsw);
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
            template: obj.result.message
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
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + 0 + ')" class="fa fa-fast-backward" aria-hidden="true"></i>'
                    },
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx <= 0,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx - 1) + ')" class="fa fa-backward" aria-hidden="true"></i>'
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
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (activeQuestionIdx + 1) + ')" class="fa fa-forward" aria-hidden="true"></i>'
                    },
                    {
                        view: 'label',
                        autowidth: true,
                        disabled: activeQuestionIdx >= max - 1,
                        label: '<i onclick="intellectQPagingClick(' + activeQuestionIdx + ',' + (max - 1) + ')" class="fa fa-fast-forward" aria-hidden="true"></i>'
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
    console.log(json);
    setGameResult(0, json,
        function (gson) {
            if (gson.result) {
                testData = gson.message.data;
                console.log(gson.message.data);
                intellectQPagingClick(null, activeQuestionIdx)
                // messageBox(getResourceName("valuation.answer"), gson.message.message);
            } else {

                messageBox(getResourceName("valuation.answer"), gson.message);
            }
        }
    );
    intellectQPagingClick(0);
}
