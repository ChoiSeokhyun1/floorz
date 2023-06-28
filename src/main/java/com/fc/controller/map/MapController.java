package com.fc.controller.map;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.dto.facility.FacilityDto;
import com.fc.dto.facility.FcDetailDto;
import com.fc.dto.facility.SearchDto;
import com.fc.service.map.MapService;

@Controller
@PropertySource("/WEB-INF/properties/application.properties")
public class MapController {
	
	@Value("${api.kakao.key}")
	private String kakaoApiKey;
	
	@Autowired
	MapService mapService;
	
	@GetMapping("/map")
	public String getMapPage(Model model) {
		Boolean isInitial = mapService.getFacilityList().size() == 0;
		List<FacilityDto> fcList;
		
		// 맨 처음 실행해서 데이터가 없는 경우..
		if (isInitial) {
			try {
				mapService.getFacilityListFromApi();
				//mapService.getFacilityDetailFromApi();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		fcList = mapService.getFacilityList();
		
		model.addAttribute("apiKey", kakaoApiKey);
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			// list를 JSON으로
			System.out.println(fcList);
			jsonList = mapper.writeValueAsString(fcList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("parsedList", jsonList);
		model.addAttribute("fcList", fcList);
		
		return "map/map";
	}
	
	@PostMapping("map")
	public String sendSearchKeyword(@ModelAttribute SearchDto searchDto) {
		List<FacilityDto> fcList;
		String txt = searchDto.getSearchTxt();
		System.out.println(searchDto.getSearchTxt());
		if (txt.trim() == "") {
			fcList = mapService.getFacilityList();
		} else {
			fcList = mapService.getSearchedFacilityList(txt);
		}
		return "map/map";
	}

}
