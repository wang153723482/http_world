package com.wangc.orgin;



import net.sf.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangc on 2022/1/11.
 *
 * @author wangc
 */
public class HttpReq {
    /**
     * 发送Post请求，参数拼接到url
     * @return
     * @throws Exception
     */
    
    public static void main(String[] args){
        try{
            Map m = new HashMap();
            m.put("name","wangc");
            sendPost("http://127.0.0.1:38080/hello_body",m);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    static Log log = new Log();
    
    public static String sendPost(String httpUrl, Map<String, String> map) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            StringBuffer param = new StringBuffer();
            boolean isFristPar = true;
            for(String key : map.keySet()){

                if(isFristPar == false){
                    param.append("&");
                }
                isFristPar = false;
                String val = URLEncoder.encode(map.get(key),"UTF-8");
                param.append(String.format("%1$s=%2$s",key,val));
            }

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
            
            
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection(proxy);
            // 设置连接请求方式
            connection.setRequestMethod("GET");
            // 设置连接主机服务器超时时间
            connection.setConnectTimeout(3000);
            // 设置读取主机服务器返回数据超时时间
            connection.setReadTimeout(3000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/json");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.toString().getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            log.error("{}",e);
        } catch (IOException e) {
            log.error("{}",e);
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("{}",e);
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("{}",e);
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("{}",e);
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    /**
     * 发送Post请求，参数放到Body中
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String sendPostBody(String url, Map<String, String> map){
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (map.equals("") == false) {
                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, String> mt : map.entrySet()){
                    jsonObject.put(mt.getKey(),mt.getValue());
                }
                out.write(jsonObject.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("","============>发送请求异常！");
            log.error("",e);
            return null;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(in!=null){
                    in.close();
                }
                if(out!=null){
                    out.close();
                }
            }
            catch(IOException e){
                log.error("{}",e);
            }
        }
        return result;
    }
    /**
     *发送HttpGet请求
     * @param getUrl
     * @return
     */

    public static String httpGet(String getUrl) throws Exception {
        StringBuffer buffer = new StringBuffer();

        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));

            URL url = new URL(getUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection(proxy);

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("POST");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception e) {
            throw new Exception("HttpGet异常",e);
        }finally {

            try{
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                inputStream = null;
                httpUrlConn.disconnect();
            }catch (Exception ex){}
        }
        return buffer.toString();
    }
    
    static class Log {
        public void error(String a,Object s){
            System.out.println(s);
        }
    }

}
