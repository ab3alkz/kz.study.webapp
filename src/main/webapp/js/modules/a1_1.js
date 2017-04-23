var cons = [
    {
        height: 60,
        cols: [
            {},
            {
                view: 'label',
                template: '<h4 style="color: #d1d1d1;">Сабақ 1.1  <span style="color: black;text-align: center;">Құрылыстар үшін Грамматика</span></h4>'
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
                data: {title: "Image One", src: "../images/urov/a1_1.png" },
                template: function (obj) {
                    return '<img src="'+obj.src+'"/>'
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
        height: 320,
        cols: [
            {},
            {
                width: 800,
                data: {title: "Image One", src: "../images/urov/a1_2.png" },
                template: function (obj) {
                    return '<img src="'+obj.src+'"/>'
                }
            },
            {}
        ]
    },
    {
        height: 320,
        cols: [
            {},
            {
                width: 800,
                data: {title: "Image One", src: "../images/urov/a1_3.png" },
                template: function (obj) {
                    return '<img src="'+obj.src+'"/>'
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
        height: 320,
        cols: [
            {},
            {
                width: 800,
                data: {title: "Image One", src: "../images/urov/a1_4.png" },
                template: function (obj) {
                    return '<img src="'+obj.src+'"/>'
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
    }
];