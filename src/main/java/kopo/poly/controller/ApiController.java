package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@RestController
public class ApiController {
    @GetMapping("/api")
    public String callApi() throws IOException {
        log.info(this.getClass().getName()+".callApi Start !!");
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553530/TRANSPORTATION/ELECTRIC_CHARGING"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=c4Ik2BSE0cthW+Mi2zgboh1zp/jd6MHWXwBUzeuV/TsCCsCp7VZXtzrZdR6YFCSyTxO3gs+h6AVrU+irLzh/tQ=="); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*검색건수*/
        urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*결과형식(xml/json)*/
        urlBuilder.append("&" + URLEncoder.encode("q1","UTF-8") + "=" + URLEncoder.encode("울산광역시", "UTF-8")); /*광역지자체*/
        urlBuilder.append("&" + URLEncoder.encode("q2","UTF-8") + "=" + URLEncoder.encode("중구", "UTF-8")); /*시군구*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        log.info(this.getClass().getName()+".callApiEnd !!");
        log.info(sb.toString());
        return sb.toString();
    }
}