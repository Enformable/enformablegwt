
function(doc){
    if (typeof String.prototype.startsWith != 'function') {
      String.prototype.startsWith = function (str){
        return this.indexOf(str) == 0;
      };
    }

    if(doc._id.startsWith("wp_posts_"))
        emit( doc.post_date_gmt,doc._id);

}