helper = {
    update: function (obj) {
        Object.keys(obj).forEach(function(key) {
            var element = document.getElementById(key)
            if (element != null) {
                element.innerHTML = obj[key]
            } else {
                console.log("cannot find key: " + key)
            }
        });
    }
}