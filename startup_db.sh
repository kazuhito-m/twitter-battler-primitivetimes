#!/bin/bash

# このアプリ用のデータベースを開始するスクリプト
# 前提は
# - Linuxである
# - dockerが使える

docker run --name tbp-db -p 6379:6379 -d redis:3.2.5-alpine
