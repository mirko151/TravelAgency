package com.travelagency.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinationName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportMode transportMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @Column(nullable = false)
    private int numberOfNights;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private int availableSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private TravelCategory category;

    // Polje za putanju slike
    @Column(name = "image_path")
    private String imagePath;

    // Polja za akcijsku cenu
    @Column(precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date discountValidUntil;

    // Konstruktor bez parametara
    public Travel() {
    }

    // Getteri i Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public TravelCategory getCategory() {
        return category;
    }

    public void setCategory(TravelCategory category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getDiscountValidUntil() {
        return discountValidUntil;
    }

    public void setDiscountValidUntil(Date discountValidUntil) {
        this.discountValidUntil = discountValidUntil;
    }
}
