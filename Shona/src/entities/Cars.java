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
@Table(name = "cars")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c"),
    @NamedQuery(name = "Cars.findById", query = "SELECT c FROM Cars c WHERE c.id = :id"),
    @NamedQuery(name = "Cars.findByTk3ib", query = "SELECT c FROM Cars c WHERE c.tk3ib = :tk3ib"),
    @NamedQuery(name = "Cars.findByIsOurs", query = "SELECT c FROM Cars c WHERE c.isOurs = :isOurs"),
    @NamedQuery(name = "Cars.findByOwnerName", query = "SELECT c FROM Cars c WHERE c.ownerName = :ownerName"),
    @NamedQuery(name = "Cars.findByCreatedat", query = "SELECT c FROM Cars c WHERE c.createdat = :createdat")})
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="cars_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name="cars_id_seq", sequenceName="CARS_ID_SEQ",allocationSize=1)
    private Integer id;
    @Column(name = "tk3ib")
    private BigInteger tk3ib;
    @Column(name = "is_ours")
    private Integer isOurs;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @OneToMany(mappedBy = "carId")
    private Collection<SaderTransaction> saderTransactionCollection;

    public Cars() {
    }

    public Cars(Integer id) {
        this.id = id;
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

    public Integer getIsOurs() {
        return isOurs;
    }

    public void setIsOurs(Integer isOurs) {
        this.isOurs = isOurs;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
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
        if (!(object instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cars[ id=" + id + " ]";
    }
    
}
