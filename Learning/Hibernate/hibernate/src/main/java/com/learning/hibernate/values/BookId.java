package com.learning.hibernate.values;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString(callSuper = true)
@Embeddable
public class BookId implements Serializable {
    
    @NonNull
    private String author;

    @NonNull
    private String title;

    public static BookId of(String author, String title)
    {
        return new BookId(author, title);
    }
    
    @Override
    public boolean equals(Object other)
    {
        return 
            !Objects.isNull(other) &&
            getClass().equals(other.getClass()) &&
            equals((BookId) other);
    }

    private boolean equals(BookId other)
    {
        return getAuthor().equals(other.getAuthor()) &&
                getTitle().equals(other.getTitle());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(title, author);
    }

}
