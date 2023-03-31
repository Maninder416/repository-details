package io.reactivestax.kubernetes.service;

import io.reactivestax.kubernetes.domain.Bookmark;
import io.reactivestax.kubernetes.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    /**
     * We are marking it ready only as we know, we are
     * not doing any write operation. So marking it
     * readOnly true improve the performance
     * @return
     */
    @Transactional(readOnly = true)
    public List<Bookmark> getBookmarks(){
        return bookmarkRepository.findAll();
    }
}
