package com.hcl.usaa.timesheet.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hcl.usaa.timesheet.hibernate.util.HibernateUtil;

/**
 * Bucket generated by hbm2java
 */
@Entity
@Table(name = "bucket", catalog = "timesheet")
public class Bucket implements java.io.Serializable {

	private static final long SerialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "idbucket")
	private Integer idbucket;
	@Column(name = "bucketname")
	private String bucketname;
	@Column(name = "active")
	private Boolean active;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idbussinesline", nullable = false)
	private BussinesLine idbussines_line;

	public Bucket(Integer idbucket, String bucketname, Boolean active,
			BussinesLine idbussines_line) {
		super();
		this.idbucket = idbucket;
		this.bucketname = bucketname;
		this.active = active;
		this.idbussines_line = idbussines_line;
	}

	public Bucket() {
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("ID Bucket: ");
		sb.append(idbucket).append(" ");
		sb.append("Bucket Name: ");
		sb.append(bucketname).append(" ");
		sb.append("Active: ");
		sb.append(active).append(" ");
		sb.append("BussinesLine: ");
		sb.append(idbussines_line).append(" ");
		sb.append("]");
		return sb.toString();
	}

	public Bucket getById(Integer id) {
		Bucket u = null;
       try {
    	   Session session=HibernateUtil.getDBSession();
	       HibernateUtil.beginTransaction();
	       Criteria c=session.createCriteria(getClass());
	       c.add(Restrictions.eq("idbucket", id));
	       c.add(Restrictions.eq("active", Boolean.TRUE));
	       
	       u=(Bucket) c.uniqueResult();
           HibernateUtil.commitTransaction();
       } catch (Exception e) {
    	   e.printStackTrace();
    	   String err="An error occurs while try to get elements";
    	   System.err.println(err);
       }finally{
    	   //HibernateUtil.closeSession();
       }
       return u;
	}
	
	@Override
	public boolean equals(Object c) {
		Bucket t = this;
		boolean bRet = false;
		if (!(c instanceof Bucket)) {
			return false;
		}
		Bucket x = (Bucket) c;
		if (x != null && x.getIdbucket().equals(t.getIdbucket())) {
			bRet = true;
		}
		return bRet;
	}

	public List<Bucket> getAllBuckets() {
		return getList(0, 0, true);

	}

	public List<Bucket> getList(int firstResult, int maxResult) {
		return getList(firstResult, maxResult, false);
	}

	private List<Bucket> getList(int firstResult, int maxResult, boolean all) {
		List<Bucket> lRet = new ArrayList<Bucket>();
		Session session = null;
		//Query q = null;
		try {
			session = HibernateUtil.getDBSession();
			HibernateUtil.beginTransaction();
			//q = session.createQuery("from Bucket");
			Criteria c=session.createCriteria(getClass());
			c.add(Restrictions.eq("active", Boolean.TRUE));
			if (!all) {
				c.setFirstResult(firstResult);
				c.setMaxResults(maxResult);
			}
			lRet = c.list();
			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			try {
				HibernateUtil.rollBackTransaction();
			} catch (Exception ex1) {
				System.err
						.println("An error occurs while try to do rollback on transaction");
			}
			System.err
					.println("An error occurs while try to get Bucket elements");
			System.out.println(e);
		} finally {
			HibernateUtil.closeSession();
		}
		return lRet;
	}

	public boolean save() {
		boolean bRet = false;
		Session session = null;
		try {
			session = HibernateUtil.getDBSession();
			HibernateUtil.beginTransaction();
			session.save(this);
			HibernateUtil.commitTransaction();
			bRet = true;
		} catch (Exception ex) {
			try {
				HibernateUtil.rollBackTransaction();
			} catch (Exception ex1) {
				System.err
						.println("An error occurs while try to do rollback on transaction");
			}
			System.err
					.println("An error occurs while try to save current element");
			System.out.println(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return bRet;
	}

	public boolean update() {
		boolean bRet = false;
		Session session = null;
		try {
			session = HibernateUtil.getDBSession();
			HibernateUtil.beginTransaction();
			session.update(this);
			HibernateUtil.commitTransaction();
			bRet = true;
		} catch (Exception ex) {
			try {
				HibernateUtil.rollBackTransaction();
			} catch (Exception ex1) {
				System.err
						.println("An error occurs while try to do rollback on transaction");
			}
			System.err
					.println("An error occurs while try to update current object");
		} finally {
			HibernateUtil.closeSession();
		}
		return bRet;
	}

	public boolean delete() {
		boolean bRet = false;
		Session session = null;
		try {
			session = HibernateUtil.getDBSession();
			HibernateUtil.beginTransaction();
			setActive(Boolean.FALSE);
			session.update(this);
			HibernateUtil.commitTransaction();
			bRet = true;
		} catch (Exception ex) {
			try {
				HibernateUtil.rollBackTransaction();
			} catch (Exception ex1) {
				System.err
						.println("An error occurs while try to do rollback on transaction");
			}
			System.err
					.println("An error occurs while try to delete current object");
		} finally {
			HibernateUtil.closeSession();
		}
		return bRet;
	}

	public Integer getIdbucket() {
		return idbucket;
	}

	public String getBucketname() {
		return bucketname;
	}

	public Boolean getActive() {
		return active;
	}

	public BussinesLine getIdbussines_line() {
		return idbussines_line;
	}

	public void setIdbucket(Integer idbucket) {
		this.idbucket = idbucket;
	}

	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setIdbussines_line(BussinesLine idbussines_line) {
		this.idbussines_line = idbussines_line;
	}

}
