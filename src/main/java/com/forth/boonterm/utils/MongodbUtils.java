package com.forth.boonterm.utils;

import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.gateway.KBankDepositReqImpl;
import com.forth.boonterm.model.common.KeyValue;
import com.forth.boonterm.model.log.LogBeWalletToBeMall;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.request.LogBoonTermKycToBeWallet;
import com.mongodb.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by khan2j on 2/23/2017 AD.
 */
public class MongodbUtils {

    public static final SimpleDateFormat SDF_REQUEST_HEAD_ID = new SimpleDateFormat("yyyyMMdd HHmmss", Locale.US);
    private final static Logger logger = LoggerFactory.getLogger(MongodbUtils.class);

    public static void logBoontermGateway(Map<String, Object> mdataLog, String dataLogResp, String urlCall) {
        Map<String, Object> dataLog = new HashMap<String, Object>();

        if (!StringUtils.isEmpty(mdataLog)) {
            dataLog.putAll(mdataLog);
            dataLog.replace("options", getKeyValue((List) dataLog.get("options")));
        }

        if (!StringUtils.isEmpty(dataLogResp)) {
            dataLog.put("response", dataLogResp);
        }

        dataLog.put("urlCalls", urlCall);
        dataLog.put("datetime", SDF_REQUEST_HEAD_ID.format(new Date()));

        try {
            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            MongoClient client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(BTConstant.mDBCollections);
            mongoCollection.insert(new BasicDBObject(dataLog));

            client.close();
        } catch (Exception e) {
        }
    }

    public static Map<String, Object> getKeyValue(List list) {
        Map<String, Object> v = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                KeyValue mobj = (KeyValue) obj;
                v.put(mobj.getKey(), mobj.getValue());
            }
        }
        return v;
    }

    public static String getKeyValueBykey(List list, String k) {
        String v = null;
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                KeyValue mobj = (KeyValue) obj;
                if (mobj.getKey().equalsIgnoreCase(k)) {
                    v = mobj.getValue().toString();
                }
            }
        }
        return v;
    }

    public static BasicDBObject insertLogMongo(LogBeWalletToBoonTerm logBeWalletToBoonTerm) {

        MongoClient client = null;
        BasicDBObject basicDBObject = null;

        try {
            basicDBObject = convertLogBeWalletToBoonTerm(logBeWalletToBoonTerm);

            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(BTConstant.mDBCollectionsBeWalletToBoonTerm);
            mongoCollection.insert(basicDBObject);

            client.close();
        } catch (Exception e) {
            logger.error("insertLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return basicDBObject;
    }

    public static BasicDBObject updateLogMongo(BasicDBObject basicDBObject, LogBeWalletToBoonTerm logBeWalletToBoonTerm) {
        MongoClient client = null;
        BasicDBObject updateBasicDBObject = convertLogBeWalletToBoonTerm(logBeWalletToBoonTerm);

        try {
            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(BTConstant.mDBCollectionsBeWalletToBoonTerm);
            mongoCollection.update(basicDBObject, updateBasicDBObject);
            //mongoCollection.findAndModify(basicDBObject,updateBasicDBObject);

        } catch (Exception e) {
            logger.error("updateLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return updateBasicDBObject;
    }

    public static BasicDBObject convertLogBeWalletToBoonTerm(LogBeWalletToBoonTerm logBeWalletToBoonTerm) {

        BasicDBObject basicDBObject = new BasicDBObject();
        Map<String, Object> request = new HashMap<String, Object>();

        logBeWalletToBoonTerm.setRequest(new HashMap(logBeWalletToBoonTerm.getRequest()));

        basicDBObject.put("timeSend", logBeWalletToBoonTerm.getTimeSend());
        basicDBObject.put("timeResponse", logBeWalletToBoonTerm.getTimeResponse());
        basicDBObject.put("timeSendTimeStamp", logBeWalletToBoonTerm.getTimeSendTimeStamp());
        basicDBObject.put("timeResponseTimeStamp", logBeWalletToBoonTerm.getTimeResponseTimeStamp());

        if (!StringUtils.isEmpty(logBeWalletToBoonTerm.getRequest())) {
            request.putAll(logBeWalletToBoonTerm.getRequest());
            request.replace("options", getKeyValue((List) request.get("options")));

        }

        basicDBObject.put("request", request);
        basicDBObject.put("pathUrl", logBeWalletToBoonTerm.getPathUrl());
        basicDBObject.put("responseCode", logBeWalletToBoonTerm.getResponseCode());
        basicDBObject.put("transactionType", logBeWalletToBoonTerm.getTransactionType());
        basicDBObject.put("serviceName", logBeWalletToBoonTerm.getServiceName());
        basicDBObject.put("responseStr", logBeWalletToBoonTerm.getResponseStr());

        return basicDBObject;
    }

    public static BasicDBObject insertLogMongoBepay(LogBeWalletToBoonTerm logBeWalletToBoonTerm, String collection) {

        MongoClient client = null;
        BasicDBObject basicDBObject = null;

        try {
            basicDBObject = convertLogBepay(logBeWalletToBoonTerm);

            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(collection);
            mongoCollection.insert(basicDBObject);

            client.close();
        } catch (Exception e) {
            logger.error("insertLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return basicDBObject;
    }

    public static BasicDBObject updateLogMongoBepay(BasicDBObject basicDBObject, LogBeWalletToBoonTerm logBeWalletToBoonTerm, String collection) {
        MongoClient client = null;
        BasicDBObject updateBasicDBObject = convertLogBepay(logBeWalletToBoonTerm);

        try {
            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(collection);
            mongoCollection.update(basicDBObject, updateBasicDBObject);
            //mongoCollection.findAndModify(basicDBObject,updateBasicDBObject);

        } catch (Exception e) {
            logger.error("updateLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return updateBasicDBObject;
    }

    public static BasicDBObject convertLogBepay(LogBeWalletToBoonTerm logBeWalletToBoonTerm) {

        BasicDBObject basicDBObject = new BasicDBObject();
        Map<String, Object> request = new HashMap<String, Object>();
        Map<String, Object> responseBepay = new HashMap<String, Object>();

        logBeWalletToBoonTerm.setRequest(new HashMap(logBeWalletToBoonTerm.getRequest()));

        basicDBObject.put("timeSend", logBeWalletToBoonTerm.getTimeSend());
        basicDBObject.put("timeResponse", logBeWalletToBoonTerm.getTimeResponse());
        basicDBObject.put("timeSendTimeStamp", logBeWalletToBoonTerm.getTimeSendTimeStamp());
        basicDBObject.put("timeResponseTimeStamp", logBeWalletToBoonTerm.getTimeResponseTimeStamp());

        if (!StringUtils.isEmpty(logBeWalletToBoonTerm.getRequest())) {
            request.putAll(logBeWalletToBoonTerm.getRequest());
            request.replace("options", getKeyValue((List) request.get("options")));

        }

        if (!StringUtils.isEmpty(logBeWalletToBoonTerm.getResponseBepay())) {
            responseBepay.putAll(logBeWalletToBoonTerm.getResponseBepay());
        }

        basicDBObject.put("request", request);
        basicDBObject.put("pathUrl", logBeWalletToBoonTerm.getPathUrl());
        basicDBObject.put("responseBepay", responseBepay);
        basicDBObject.put("responseCode", logBeWalletToBoonTerm.getResponseCode());
        basicDBObject.put("transactionType", logBeWalletToBoonTerm.getTransactionType());
        basicDBObject.put("serviceName", logBeWalletToBoonTerm.getServiceName());

        return basicDBObject;
    }


    public static BasicDBObject convertBeWalletToBeMall(LogBeWalletToBoonTerm logBeWalletToBoonTerm) {

        BasicDBObject basicDBObject = new BasicDBObject();
        Map<String, Object> request = new HashMap<String, Object>();

        logBeWalletToBoonTerm.setRequest(new HashMap(logBeWalletToBoonTerm.getRequest()));

        basicDBObject.put("timeSend", logBeWalletToBoonTerm.getTimeSend());
        basicDBObject.put("timeResponse", logBeWalletToBoonTerm.getTimeResponse());
        basicDBObject.put("timeSendTimeStamp", logBeWalletToBoonTerm.getTimeSendTimeStamp());
        basicDBObject.put("timeResponseTimeStamp", logBeWalletToBoonTerm.getTimeResponseTimeStamp());

        basicDBObject.put("request", request);
        basicDBObject.put("pathUrl", logBeWalletToBoonTerm.getPathUrl());
        basicDBObject.put("responseCode", logBeWalletToBoonTerm.getResponseCode());
        basicDBObject.put("transactionType", logBeWalletToBoonTerm.getTransactionType());
        basicDBObject.put("serviceName", logBeWalletToBoonTerm.getServiceName());
        basicDBObject.put("responseStr", logBeWalletToBoonTerm.getResponseStr());

        return basicDBObject;
    }


    public static BasicDBObject insertLogMongo(LogBoonTermKycToBeWallet logBeWalletToBoonTerm) {

        MongoClient client = null;
        BasicDBObject basicDBObject = null;

        try {
            basicDBObject = convertLogBeWalletToBoonTerm(logBeWalletToBoonTerm);

            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(BTConstant.mDBCollectionsBoonTermKycToBeWallet);
            mongoCollection.insert(basicDBObject);

            client.close();
        } catch (Exception e) {
            logger.error("insertLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return basicDBObject;
    }

    public static BasicDBObject updateLogMongo(BasicDBObject basicDBObject, LogBoonTermKycToBeWallet logBeWalletToBoonTerm) {
        MongoClient client = null;
        BasicDBObject updateBasicDBObject = convertLogBeWalletToBoonTerm(logBeWalletToBoonTerm);

        try {
            MongoClientURI uri = new MongoClientURI(BTConstant.mDBUrl);
            client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection mongoCollection = db.getCollection(BTConstant.mDBCollectionsBoonTermKycToBeWallet);
            mongoCollection.update(basicDBObject, updateBasicDBObject);
            //mongoCollection.findAndModify(basicDBObject,updateBasicDBObject);

        } catch (Exception e) {
            logger.error("updateLogMongo : {}", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {

                }
            }
        }

        return updateBasicDBObject;
    }

    public static BasicDBObject convertLogBeWalletToBoonTerm(LogBoonTermKycToBeWallet logBeWalletToBoonTerm) {

        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("timeSend", logBeWalletToBoonTerm.getTimeSend());
        basicDBObject.put("timeResponse", logBeWalletToBoonTerm.getTimeResponse());
        basicDBObject.put("timeSendTimeStamp", logBeWalletToBoonTerm.getTimeSendTimeStamp());
        basicDBObject.put("timeResponseTimeStamp", logBeWalletToBoonTerm.getTimeResponseTimeStamp());
        basicDBObject.put("errorDescription", logBeWalletToBoonTerm.getErrorDescription());
        basicDBObject.put("transactionNo", logBeWalletToBoonTerm.getTransactionNo());

        basicDBObject.put("requestStr", logBeWalletToBoonTerm.getRequestStr());
        basicDBObject.put("pathUrl", logBeWalletToBoonTerm.getPathUrl());
        basicDBObject.put("responseCode", logBeWalletToBoonTerm.getResponseCode());
        basicDBObject.put("responseStr", logBeWalletToBoonTerm.getResponseStr());

        return basicDBObject;
    }


}