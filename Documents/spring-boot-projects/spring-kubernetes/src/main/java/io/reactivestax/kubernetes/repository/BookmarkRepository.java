package io.reactivestax.kubernetes.repository;

import io.reactivestax.kubernetes.domain.Bookmark;
import io.reactivestax.kubernetes.dto.BookmarkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("select new io.reactivestax.kubernetes.dto.BookmarkDto(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDto> findBookmarks(Pageable pageable);

}
