
function registrationWin() {
    if (!$$('registrationWin')) {
        webix.ui({
            view: "window",
            id: "registrationWin",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: getResourceName("registration.registration")},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "   fa fa-times", css: "buttonIcon",
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
                        id: "registrationWinForm",
                        view: "form",
                        width: 500,
                        elementsConfig: {
                            labelWidth: 100
                        },
                        elements: [
                            {
                                view: "text",
                                name: "uName",
                                label: getResourceName("registration.login"),
                                required: true
                            },
                            {
                                view: "text",
                                name: "fName",
                                label: getResourceName("registration.firstname"),
                                required: true
                            },
                            {
                                view: "text",
                                name: "lName",
                                label: getResourceName("registration.lastname"),
                                required: true
                            },
                            {
                                view: "text",
                                name: "email",
                                label: getResourceName("registration.email"),
                                required: true
                            },
                            {
                                view: "text",
                                name: "password",
                                label: getResourceName("registration.password"),
                                type: "password",
                                required: true
                            },
                            {
                                view: "text",
                                name: "passwordс",
                                label: getResourceName("registration.password"),
                                type: "password",
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
                                template: "<button onclick='registration()' style='width: 145px;'  class='btn btn-success'>" + getResourceName("registration.registration") + "</button>"
                            }, {
                                height: 50,
                                width: 155,
                                css: "noBorder",
                                template: "<button onclick='viewSignInWin()'  style='width: 145px;' class='btn btn-primary'>" + getResourceName("registration.iwasregistr") + "</button>"
                            }
                        ]
                    }
                ]
            }
        }).hide();
    }
    $$('registrationWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}
function registration() {
    var form = $$('registrationWinForm');
    if (form.validate()) {
        var values = form.getValues();
        var json = JSON.stringify(values, null, 2);
        var username = values.uName;
        var password = values.password;
        get_ajax('/study/registration', 'GET', {json: json}, function (gson) {
            if (gson) {
                if (gson.result) {
                    loginSubmit1(username, password)
                } else {
                    messageBox("Ошибка", gson.message);
                }
            } else {
                messageBox("Ошибка", gson);
            }
            console.log(gson)
        }, function (url) {
            messageBox("Ошибка", "Ошибка службы " + url);
        });
    }
}
