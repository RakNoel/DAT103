#!/usr/bin/env bash
#filname  = $1
#interval = $2

#Validate parameters
if [ -z $1 ] ; then
    echo "Filename is un-set"
    exit
elif [ -z $2 ] ; then
    echo "Interval is un-set" ;
    exit
fi

###################################

if [ ! -e "$1" ] ; then
    touch "$1"
    echo "Filen $1 ble opprettet"
    exit
fi

forrigeEdit=$(stat --format "%y" "$1")

while true; do
    sleep "$2"
    if [ ! -e "$1" ] ; then
        echo "Filen $1 ble slettet"
        break
    elif [ "$forrigeEdit" != "$(stat --format %y $1)" ] ; then
        echo "File $1 ble endret"
        break
    fi
done   