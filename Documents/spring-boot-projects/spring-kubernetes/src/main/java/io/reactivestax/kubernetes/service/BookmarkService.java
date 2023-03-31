package io.reactivestax.kubernetes.service;

import io.reactivestax.kubernetes.domain.Bookmark;
import io.reactivestax.kubernetes.dto.BookmarkDto;
import io.reactivestax.kubernetes.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    /**
     * We are using constructor injection over autowired
     * it provide better control over the creation of
     * objects. you can ensure all the required dependencies
     * are present during the object creation
     *
     * we can also mark this as final. this makes your code
     * more testable, maintainable and less error-prone.
     *
     */
    private final BookmarkRepository bookmarkRepository;

    /**
     * We are marking it ready only as we know, we are
     * not doing any write operation. So marking it
     * readOnly true improve the performance
     * @return
     */
    @Transactional(readOnly = true)
    public BookmarkDto getBookmarks(Integer page){
        int pageNo = page<1 ? 0: page -1;
        Pageable pageable = PageRequest.of(pageNo,5, Sort.Direction.DESC,"createdAt");
        return new BookmarkDto(bookmarkRepository.findAll(pageable));
    }
}
