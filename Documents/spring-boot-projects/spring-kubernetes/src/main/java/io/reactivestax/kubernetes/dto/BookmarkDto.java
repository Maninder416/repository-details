package io.reactivestax.kubernetes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookmarkDto {
    private Long id;
    private String title;
    private String url;
    private Instant createdAt;
}
