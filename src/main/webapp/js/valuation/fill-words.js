var charArray = [];
var allWords;

function startFillWords() {
    get_ajax('/study/wr/app/getRandom10WordList', 'GET', null, function (gson) {
        createFillWords(gson);
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}

function createFillWords(gson) {
    if (gson) {
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
                            onChange: function (newV, oldV) {
                                newV = newV.toLowerCase();
                                var me = this;
                                var sel = getCharBySelected(charArray, this.config.id);
                                if (sel) {
                                    sel.selected = null;
                                }

                                var obj = getCharById(charArray, newV);
                                if (obj) {
                                    obj.selected = this.config.id;
                                    $$(this.config.id + "Required").hide()
                                } else {
                                    if (isNullOrEmpty(newV)) {
                                        $$(this.config.id + "Required").hide();
                                    } else {
                                        $$(this.config.id + "Required").show();
                                    }
                                }
                                setSelectedCharArray();
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
                }
            ]
        });
    }
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
    var templ = "<h4 style='padding: 0 10px'>";
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
            var color="red";
            if (left.toLowerCase() + ch.toLowerCase() + right.toLowerCase() == word.valueKz.toLowerCase()) {
                result++;
                color="navy"
            }
            templ += left.toLowerCase() + '<b style="color: '+color+';">' + ch.toLowerCase() + '</b>' + right.toLowerCase() + " &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;" + word.valueKz.toLowerCase() + "<br />";

            console.log(left.toLowerCase() + ch.toLowerCase() + right.toLowerCase(), word.valueKz.toLowerCase())

        }
    }

    templ += "</h4>";
    fillWordsResultWin(templ, (100 / all * result));
}


function fillWordsResultWin(templ, result) {

    var round = Math.round;
    var x = round(result);
    templ += "<h4> нәтиже:" + x + "%</h4>";
    setGameResult('fillWords', x, templ)
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
                id: "fillWordsResultWinBody",
                rows: []
            }
        }).hide();
    }
    $$('fillWordsResultWin').show(false, false);
    $$("fillWordsResultWinBody").removeView("fillWordsResultWinTempl");
    $$("fillWordsResultWinBody").addView({
        id: "fillWordsResultWinTempl",
        template: templ,
        width: 600,
        height: 400
    });
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function setGameResult(game, result, templ) {
    get_ajax('/study/wr/app/setGameResult', 'GET', {
        gameId: game,
        uName: myuser,
        result: result,
        info: templ
    }, null, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
