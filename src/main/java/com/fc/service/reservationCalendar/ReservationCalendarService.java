package com.fc.service.reservationCalendar;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.fc.dto.board.BoardDto;
import com.fc.dto.reservationCalendar.ReservationCalendarDto;

public interface ReservationCalendarService  {

	//아이디 중복 검사
	public boolean checkDuplicateId(ReservationCalendarDto reservationCalendarDto)
	;
	public boolean checkMyReservation(String loginId);
	
	public boolean checkMyReservation2(String loginId);
	
	public int createReservation(ReservationCalendarDto reservationCalendarDto);
	
	public int updateReservation(ReservationCalendarDto reservationCalendarDto);
	
	public int clearReservation(ReservationCalendarDto reservationCalendarDto);
	public int clearReservation2(ReservationCalendarDto reservationCalendarDto, String loginId);
	
	public List<ReservationCalendarDto> myReservation(String loginId);
	
	public List<ReservationCalendarDto> findDb2(String rsvfNm, String category, Date date);
	
	
}
