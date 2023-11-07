package com.fc.dao.reservationCalendar.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fc.dao.reservationCalendar.ReservationCalendarDao;
import com.fc.dto.reservationCalendar.ReservationCalendarDto;

@Repository
public class ReservationCalendarDaoImpl implements ReservationCalendarDao{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ReservationCalendarDto> selectReservationListBySearchKeyword(String rsvfNm, String category, Date date) {
	    List<ReservationCalendarDto> list = 
	        sqlSessionTemplate.selectList("reservationCalendar_mapper.select_reservation_list", Map.of("rsvfNm", rsvfNm, "category", category, "date", date));
	    
	    return list;
	}

	@Override
	public int insertReservation(ReservationCalendarDto reservationCalendarDto) {

		int result = sqlSessionTemplate.insert("reservationCalendar_mapper.insert_reservation", reservationCalendarDto);
		
		return result;
	}

	@Override
	public int updateReservation(ReservationCalendarDto reservationCalendarDto) {
		
		int result = sqlSessionTemplate.update("reservationCalendar_mapper.update_reservation", reservationCalendarDto);
		
		return result;
	}

	@Override
	public List<ReservationCalendarDto> selectMyReservationList(String loginId) {
		 List<ReservationCalendarDto> list = 
			        sqlSessionTemplate.selectList("reservationCalendar_mapper.select_my_reservation_list", Map.of("rsrcId", loginId));
			    
			    return list;
	}

	@Override
	public boolean isDuplicateId(ReservationCalendarDto reservationCalendarDto) {
		
		int count = sqlSessionTemplate.selectOne("reservationCalendar_mapper.count_duplicate_id", reservationCalendarDto);
	    return count > 0;
	}

	@Override
	public int clearReservation(ReservationCalendarDto reservationCalendarDto) {
		int result = sqlSessionTemplate.delete("reservationCalendar_mapper.delete_reservation", reservationCalendarDto);
		
		return result;
	}
	
	
	@Override
	public boolean checkMyReservation(String loginId) {
	    int count = sqlSessionTemplate.selectOne("reservationCalendar_mapper.check_my_reservation_list", loginId);
	    return count > 0;
	}

	@Override
	public boolean checkMyReservation2(String loginId) {
	    int count = sqlSessionTemplate.selectOne("reservationCalendar_mapper.check_my_reservation_list2", loginId);
	    return count > 0;
	}

	@Override
	public int clearReservation2(ReservationCalendarDto reservationCalendarDto, String loginId) {
	    reservationCalendarDto.setLoginId(loginId);
	    int result = sqlSessionTemplate.delete("reservationCalendar_mapper.delete_reservation2", reservationCalendarDto);
	    return result;
	}
	
}
