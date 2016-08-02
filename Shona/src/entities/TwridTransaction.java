/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bassem
 */
@Entity
@Table(name = "twrid_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TwridTransaction.findAll", query = "SELECT t FROM TwridTransaction t"),
    @NamedQuery(name = "TwridTransaction.findById", query = "SELECT t FROM TwridTransaction t WHERE t.id = :id"),
    @NamedQuery(name = "TwridTransaction.findByTk3ib", query = "SELECT t FROM TwridTransaction t WHERE t.tk3ib = :tk3ib"),
    @NamedQuery(name = "TwridTransaction.findByPricePerMeter", query = "SELECT t FROM TwridTransaction t WHERE t.pricePerMeter = :pricePerMeter"),
    @NamedQuery(name = "TwridTransaction.findByTotalPrice", query = "SELECT t FROM TwridTransaction t WHERE t.totalPrice = :totalPrice"),
    @NamedQuery(name = "TwridTransaction.findByPaid", query = "SELECT t FROM TwridTransaction t WHERE t.paid = :paid"),
    @NamedQuery(name = "TwridTransaction.findByRest", query = "SELECT t FROM TwridTransaction t WHERE t.rest = :rest"),
    @NamedQuery(name = "TwridTransaction.findByGararPrice", query = "SELECT t FROM TwridTransaction t WHERE t.gararPrice = :gararPrice"),
    @NamedQuery(name = "TwridTransaction.findByIsalNumber", query = "SELECT t FROM TwridTransaction t WHERE t.isalNumber = :isalNumber"),
    @NamedQuery(name = "TwridTransaction.findByCreatedat", query = "SELECT t FROM TwridTransaction t WHERE t.createdat = :createdat")})
public class TwridTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "TwridTransaction_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "TwridTransaction_id_seq", sequenceName = "TwridTransaction_ID_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "tk3ib")
    private BigInteger tk3ib;
    @Column(name = "price_per_meter")
    private BigInteger pricePerMeter;
    @Column(name = "total_price")
    private BigInteger totalPrice;
    @Column(name = "paid")
    private BigInteger paid;
    @Column(name = "rest")
    private BigInteger rest;
    @Column(name = "garar_price")
    private BigInteger gararPrice;
    @Basic(optional = false)
    @Column(name = "isal_number")
    private int isalNumber;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @JoinColumn(name = "grar_id", referencedColumnName = "id")
    @ManyToOne
    private Grarat grarId;
    @JoinColumn(name = "m7gr_id", referencedColumnName = "id")
    @ManyToOne
    private M7ger m7grId;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @JoinColumn(name = "zalat_id", referencedColumnName = "id")
    @ManyToOne
    private Zalat zalatId;

    public TwridTransaction() {
    }

    public TwridTransaction(Integer id) {
        this.id = id;
    }

    public TwridTransaction(Integer id, int isalNumber) {
        this.id = id;
        this.isalNumber = isalNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getTk3ib() {
        return tk3ib;
    }

    public void setTk3ib(BigInteger tk3ib) {
        this.tk3ib = tk3ib;
    }

    public BigInteger getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(BigInteger pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }

    public BigInteger getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigInteger totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigInteger getPaid() {
        return paid;
    }

    public void setPaid(BigInteger paid) {
        this.paid = paid;
    }

    public BigInteger getRest() {
        return rest;
    }

    public void setRest(BigInteger rest) {
        this.rest = rest;
    }

    public BigInteger getGararPrice() {
        return gararPrice;
    }

    public void setGararPrice(BigInteger gararPrice) {
        this.gararPrice = gararPrice;
    }

    public int getIsalNumber() {
        return isalNumber;
    }

    public void setIsalNumber(int isalNumber) {
        this.isalNumber = isalNumber;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Grarat getGrarId() {
        return grarId;
    }

    public void setGrarId(Grarat grarId) {
        this.grarId = grarId;
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

    public Zalat getZalatId() {
        return zalatId;
    }

    public void setZalatId(Zalat zalatId) {
        this.zalatId = zalatId;
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
        if (!(object instanceof TwridTransaction)) {
            return false;
        }
        TwridTransaction other = (TwridTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TwridTransaction[ id=" + id + " ]";
    }

}
