package com.sfwe302;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        disableVerification();
        MySetDemo mySetDemo = new MySetDemo();
        mySetDemo.loadData();
        mySetDemo.applySearch();
        System.out.println(mySetDemo.list.size());
        mySetDemo.createXLS();
        System.out.println(mySetDemo.all.size());

        System.out.println("Size all: " + mySetDemo.list.size());
        System.out.println("Size unique: " + mySetDemo.all.size());
        System.out.println("Size duplicates: " + mySetDemo.duplicates.size());
        System.out.println("Size one occurence: " + mySetDemo.oneOccurence.size());

        for (Entry<String, Integer> entry : MySetDemo.occurancesMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static void disableVerification() {
        // Disable SSL verification
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

}
