package com.fc.service.map.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fc.dao.facility.FacilityDao;
import com.fc.dto.facility.FacilityDto;
import com.fc.dto.facility.FcDetailDto;
import com.fc.dto.facility.SearchDto;
import com.fc.service.map.MapService;
import com.fc.utils.CommonCode;

@Service
@PropertySource("/WEB-INF/properties/application.properties")
public class MapServiceImpl implements MapService {

	@Autowired
	FacilityDao facilityDao;

	@Value("${api.shareNuri.key}")
	private String shareNuriKey;

	@Override
	public List<FacilityDto> getFacilityList() {
		// TODO Auto-generated method stub
		List<FacilityDto> fcList = facilityDao.selectFacilitiesList();
		return fcList;
	}

	@Override
	public FcDetailDto getCurrentFacility(String fcCode) {
		// TODO Auto-generated method stub
		FcDetailDto currentFc = facilityDao.selectCurrentFacility(fcCode);
		System.out.println(currentFc.toString());
		return currentFc;
	}

	@Override
	public void getFacilityListFromApi() throws IOException {
		// TODO Auto-generated method stub\
		List<FacilityDto> list = new ArrayList<FacilityDto>();
		for (int i = 44131; i<= 44133; i += 2) {
			URL url = new URL("https://www.eshare.go.kr/eshare-openapi/rsrc/list/010500/" + shareNuriKey); /*URL*/
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 요청 방법 설정
			conn.setRequestMethod("POST");

			// 응답 형식 유형 설정
			conn.setRequestProperty("Accept", "application/json");

			// 요청 본문의 형식을 정해줌
			conn.setRequestProperty("Content-type", "application/json; utf-8");
			conn.setRequestProperty("Content-Length", "length");

			// 콘텐츠를 보내는데 연결이 사용되는지 확인
			conn.setDoOutput(true);

			// 보내줄 데이터
			JSONObject jobj = new JSONObject();
			jobj.put("pageNo", 1);
			jobj.put("numOfRows", 100);
			jobj.put("sggCd", i);

			// string 형식으로 변환
			String jsonInputString = jobj.toString();

			try(OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);			
			}

			conn.setConnectTimeout(1000);
			conn.setReadTimeout(1000);
			System.out.println(conn.getHeaderFields());
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
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

			// facilityDao.insert어찌구(sb.toString());
			facilityDao.insertFacilitiesInfo(sb.toString());
		}
	}

	@Override
	public void getFacilityDetailFromApi() throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("https://www.eshare.go.kr/eshare-openapi/rsrc/detail/" + shareNuriKey); /*URL*/
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 요청 방법 설정
		conn.setRequestMethod("POST");

		// 응답 형식 유형 설정
		conn.setRequestProperty("Accept", "application/json");

		// 요청 본문의 형식을 정해줌
		conn.setRequestProperty("Content-type", "application/json; utf-8");
		conn.setRequestProperty("Content-Length", "length");

		// 콘텐츠를 보내는데 연결이 사용되는지 확인
		conn.setDoOutput(true);


		JSONObject jobj = new JSONObject();
		JSONArray jArr = new JSONArray();
		List<String> list = new ArrayList<String>();
		for (String code : CommonCode.FACILITY_CODES_LIST ) {
			list.add(code);
		}

		for (int i = 0; i<list.size(); i++) {
			jArr.add(list.get(i));
		}

		// 보내줄 데이터
		jobj.put("rsrcNoList", jArr);

		// string 형식으로 변환
		String jsonInputString = jobj.toString();

		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}

		conn.setConnectTimeout(1000);
		conn.setReadTimeout(1000);
		System.out.println(conn.getHeaderFields());
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
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

		System.out.println(sb.toString());

		facilityDao.insertFacilitiesDetailInfo(sb.toString());
	}

	@Override
	public List<FacilityDto> getSearchedFacilityList(SearchDto searchDto) {
		// TODO Auto-generated method stub
		String[] splittedKeyword = searchDto.getSearchTxt().split(" ");
		searchDto.setSplittedKeyword(splittedKeyword);
		List<FacilityDto> fcList = facilityDao.selectSearchedFacilitiesList(searchDto);
		return fcList;
	}
}