package com.autodb_api.models;

import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "postcode", nullable = false)
    private Integer postcode;

    @Column(name = "point")
    private Point point;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

/*
  TODO [JPA Buddy] create field to map the 'point' column
   Available actions: Define target Java type | Uncomment as is | Remove column mapping
  @Column(name = "point", columnDefinition = "geography")
  private Object point;
*/
}