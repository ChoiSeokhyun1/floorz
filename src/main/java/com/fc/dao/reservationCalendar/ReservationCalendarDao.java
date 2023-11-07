package com.fc.dao.reservationCalendar;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.fc.dto.reservationCalendar.ReservationCalendarDto;

public interface ReservationCalendarDao {
	
	public boolean isDuplicateId(ReservationCalendarDto reservationCalendarDto);
	
	public boolean checkMyReservation(String loginId);
	
	public boolean checkMyReservation2(String loginId);

	public int insertReservation(ReservationCalendarDto reservationCalendarDto);
	
	public int updateReservation(ReservationCalendarDto reservationCalendarDto);
	
	public int clearReservation(ReservationCalendarDto reservationCalendarDto);
	public int clearReservation2(ReservationCalendarDto reservationCalendarDto, String loginId);
	
	public List<ReservationCalendarDto> selectReservationListBySearchKeyword(String rsvfNm, String category, Date date);
	
	public List<ReservationCalendarDto> selectMyReservationList(String loginId);
	

}
