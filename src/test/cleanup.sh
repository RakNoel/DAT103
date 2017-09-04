#!/usr/bin/env bash
#2uker = 1209600
#test

#############################################
#                Cleanup.sh                 #
#############################################
# A small script that can run on at startup #
# and will clean a windows-users downloads  #
# and leaves a log of deleted files.        #
#############################################

downloads=/mnt/c/Users/oskar/Downloads/*

for file in $( ls -A -R -d -1 $downloads ) ; do
    lastused=$( stat -c "%X" "$file" )
    timeIs=$( date "+%s" )
    timeSince=$((timeIs - lastused))
    if [ "$timeSince" -gt 1209600 ] ; then
        ( rm -f -r "$file")
        ( echo -e "$( date "+%D")>  $file" ) >> "/mnt/c/Users/oskar/Downloads/deleted.logg"
    fi
done
