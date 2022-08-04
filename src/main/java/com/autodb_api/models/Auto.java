package com.autodb_api.models;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body", referencedColumnName = "id")
    private Body body;

    @Column(name = "city_mpg")
    private Double cityMpg;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine", referencedColumnName = "id")
    private Engine engine;

    @Column(name = "exterior_color")
    private String exteriorColor;

    @Column(name = "fleet")
    private Boolean fleet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report", referencedColumnName = "id")
    private Report report;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer", referencedColumnName = "id")
    private Dealer dealer;

    @Column(name = "fuel_tank_volume")
    private String fuelTankVolume;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel", referencedColumnName = "id")
    private Fuel fuel;

    @Column(name = "highway_mpg")
    private Double highwayMpg;

    @Column(name = "horsepower")
    private Double horsepower;

    @Column(name = "interior_color")
    private String interiorColor;

    @Column(name = "is_cab")
    private Boolean isCab;

    @Column(name = "is_cpo")
    private Boolean isCpo;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "is_oemcpo")
    private Boolean isOemcpo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color", referencedColumnName = "id")
    private Color color;

    @Column(name = "listing_id")
    private String listingId;

    @Column(name = "main_picture_url")
    private String mainPictureUrl;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "make", referencedColumnName = "id")
    private Make make;

    @Column(name = "seating")
    private Integer seating;

    @Column(name = "mileage")
    private Double mileage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model", referencedColumnName = "id")
    private Model model;

    @Column(name = "power")
    private String power;

    @Column(name = "price")
    private Double price;

    @Column(name = "torque")
    private String torque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission", referencedColumnName = "id")
    private Transmission transmission;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trim", referencedColumnName = "id")
    private Trim trim;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drivetrain", referencedColumnName = "id")
    private Drivetrain drivetrain;

    @Column(name = "year")
    private Integer year;

    @Column(name = "vin")
    private String vin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_id")
    private java.util.List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auto_option",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private java.util.List<Option> options;


    /**
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinTable(
            name = "auto_image",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private StockImage stockImage;
    **/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Double getCityMpg() {
        return cityMpg;
    }

    public void setCityMpg(Double cityMpg) {
        this.cityMpg = cityMpg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Boolean getFleet() {
        return fleet;
    }

    public void setFleet(Boolean fleet) {
        this.fleet = fleet;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getFuelTankVolume() {
        return fuelTankVolume;
    }

    public void setFuelTankVolume(String fuelTankVolume) {
        this.fuelTankVolume = fuelTankVolume;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Double getHighwayMpg() {
        return highwayMpg;
    }

    public void setHighwayMpg(Double highwayMpg) {
        this.highwayMpg = highwayMpg;
    }

    public Double getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Double horsepower) {
        this.horsepower = horsepower;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public Boolean getIsCab() {
        return isCab;
    }

    public void setIsCab(Boolean isCab) {
        this.isCab = isCab;
    }

    public Boolean getIsCpo() {
        return isCpo;
    }

    public void setIsCpo(Boolean isCpo) {
        this.isCpo = isCpo;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsOemcpo() {
        return isOemcpo;
    }

    public void setIsOemcpo(Boolean isOemcpo) {
        this.isOemcpo = isOemcpo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getMainPictureUrl() {
        return mainPictureUrl;
    }

    public void setMainPictureUrl(String mainPictureUrl) {
        this.mainPictureUrl = mainPictureUrl;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Integer getSeating() {
        return seating;
    }

    public void setSeating(Integer seating) {
        this.seating = seating;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission ) {
        this.transmission = transmission;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }


    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public java.util.List<Image> getImages() {
        return images;
    }

    public void setImages(java.util.List<Image> images) {
        this.images = images;
    }

    public java.util.List<Option> getOptions() {
        return options;
    }

    public void setOptions(java.util.List<Option> options) {
        this.options = options;
    }



    /**
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setStockImage(StockImage stockImage) {
        this.stockImage = stockImage;
    }

    public StockImage getStockImage() {
        return stockImage;
    }
    **/
}