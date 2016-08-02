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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
    @NamedQuery(name = "Client.findByTotalPaid", query = "SELECT c FROM Client c WHERE c.totalPaid = :totalPaid"),
    @NamedQuery(name = "Client.findByTotalRest", query = "SELECT c FROM Client c WHERE c.totalRest = :totalRest"),
    @NamedQuery(name = "Client.findByCreatedat", query = "SELECT c FROM Client c WHERE c.createdat = :createdat")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "client_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "client_id_seq", sequenceName = "CLIENT_ID_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_paid")
    private BigInteger totalPaid;
    @Column(name = "total_rest")
    private BigInteger totalRest;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @JoinColumn(name = "createdbyid", referencedColumnName = "id")
    @ManyToOne
    private Users createdbyid;
    @OneToMany(mappedBy = "clientId")
    private Collection<SaderTransaction> saderTransactionCollection;

    public Client() {
    }

    public Client(Integer id) {
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }

}
