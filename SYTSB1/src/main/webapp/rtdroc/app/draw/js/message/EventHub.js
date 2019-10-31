var EventHub = function () {
        var hub = {};
        var register = function (event, callback) {
            if (!(event in hub)) {
                hub[event] = [];
            }
            if (typeof callback !== 'function') {
                throw new Error('Register callback must be a function.')
            }
            hub[event].push(callback);
        };
        var dispatch = function (event) {
            if (!(event in hub)) {
                return;
            }
            for (var i in hub[event]) {
                hub[event][i].apply(this, [].slice.call(arguments, 1));
            }
        };
        return {
            register: register,
            dispatch: dispatch,
            on: register,
            emit: dispatch
        };
    }();