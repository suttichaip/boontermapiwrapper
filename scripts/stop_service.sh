#!/bin/bash
if [ "$APPLICATION_NAME" == "BoonTermAPIWrapper" ] ; then
  bwservice=$(cat /etc/bewallet.service)
  if [ "$bwservice" == "AccessControllerServer" ] ; then systemctl stop gradle-oauth ; fi
  if [ "$bwservice" == "APIProxyService" ] ; then systemctl stop gradle-api  ; fi
  if [ "$bwservice" == "BeWalletService" ] ; then systemctl stop gradle-core ; fi
  if [ "$bwservice" == "BeWalletWebBackEnd" ] ; then systemctl stop gradle-backoffice ; fi
  /bin/rm -rf /root/git/${APPLICATION_NAME}
fi
