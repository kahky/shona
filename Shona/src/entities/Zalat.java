/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bassem
 */
@Entity
@Table(name = "zalat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zalat.findAll", query = "SELECT z FROM Zalat z"),
    @NamedQuery(name = "Zalat.findById", query = "SELECT z FROM Zalat z WHERE z.id = :id"),
    @NamedQuery(name = "Zalat.findByName", query = "SELECT z FROM Zalat z WHERE z.name = :name"),
    @NamedQuery(name = "Zalat.findByPricePerMeterBuy", query = "SELECT z FROM Zalat z WHERE z.pricePerMeterBuy = :pricePerMeterBuy"),
    @NamedQuery(name = "Zalat.findByPricePerMeterSell", query = "SELECT z FROM Zalat z WHERE z.pricePerMeterSell = :pricePerMeterSell"),
    @NamedQuery(name = "Zalat.findByCreatedat", query = "SELECT z FROM Zalat z WHERE z.createdat = :createdat")})
public class Zalat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "Zalat_seq")
    @Basic(optional = false)
    @Column(name = "id")
        @SequenceGenerator(name = "Zalat_seq", sequenceName = "Zalat_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "price_per_meter_buy")
    private BigInteger pricePerMeterBuy;
    @Column(name = "price_per_meter_sell")
    private BigInteger pricePerMeterSell;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @OneToMany(mappedBy = "zalatId")
    private Collection<TwridTransaction> twridTransactionCollection;
    @JoinColumn(name = "m7gr_id", referencedColumnName = "id")
    @ManyToOne
    private M7ger m7grId;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @OneToMany(mappedBy = "zalatId")
    private Collection<SaderTransaction> saderTransactionCollection;

    public Zalat() {
    }

    public Zalat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPricePerMeterBuy() {
        return pricePerMeterBuy;
    }

    public void setPricePerMeterBuy(BigInteger pricePerMeterBuy) {
        this.pricePerMeterBuy = pricePerMeterBuy;
    }

    public BigInteger getPricePerMeterSell() {
        return pricePerMeterSell;
    }

    public void setPricePerMeterSell(BigInteger pricePerMeterSell) {
        this.pricePerMeterSell = pricePerMeterSell;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @XmlTransient
    public Collection<TwridTransaction> getTwridTransactionCollection() {
        return twridTransactionCollection;
    }

    public void setTwridTransactionCollection(Collection<TwridTransaction> twridTransactionCollection) {
        this.twridTransactionCollection = twridTransactionCollection;
    }

    public M7ger getM7grId() {
        return m7grId;
    }

    public void setM7grId(M7ger m7grId) {
        this.m7grId = m7grId;
    }

    public Users getCreatedbyid() {
        return createdbyid;
    }

    public void setCreatedbyid(Users createdbyid) {
        this.createdbyid = createdbyid;
    }

    @XmlTransient
    public Collection<SaderTransaction> getSaderTransactionCollection() {
        return saderTransactionCollection;
    }

    public void setSaderTransactionCollection(Collection<SaderTransaction> saderTransactionCollection) {
        this.saderTransactionCollection = saderTransactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zalat)) {
            return false;
        }
        Zalat other = (Zalat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Zalat[ id=" + id + " ]";
    }
    
}
