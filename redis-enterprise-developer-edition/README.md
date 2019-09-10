################ Redis Instance ############
-----Start Redis instance----
docker run -d --cap-add sys_resource --network="all_apps_default" \
  --name rp -p 8443:8443 -p 9443:9443 -p 12000:12000 -p 6379:6379 redislabs/redis

---- Create cluster of redislabs/redis named local (user and password) ----
docker exec -d --privileged rp "/opt/redislabs/bin/rladmin" cluster create \
  name cluster.local username admin@infosys.com password admin

---- Setup a new database with size 300 MB ----
curl -k -u "admin@infosys.com:admin" --request POST --url "https://localhost:9443/v1/bdbs" \   
  --header 'content-type: application/json' --data '{"name":"user-event","type":"redis","memory_size":300000000,"port":12000}'

---- Above steps can be done using UI (Only after staring instance) ----
redis: https://localhost:8443/

---- Start the redis-server ----
docker exec -it rp bash

cat ~/redis.conf
daemonize yes
protected-mode no
bind 0.0.0.0

redis-server ~/redis.conf

---- redis-cli to shutdown ----
redis-cli shutdown
