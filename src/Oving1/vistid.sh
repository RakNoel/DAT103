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
echo "Hva er hendelsen? "
read hendelse
sum=0
i=0

for line in $(grep "$hendelse" "$1") ; do
    if [ "$i" -eq 0 ] ; then
        i=1
    else
        sum=$(($sum + $line))
        i=0
    fi
done
echo "$sum"