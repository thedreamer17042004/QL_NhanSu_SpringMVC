package com.repositories.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.hocvan.HocVan;
import com.entities.hocvan.HocVanView;
import com.entities.room.RoomPage;
import com.entities.room.RoomView;
import com.repositories.dao.ICong;

@Repository
public class CongRepo extends CommonRepo<Cong> implements ICong {

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public boolean delete(Cong entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cong findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Cong category = (Cong) session.createQuery("from Cong where id = :id")
			.setParameter("id", value).uniqueResult();
			return category;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

	@Override
	public CongPage paginate(String keyword, int pageno, int pagesize) {
		Locale locale = LocaleContextHolder.getLocale();
		 
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if( keyword.equals("")) {

			  query = session.createQuery(
					  "from Cong "
				          );
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if( keyword!=null) {
			
			  query = session.createQuery(	
					  "from Cong where nhanVien.ten like :ten"
					  );
		        query.setParameter("ten", "%"+ keyword +"%");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		}
		List<Cong> result=query.getResultList();
		
		
		
		CongPage fp=new CongPage();
		fp.setCongs(result);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		

	    return fp;
	}

	@Override
	public Cong edit(int id) {
		return null;
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(Cong.class, id);

			session.remove(st);

			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return false;
	}

	@Override
	public Cong findSoCongByMaAndDate(int maNhanVien, Date ngayNhanluong) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		  LocalDate localDate = ngayNhanluong.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        int month = localDate.getMonthValue();
	       System.out.println(month);
	        System.out.println("mã nhân viên "+ maNhanVien);
		try {
			Cong category = (Cong) session.createQuery("from Cong as c where c.nhanVien.maNhanSu = :id and MONTH(ngay) = :ngay")
			.setParameter("id", maNhanVien)
			.setParameter("ngay", month)
			.uniqueResult();
			System.err.println("Có công"+ category.getSoCong());
			return category;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}
	 public int getMonthFromDate(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.MONTH) + 1; // Adding 1 because month index starts from 0
	    }
	
}
