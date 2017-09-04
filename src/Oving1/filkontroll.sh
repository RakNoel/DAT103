#!/usr/bin/env bash
#interval = $1
#filname  = $2

exists=$([ ! -e "$2" ])
forrigeEdit=$(stat --format "%y" "$2")

while true; do
    sleep "$1"
    if [ [ -e "$2"  ] -a [ ! "$exists" ] ] ; then
        echo "Filen $2 ble opprettet"
        break
    elif [ [ ! -e "$2" ] -a [ "$exists" ] ] ; then
        echo "Filen $2 ble slettet"
        break
    elif [ [ "$exists" ] -a [ "$forrigeEdit" != "$(stat --format %y $2)" ] ] ; then
        echo "Filen $2 ble endret"
        break
    fi
done
