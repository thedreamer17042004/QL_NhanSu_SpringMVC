package com.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.entities.nhanvien.NhanVien;
import com.entities.nhanvien.NhanVienForm;
import com.entities.nhanvien.NhanVienPage;
import com.entities.user.Account;
import com.entities.user.AccountPage;
import com.repositories.dao.INhanVien;

@Repository
public class NhanVienRepo extends CommonRepo<NhanVien> implements INhanVien{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(NhanVien entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NhanVien findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			System.out.println(value);
			NhanVien account = (NhanVien) session.createQuery("from NhanVien where maNhanSu = :maNhanSu")
			.setParameter("maNhanSu", value).uniqueResult();

			return account;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

	@Override
	public NhanVien findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhanVienPage paginate
		(	Integer maCapBac,
			String maPhong,
			Integer trinhDoHocVan, 
			String keyword, 
			int pageno,
			int pagesize
		) {
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;

		// Query construction
		String queryString ="select nv.maNhanSu,nv.ten,nv.tuoi,nv.gioiTinh,nv.hinhAnh,nv.sdt,nv.queQuan,nv.ngaySinh,cvl.chucvu.id,rl.room.id,hvl.hocVan.maTrinhDo,cvl.tenChucVu,hvl.tenTrinhDo,rl.roomName from NhanVien nv " +
				 "JOIN ChucVuLanguage cvl ON nv.chucVu.id = cvl.chucvu.id " +
				"JOIN HocVanLanguage hvl ON nv.hocVan.maTrinhDo = hvl.hocVan.maTrinhDo " +
				"JOIN RoomLanguage rl ON nv.room.id = rl.room.id " +
				"where maNhanSu > 0 ";
		                    

		// Add search condition
		if (!keyword.equals("")) {
		    queryString += "AND ten LIKE :ten ";
		}

		if (maCapBac != -1) {
		    queryString += "AND nv.chucVu.maChucVuNV = :maChucVuNV ";
		}
		if (!maPhong.equals("")) {
		    queryString += "AND nv.room.id = :roomId ";
		}
		if (trinhDoHocVan != -1) {
		    queryString += "AND nv.hocVan.maTrinhDo = :maTrinhDo ";
		}
		

		// Create query
		query = session.createQuery(queryString);
	
		// Set search parameter if applicable
		if (!keyword.equals("")) {
		    query.setParameter("ten", "%" + keyword + "%");
		}

		if (maCapBac != -1) {
		    query.setParameter("maChucVuNV", maCapBac);
		}
		if (!maPhong.equals("")) {
		    query.setParameter("roomId", maPhong);
		}
		
		if (trinhDoHocVan != -1) {
		    query.setParameter("maTrinhDo", trinhDoHocVan);
		}
		// Set pagination
		 records = query.getResultList().size();
		query.setFirstResult((pageno - 1) * pagesize).setMaxResults(pagesize);

		
		List result=query.getResultList();
		
		List<NhanVienForm> nhanVienForms = new ArrayList<>();
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;

		    NhanVienForm nvForm = new NhanVienForm(row);
		    nhanVienForms.add(nvForm);
		 
		}

		
		NhanVienPage fp=new NhanVienPage();
		fp.setNhanViens(nhanVienForms);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		
	    return fp;
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(NhanVien.class, id);

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
	public NhanVien findById(int value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			System.out.println(value);
			NhanVien account = (NhanVien) session.createQuery("from NhanVien where maNhanSu = :maNhanSu")
			.setParameter("maNhanSu", value).uniqueResult();

			return account;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

}
