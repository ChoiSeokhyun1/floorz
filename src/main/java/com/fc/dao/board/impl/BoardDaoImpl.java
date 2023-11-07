package com.fc.dao.board.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.fc.dao.board.BoardDao;
import com.fc.dto.board.BoardDto;
import com.fc.dto.facility.SearchDto;



@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public int boardInsert(BoardDto boardDto) {
		
		 System.out.println(boardDto.toString());
		 
		int result = sqlSessionTemplate.insert("board_mapper.insert_contents", boardDto);
		
		return result;
	}

		//게시판 리스트
	@Override
	public List<BoardDto> selectBoardList() {

		List<BoardDto> list = 
				sqlSessionTemplate.selectList("board_mapper.list_contents");
		
		return list;
	}

	@Override
	public List<BoardDto> selectBoardListByNumber(BoardDto boardDto) {

		List<BoardDto> list = 
				sqlSessionTemplate.selectList("board_mapper.list_contents_byNumber", boardDto);
		
		
		return null;
	}
	@Override
	public List<BoardDto> selectBoardListBySearchKeywordMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<BoardDto> list = 
				sqlSessionTemplate.selectList("board_mapper.select_b_list_by_searchKeyword_map", map);
																	//수정대기 
		return list;
	}
	@Override
	public List<BoardDto> selectBoardListBySearchKeywordMap2(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		List<BoardDto> list = 
				
				sqlSessionTemplate.selectList("board_mapper.select_board_title_by_searchKeyword_map", map);
																	//수정대기 
		return list;
	}

	@Override
	public BoardDto pageView(int postno) {
		// TODO Auto-generated method stub
		
		BoardDto dto = sqlSessionTemplate.selectOne("board_mapper.list_view",postno);
		
		return dto;
	}

	
	  @Override 
	  public void viewCount(int postno) {  
		  
	 sqlSessionTemplate.update("board_mapper.list_count",postno);
	 
	  }

	  
	  
	@Override
	public void update(BoardDto boardDto) {
		// TODO Auto-generated method stub
		
		

		sqlSessionTemplate.update("board_mapper.list_update",boardDto);
	}

	@Override
	public void delete(int postno) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete("board_mapper.list_delete",postno);

	
	}

	@Override
	public List<BoardDto> selectBoardListBySearchDto(SearchDto searchDto) {
		// TODO Auto-generated method stub
		List<BoardDto> list = sqlSessionTemplate.selectList("search_boardList_by_searchDto", searchDto);
		return list;
	}

	@Override
	public int insertBoardOpinion(Map<String, String> infoMap){
		// TODO Auto-generated method stub
		int finalResult = 0;
		try {
			// 신고 혹은 추천 테이블에 값 넣기 시도
		int result = sqlSessionTemplate.insert("insert_opinion_info", infoMap);
			if (result == 1) { // 넣을 수 있는 상황이면, 해당 게시글의 정보 업데이트
				// 업데이트 성공하면 1을 리턴
				finalResult = sqlSessionTemplate.update("update_board_infoCount", infoMap);
			}
		// 이미 테이블에 값(아이디, 신고타입, 게시글 번호)이 존재하는 경우
		// (아이디, 신고타입, 게시글 번호)를 PK로 설정해놓아서 중복되는 경우 에러 발생
		// 0을 리턴
		} catch (DuplicateKeyException e) {
			finalResult = 0;
		}
		return finalResult;	
	}

	@Override
	public int selectTotalBoardsCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("pageCount");
	}

	@Override
	public int selectTotalSearchedBoardsCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("searchedPageCount");
	}

	@Override
	public int pageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDto> selectBoardListByViewCnt() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("search_boardList_by_viewCnt");
	}



		
}
