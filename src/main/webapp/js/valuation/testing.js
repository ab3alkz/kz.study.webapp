var testData;
var activeQuestionIdx = 0;

function startTesting() {
    get_ajax('/study/wr/app/getRandom25Guestions', 'GET', null, function (gson) {

        testData = gson;
        createTestByData();
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createTestByData() {
    createAnswTempl(testData[activeQuestionIdx])
}

function createAnswTempl(obj) {
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
                height: 10
            }
        ]
    });

    setTemplAnsw(obj);
    setQuestionPaging();
}

function setTemplAnsw(obj) {
    for (var i = 1; i <= 4; i++) {
        $$("answTempl").addView({
            autoheight: true,
            width: 900,
            template: "<div onclick='answerClick(" + i + ")' class='test-answ test-answ" + i + "'>" + obj['answ' + i] + "</div>"
        });
        $$("answTempl").addView({
            height: 10
        });
    }
}

function answerClick(answIdx) {
    $('.test-answ' + answIdx).addClass(' test-answ-true')
    // activeQuestionIdx
}

function setQuestionPaging() {
    var max = testData.length;
    $$("answTempl").addView({
        css: 'answTemplPaging',
        cols: [
            {
                view: 'label',
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + 0 + ')" class="fa fa-fast-backward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                disabled: activeQuestionIdx <= 0,
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx - 1) + ')" class="fa fa-backward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                label: "<h4>" + (activeQuestionIdx + 1) + " / " + max + "</h4>"
            },
            {
                view: 'label',
                disabled: activeQuestionIdx >= max-1,
                label: '<i onclick="questionPagingClick(' + (activeQuestionIdx + 1) + ')" class="fa fa-forward" aria-hidden="true"></i>'
            },
            {
                view: 'label',
                disabled: activeQuestionIdx >= max-1,
                label: '<i onclick="questionPagingClick(' + (max - 1) + ')" class="fa fa-fast-forward" aria-hidden="true"></i>'
            }
        ]
    });
}

function questionPagingClick(idx) {
    if (idx < 0 || idx > testData.length - 1)
        return;
    activeQuestionIdx = idx;
    createTestByData();
}
