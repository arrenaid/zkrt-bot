package ru.msocialproduction.test.zkrtbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "domains")
public class DomainEntity {

    @Id
    @SequenceGenerator(name = "domains_generator", sequenceName = "seq_domains", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domains_generator")
    private Integer id;
    @JsonProperty(value = "domainname")
    @Column(name = "domain_name")
    private String domainName;
    @Column
    private Integer hotness;
    @Column
    private Integer price;
    @JsonProperty(value = "x_value")
    @Column(name = "x_value")
    private Double xValue;
    @JsonProperty(value = "yandex_tic")
    @Column(name = "yandex_tic")
    private Integer yandexTic;
    @Column
    private Integer links;
    @Column
    private Integer visitors;
    @Column
    private String registrar;
    @Column
    private Integer old;
    @JsonProperty(value = "delete_date")
    @Column(name = "delete_date")
    private Date deleteDate;
    @Column
    private Boolean rkn;
    @Column
    private Boolean judicial;
    @Column
    private Boolean block;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Integer getHotness() {
        return hotness;
    }

    public void setHotness(Integer hotness) {
        this.hotness = hotness;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getxValue() {
        return xValue;
    }

    public void setxValue(Double xValue) {
        this.xValue = xValue;
    }

    public Integer getYandexTic() {
        return yandexTic;
    }

    public void setYandexTic(Integer yandexTic) {
        this.yandexTic = yandexTic;
    }

    public Integer getLinks() {
        return links;
    }

    public void setLinks(Integer links) {
        this.links = links;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getRkn() {
        return rkn;
    }

    public void setRkn(Boolean rkn) {
        this.rkn = rkn;
    }

    public Boolean getJudicial() {
        return judicial;
    }

    public void setJudicial(Boolean judicial) {
        this.judicial = judicial;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }
}
