var charArray;
var allWords;

function startFillWords( ) {
    get_ajax('/study/wr/app/getRandom10WordList', 'GET', {idTest: activeGameId}, function (gson) {
        createFillWords(gson);
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createFillWords(gson) {
    if (gson) {
        charArray = [];
        allWords = gson;
        $$("fillWordsContainer").removeView("fillWordsArena");
        $$("fillWordsContainer").addView({
            id: "fillWordsArena",
            css: "fill-words-arena",
            rows: []
        });

        var lastRand = 0;

        $$("fillWordsArena").addView({
            id: "fillWordsArenaCharsWrap",
            rows: []
        });

        $$("fillWordsArena").addView({height: 30});
        for (var i in gson) {
            var word = gson[i].valueKz;
            var ch = getRandomInt(1, word.length - 1);
            if (lastRand == ch) {
                ch = getRandomInt(1, word.length - 1);
            }
            var left = word.substring(0, ch - 1);
            var right = word.substring(ch);
            charArray.push({id: word.substring(ch - 1, ch).toLowerCase()});

            $$("fillWordsArena").addView({
                cols: [
                    {
                        view: 'text',
                        id: gson[i].id + "Word",
                        value: word,
                        hidden: true
                    },
                    {
                        view: "label",
                        height: 40,
                        autowidth: true,
                        labelAlign: 'right',
                        hidden: left.length == 0,
                        id: gson[i].id + "Left",
                        label: "<h3 style='margin-top: 0'>" + left + "</h3>",
                        word: left
                    }, {
                        view: 'text',
                        width: 40,
                        attributes: {maxlength: 1},
                        id: gson[i].id,
                        on: {
                            onTimedKeyPress: function (newV) {
                                onChangeFillWordCenter(this.getValue(), this)
                            },
                            onChange: function (newV, oldV) {
                                onChangeFillWordCenter(newV, this)

                            }
                        }
                    },
                    {
                        view: "label",
                        height: 40,
                        autowidth: true,
                        id: gson[i].id + "Right",
                        label: "<h3 style='margin-top: 0'>" + right + "</h3>",
                        word: right
                    },
                    {
                        view: "label",
                        height: 40,
                        width: 200,
                        hidden: true,
                        id: gson[i].id + "Required",
                        label: "<p style='margin: 0;color: red'>Тізімде бар әріпті жазыңыз</p>"
                    }
                ]
            });

        }

        charArraySort();
        setSelectedCharArray();

        $$("fillWordsContainer").removeView("fillWordsRequireBtnWrap");
        $$("fillWordsContainer").addView({
            id: "fillWordsRequireBtnWrap",
            rows: [
                {
                    height: 30
                },
                {
                    height: 50,
                    id: "fillWordsRequireBtn",
                    width: 155,
                    css: "noBorder",
                    template: "<button id='fillWordsRequireBtn' style='width: 145px;' onclick=\"fillWordsRequired('fillWordsRequireBtn')\" class='btn btn-success'>Аяқтау</button>",
                },
                {
                    height: 50,
                    id: "fillWordsRestartBtn",
                    width: 195,
                    hidden: true,
                    css: "noBorder",
                    template: "<button id='fillWordsRestartBtn' style='width: 145px;' onclick=\"startFillWords('fillWordsRestartBtn')\" class='btn btn-primary'>Қайтадан бастау</button>",
                }
            ]
        });
    }
}

function onChangeFillWordCenter(newV, me) {
    newV = newV.toLowerCase();
    var sel = getCharBySelected(charArray, me.config.id);
    if (sel) {
        sel.selected = null;
    }

    var obj = getCharById(charArray, newV);
    if (obj) {
        obj.selected = me.config.id;
        $$(me.config.id + "Required").hide()
    } else {
        if (isNullOrEmpty(newV)) {
            $$(me.config.id + "Required").hide();
        } else {
            $$(me.config.id + "Required").show();
        }
    }
    setSelectedCharArray();
}

function setSelectedCharArray() {

    $$("fillWordsArenaCharsWrap").removeView("fillWordsArenaChars")
    $$("fillWordsArenaCharsWrap").addView({
        id: "fillWordsArenaChars",
        cols: []
    });
    for (var i in charArray) {
        var obj = charArray[i];
        var className = "fill-words-arena-chars";
        if (obj.selected) {
            className += " fill-words-arena-line-through";
        }
        $$("fillWordsArenaChars").addView({
            view: "label",
            width: 30,
            height: 30,
            label: "<h3 class='" + className + "'>" + obj.id + "</h3>"
        });
    }
}

function charArraySort() {
    charArray.sort(function (a, b) {
        var c = a.id,
            d = b.id;

        if (c < d) {
            return -1;
        } else if (c > d) {
            return 1;
        }
        return 0;
    });
}

function getCharById(list, id) {
    for (var i in list) {
        if (list[i].id == id && isNullOrEmpty(list[i].selected)) {
            return list[i];
        }
    }
    return null;
}

function getCharBySelected(list, selected) {
    for (var i in list) {
        if (list[i].selected == selected) {
            return list[i];
        }
    }
    return null;
}

function fillWordsRequired(btnId) {
    var btn = $('#' + btnId);
    btn.prop('disabled', true);
    var json = {};
    json.data = [];
    var result = 0;
    var all = 0;
    if (allWords) {
        for (var i in allWords) {
            all++;
            var word = allWords[i];
            var inp = $$(word.id);
            if (inp) {
                inp.define("readonly", true);
                inp.adjust()
            }
            var left = $$(word.id + "Left").config.word;
            var right = $$(word.id + "Right").config.word;
            var sel = getCharBySelected(charArray, word.id);
            var ch = "_";
            if (sel) {
                ch = sel.id;
            }
            var result_ = false;
            if (left.toLowerCase() + ch.toLowerCase() + right.toLowerCase() == word.valueKz.toLowerCase()) {
                result++;
                var result_ = true;
            }
            var resultObj = {};
            if (!isNullOrEmpty(left)) {
                resultObj.left = left.toLowerCase();
            }
            resultObj.center = ch.toLowerCase();
            if (!isNullOrEmpty(right)) {
                resultObj.right = right.toLowerCase();
            }
            resultObj.word = word.valueKz.toLowerCase();
            resultObj.id = word.id;
            resultObj.result = result_;

            json.data.push(resultObj)
        }
    }

    var round = Math.round;
    var total = round(100 / all * result);
    json.total = total;
    fillWordsResultWin(json, total);
}


function fillWordsResultWin(json, total) {

    setGameResult(total, json, function (gson) {
        $$("fillWordsRequireBtn").hide();
        $$("fillWordsRestartBtn").show();
    })
    if (!$$('fillWordsResultWin')) {
        webix.ui({
            view: "window",
            id: "fillWordsResultWin",
            modal: true,
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Сынақ нәтижесі"},
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
                id: "fillWordsResultWinBody",
                rows: []
            }
        }).hide();
    }
    $$('fillWordsResultWin').show(false, false);
    $$("fillWordsResultWinBody").removeView("fillWordsResultWinTempl");
    $$("fillWordsResultWinBody").addView({
        id: "fillWordsResultWinTempl",
        template: getFillWordsResultByJson(json),
        width: 600,
        height: 400
    });
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}


function getFillWordsResultByJson(json) {
    var result = "";
    var data = json.data;
    for (var i in data) {
        var obj = data[i];
        result += "<h4>" + nvl(obj.left, '') + "<span style='color: " + (obj.result ? "navy" : "red") + "'>" + obj.center + "</span>" + nvl(obj.right, '') + "  &nbsp; " + obj.word + "</h4>";
    }
    result += " <h3>Нәтиже:" + json.total + "</h3>";
    return result;
}


function editFillWords(item) {

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
                        template: "<button onclick='editWord({idTest:" + item.id + "})' style='width: 145px;'  class='btn btn-success'>жаңа сөз қосу</button>"
                    }
                ]
            },
            {
                view: "datatable",
                css: "appTable",
                id: "fillWordsAdminTable",
                scroll: false,
                header: false,
                footer: false,
                minHeight: 400,
                autoheight: true,
                url: "/study/wr/app/getWordsDataByIdTest?idTest=" + item.id,
                rowHeight: 45,
                select: 'row',
                columns: [
                    {id: "index", header: "№пп", width: 60},
                    {
                        id: "valueKz",
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
                        obj.editBtn = "<button style='width:40px;'  class='editWord btn btn-primary'><i class=\" fa fa-pencil-square-o\" aria-hidden=\"true\"></i></button>";
                        obj.removBtn = "<button style='width:40px;'  class='removeWord btn btn-danger'><i class=\" fa fa-trash\" aria-hidden=\"true\"></i></button>";

                    }
                },
                onClick: {
                    editWord: function (e, item, cell) {
                        setTimeout(function () {
                            var obj = $$("fillWordsAdminTable").getSelectedItem();
                            editWord(obj);
                        }, 100)
                    },
                    removeWord: function (e, item, cell) {
                        setTimeout(function () {
                            removeWord(item.row);
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

function editWord(item) {

    if (!$$('editWordWin')) {
        webix.ui({
            view: "window",
            id: "editWordWin",
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
                    {view: "label", id: "editWordLabel", label: getResourceName("valuation.editword")},
                    {view: "label", id: "addQuestionLabel", label: getResourceName("valuation.addword")},
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
                        id: "editWordWinForm",
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
                                name: "idTest",
                                required: true,
                                hidden: true
                            },
                            {
                                view: "text",
                                name: "valueKz",
                                label: "Сөз",
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
                                template: "<button onclick='saveWord()' style='width: 145px;'  class='btn btn-success'>Сақтау</button>"
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
        $$("editWordLabel").show();
        $$("addQuestionLabel").hide();
    } else {
        $$("editWordLabel").hide();
        $$("addQuestionLabel").show();
    }
    $$("editWordWinForm").parse(item);
    $$('editWordWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function saveWord() {
    var form = $$("editWordWinForm");
    if (!form.validate()) {
        return;
    }
    var strJson = JSON.stringify(form.getValues(), null, 1);
    get_ajax('/study/wr/app/saveWord', 'POST', strJson, function (gson) {
            if (gson && gson.result) {
                $$("fillWordsAdminTable").parse(gson.message);
                $$("editWordWin").hide();
                notifyMessage("Инфо", "сөз сақталды", notifyType.success);
            } else {
                messageBox("Ошибка", gson.message);
            }
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + ' ' + url);
        }
    );
}

function removeWord(id) {
    get_ajax('/study/wr/app/removeWordById?id=' + id, 'GET', null, function (gson) {
        if (gson && gson.result) {
            $$("fillWordsAdminTable").remove(id);
        } else {
            messageBox("Ошибка", gson.message);
        }
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
