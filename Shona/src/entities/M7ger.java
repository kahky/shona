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
@Table(name = "m7ger")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M7ger.findAll", query = "SELECT m FROM M7ger m"),
    @NamedQuery(name = "M7ger.findById", query = "SELECT m FROM M7ger m WHERE m.id = :id"),
    @NamedQuery(name = "M7ger.findByName", query = "SELECT m FROM M7ger m WHERE m.name = :name"),
    @NamedQuery(name = "M7ger.findByTotalPaid", query = "SELECT m FROM M7ger m WHERE m.totalPaid = :totalPaid"),
    @NamedQuery(name = "M7ger.findByTotalRest", query = "SELECT m FROM M7ger m WHERE m.totalRest = :totalRest"),
    @NamedQuery(name = "M7ger.findByM7grOwner", query = "SELECT m FROM M7ger m WHERE m.m7grOwner = :m7grOwner"),
    @NamedQuery(name = "M7ger.findByCreatedat", query = "SELECT m FROM M7ger m WHERE m.createdat = :createdat")})
public class M7ger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "m7ger_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "m7ger_id_seq", sequenceName = "m7ger_ID_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_paid")
    private BigInteger totalPaid;
    @Column(name = "total_rest")
    private BigInteger totalRest;
    @Column(name = "m7gr_owner")
    private String m7grOwner;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @OneToMany(mappedBy = "m7grId")
    private Collection<TwridTransaction> twridTransactionCollection;
    @OneToMany(mappedBy = "m7grId")
    private Collection<Grarat> graratCollection;
    @OneToMany(mappedBy = "m7grId")
    private Collection<Zalat> zalatCollection;

    public M7ger() {
    }

    public M7ger(Integer id) {
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

    public BigInteger getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigInteger totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigInteger getTotalRest() {
        return totalRest;
    }

    public void setTotalRest(BigInteger totalRest) {
        this.totalRest = totalRest;
    }

    public String getM7grOwner() {
        return m7grOwner;
    }

    public void setM7grOwner(String m7grOwner) {
        this.m7grOwner = m7grOwner;
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
    public Collection<TwridTransaction> getTwridTransactionCollection() {
        return twridTransactionCollection;
    }

    public void setTwridTransactionCollection(Collection<TwridTransaction> twridTransactionCollection) {
        this.twridTransactionCollection = twridTransactionCollection;
    }

    @XmlTransient
    public Collection<Grarat> getGraratCollection() {
        return graratCollection;
    }

    public void setGraratCollection(Collection<Grarat> graratCollection) {
        this.graratCollection = graratCollection;
    }

    @XmlTransient
    public Collection<Zalat> getZalatCollection() {
        return zalatCollection;
    }

    public void setZalatCollection(Collection<Zalat> zalatCollection) {
        this.zalatCollection = zalatCollection;
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
        if (!(object instanceof M7ger)) {
            return false;
        }
        M7ger other = (M7ger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.M7ger[ id=" + id + " ]";
    }

}
