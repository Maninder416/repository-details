package io.reactivestax.kubernetes.repository;

import io.reactivestax.kubernetes.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
