#!/usr/bin/env bash
#interval = $1
#filname  = $2

if [ ! -e "$2" ] ; then

    
else
    forrigeEdit=$(stat --format "%y" "$2")
fi

while true; do
    sleep "$1"
    if [ ! -e "$2" ] ; then
        echo "Filen $2 ble slettet"
        break
    elif [ "$forrigeEdit" != "$(stat --format %y $2)" ] ; then
        echo "Filen $2 ble endret"
        break
    fi
done
