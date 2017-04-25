function getGrammarBanById(paramId) {
    switch (paramId) {
        case "1":
            return grammarBan1(paramId);
        case "2":
            return grammarBan2(paramId);
        case "3":
            return grammarBan3(paramId);
    }
}

function grammarBan1(id) {
    return [
        {
            height: 60,
            cols: [
                {},
                {
                    view: 'label',
                    template: getResourceName('grammar.aone.title' + id)
                },
                {}
            ]
        },
        {height: 10},
        {
            cols: [
                {
                    view: 'label',
                    template: '<span style="color: red">№1</span>'
                }
            ]
        },
        {height: 10},
        {
            height: 320,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/a1_1.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Так говорят</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 1:    -  Жатуға бола ма?</b></br> -  Иә, болады, жатыңыз.</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 2:    - Ішуге бола ма?</b></br> - Жоқ, болмайды, ішуді қойыныз. </span>'
        },
        {
            cols: [
                {
                    view: 'label',
                    template: '<span style="color: red">№1</span>'
                }
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Қай жерде? // Қай жақта?</span>'
        },
        {
            height: 360,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/a1_2.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            height: 360,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/a1_3.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Қай жерде? // Қай жақта?</span>'
        },
        {
            height: 360,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/a1_4.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Так говорят</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 1:    - Сұрауға бола ма? Интернет кафе  қай жақта?</b></br> - Вокзалдың оң жағында.</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 2:    - Айтып жіберіңізші, осы маңайда банкомат бар ма?</b></br>- Иә, Рамстор дүкенінде бар.</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 3:    - Кешіріңіз, айтып жібересіз бе, мұражай қай жерде?</b></br>- Республика даңғылында.</span>'
        },
        {
            view: 'label',
            height: 60,
            template: '<span><b>Модель 4:    - Кешіріңіз, дәріхана қай жерде?</b></br>- Дәріхана келесі көшеде.</span>'
        },
        {height: 20},
        {
            view: 'label',
            template: '<span style="font-size: medium; color: #993300;"><strong>ОБРАТИТЕ ВНИМАНИЕ</strong></span>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="font-size: medium;">В казахском языке образование слов и словоизменения регулируются законом сингармонизма (в пределах слова гласные звуки должны быть единообразны и созвучны).&nbsp; Выбор фонетического варианта аффикса обусловлен конечным звуком и характером основы – жуан (твердый) и жіңішке (мягкий). </span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="font-size: medium;">Если слог основы содержит твердые гласные: <strong><span style="text-decoration: underline;">а, о, ұ, ы,</span></strong> то окончания тоже будут твердыми: мұ-ра-жай-<strong>да</strong>, даң-ғы-лы-<strong>нда. </strong>Если слог основы имеет мягкие гласные:<strong> <span style="text-decoration: underline;">ә, ө, ү, і, е,</span> </strong>то окончания тоже будут мягкими: кө-ше-<strong>де</strong>, мек-теп-<strong>те</strong>, Рамстор дү-кен -і-<strong>нде. </strong>В казахском языке встречаются слова и с мягкими и с твердыми гласными. В таком случае окончания будут добавляться по последнему гласному звуку: қонақ<strong><span style="text-decoration: underline;">ү</span></strong>й-<strong>де, </strong>дәріхан<strong><span style="text-decoration: underline;">а</span></strong>-<strong>да.</strong></span></p>'
        },
        {
            view: 'label',
            template: '<p><span style="font-size: medium;"><strong><span style="color: #993300;">Вопросительное предложение образуется двумя способами: </span></strong></span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="font-size: medium;">1) с помощью вопросительных частиц <strong>ма, ме, ба, бе, па, пе</strong> (соответствует русской частице <em>ли</em>, в казахской речи они никогда не опускаются).</span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="font-size: medium;">2) с помощью вопросительных слов <strong>кім? не? қайда? қашан? қалай? қандай?</strong></span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="font-size: medium;">- Служебные слова самостоятельно не употребляются. Они выражают отношение говорящего к высказываемой мысли, и придают отдельным словам и словосочетаниям &nbsp;следующие оттенки: 1) подтверждение <em>(иә – да, әрине – конечно, дұрыс – правильно);</em> 2)предположение <em>(мүмкін – может быть/ возможно, шығар – наверное, кажется и т. д).</em></span><br><span style="font-size: medium;"><br>- Слово «пожалуйста» переводится с помощью суффикса <strong>-шы/ші</strong>. Когда вы хотите попросить о чем-нибудь вы говорите: <em>айтшы</em> (скажи, пожалуйста), <em>көмектесші</em> (помоги, пожалуйста), <em>айтыңызшы</em> (скажите, пожалуйста), <em>көмектесіңізші </em>(помогите, пожалуйста).</span></p>'
        }
    ];
}

function grammarBan2(id) {
    return [
        {
            height: 60,
            cols: [
                {},
                {
                    view: 'label',
                    template: getResourceName('grammar.aone.title' + id)
                },
                {}
            ]
        },
        {height: 10},
        {
            cols: [
                {
                    view: 'label',
                    template: '<span style="color: red">№1</span>'
                }
            ]
        },
        {height: 10},
        {
            view: 'label',
            template: '<span style="color: #00a379">Қайда бара жатырсың?</span>'
        },
        {height: 10},
        {
            height: 320,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/" + id + "_1.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Қайдан келе жатырсың?</span>'
        },
        {
            height: 320,
            cols: [
                {},
                {
                    width: 800,
                    data: {title: "Image One", src: "../images/urov/" + id + "_2.png"},
                    template: function (obj) {
                        return '<img src="' + obj.src + '"/>'
                    }
                },
                {}
            ]
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Так говорят</span>'
        },
        {
            view: 'label',
            height: 200,
            template: '<p><span style="font-size: medium;"><em>Модель 1:&nbsp;</em>&nbsp;&nbsp; -&nbsp; Балам, қайдан келе жатырсың? </span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Вокзалдан келе жатырмын, апа.</span><br><br><span style="font-size: medium;"><em>Модель 2:</em>&nbsp;&nbsp;&nbsp; - Ата, қайда бара жатырсыз?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Емханаға бара жатырмын. </span></p>'
        },
        {height: 10},
        {
            cols: [
                {
                    view: 'label',
                    template: '<span style="color: red">№1</span>'
                }
            ]
        },
        {height: 10},
        {
            view: 'label',
            template: '<span style="color: #800000;"><strong><span style="font-size: medium;">Сіз қазір не істеп жатырсыз?</span></strong></span>'
        },
        {
            view: 'label',
            template: '<span style="font-size: medium;">(Что вы сейчас делаете? Чем вы заняты в данный момент?)</span>'
        },
        {
            view: 'label',
            height: 600,
            template: '<table style="width: 848px; height: 372px; border: 3px solid #e88816;" border="3" cellspacing="0" cellpadding="8"> <tbody> <tr> <td style="width: 376px; border: 1px solid #e88816;" valign="top"> <p><span style="font-size: medium;"><strong>Мен</strong></span></p> <p><span style="font-size: medium;">Мен Астанада   жұмыс істе-<strong>п жүр-мін</strong></span></p> <p><span style="font-size: medium;">Мен Алматыда   тұр-<strong>ып жатыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен музыка   тыңда-<strong>п отыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен азық-түлік   сатып ал-<strong>ып жатыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен Бурабайда   демал-<strong>ып жатыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен телефонмен   сөйлес-<strong>іп жатыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен кино көр-<strong>іп жатыр-мын</strong></span></p> <p><span style="font-size: medium;">Мен анама көмектесіп<strong> жатыр-мын</strong></span></p> <p>&nbsp;</p> </td> <td style="width: 274px; border: 1px solid #e88816;" valign="top"> <p><span style="font-size: medium;"><strong>Я</strong></span></p> <p><span style="font-size: medium;">Я работаю в   Астане</span></p> <p><span style="font-size: medium;">Я живу в   Алматы</span></p> <p><span style="font-size: medium;">Я слушаю   музыку</span></p> <p><span style="font-size: medium;">Я покупаю   продукты</span></p> <p><span style="font-size: medium;">Я отдыхаю в   Боровом</span></p> <p><span style="font-size: medium;">Я говорю по телефону</span></p> <p><span style="font-size: medium;">Я смотрю кино</span></p> <p><span style="font-size: medium;">Я помогаю маме</span></p> <p>&nbsp;</p> </td> </tr> <tr> <td colspan="2" width="650" valign="top"> <p><span style="font-size: medium;"><strong>!</strong>Глагол (основной)<strong>&nbsp; +&nbsp;   -ып/ -іп/ -п&nbsp; + жатыр, отыр,   тұр, жүр </strong>(вспомогательные глаголы) <strong><em>+</em> личные окончания (мын/мін<em>) </em></strong><strong>= </strong><strong>настоящее время глагола</strong></span><strong>&nbsp;</strong></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            height: 200,
            template: '<span style="font-size: medium;"><em>Модель 1:</em>&nbsp;&nbsp;&nbsp; -&nbsp; Сен не істеп жатырсың? <strong><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Кино көріп жатырмын. <br><br></strong><em>Модель 2:&nbsp;</em>&nbsp;&nbsp; -&nbsp; Сіз қазір не істеп жатырсыз?<strong><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Телефонмен сөйлесіп жатырмын. <br><br></strong><em>Модель 3:</em>&nbsp;&nbsp; -&nbsp; Не істеп отырсың? &nbsp;<strong><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Музыка тыңдап отырмын. <br><br></strong></span>'
        },
        {
            view: 'label',
            template: '<p><span style="font-size: medium;"><strong>№ 3. </strong>Употребление глаголов движения в прошедшем, настоящем и будущем времени. </span></p>'
        },
        {
            view: 'label',
            height: 300,
            template: '<table style="width: 856px; height: 250px; border: 3px solid #e88816;" border="3" cellspacing="0" cellpadding="8"> <tbody> <tr> <td style="width: 208px; border: 1px solid #e88816;" valign="top"> <p><span style="font-size: medium;"><strong>Кеше </strong>қайда бардың?</span></p> <p><span style="font-size: medium;">(Куда ты ходил <strong>вчера</strong>?)</span></p> </td> <td width="265" valign="top"> <p><span style="font-size: medium;"><strong>Қазір</strong> қайда бара   жатырсың?</span></p> <p><span style="font-size: medium;">(Куда ты идешь <strong>сейчас?)</strong></span></p> </td> <td style="width: 227px; border: 1px solid #e88816;" valign="top"> <p><span style="font-size: medium;"><strong>Ертең </strong>қайда барасың?</span></p> <p><span style="font-size: medium;">(Куда ты пойдешь <strong>завтра?)</strong></span></p> </td> </tr> <tr> <td width="208" valign="top"> <p><span style="font-size: medium;"><em>Кеше   киноға <strong>бардым.</strong></em></span></p> <p><span style="font-size: medium;"><em>Кеше   концертке <strong>бардым.</strong></em></span></p> <p><span style="font-size: medium;"><em>Кеше   жұмысқа<strong> бардым.</strong></em></span></p> </td> <td style="width: 265px; border: 1px solid #e88816;" valign="top"> <p><span style="font-size: medium;"><em>Қазір үйге   <strong>бара жатырмын.</strong></em></span></p> <p><span style="font-size: medium;"><em>Қазір   дүкенге <strong>бара жатырмын.</strong></em></span></p> <p><span style="font-size: medium;"><em>Қазір   банкке <strong>бара жатырмын.</strong></em></span></p> </td> <td width="227" valign="top"> <p><span style="font-size: medium;"><em>Ертең   сабаққа <strong>барамын.</strong></em></span></p> <p><span style="font-size: medium;"><em>Ертең   қонаққа <strong>&nbsp;барамын</strong>.</em></span></p> <p><span style="font-size: medium;"><em>Ертең   базарға <strong>барамын.</strong> </em></span></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            template: '<p><span style="font-size: medium; color: #800000;"><strong>Так говорят</strong></span><br><span style="font-size: medium;"><strong>&nbsp;</strong></span></p>'
        },
        {
            view: 'label',
            height: 200,
            template: '<p><span style="font-size: medium;"><em>Модель 1:</em>&nbsp;&nbsp;&nbsp; -&nbsp; Алмат, <strong>кеше </strong>қайда бардың?<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - <strong>Кеше </strong>киноға <strong>бардым</strong>. <br><br><em>Модель 2:&nbsp;</em>&nbsp;&nbsp; -&nbsp; Апа, <strong>қазір </strong>қайда бара жатырсыз?<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Балабақшаға <strong>бара жатырмын.</strong><br>&nbsp;<br><em>Модель 3:</em>&nbsp;&nbsp;&nbsp; - Қымбаттым, <strong>ертең </strong>қайда барасың?<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Ертең сұлулық салонына <strong>барамын</strong>, сосын дүкенге барамын.</span></p>'
        },
        {
            view: 'label',
            template: '<strong><span style="font-size: medium;">ОБРАТИТЕ ВНИМАНИЕ</span></strong>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="color: #000000;"><span style="font-size: medium;">Только к глаголам <strong>бару, келу, апару, әкелу</strong>&nbsp; при образовании настоящего времени добавляются&nbsp; <em><strong>–а /-е + жатыр. Мен бара жатырмын / келе жатырмын /апара жатырмын/ әкеле жатырмын.</strong></em><br></span></span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="color: #000000;"><span style="font-size: medium;">Глаголы <strong>тұр, отыр, жатыр, жүр,</strong> при помощи которых образуется настоящее время, характеризует состояние дейтвующего лица. В таких случаях вспомогательные глаголы теряют свое лексическое значение и обозначают лишь грамматическую категорию.<br></span></span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="color: #000000;"><span style="font-size: medium;">ЖАТЫР придает действию постоянный характер и указывает на длительность: <em>Мен бара жатырмын – Я иду (продолжаю идти). Айгүл институтта оқып жатыр – Айгул учится (продолжает учиться) в институте. </em><br></span></span></p>'
        },
        {
            view: 'label',
            template: '<p style="text-align: justify;"><span style="color: #000000;"><span style="font-size: medium;">Как же звучит на казахском языке вопрос «А ты (вы, мы, они)?». Просто добавьте вопросительную частицу <strong>ше</strong>? Вопросительная частица <span style="text-decoration: underline;"><strong>ше </strong></span>не имеет точного эквивалента в русском языке. Мен үйге барамын, сен ше? – Я пойду домой, а ты? Келмесем ше?&nbsp; А если я не приду? А они? – Ал олар ше? <br></span></span></p>'
        }
    ]
}

function grammarBan3(id) {
    return [
        {
            height: 60,
            cols: [
                {},
                {
                    view: 'label',
                    template: getResourceName('grammar.aone.title' + id)
                },
                {}
            ]
        },
        {height: 10},
        {
            cols: [
                {
                    view: 'label',
                    template: '<span style="color: red">№1</span>'
                }
            ]
        },
        {height: 10},
        {
            view: 'label',
            template: '<span style="color: #00a379">Сен кеше не істедің?/ Что ты делал/сделал вчера?</span>'
        },
        {
            view: 'label',
            height: 400,
            template: '<table border="1" cellspacing="0" cellpadding="8" width="843" height="291"> <tbody> <tr> <td width="227" valign="top"> <p><span style="font-size: medium;"><strong>Таңертең</strong> (утром):</span></p> <p><span style="font-size: medium;">- ерте тұр-<strong>ды-м</strong></span></p> <p><span style="font-size: medium;">- таңғы асымды іш<strong>-ті-м</strong></span></p> <p><span style="font-size: medium;">- тез киін-<strong>ді-м</strong></span></p> <p><span style="font-size: medium;">- сосын жұмысқа кет-<strong>ті-м</strong></span></p> <p>&nbsp;</p> </td> <td width="246" valign="top"> <p><span style="font-size: medium;"> <strong>Күндіз </strong>(днем):</span></p> <p><span style="font-size: medium;">- жұмыста/сабақта бол-<strong>ды</strong><strong>-</strong><strong>м</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - семинарға бар-<strong>ды</strong><strong>-</strong><strong>м</strong>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- досыммен кездес-<strong>ті-м</strong></span></p> <p><span style="font-size: medium;">- қатты шарша-<strong>ды</strong><strong>-</strong><strong>м</strong></span></p> </td> <td width="213" valign="top"> <p><span style="font-size: medium;"><strong>Кешке</strong> (вечером):</span></p> <p><span style="font-size: medium;">- досыммен кездес-<strong>ті</strong><strong>-</strong><strong>м</strong></span></p> <p><span style="font-size: medium;">- үйге кел-<strong>ді-м</strong></span></p> <p><span style="font-size: medium;">- кешкі асымды іш-<strong>ті-м</strong></span></p> <p><span style="font-size: medium;">- теледидар көр-<strong>ді-м</strong></span></p> </td> </tr> <tr> <td colspan="3" width="686" valign="top"> <p class="2"><span style="font-size: medium;">Основа глагола   +&nbsp; суффиксы <span style="color: #ff0000;"><strong>-<span style="text-decoration: underline;">ды/-ді, -ты/-ті</span></strong></span>&nbsp; + личные   окончания =   <strong><em>&nbsp;</em></strong></span></p> <p><span style="font-size: medium;"><strong>очевидное прошедшее время</strong><strong>&nbsp;</strong></span></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Так говорят  // Сөйлеу үлгісі </span>'
        },
        {
            view: 'label',
            height: 400,
            template: '<p><strong><span style="font-size: medium;"> Вопрос&nbsp;&nbsp;&nbsp; -&gt; &nbsp;&nbsp; Ответ</span></strong><br><br><span style="font-size: medium;">Модель 1:&nbsp;&nbsp; – Қанат, кеше қайда болдың?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; – Досыммен бірге киноға бардым. </span><br><br><span style="font-size: medium;">Модель 2:&nbsp;&nbsp; – Сәуле Сакеновна, сіз қайда болдыңыз?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; – Мен емханада болдым.</span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<br><span style="font-size: medium;">Модель 3:&nbsp;&nbsp; – Кеше футбол көрдің бе?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; – Жоқ, үйге кеш келдім, қатты шаршадым. &nbsp;</span></p>'
        },
        {
            view: 'label',
            height: 600,
            template: '<table border="1" cellspacing="0" cellpadding="8" width="843" height="368"><tbody> <tr> <td width="196" valign="top"> <p><span style="font-size: medium; color: #ff0000;">–<strong>ма, -ме</strong></span></p> </td> <td colspan="2" width="189" valign="top"> <p><span style="font-size: medium;"><strong><span style="font-size: medium; color: #ff0000;"><strong>-ба, -бе</strong></span></strong></span></p> </td> <td width="255" valign="top"> <p><span style="font-size: medium; color: #ff0000;"><strong>-па, -пе</strong></span></p> </td> </tr> <tr> <td width="196" valign="top"> <p><span style="font-size: medium;">футбол көр-<strong><span style="text-decoration: underline;">ме</span></strong>-ді-м</span></p> <p><span style="font-size: medium;">кеше ұйықта-<strong><span style="text-decoration: underline;">ма</span></strong>-ды-м</span></p> <p>&nbsp;</p> </td> <td colspan="2" width="189" valign="top"> <p><span style="font-size: medium;">хат жаз-<strong><span style="text-decoration: underline;">ба</span></strong>-ды-м</span></p> <p><span style="font-size: medium;">жылы киін -<strong><span style="text-decoration: underline;">бе</span></strong>-ді-м</span></p> <p>&nbsp;</p> </td> <td width="255" valign="top"> <p><span style="font-size: medium;">досыммен хабарлас-<strong><span style="text-decoration: underline;">па</span></strong>-ды-м</span></p> <p><span style="font-size: medium;">таңғы ас іш-<strong><span style="text-decoration: underline;">пе</span></strong>-ді-м</span></p> <p>&nbsp;</p> </td> </tr> <tr> <td colspan="2" width="294" valign="top"> <p><span style="font-size: medium;">Сен&nbsp; келмедің</span></p> <p><span style="font-size: medium;">Сіз бұл туралы айтпадыңыз</span></p> <p><span style="font-size: medium;">Біз кездеспедік</span></p> <p><span style="font-size: medium;">Сендер жазбадыңдар</span></p> <p><span style="font-size: medium;">Олар&nbsp; қатыспады</span></p> <p><strong>&nbsp;</strong></p> </td> <td colspan="2" width="347" valign="top"> <p><span style="font-size: medium;">Ты не пришел</span></p> <p><span style="font-size: medium;">Вы не говорили об этом</span></p> <p><span style="font-size: medium;">Мы не встретились</span></p> <p><span style="font-size: medium;">Вы не писали</span></p> <p><span style="font-size: medium;">Они не участвовали</span></p> </td> </tr> <tr> <td colspan="4" width="640" valign="top"> <p class="2"><span style="font-size: medium;">Основа глагола + аффиксы   отрицания <span style="color: #ff0000;">–<strong><span style="text-decoration: underline;">ма,   -ме, -ба, -бе, -па, -пе</span></strong></span> + суффиксы <span style="color: #ff0000;"><strong>-<span style="text-decoration: underline;">ды/-ді, </span></strong></span>+ личные окончания = <strong>отрицательная форма прошедшего времени</strong></span></p> <p><strong>&nbsp;</strong></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">№ 2 Кіммен? Немен?</span>'
        },
        {
            view: 'label',
            height: 600,
            template: '<table style="width: 839px; height: 301px;" border="1" cellspacing="0" cellpadding="8" width="687" height="350"> <tbody> <tr> <td width="262" valign="top"> <p><span style="font-size: medium;"><strong>Кіммен?&nbsp;&nbsp; </strong> <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  Асқар-<strong>мен</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></p> <p><span style="font-size: medium;">Мен&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Бақыт<strong>-пен</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Жұлдыз<strong>-бен</strong> <strong>&nbsp;</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> <p>&nbsp;</p> </td> <td width="170" valign="top"> <div> <p><span style="font-size: medium;">кездестім</span></p> <p><span style="font-size: medium;">сөйлестім&nbsp;&nbsp;</span></p> <p><span style="font-size: medium;">әңгімелестім&nbsp;</span></p> <p><span style="font-size: medium;">хабарластым</span></p> </div> <p>&nbsp;</p> </td> </tr> <tr> <td width="262" valign="top"> <p><span style="font-size: medium;"><strong>Немен?</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   автобус-<strong>пен </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> <p><span style="font-size: medium;">Мен&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   машина-<strong>мен</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   пойыз<strong>-бен</strong> <strong>&nbsp;</strong></span></p> </td> <td width="170" valign="top"> <p>&nbsp;</p> <p><span style="font-size: medium;">кеттім</span></p> <p><span style="font-size: medium;">бардым</span></p> <p><span style="font-size: medium;">келдім</span></p> <p>&nbsp;</p> </td> </tr> <tr> <td colspan="2" width="432" valign="top"> <p><span style="font-size: medium;"><strong>&nbsp;! </strong>Слово +<strong> </strong><span style="color: #ff0000;">- <strong>мен, -пен, -бен</strong><strong> </strong></span>= обозначает совместность действий,   средство передвижения</span></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">Так говорят // Сөйлеу үлгісі  </span>'
        },
        {
            view: 'label',
            height: 200,
            template: '<p><span style="font-size: medium;"> Вопрос&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -&gt; &nbsp;&nbsp;&nbsp;&nbsp; Ответ</span><br><br><span style="font-size: medium;">Модель 1:&nbsp;&nbsp;&nbsp;&nbsp; – Асқар, кешке қайда болдың?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; – Мен&nbsp; Жұлдызбен кездестім.</span><br><br><span style="font-size: medium;">Модель 2:&nbsp;&nbsp;&nbsp;&nbsp; –&nbsp; Мен ұшақпен келдім, сен ше?</span><br><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; – Мен пойызбен келдім.</span><br><br><span style="font-size: medium;">&nbsp;</span></p>'
        },
        {
            view: 'label',
            template: '<span style="color: #00a379">№3</span>'
        },
        {
            view: 'label',
            height: 400,
            template: '<table border="1" cellspacing="0" cellpadding="8" width="659" height="248" align="left"> <tbody> <tr> <td width="243" valign="top"> <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; әріптес-<strong>ім</strong></span></p> <p><span style="font-size: medium;"><strong>Менің&nbsp;&nbsp; </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; дос-<strong>ым</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; құрбы-<strong>м</strong></span></p> <p><span style="font-size: medium;"><strong><em>&nbsp;</em></strong></span></p> <p><span style="font-size: medium;"><strong><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </em></strong>туыс-<strong>ың</strong></span></p> <p><span style="font-size: medium;"><strong>Сенің </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;дүкен-<strong>ің</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; машина-<strong>ң</strong>&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;</p> <p>&nbsp;</p> </td> <td width="267" valign="top"> <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;   бастығ-<strong>ыңыз</strong></span></p> <p><span style="font-size: medium;"><strong>Сіздің&nbsp; </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; көше-<strong>ңіз</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; көліг-<strong>іңіз</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <strong>&nbsp;</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; пойыз-<strong>ы</strong></span></p> <p><span style="font-size: medium;"><strong>Оның&nbsp; </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; көше-<strong>сі</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; құрбы-<strong>сы</strong></span></p> <p>&nbsp;</p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            template: '<p><span style="font-size: medium; color: #800000;"><strong>ОБРАТИТЕ ВНИМАНИЕ</strong></span></p>'
        },
        {
            view: 'label',
            height: 200,
            template: '<p style="text-align: justify;"><span style="font-size: medium;">- Окончания <strong><span style="text-decoration: underline;">–ды, -ді, -ты, -ті</span></strong><span style="text-decoration: underline;"> </span>– формы <strong>жедел өткен шақ</strong> (недавно прошедшее время). Они прибавляются к основе глагола и выражают законченное действие, которое происходило в ближайшее к моменту речи время:<em> <strong>Досыммен кездестім. Ерте тұрдым. Тез киіндім. </strong></em></span></p>'
        },
        {
            view: 'label',
            height: 200,
            template: '<p style="text-align: justify;"><span style="font-size: medium;"><strong>- Жедел өткен шақ</strong> принимает личные окончания І и ІІ лица. В ІІІ лице личные окончания не прибавляются:</span></p>'
        },
        {
            view: 'label',
            height: 300,
            template: '<table border="1" cellspacing="0" cellpadding="8"> <tbody> <tr> <td width="319" valign="top"> <p><span style="font-size: medium;">Жекеше</span></p> </td> <td width="319" valign="top"> <p><span style="font-size: medium;">Көпше</span></p> </td> </tr> <tr> <td width="319" valign="top"> <p><span style="font-size: medium;">І. Мен жаз-ды-<strong>м</strong></span></p> <p><span style="font-size: medium;">ІІ. Сен жаз-ды<strong>-ң</strong></span></p> <p><span style="font-size: medium;">&nbsp;&nbsp;&nbsp; Сіз жаз-ды-<strong>ңыз</strong></span></p> <p><span style="font-size: medium;">ІІІ. Ол жаз-ды</span></p> </td> <td width="319" valign="top"> <p><span style="font-size: medium;">Біз жаз-ды-<strong>қ</strong></span></p> <p><span style="font-size: medium;">Сендер жаз-ды-<strong>ң-дар</strong></span></p> <p><span style="font-size: medium;">Сіздер жаз-ды-<strong>ңыз-дар</strong></span></p> <p><span style="font-size: medium;">Олар жаз-ды</span></p> </td> </tr> </tbody> </table>'
        },
        {
            view: 'label',
            height: 300,
            template: '<p style="text-align: justify;"><span style="font-size: medium;">- Притяжательные местоимения обязательно согласуются с предметом принадлежности, т.е. к основе имени существительного прибавляются специальные притяжательные окончания в соответствии с законом сингармонизма. Если слово оканчивается на<strong><span style="text-decoration: underline;"> п, к, қ,</span></strong> то после прибавления притяжательных окончаний глухие согласные переходят в звонкие <strong><span style="text-decoration: underline;">б, г, ғ</span></strong>:<strong><em> бастық</em></strong><strong><em>-</em></strong><strong><em>бастығы, көлік </em></strong><strong><em>–</em></strong><strong><em> көлігі, кітап </em></strong><strong><em>–</em></strong><strong><em> кітабы.</em></strong></span></p>'
        }
    ]
}