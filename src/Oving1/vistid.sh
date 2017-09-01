#!/usr/bin/env bash
#fil = $1

#Validate parameter is set
if [ -z $1 ] ; then
    echo "Filename is not set"
    exit
fi

#Validate file exists
if [ ! -e "$1" ] ; then
    echo "Filen $1 finnes ikke"
    exit
fi

#User input
read -p "Hva er hendelsen?     > " hendelse

#Grep, then cut, then sum
for line in $(grep "$hendelse" "$1" | cut -f 2) ; do
   sum=$(($sum + $line))
done

echo "$sum"