package ru.learnUP.springboottest.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class DtoClassCountCommentsSQL implements DtoInterfaceCountCommentsSQL {//
    private Long post_id;
    private Long count;

    public DtoClassCountCommentsSQL(Long post_id, Long count) {
        this.post_id = post_id;
        this.count = count;
    }
}
