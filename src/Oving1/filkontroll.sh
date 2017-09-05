#!/usr/bin/env bash
#interval = $1
#filname  = $2

[ -e "$2" ] ; exists=$?
if [ "$exists" == 0 ]; then
	forrigeEdit=$(stat --format "%y" "$2")
fi

while true; do
    sleep "$1"
    if [ -e "$2"  ] && (( $exists == 1 )) ; then
        echo "Filen $2 ble opprettet"
        break
    elif [ ! -e "$2" ] && (( $exists == 0 )) ; then
        echo "Filen $2 ble slettet"
        break
    elif (( $exists == 0 )) && [ "$forrigeEdit" != "$(stat --format %y $2)" ] ; then
        echo "Filen $2 ble endret"
        break
    fi
done
