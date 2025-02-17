package com.sfwe302;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MySetDemo extends URLLoader {
    protected Set<String> resultSet = new HashSet<>();
    protected List<Product> list = new ArrayList<>();
    protected Set<String> all = new HashSet<>();
    protected Set<String> duplicates = new HashSet<>();
    protected Set<String> oneOccurence = null;
    protected static Map<String, Double> map = null;
    protected static Map<String, Integer> occurancesMap = new HashMap<>();
    protected Map<String, List<Product>> territoryMap = null;

    public static void main(String[] args) {
        disableVerification();
        MySetDemo myListDemo = new MySetDemo();
        myListDemo.loadData();
        myListDemo.applySearch();
        System.out.println(myListDemo.list.size());
        myListDemo.createXLS();
        System.out.println(myListDemo.all.size());

        System.out.println("Size all: " + myListDemo.list.size());
        System.out.println("Size unique: " + myListDemo.all.size());
        System.out.println("Size duplicates: " + myListDemo.duplicates.size());
        System.out.println("Size one occurence: " + myListDemo.oneOccurence.size());

        for (Entry<String, Integer> entry : occurancesMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    protected void applySearch() {
        for (Product product : list) {
            String name = product.getName();
            if (!all.add(name)) {
                duplicates.add(name);
            }

        }
        for (Product product : list) {
            String name = product.getName();
            Integer count = occurancesMap.get(name);
            if (count == null) {
                count = 0;
            }
            occurancesMap.put(name, ++count);
        }

        territoryMap = list.stream().collect(Collectors.groupingBy(Product::getTerritory));
        map = list.stream().sorted((o1, o2) -> o1.getTerritory().compareTo(o2.getTerritory()))
                .collect(Collectors.groupingBy(Product::getTerritory, Collectors.summingDouble(Product::getPrice)));

    }

    protected String[] split(String inputLine) {
        String[] tokens = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].contains("\"")) {
                tokens[i] = tokens[i].replaceAll("\"\"", "\"");
                tokens[i] = tokens[i].replaceAll("^\"", ""); // beginning
                tokens[i] = tokens[i].replaceAll("\"$", ""); // end
            }
        }
        return tokens;
    }

    @Override
    protected void processLine(String[] tokens) {

        Product product = new Product();
        product.setId(Long.parseLong(tokens[0]));
        product.setName(tokens[1]);
        product.setAgentName(tokens[2]);
        product.setAgentId(Long.parseLong(tokens[3]));
        product.setPrice(Double.parseDouble(tokens[5]));
        product.setTerritory(tokens[7]);
        product.setCategory(tokens[8]);
        list.add(product);

    }

    public final void loadData() {
        URL url = null;
        BufferedReader in = null;

        try {
            url = new URL("https://sample-videos.com/csv/Sample-Spreadsheet-1000-rows.csv");

            // this works on Cerny's try UTF-8 if messy chars
            in = new BufferedReader(new InputStreamReader(url.openStream(),
                    StandardCharsets.ISO_8859_1));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                processLine(split(inputLine));
            }

        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    protected void createXLS() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            int rownum = 0;
            XSSFSheet sheet = workbook.createSheet("sheet1");
            for (Product product : list) {
                Row row = sheet.createRow(rownum++);
                createList(product, row);
            }

            rownum = 0;
            XSSFSheet totalCostSheet = workbook.createSheet("Total Costs");
            for (Entry<String, Double> result : map.entrySet()) {
                Row row = totalCostSheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result.getKey());
                cell = row.createCell(1);
                cell.setCellValue(result.getValue());
            }

            rownum = 0;
            XSSFSheet oneOccuranceSheet = workbook.createSheet("One Occurance");
            oneOccurence = new TreeSet<>(all);
            oneOccurence.removeAll(duplicates);

            for (String result : oneOccurence) {
                Row row = oneOccuranceSheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result);
            }

            rownum = 0;
            XSSFSheet sortedSheet = workbook.createSheet("Sorted");
            LinkedHashMap<String, Integer> sortedMap = sortByValue(occurancesMap);
            for (Entry<String, Integer> result : sortedMap.entrySet()) {
                Row row = sortedSheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result.getKey());
                cell = row.createCell(1);
                cell.setCellValue(result.getValue());
            }

            rownum = 0;
            XSSFSheet territorySheet = workbook.createSheet("Territories");
            for (Entry<String, List<Product>> result : territoryMap.entrySet()) {
                Row row = territorySheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result.getKey());
                boolean skipLine = true;
                for (Product product : result.getValue()) {
                    if (skipLine) {
                        skipLine = false;
                    } else {
                        row = territorySheet.createRow(rownum++);
                    }
                    cell = row.createCell(1);
                    cell.setCellValue(product.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(product.getTerritory());
                }
            }

            FileOutputStream out = new FileOutputStream(new File("NewFile6.xlsx")); // file name with path
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createList(Product product, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(product.getId());
        cell = row.createCell(1);
        cell.setCellValue(product.getName());
        cell = row.createCell(2);
        cell.setCellValue(product.getAgentName());
        cell = row.createCell(3);
        cell.setCellValue(product.getAgentId());
        cell = row.createCell(4);
        cell.setCellValue(product.getTerritory());
        cell = row.createCell(5);
        cell.setCellValue(product.getCategory());

    }

    public static LinkedHashMap<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (o2.getValue().equals(o1.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
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
