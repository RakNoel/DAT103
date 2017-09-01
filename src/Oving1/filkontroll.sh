#!/usr/bin/env bash
#filname  = $2
#interval = $1

#Validate parameters
if [ -z $2 ] ; then
    echo "Filename is un-set"
    exit
elif [ -z $1 ] ; then
    echo "Interval is un-set" ;
    exit
fi

###################################

if [ ! -e "$2" ] ; then
    touch "$2"
    echo "Filen $2 ble opprettet"
    exit
fi

forrigeEdit=$(stat --format "%y" "$2")

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
