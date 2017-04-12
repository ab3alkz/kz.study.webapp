$(document).ready(function () {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        autoheight: true,
        scroll: true,
        cols: [
            {
                id: "body",
                rows: []
            }
        ]
    });
    lessons();
});

function lessons() {
    for (var i = 1; i < 13; i++) {
        $$('body').addView(
            {
                id: "vContent" + i,
                rows: [
                    {
                        cols: [
                            {},
                            {
                                view: 'label',
                                id: i,
                                template: "<a>" + i + ' -шi сабақ' + "</a>",
                                click: function () {
                                    getData(this.data.id)
                                }
                            },
                            {}
                        ]
                    }
                ]
            }
        )
    }
}

function getData(id) {
    get_ajax('/study/wr/lrn/getVideoById', 'GET', {id: id}, function (gson) {
        if (!gson || !gson.result) {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
            return;
        }
        createVideShowWin(gson.message.value, gson.message.addValue);
    });
}

function createVideShowWin(value, title) {
    var cont_h = $(window).height() - 70;
    var cont_w = $(window).width() - 70;
    if (!$$('reportWin')) {
        webix.ui({
            view: "window",
            id: "reportWin",
            modal: true,
            scroll: true,
            position: "center",
            height: cont_h,
            width: cont_w,
            on: {
                onHide: function () {
                    window.onscroll = null;
                    $$("reportWinBody").removeView("123");
                }
            },
            head: {
                cols: [
                    {width: 10},
                    {view: 'label', id: 'videName'},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {},
                            {
                                view: "icon", icon: "times", css: 'buttonIconRed',
                                click: function () {
                                    $$('videContent').removeView('videoCont');
                                    this.getTopParentView().hide();
                                    window.onscroll = null;
                                }
                            },
                            {width: 5}
                        ]
                    }
                ]
            },
            body: {
                view: "form",
                scroll: false,
                elements: [{
                    id: "reportWinBody",
                    rows: [
                        {
                            id: 'videContent',
                            height: 500,
                            width: 700,
                            cols: []
                        }
                    ]
                }],
                elementsConfig: {labelPosition: "top", labelAlign: "left"}
            }
        }).hide();
    }
    $$('reportWin').show(false, false);
    $$('videName').setValue(title);
    $$('videContent').addView(
        {
            id: "videoCont",
            height: 500,
            width: 700,
            template: '<iframe width="700" height="500" src="' + value + '" frameborder="0" allowfullscreen></iframe>'
        }
    );
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}