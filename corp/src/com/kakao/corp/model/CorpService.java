package com.kakao.corp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorpService {
	private String name;
	private String text;
	private String category;
	private String img;
}
