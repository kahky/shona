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
@Table(name = "sader_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaderTransaction.findAll", query = "SELECT s FROM SaderTransaction s"),
    @NamedQuery(name = "SaderTransaction.findById", query = "SELECT s FROM SaderTransaction s WHERE s.id = :id"),
    @NamedQuery(name = "SaderTransaction.findByTk3ib", query = "SELECT s FROM SaderTransaction s WHERE s.tk3ib = :tk3ib"),
    @NamedQuery(name = "SaderTransaction.findByPricePerMeter", query = "SELECT s FROM SaderTransaction s WHERE s.pricePerMeter = :pricePerMeter"),
    @NamedQuery(name = "SaderTransaction.findByTotalPrice", query = "SELECT s FROM SaderTransaction s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "SaderTransaction.findByPaid", query = "SELECT s FROM SaderTransaction s WHERE s.paid = :paid"),
    @NamedQuery(name = "SaderTransaction.findByRest", query = "SELECT s FROM SaderTransaction s WHERE s.rest = :rest"),
    @NamedQuery(name = "SaderTransaction.findByCarPrice", query = "SELECT s FROM SaderTransaction s WHERE s.carPrice = :carPrice"),
    @NamedQuery(name = "SaderTransaction.findByIsalNumber", query = "SELECT s FROM SaderTransaction s WHERE s.isalNumber = :isalNumber"),
    @NamedQuery(name = "SaderTransaction.findByCreatedat", query = "SELECT s FROM SaderTransaction s WHERE s.createdat = :createdat")})
public class SaderTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "SaderTransaction_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "SaderTransaction_id_seq", sequenceName = "SaderTransaction_ID_SEQ", allocationSize = 1)
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
    @Column(name = "car_price")
    private BigInteger carPrice;
    @Basic(optional = false)
    @Column(name = "isal_number")
    private int isalNumber;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @ManyToOne
    private Cars carId;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @JoinColumn(name = "zalat_id", referencedColumnName = "id")
    @ManyToOne
    private Zalat zalatId;

    public SaderTransaction() {
    }

    public SaderTransaction(Integer id) {
        this.id = id;
    }

    public SaderTransaction(Integer id, int isalNumber) {
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

    public BigInteger getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigInteger carPrice) {
        this.carPrice = carPrice;
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

    public Cars getCarId() {
        return carId;
    }

    public void setCarId(Cars carId) {
        this.carId = carId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof SaderTransaction)) {
            return false;
        }
        SaderTransaction other = (SaderTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SaderTransaction[ id=" + id + " ]";
    }

}
