package org.iesbelen.videoclub.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private int addressId;

    private String street;

    private int houseNumber;

    private String city;

    private int zipCode;
}
