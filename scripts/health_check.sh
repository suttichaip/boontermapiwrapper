#!/bin/bash
bwservice=$(cat /etc/bewallet.service)
if [ "$bwservice" == "AccessControllerServer" ] ; then bwport = "9100" ; fi
if [ "$bwservice" == "APIProxyService" ] ; then bwport = "9200"; fi
if [ "$bwservice" == "BeWalletService" ] ; then bwport = "9300"; fi
if [ "$bwservice" == "BeWalletWebBackEnd" ] ; then bwport = "9500" ; fi

for i in `seq 1 10`;
do
  #HTTP_CODE=`curl --write-out '%{http_code}' -o /dev/null -m 10 -q -s http://localhost:80`
  #if [ "$HTTP_CODE" == "200" ]; then
  #  echo "Successfully pulled root page."
  #  exit 0;
  #fi
  port=$(netstat -an | grep $bwport | awk '{print $4}' | awk -F: '{print $4}')
  if [ "$port" == "$bwport" ]; then
     echo "start successfully."
     exit 0
  fi
  #echo "Attempt to curl endpoint returned HTTP Code $HTTP_CODE. Backing off and retrying."
  echo "Retrying."
  sleep 10
done

echo "Server did not come up after expected time. Failing."
exit 1
