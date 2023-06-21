package me.alextodea.testioapplication.thymeleaf.bootstrap.model.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {
    List<T> content;
    int totalPages;
}
