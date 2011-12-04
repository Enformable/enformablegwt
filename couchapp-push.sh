#!/bin/bash
rm -fr news/_attachments target/news-1.0-SNAPSHOT/*-INF
cp -a target/news-1.0-SNAPSHOT news/_attachments
pushd news
couchapp push
couchapp push http://fucknutreport.com:5984/enf1
