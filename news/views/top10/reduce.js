function(keys, values) {

    var a = [];
    for (var i = 0; i < keys.length; i++) {
        a.push([ keys[i],  values[i] ]);
    }
    var sorta = a.sort(function(a, b) {
        return a[0]- b[0]
    });
    var s = sorta.slice(0, 9);
    var v = [];
    for (var i = 0; i < s.length; i++) {
        v .push(s[i][1]);
    }

    return v;
}