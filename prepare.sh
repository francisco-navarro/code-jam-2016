#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Illegal number of parameters"
fi

PROJ=$1

tar -zcvf toSend/`echo $PROJ`.tar.gz $PROJ 
