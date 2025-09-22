//package main.java.com.ismail.insurancemanagement.util;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class SqlFile {
//    public boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException, SQLException {
//        boolean isScriptExecuted = false;
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
//            String str;
//            StringBuffer sb = new StringBuffer();
//            while ((str = in.readLine()) != null) {
//                sb.append(str + "\n ");
//            }
//            in.close();
//            stmt.executeUpdate(sb.toString());
//            isScriptExecuted = true;
//        } catch (Exception e) {
//            System.err.println("Failed to Execute" + aSQLScriptFilePath +". The error is"+ e.getMessage());
//        }
//        return isScriptExecuted;
//    }
//}
