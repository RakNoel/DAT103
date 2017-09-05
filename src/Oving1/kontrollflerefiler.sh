#!/usr/bin/env bash
for i in "$@" ; do
    ( ./filkontroll.sh "1" "$i" & )
done
