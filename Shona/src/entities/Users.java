/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByIsadmin", query = "SELECT u FROM Users u WHERE u.isadmin = :isadmin"),
    @NamedQuery(name = "Users.findByPasswo", query = "SELECT u FROM Users u WHERE u.passwo = :passwo"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByCreatedbyid", query = "SELECT u FROM Users u WHERE u.createdbyid = :createdbyid"),
    @NamedQuery(name = "Users.findByCreatedat", query = "SELECT u FROM Users u WHERE u.createdat = :createdat")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "Users_seq")
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "Users_seq", sequenceName = "Users_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "isadmin")
    private Integer isadmin;
    @Column(name = "passwo")
    private String passwo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "createdbyid")
    private Integer createdbyid;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<M7ger> m7gerCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<Cars> carsCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<TwridTransaction> twridTransactionCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<Grarat> graratCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<Client> clientCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<Zalat> zalatCollection;
    @OneToMany(mappedBy = "createdbyid")
    private Collection<SaderTransaction> saderTransactionCollection;

    public Users() {
    }

    public Users(Integer id) {
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

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public String getPasswo() {
        return passwo;
    }

    public void setPasswo(String passwo) {
        this.passwo = passwo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCreatedbyid() {
        return createdbyid;
    }

    public void setCreatedbyid(Integer createdbyid) {
        this.createdbyid = createdbyid;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @XmlTransient
    public Collection<M7ger> getM7gerCollection() {
        return m7gerCollection;
    }

    public void setM7gerCollection(Collection<M7ger> m7gerCollection) {
        this.m7gerCollection = m7gerCollection;
    }

    @XmlTransient
    public Collection<Cars> getCarsCollection() {
        return carsCollection;
    }

    public void setCarsCollection(Collection<Cars> carsCollection) {
        this.carsCollection = carsCollection;
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
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @XmlTransient
    public Collection<Zalat> getZalatCollection() {
        return zalatCollection;
    }

    public void setZalatCollection(Collection<Zalat> zalatCollection) {
        this.zalatCollection = zalatCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ id=" + id + " ]";
    }
    
}
