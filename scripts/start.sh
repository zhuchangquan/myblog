#!/bin/sh

#定义程序名 及jar包的名
PROJECT_NAME=MyBlog-0.0.1-SNAPSHOT.jar

## 编写判断程序是否正在运行的方法
isExist(){
	## 首先查找进程号
    pid=`ps -ef | grep ${PROJECT_NAME} | grep -v grep | awk '{print $2}'`
    ## 如果进程号不存在，则返回0 否则返回1
    if [ -z "${pid}" ]; then
    	return 0
    else
    	return 1
    fi
}

start(){
	## 调用 判断程序是否正在运行的方法
    isExist
    ## 判断方法返回值是否等于0 ，等于则不存在
    if [ $? -eq "0" ]; then
    	echo "${PROJECT_NAME} is starting ......"
    	nohup java -Xms512m -Xmx1024m -jar ${PROJECT_NAME} > ./log/startup.log 2>&1 &
    	echo "${PROJECT_NAME} startup success"
    else
    	echo "${PROJECT_NAME} is running, pid=${pid} "
    fi
}

start