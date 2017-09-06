#!/usr/bin/env bash
read -p "Hva er hendelsen?     > " hendelse
for line in $(grep -w "$hendelse" "$1" | cut -f 2) ; do
   sum=$(($sum + $line))
done

echo "$sum"