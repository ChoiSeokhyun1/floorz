package com.fc.service.reservationCalendar.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fc.dao.reservationCalendar.ReservationCalendarDao;
import com.fc.dto.reservationCalendar.ReservationCalendarDto;
import com.fc.service.reservationCalendar.ReservationCalendarService;

@Service
public class ReservationCalendarServiceImpl implements ReservationCalendarService {

	@Autowired
	ReservationCalendarDao reservationCalendarDao;

	@Override
	public List<ReservationCalendarDto> findDb2(String rsvfNm, String category, Date date) {
		List<ReservationCalendarDto> list = reservationCalendarDao.selectReservationListBySearchKeyword(rsvfNm, category, date);
	    return list;
	}

	
	@Override
	public int createReservation(ReservationCalendarDto reservationCalendarDto) {

		int result = reservationCalendarDao.insertReservation(reservationCalendarDto);
		
		return result;
	}


	@Override
	public int updateReservation(ReservationCalendarDto reservationCalendarDto) {
		
			int result = reservationCalendarDao.updateReservation(reservationCalendarDto);
		
		return result;
	}


	@Override
	public List<ReservationCalendarDto> myReservation(String loginId) {
		List<ReservationCalendarDto> list = reservationCalendarDao.selectMyReservationList(loginId);
	    return list;
	}


	@Override
	public boolean checkDuplicateId(ReservationCalendarDto reservationCalendarDto) {
	    return reservationCalendarDao.isDuplicateId(reservationCalendarDto);
	}


	@Override
	public int clearReservation(ReservationCalendarDto reservationCalendarDto) {
		
	int result = reservationCalendarDao.clearReservation(reservationCalendarDto);
		
		return result;
	}
	


	@Override
	public boolean checkMyReservation(String loginId) {
		// TODO Auto-generated method stub
		 return reservationCalendarDao.checkMyReservation(loginId);
			}

	@Override
	public boolean checkMyReservation2(String loginId) {
		// TODO Auto-generated method stub
		 return reservationCalendarDao.checkMyReservation2(loginId);
			}


	@Override
	public int clearReservation2(ReservationCalendarDto reservationCalendarDto, String loginId) {
		int result = reservationCalendarDao.clearReservation2(reservationCalendarDto, loginId);
		
		return result;
	}




	
	
}
