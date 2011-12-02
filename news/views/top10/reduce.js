function(keys,values){

    var newKeys = keys.slice(0);

    var nk = newKeys.sort().reverse().slice(0, 10);
    var nv = [];
    for (var i = 0; i < values.length; i++) {
          nv.add(values[keys.indexOf(nk[i])]) = values[i];

    }

    return nv;
}