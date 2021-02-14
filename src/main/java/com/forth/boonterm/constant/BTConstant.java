package com.forth.boonterm.constant;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class BTConstant {

  public static String BUILD_MODE = "";
  public static String BT_API_SERVER = "";
  public static String BT_API_URL = "";
  public static String BT_CHG  = "beta/charger";
  public static String mDBUrl = "";
  public static String mDBCollections = "";
  public static String BT_API_KBANK="";
  public static String BT_API_SCB="";
  public static String BT_API_BANK="";

  public static String BT_API_BANK_AUTO="bankdep_auto";
  public static String BT_API_BANK_UN_BIND_ACCOUNT="unbindBank";

  public static String mDBCollectionsBeWalletToBoonTerm= "";
  public static String mDBCollectionsBeWalletTransfer= "";
  public static String mDBCollectionsMobileToBeWallet= "";
  public static String mDBCollectionsPgwMobileToBeWallet= "";
  public static String mDBCollectionsPgwBepayToBeWallet = "";
  public static String mDBCollectionsPgwBewalletToBepay = "";
  public static String mDBCollectionsAccessLogApi = "";
  public static String mDBCollectionsRegisterReference = "";
  public static String mDBCollectionsBindingSCB = "";
  public static String mDBCollectionsRegisterKBank = "";
  public static String mDBCollectionsSCBTopup = "";
  public static String mDBCollectionsCampaignTrans = "";
  public static String mDBCollectionsTest = "";
  public static String mDBCollectionsBoonTermKycToBeWallet= "BoonTermKycToBeWallet";

  public static final String BT_SERVICE = "service";
  public static final String BT_PRICE_LIST = "pricelist";
  public static final String BT_PAY_INFO = "payinfo";
  public static final String BT_SMS = "sms";
  public static final String BT_BLACKLIST = "blacklist";
  public static final String BT_WEIGHT_SCALE = "product/weight-scale";

  public static final String BT_BANK_DEPOSIT = "kbank_dep";
  public static final String BT_BANK_WITHDRAW = "kbank_wd";

  public static final String BT_SCB_DEPOSIT = "scb_dep";
  public static final String BT_SCB_WITHDRAW = "scb_wd";

  public static final String BT_BANK_DEPOSIT_AUTO = "kbank_dep_auto";
  public static final String BT_BANK_WITHDRAW_AUTO = "kbank_wd_auto";

  public static final String BT_PAYMENT = "payment";
  public static final String TH = "th_TH";
  public static final String MOBILE_FORMAT = "^[0]{1}+[689]{1}+[0-9]{8}$";
  public static final String ID_FORMAT = "[0-9]{13}$";
  public static final String NUMBER = "^([+-]?\\d*\\.?\\d*)$";

  public static final String ERROR_MESSAGE = "Internal Server Error";
  public static final String BT_RESULT_INVALID = "Invalid internal BT result";
}
