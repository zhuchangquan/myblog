#!/bin/sh
CURR_PATH=$(cd "$(dirname "$0")";pwd)
APP_DIR=$(dirname $CURR_PATH)
cd $APP_DIR

PROG=

if[ ! -d $APP_DIR/logs/dump]; then
    mkdir -p $APP_DIR/logs/dump
fi

if[ ! -d $APP_DIR/logs/gc]; then
    mkdir -p $APP_DIR/logs/gc
fi

touch $APP_DIR/logs/gc/GCLog

JAVA_OPTS="-Xmx 5024m -XX:+useG1GC -XX:MaxGcPauseMills=200 -XX:+HeapDumpOnOutofMemoryError -XX:HeapDumpPath=$APP_DIR/logs/dump"
JAVA_OPTS="$JAVA_OPTS -XX:PrintGCDetails"
JAVA_OPTS="$JAVA_OPTS -XX:PrintGCDateStamps"
JAVA_OPTS="$JAVA_OPTS -Xloggc:$APP_DIR/logs/gc/GCLog"
RUN_JAR=

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
    echo "process is already running.pid : $PIDs"
    exit 1
fi
echo "starting $PROG ..."
java -server $JAVA_OPTS -jar $RUN_JAR > /dev/null 2>&1 &
RETVAL=$?

if[$RETVAL -eq 0]; then
    echo "$PROG is started. pid : $!"
else
    echo "$PROG start failed"
fi