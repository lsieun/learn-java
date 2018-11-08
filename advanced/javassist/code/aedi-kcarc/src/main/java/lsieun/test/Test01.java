package lsieun.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.Signature;

//import com.jetbrains.a.b.s;
//import com.jetbrains.ls.a;
import com.jetbrains.ls.responses.ObtainTicketResponse;
import com.jetbrains.ls.responses.PingResponse;
import com.jetbrains.ls.responses.ResponseCode;

import b.k.aa;

public class Test01 {
    public static void main(String[] args) throws Exception {
        String algorithm = "MD5withRSA";

        Object[] arr = aa.a(algorithm);
        System.out.println(arr.length);
        System.out.println(arr[0]);


//        Method method_getInstance = aa.d(-7486281486869782331L, 75384136046592L);
//        // public static java.security.Signature java.security.Signature.getInstance(java.lang.String)
//        System.out.println(method_getInstance);

//        Signature signature = (Signature)method_getInstance.invoke(null, arr);
//        System.out.println(signature);

        Signature signature = Signature.getInstance("MD5withRSA");
        System.out.println(signature);

//        PublicKey publicKey = a.a;
//        System.out.println(publicKey);
//        Object[] pubKeyArr = aa.a(a.a);
//        System.out.println("len: " + pubKeyArr.length);
//        for (Object obj : pubKeyArr) {
//            System.out.println(obj);
//        }
//
//        Method method_initVerify = aa.d(-7486212924149129043L, 75384136046592L);
//        // public final void java.security.Signature.initVerify(java.security.PublicKey)
//        System.out.println(method_initVerify);

//        signature.initVerify(publicKey);
//
//        String str = "7d3fb49c7e69f5f7f3c2497ccce5d385b0a13bf783bbc666a73c583f1e671079a220c29b6305352086f678480d03ae1c12a4a9ed334a73c6668897b522d8ac05";
//        System.out.println(s.b);

        ObtainTicketResponse response = new ObtainTicketResponse();
        long timestamp = System.currentTimeMillis();
        response.setResponseCode(ResponseCode.OK);
        response.setTicketId("liusen_ticketId");
        String username = System.getProperty("user.name");
        response.setTicketProperties("licensee=" + username + "\tlicenseType=0");
        response.setMessage("liusen ObtainTicketResponse Message");

        response.setSalt(timestamp);
        response.setConfirmationStamp(String.valueOf(timestamp));

        response.setServerUid("liusen_server_id");
        response.setServerLease("liusen_serverlease");
        response.setLeaseSignature("liusen_leasesignature");
        response.setSignature("liusen_signature");

        long period = 3600 * 60 * 24 * 365 * 10;
        response.setProlongationPeriod(period);
        response.setValidationPeriod(period);
        response.setValidationDeadlinePeriod(period);
        //return response;
        //System.out.println(response);

//        PingResponse response = new PingResponse();
//        response.setResponseCode(ResponseCode.OK);
        //return response;
    }
}
