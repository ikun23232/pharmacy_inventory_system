//package com.kgc.utils;
//
//import com.kgc.easybuy.pojo.Product;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EncodingUtil {
//
//
//    public static List<Product> encoding(List<Product> products) {
//        List<Product> productList = new ArrayList<>();
//        String path = null;
//        try {
//            for (Product product : products) {
//                if (product.getFilePath() != null) {
//                    path = URLEncoder.encode(product.getFilePath(), "utf-8");
//                }
//                product.setFilePath(path);
//                productList.add(product);
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return productList;
//    }
//}