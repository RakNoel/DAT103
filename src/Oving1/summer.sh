#!/usr/bin/env bash
while read i; do
    sum=$(($sum + $i))
done
echo "$sum"