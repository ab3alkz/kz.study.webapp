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
            },
            {
               // readonly: testFinish,
                id: "answTextarea",
                view: 'textarea',
                value: obj.answ,
                height: 200
            }
        ]
    });

    setIntellectQuestionPaging();
}


function setIntellectQuestionPaging() {
    var max = testData.length;
    $$("answTempl").addView({
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
                    {}
                ]
            }, {
               // hidden: testFinish,
                cols: [
                    {},
                    {
                        height: 50,
                        id: "finishTestingBtn",
                        width: 155,
                        css: "noBorder",
                        template: "<button id='fillWordsRequireBtn' style='width: 145px;' onclick=\"finishIntellectTesting()\" class='btn btn-success'>Аяқтау</button>",
                    }
                ]
            }
        ]

    });
}

function intellectQPagingClick(idx, nextIdx) {
    getAnswTextareaVal(idx)

    if (isNullOrEmpty(nextIdx)) {
        nextIdx = 0;
    }
    if (nextIdx < 0 || nextIdx > testData.length - 1)
        return;
    activeQuestionIdx = nextIdx;
    createIntellectTempl();
}

function getAnswTextareaVal(idx) {
    if (idx != null) {
        testData[idx].answ = $$("answTextarea").getValue();
    }
}

function finishIntellectTesting() {
    getAnswTextareaVal(activeQuestionIdx);
    testFinish = true;
    //$$('finishTestingBtn').disable();

    var json = {};
    json.data = testData;
    console.log(json)
    setGameResult(0, json,
        function (gson) {
            if(gson.result) {
                notifyMessage(gson.message.result,gson.message.message)
            } else {

                notifyMessage(gson.result,gson.message)
            }
        }
    );
    intellectQPagingClick(0);
}
