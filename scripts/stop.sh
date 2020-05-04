#!/bin/sh
CURR_PATH=$(cd "$(dirname "$0")";pwd)
APP_DIR=$(dirname $CURR_PATH)
cd $APP_DIR

PROG=

status(){
    PIDs=$(ps -ef | grep $PROG | grep -v grep | awk '{print $2}')
    if["XPIDs" = "X"]; then
        return 1
    else
        return 0
    fi
}
status
RETVAL=$?
if[$RETVAL -eq 0]; then
    echo "shutting down $PROG"
    for pid in ${PIDs[@]};
    do
        kill -9 $pid
        RETVAL=$?
        if[$RETVAL -eq 0]; then
            echo "$pid is stopped"
        else
            echo "Failed to stopping $pid"
        fi
     done
else
    echo "$PROG is not Running"
fi