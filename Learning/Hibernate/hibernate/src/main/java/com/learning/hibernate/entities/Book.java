package com.learning.hibernate.entities;

import java.time.ZonedDateTime;

import com.learning.hibernate.values.BookId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "books")
public class Book extends AbstractExampleEntity<BookId> {

    @NonNull
    @EmbeddedId
    private BookId id;

    @NonNull
    @Column(name = "publishing_datetime")
    private ZonedDateTime publishingDateTime;

    @Override
    public BookId getId() {
    
        return id;
    }
}
