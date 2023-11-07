package com.fc.dto.facility;

import lombok.Data;

@Data
public class SearchDto {
	String isChecked;
	String searchTxt;
	String searchKeyword;
	String[] splittedKeyword;
}
