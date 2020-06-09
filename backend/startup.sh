#!/bin/sh
# 用于在服务器上快速启动 jar 文件

PID=$(lsof -i:80 | grep java | awk '{print$2}')
if [ -n "$PID" ]; then
	echo "Killing old process (pid = $PID)..."
	kill -9 "$PID"
fi
echo "Starting..."
nohup java -jar backend-0.0.1-SNAPSHOT.jar > stdout.log &