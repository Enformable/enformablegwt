function(keys, values) {

    var a = [];
    for (var i = 0; i < keys.length; i++) {
        a.push({date: keys[i], id: values[i] });
    }
    var sorta = a.sort(function(a, b) {
        return a.date - b.date
    });
    return sorta.slice(0, 9);
}