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
@Table(name = "grarat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grarat.findAll", query = "SELECT g FROM Grarat g"),
    @NamedQuery(name = "Grarat.findById", query = "SELECT g FROM Grarat g WHERE g.id = :id"),
    @NamedQuery(name = "Grarat.findByTk3ib", query = "SELECT g FROM Grarat g WHERE g.tk3ib = :tk3ib"),
    @NamedQuery(name = "Grarat.findByOwnerName", query = "SELECT g FROM Grarat g WHERE g.ownerName = :ownerName"),
    @NamedQuery(name = "Grarat.findByCreatedat", query = "SELECT g FROM Grarat g WHERE g.createdat = :createdat")})
public class Grarat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "grarat_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "grarat_id_seq", sequenceName = "GRARAT_ID_SEQ", allocationSize = 1)

    private Integer id;
    @Column(name = "tk3ib")
    private BigInteger tk3ib;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @OneToMany(mappedBy = "grarId")
    private Collection<TwridTransaction> twridTransactionCollection;
    @JoinColumn(name = "m7gr_id", referencedColumnName = "id")
    @ManyToOne
    private M7ger m7grId;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;

    public Grarat() {
    }

    public Grarat(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grarat)) {
            return false;
        }
        Grarat other = (Grarat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Grarat[ id=" + id + " ]";
    }

}
