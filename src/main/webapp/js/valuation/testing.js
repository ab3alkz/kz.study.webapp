var testData;
var activeQuestionIdx = 0;
var testFinish = false;

function startTesting(item) {
    get_ajax('/study/wr/app/getRandom25Guestions?srcId=' + item.id + "&start=0&count=25", 'GET', null, function (gson) {

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
        } else  if(testFinish && i==1) {
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
    if (!isNullOrEmpty(testData[activeQuestionIdx].answIdx)) {
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
            {},
            {},
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + 0 + ')" class="fa fa-fast-backward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx - 1) + ')" class="fa fa-backward" aria-hidden="true"></i>'
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
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx + 1) + ')" class="fa fa-forward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                autowidth: true,
                disabled: activeQuestionIdx >= max - 1,
                label: '<i onclick="questionPagingClick(' + (max - 1) + ')" class="fa fa-fast-forward" aria-hidden="true"></i>'
            },
            {},
            {
                hiddem: testFinish,
                height: 50,
                id: "finishTestingBtn",
                width: 155,
                css: "noBorder",
                template: "<button id='fillWordsRequireBtn' style='width: 145px;' onclick=\"finishTesting()\" class='btn btn-success'>Аяқтау</button>",
            }
        ]
    });
}

function questionPagingClick(idx) {
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
}
