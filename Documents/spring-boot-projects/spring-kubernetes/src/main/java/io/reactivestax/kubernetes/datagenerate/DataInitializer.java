package io.reactivestax.kubernetes.datagenerate;

import io.reactivestax.kubernetes.domain.Bookmark;
import io.reactivestax.kubernetes.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookmarkRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new Bookmark(null,"Maninder1","dataguise1.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder2","dataguise2.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder3","dataguise3.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder4","dataguise4.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder5","dataguise5.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder6","dataguise6.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder7","dataguise7.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder8","dataguise8.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder9","dataguise9.com", Instant.now()));
//        repository.save(new Bookmark(null,"Maninder10","dataguise10.com", Instant.now()));

    }
}
