package com.forth.boonterm.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forth.boonterm.bean.ErrorListItem;
import com.forth.boonterm.bean.ErrorMpayBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public class ErrorMessage {
    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


    public static String mapError(String errorCode, String text) {
        String msg = text;
        switch (errorCode) {
            case "0007":
                msg = "หมายเลขปลายทางที่ระบุ ไม่สามารถทำรายการนี้ได้ \nกรุณาติดต่อผู้รับ/ผู้ให้บริการปลายทาง";
                break;
            case "0071":
                msg = "หมายเลขปลายทางที่ระบุ ไม่สามารถทำรายการนี้ได้ \nกรุณาติดต่อผู้รับ/ผู้ให้บริการปลายทาง";
                break;
            case "0082":
                msg = "เกิดข้อผิดพลาดในการค้นหา \nกรุณาทำรายการใหม่อีกครั้ง";
                break;
            case "0021":
                msg = "หมายเลขปลายทางที่ระบุไม่ถูกต้อง \nกรุณาทำรายการใหม่อีกครั้ง";
                break;
            case "0065":
                msg = "ท่านทำรายการเกินจำนวนเงิน \nหรือจำนวนครั้งที่ปลายทางกำหนด";
                break;
            case "0031":
                msg = "ข้อมูล reference 1 ไม่ถูกต้อง";
                break;
            case "0032":
                msg = "ข้อมูล reference 2 ไม่ถูกต้อง";
                break;
            case "0033":
                msg = "ข้อมูล reference 3 ไม่ถูกต้อง";
                break;
            case "0034":
                msg = "ข้อมูลอ้างอิงไม่ถูกต้อง";
                break;
            case "0017":
                msg = "ช่องทางการให้บริการไม่ถูกต้อง";
                break;
            case "0063":
                msg = "เกินกำหนดเวลาชำระ \nไม่สามารถทำรายการได้";
                break;
            case "0084":
                msg = "ไม่สามารถใช้บริการได้ในขณะนี้";
                break;
            case "0013":
                msg = "จำนวนเงินไม่ถูกต้องกรุณากรอกข้อมูลอีกครั้ง";
                break;
            case "0066":
                msg = "เกินกำหนดระยะเวลาชำระเงิน";
                break;
            case "8021":
                msg = "ผู้ส่งและผู้รับเป็นผู้ให้บริการ E-Wallet เดียวกัน";
                break;
            case "8998":
                msg = "ข้อมูลไม่ถูกต้อง";
                break;
        }
        return msg;
    }

    public static String mapErrorMpay(String errorMsg) {
        String filename = "ErrorMapay.json";
        ErrorMpayBean form = null;
        ApplicationContext appContext = new ClassPathXmlApplicationContext();
        Resource resource = appContext.getResource("classpath:json/" + filename);
        String msg = errorMsg;
        boolean isMap = false;
        try {
            //final File file = resource.getFile();
            final InputStream inputStream = resource.getInputStream();
            final ObjectMapper mapper = new ObjectMapper();
            form = mapper.readValue(inputStream, ErrorMpayBean.class);
            //form = mapper.readValue(file, ResponseInputForm.class);

            if (form != null && form.getErrorList().size() > 0) {
                for (ErrorListItem errorListItem : form.getErrorList()
                ) {
                    if (errorMsg.contains(errorListItem.getErrorCode())) {
                        msg = errorListItem.getMsg();
                        isMap = true;
                    }
                }


            }

        } catch (IOException e) {
        }
        if (isMap) {
            return msg;
        } else {
            return "ไม่สามารถเชื่อมต่อเพื่อทำธุรกรรมได้ในขณะนี้ \n" + msg;
        }

    }
}
