var charArray = [];

function startFillWords() {
    get_ajax('/study/wr/app/getRandom10WordList', 'GET', null, function (gson) {
        if (gson) {
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
                            label: "<h3 style='margin-top: 0'>" + left + "</h3>"
                        }, {
                            view: 'text',
                            width: 40,
                            attributes: {maxlength: 1},
                            id: gson[i].id,
                            on: {
                                onChange: function (newV, oldV) {
                                    newV = newV.toLowerCase();
                                    var me = this;
                                    console.log(me)
                                    var sel = getCharBySelected(charArray, this.config.id);
                                    if (sel) {
                                        sel.selected = null;
                                    }

                                    var obj = getCharById(charArray, newV);
                                    if (obj) {
                                        obj.selected = this.config.id;
                                    } else {
                                        //$$(this.config.id + "Required").define("label", "<p style='margin-top: 0;color: red;'>әріп " + newV + " табылмады</p>")
                                        $$(this.config.id + "Required").define("label", "<p style='margin-top: 0;color: red;'>қате</p>")
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
                            label: "<h3 style='margin-top: 0'>" + right + "</h3>"
                        },
                        {
                            view: "label",
                            height: 40,
                            ///autowidth: true,
                            id: gson[i].id + "Required",
                            label: ""
                        }
                    ]
                });

            }

            charArraySort();
            setSelectedCharArray()
        }

    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
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
            return list[i]
        }
    }
    return null;
}

function getCharBySelected(list, selected) {
    for (var i in list) {
        if (list[i].selected == selected) {
            return list[i]
        }
    }
    return null;
}
