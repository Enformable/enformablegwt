function(doc){
    if(doc._id.indexOf("wp_posts_") == 0)
        emit( Date.parse(doc.post_date_gmt).getMilliseconds(),doc._id); //hoping there are milliseconds to get
}