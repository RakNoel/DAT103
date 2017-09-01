#!/usr/bin/env bash
for i in "$@" ; do
    ( ./filkontroll.sh "2" "$i" & )
done
wait
