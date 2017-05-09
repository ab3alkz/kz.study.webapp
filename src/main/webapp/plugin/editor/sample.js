if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
    CKEDITOR.tools.enableHtml5Elements(document);

CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';

var initSample = (function () {
    var wysiwygareaAvailable = isWysiwygareaAvailable();

    return function () {
        var editorElement = CKEDITOR.document.getById('descRus');
        if (wysiwygareaAvailable) {
            CKEDITOR.replace('descRus');
        } else {
            editorElement.setAttribute('contenteditable', 'true');
            CKEDITOR.inline('descRus');
        }
    };

    function isWysiwygareaAvailable() {
        if (CKEDITOR.revision == ( '%RE' + 'V%' )) {
            return true;
        }
        return !!CKEDITOR.plugins.get('wysiwygarea');
    }
})();

var initSample2 = (function () {
    var wysiwygareaAvailable = isWysiwygareaAvailable();

    return function () {
        var editorElement = CKEDITOR.document.getById('descKaz');
        if (wysiwygareaAvailable) {
            CKEDITOR.replace('descKaz');
        } else {
            editorElement.setAttribute('contenteditable', 'true');
            CKEDITOR.inline('descKaz');
        }
    };
    function isWysiwygareaAvailable() {
        if (CKEDITOR.revision == ( '%RE' + 'V%' )) {
            return true;
        }
        return !!CKEDITOR.plugins.get('wysiwygarea');
    }
})();

var initSample3 = (function () {
    var wysiwygareaAvailable = isWysiwygareaAvailable();

    return function () {
        var editorElement = CKEDITOR.document.getById('descLan');
        if (wysiwygareaAvailable) {
            CKEDITOR.replace('descLan');
        } else {
            editorElement.setAttribute('contenteditable', 'true');
            CKEDITOR.inline('descLan');
        }
    };
    function isWysiwygareaAvailable() {
        if (CKEDITOR.revision == ( '%RE' + 'V%' )) {
            return true;
        }

        return !!CKEDITOR.plugins.get('wysiwygarea');
    }
})();