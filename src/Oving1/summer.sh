#!/usr/bin/env bash
sum=0
while read i; do
    sum=$(($sum + $i))
done

echo "$sum"