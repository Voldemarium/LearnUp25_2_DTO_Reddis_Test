package ru.learnUP.springboottest.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class DtoClassCountComments implements DtoInterfaceCountComments {//
    private Long post_id;
    private Long count;

    public DtoClassCountComments(Long post_id, Long count) {
        this.post_id = post_id;
        this.count = count;
    }
}
