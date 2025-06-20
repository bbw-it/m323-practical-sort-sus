package ch.bbw.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private LocalDate publishDate;
    private BigDecimal price;

    public Book(int id, String title, LocalDate publishDate, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                '}';
    }
}
