package io.reactivestax.kubernetes.repository;

import io.reactivestax.kubernetes.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
