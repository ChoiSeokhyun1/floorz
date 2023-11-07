package com.fc.dao.facility.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fc.dao.facility.FacilityDao;
import com.fc.dto.facility.FacilityDto;
import com.fc.dto.facility.FcDetailDto;
import com.fc.dto.facility.SearchDto;

@Repository
public class FacilityDaoImpl implements FacilityDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<FacilityDto> selectFacilitiesList() {
		// TODO Auto-generated method stub
		List<FacilityDto> facilityList = sqlSessionTemplate.selectList("map_mapper.select_facility_list");

		return facilityList;
	}

	@Override
	public FcDetailDto selectCurrentFacility(String fcCode) {
		// TODO Auto-generated method stub
		FcDetailDto currentFc = sqlSessionTemplate.selectOne("map_mapper.select_facility", fcCode);

		return currentFc;
	}

	@Override
	public void insertFacilitiesInfo(String dataFromApi) {
		// TODO Auto-generated method stub

		try {			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(dataFromApi);
			
			JSONArray itemArray = (JSONArray)jsonObject.get("data"); //배열 item 은 배열
			
			for(int i=0; i<itemArray.size(); i++) {
				JSONObject itemObj = (JSONObject)itemArray.get(i); // index:0~7  8개
			
				FacilityDto fctyInfo = new FacilityDto();
				fctyInfo.setRsrcNo(itemObj.get("rsrcNo").toString());
				fctyInfo.setRsrcNm(itemObj.get("rsrcNm").toString());
				fctyInfo.setZip(itemObj.get("zip").toString());
				fctyInfo.setAddr(itemObj.get("addr").toString());
				fctyInfo.setDaddr(itemObj.get("daddr").toString());
				fctyInfo.setLot(itemObj.get("lot").toString());
				fctyInfo.setLat(itemObj.get("lat").toString());
				fctyInfo.setInstUrlAddr(itemObj.get("instUrlAddr").toString());
				fctyInfo.setImgFileUrlAddr(itemObj.get("imgFileUrlAddr").toString());

				sqlSessionTemplate.insert("insert_facility_from_api", fctyInfo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	@Override
	public void insertFacilitiesDetailInfo(String dataFromApi) {
		// TODO Auto-generated method stub
		List<FcDetailDto> detailList = new ArrayList<FcDetailDto>();
		try {			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(dataFromApi);
			
			JSONArray itemArray = (JSONArray)jsonObject.get("data"); //배열 item 은 배열
			
			for(int i=0; i<itemArray.size(); i++) {
				JSONObject itemObj = (JSONObject)itemArray.get(i); // index:0~7  8개

				FcDetailDto fcDetailInfo = new FcDetailDto();
				
				fcDetailInfo.setRsrcNo(itemObj.get("rsrcNo").toString());
				fcDetailInfo.setRsrcClsCd(itemObj.get("rsrcClsCd").toString());
				fcDetailInfo.setRsrcClsNm(itemObj.get("rsrcClsNm").toString());
				fcDetailInfo.setRsrcInstCd(itemObj.get("rsrcInstCd").toString());
				fcDetailInfo.setRsrcInstNm(itemObj.get("rsrcInstNm").toString());
				fcDetailInfo.setUsePsblYn(itemObj.get("usePsblYn").toString());
				fcDetailInfo.setFreeYn(itemObj.get("freeYn").toString());
				fcDetailInfo.setRsrcIntr(itemObj.get("rsrcIntr").toString());
				fcDetailInfo.setAtpn(itemObj.get("atpn").toString());
				fcDetailInfo.setLcInf(itemObj.get("lcInf").toString());
				fcDetailInfo.setInqTag(itemObj.get("ingTag").toString());
				fcDetailInfo.setUpdYmd(itemObj.get("updYmd").toString());
				fcDetailInfo.setDelYn(itemObj.get("delYn").toString());
				fcDetailInfo.setRsrcAprvYn(itemObj.get("rsrcAprvYn").toString());
				fcDetailInfo.setSggCd(itemObj.get("sggCd").toString());
				fcDetailInfo.setRsvtNdlsYn(itemObj.get("rsvtNdlsYn").toString());
				fcDetailInfo.setLcInsttCd(itemObj.get("lcInsttCd").toString());
				fcDetailInfo.setLcInsttNm(itemObj.get("lcInsttNm").toString());
			
				detailList.add(fcDetailInfo);
				
			}
			
			//sqlSessionTemplate.insert("insert_detail_from_api", detailList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	@Override
	public List<FacilityDto> selectSearchedFacilitiesList(SearchDto searchDto) {
		// TODO Auto-generated method stub
		List<FacilityDto> searchedList = sqlSessionTemplate.selectList("select_searched_facility_list2", searchDto);
		return searchedList;
	}
}
