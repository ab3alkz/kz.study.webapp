$(document).ready(function () {
    webix.ui({
        id: "mainlayot",
        container: "mainContainer",
        autoheight: true,
        scroll: true,
        cols: [
            {
                id: "body",
                rows: [

                ]
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
                                template: "<a>" + i + ' -шы сабақ' + "</a>",
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
        for (var i in gson.message) {
            console.log(gson.message)
            createVideShowWin(gson.message[i], i);
        }

    });
}

function createVideShowWin() {
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
                    {view: 'label', id: 'reportName'},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "print",
                                click: function () {
                                    printReport();
                                }
                            },
                            {
                                view: "icon", icon: "times",
                                click: function () {
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
                            cols: [
                                {},
                                {
                                    id: "textNoticeCont",
                                    height: 500,
                                    width: 700,
                                    template: '<iframe width="700" height="500" src="https://www.youtube.com/embed/pw8qgxpwoZI?list=PLRnwb2a4Ecp5tizeCb_34Xg35RigC6zBO" frameborder="0" allowfullscreen></iframe>'
                                },
                                {}
                            ]
                        }
                    ]
                }],
                elementsConfig: {labelPosition: "top", labelAlign: "left"}
            }
        }).hide();
    }
    $$('reportWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}