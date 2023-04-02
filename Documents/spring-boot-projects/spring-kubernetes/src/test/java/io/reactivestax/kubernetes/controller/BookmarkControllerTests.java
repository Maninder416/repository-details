package io.reactivestax.kubernetes.controller;

import io.reactivestax.kubernetes.domain.Bookmark;
import io.reactivestax.kubernetes.repository.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
public class BookmarkControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    private List<Bookmark> bookmarkList;
    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        bookmarkList= new ArrayList<>();
        bookmarkList.add(new Bookmark(null, "dataguise1","dataguise1.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise2","dataguise2.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise3","dataguise3.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise4","dataguise4.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise5","dataguise5.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise6","dataguise6.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise7","dataguise7.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise8","dataguise8.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise9","dataguise9.com", Instant.now()));
        bookmarkList.add(new Bookmark(null, "dataguise10","dataguise10.com", Instant.now()));
        bookmarkRepository.saveAll(bookmarkList);

    }

    /**
     * Integration test cases. It will hit the API and verify that count is same that API
     * is returned. Before executing the test cases, in setup method, we deleted the data
     * from database and save the new data to verify that the same data we will get
     * whenever we write the test cases. So, now we know that it will return 10 records.
     *
     * @throws Exception
     */
    @Test
    void shouldGetBookmarks() throws Exception {
        mockMvc.perform(get("/api/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(10)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(2)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(true)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(false)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)));
    }


    /**
     * here we are passing the parameter to verify either it works or not
     * @param pageNo
     * @throws Exception
     */
    @ParameterizedTest
    @CsvSource({
            "1,10,2,1,true,false,true,false"
    })
    void shouldGetBookmarksByPageNo(int pageNo, int totalElements, int totalPages,
                                    int currentPage, boolean isFirst, boolean isLast,
                                    boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc.perform(get("/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)));
    }

    /**
     * here we are passing the multiple pageNo 1 and 2 and also
     * providing the expected result for these parameter.
     * This test case will run for 2 times. One is for page no 1
     * and another is for page no 2.
     * When the page no is 1, we are expecting:
     *
     * 1,10,2,1,true,false,true,false
     * It means: page no is: 1
     * total elements in list: 10
     * total pages is: 2
     * current page: 1
     * hasNext: true //means does it has next page ans is yes that is page 2:
     * hasPrevious: no // there is no previous page for it.
     * isFirst: true //Does it first page? ans is yes
     * isLast: false //Does it last page? ans is no.
     *
     * @param pageNo
     * @throws Exception
     */
    @ParameterizedTest
    @CsvSource({
            "1,10,2,1,true,false,true,false",
            "2,10,2,2,false,true,false,true"
    })
    void shouldGetBookmarksByMultiplePageNo(int pageNo, int totalElements, int totalPages,
                                            int currentPage, boolean isFirst, boolean isLast,
                                            boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc.perform(get("/api/bookmarks?page="+pageNo))
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)));
    }
}
