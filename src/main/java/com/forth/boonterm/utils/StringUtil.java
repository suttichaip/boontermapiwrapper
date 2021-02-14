package com.forth.boonterm.utils;

/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public class StringUtil {
  public static String replace(String txt, String src[], String des[]) {
    for (int i = 0; i < src.length; i++)
      txt = txt.replace(src[i], des[i]);
    return txt;
  }
}
