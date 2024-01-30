#!/bin/sh

systemctl stop kafka
systemctl stop zookeeper

kafka_logs=/var/kafka-logs
zookeeper=/var/zookeeper

rm -rf $kafka_logs
rm -rf $zookeeper

mkdir $kafka_logs
chown user $kafka_logs

mkdir $zookeeper
chown user $zookeeper

systemctl start zookeeper
systemctl start kafka
