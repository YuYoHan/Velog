package com.example.Belog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Criteria {
    // 현재 페이지
    private int page;
    // 페이지당 게시글 개수
    private final int BOARD_NUM_PER_PAGE = 10;
    private int startRow;

    public Criteria(int page) {
        this.page = page;
        this.startRow = (page - 1) * BOARD_NUM_PER_PAGE;
    }

    public int getPageFirstBoardNum() {
        // 현재 페이지의 게시글 시작 번호
        return (this.page - 1) * BOARD_NUM_PER_PAGE;
    }

    public int getPage() {
        return this.page;
    }

    public int getBOARD_NUM_PER_PAGE() {
        return this.BOARD_NUM_PER_PAGE;
    }

    public int getStartRow() {
        return startRow;
    }
}
