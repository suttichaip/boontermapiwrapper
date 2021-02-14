#!/bin/bash
export PATH="$PATH:/opt/gradle-2.13/bin"
if [ "$APPLICATION_NAME" == "BoonTermAPIWrapper" ] ; then
  bwservice=$(cat /etc/bewallet.service)
  if [ "$bwservice" == "AccessControllerServer" ] ; then systemctl start gradle-oauth ; fi
  if [ "$bwservice" == "APIProxyService" ] ; then systemctl start gradle-api  ; fi
  if [ "$bwservice" == "BeWalletService" ] ; then systemctl start gradle-core ; fi
  if [ "$bwservice" == "BeWalletWebBackEnd" ] ; then systemctl start gradle-backoffice ; fi
fi
