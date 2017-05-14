function determineLevel() {
    get_ajax('/study/wr/app/getRandom25determineGuestions?start=0&count=40&lang=' + lang, 'GET', null, function (gson) {

        $("#mainContainer").hide();
        $('#gameResultContainerWrapper').hide();
        webix.ui(
            {
                id: "testsContainer",
                container: "testsContainer",
                rows: [
                    {
                        view: 'label',
                        height: 60,
                        autowidth: true,
                        label: "<h3 style='margin: 0'>" + getResourceName('valuation.denggejdianyqtau') + "</h3>"
                    }
                ]
            }
        );

        $('#userInfo').hide();

        $('.mainwrapper').removeClass(' top80px');
        $('.mainwrapper').addClass(' top20px');
        testData = gson;
        createAnswTempl();
    }, function (url) {
        messageBox("Ошибка", "Ошибка службы " + ' ' + url);
    });
}
