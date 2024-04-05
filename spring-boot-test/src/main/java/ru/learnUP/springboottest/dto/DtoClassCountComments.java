package ru.learnUP.springboottest.dto;

import lombok.*;

@Data
public class DtoClassCountComments implements DtoInterfaceCountComments {//
    private Long post_id;
    private Long count;
}
