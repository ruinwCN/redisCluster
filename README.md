# redisCluster

download redis.conf

```
http://download.redis.io/redis-stable/redis.conf
```

```
/mnt/redis/master/data/redis.conf

add

​```
dir /data 
appendonly yes
requirepass 123456 
bind 0.0.0.0 
​```

/mnt/redis/slavel1/data/redis.conf
​```
dir /data 
appendonly yes
bind 0.0.0.0 
replicaof 192.168.0.2 6379
masterauth 123456
​```


/mnt/redis/slavel2/redis.conf
​```
dir /data 
appendonly yes
bind 0.0.0.0 
replicaof 192.168.0.2 6379
masterauth 123456
​```


192.168.0.2 is localnetwork
```



cluster: 

1 master,2 slavel

```shell
# master
docker run --name redis-master -p 6379:6379 \
		   -v /mnt/redis/master/data:/data \
		   -v /mnt/redis/master/data/redis.conf:/etc/redis/redis.conf \
		   -d redis redis-server /etc/redis/redis.conf
```

```shell
# slavel 1
docker run --name redis-slave1 -p 6380:6379 \
		   -v /mnt/redis/slave1/data:/data \
		   -v /mnt/redis/slave1/data/redis.conf:/etc/redis/redis.conf \
		   -d redis redis-server /etc/redis/redis.conf
```

```shell
# slavel 2
docker run --name redis-slave2 -p 6381:6379 \
		   -v /mnt/redis/slave2/data:/data \
		   -v /mnt/redis/slave2/data/redis.conf:/etc/redis/redis.conf \
		   -d redis redis-server /etc/redis/redis.conf
```



redis-cli

\# info replication

```shell
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:2
slave0:ip=172.17.0.1,port=6379,state=online,offset=1597,lag=0
slave1:ip=172.17.0.1,port=6379,state=online,offset=1597,lag=0
master_replid:2d54dd749bc2577088077edf95d2e7e84a87ce49
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:1597
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:1597
```

