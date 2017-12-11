package com.punuo.sys.app.queryip;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String ip;
    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String result=query();
                if (result!=null){
                    parseResult(result);
                }
            }
        }.start();
//        QueryTask task = new QueryTask();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"jsonp","find","f6e53cb2591f8ed640354e552c54df3d");
//        } else {
//            task.execute("jsonp","find","f6e53cb2591f8ed640354e552c54df3d");
//        }
    }

    private void parseResult(String  result) {
        IPBean ipBean=new Gson().fromJson(result,IPBean.class);
        Log.d("query",ipBean.getIp());

    }

    //    class QueryTask extends AsyncTask<String,Void,String>
//    {
//        @Override
//        protected String doInBackground(String... strings) {
//        String strUrl = "http://api.ip138.com/query/" ;
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("datatype",strings[0]);
//        params.put("callback",strings[1]); //可为空
//        String result = null;
//            try {
//                URL url = new URL(strUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                //头信息
//                HashMap<String, String> header = new HashMap<String, String>();
//                header.put("token", strings[2]);
//                for (String headerName : header.keySet()) {
//                    connection.addRequestProperty(headerName, header.get(headerName));
//                }
//                //设置超时时间
//                connection.setConnectTimeout(5 * 1000);
//                //设置允许输入
//                connection.setDoInput(true);
//                //设置读取超时：
//                connection.setReadTimeout(5 * 1000);
//                connection.setRequestMethod("GET");
//                //参数
//                byte[] body = encodeParameters(params, "UTF-8");
//                if (body != null) {
//                    connection.setDoOutput(true);
//                    connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + "UTF-8");
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    out.write(body);
//                    out.close();
//                }
//                int responseCode = connection.getResponseCode();
//                if (responseCode == -1) {
//                    throw new IOException("Could not retrieve response code from HttpUrlConnection.");
//                }
//                if (responseCode != 200) {
//                    return responseCode + "";
//                }
//                InputStream inputStream = null;
//                try {
//                    inputStream = connection.getInputStream();
//                } catch (IOException ioe) {
//                    inputStream = connection.getErrorStream();
//                }
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] data = new byte[1024];
//                int len = 0;
//                if (inputStream != null) {
//                    try {
//                        while ((len = inputStream.read(data)) != -1) {
//                            outputStream.write(data, 0, len);
//                        }
//                        result = new String(outputStream.toByteArray(), "UTF-8");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return result;
//            } catch (SocketTimeoutException e) {
//
//            } catch (MalformedURLException e) {
//
//            } catch (IOException e) {
//
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Log.i("Query", "onPostExecute: " + s);
//        }
//
//        byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
//            if (params == null || params.size() == 0) return null;
//
//            StringBuilder encodedParams = new StringBuilder();
//            try {
//                for (Map.Entry<String, String> entry : params.entrySet()) {
//                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
//                    encodedParams.append('=');
//                    encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
//                    encodedParams.append('&');
//                }
//                return encodedParams.toString().getBytes(paramsEncoding);
//            } catch (UnsupportedEncodingException uee) {
//                throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
//            }
//        }
//    }
    private String query(){
        String strUrl = "http://api.ip138.com/status/" ;
        Map<String,String> params = new HashMap<String,String>();
//        params.put("datatype","jsonp");
//        params.put("callback","find"); //可为空
        String result = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //头信息
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("token", "f6e53cb2591f8ed640354e552c54df3d");
            for (String headerName : header.keySet()) {
                connection.addRequestProperty(headerName, header.get(headerName));
            }
            //设置超时时间
            connection.setConnectTimeout(5 * 1000);
            //设置允许输入
            connection.setDoInput(true);
            //设置读取超时：
            connection.setReadTimeout(5 * 1000);
            connection.setRequestMethod("GET");
            //参数
            byte[] body = encodeParameters(params, "UTF-8");
            if (body != null) {
                connection.setDoOutput(true);
                connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + "UTF-8");
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.write(body);
                out.close();
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            if (responseCode != 200) {
                return responseCode + "";
            }
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException ioe) {
                inputStream = connection.getErrorStream();
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            if (inputStream != null) {
                try {
                    while ((len = inputStream.read(data)) != -1) {
                        outputStream.write(data, 0, len);
                    }
                    result = new String(outputStream.toByteArray(), "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d("query", result);
            return result;
        } catch (SocketTimeoutException e) {

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return null;
    }
    byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        if (params == null || params.size() == 0) return null;

        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            Log.d("query", encodedParams.toString());
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

}

