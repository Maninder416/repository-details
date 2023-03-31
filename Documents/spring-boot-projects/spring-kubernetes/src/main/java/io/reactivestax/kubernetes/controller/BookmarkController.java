package io.reactivestax.kubernetes.controller;

import io.reactivestax.kubernetes.dto.BookmarksDto;
import io.reactivestax.kubernetes.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;
    @GetMapping
    public BookmarksDto getBookmarks(@RequestParam(name = "page",defaultValue = "1") Integer pageNo) {
        return bookmarkService.getBookmarks(pageNo);
    }
}
