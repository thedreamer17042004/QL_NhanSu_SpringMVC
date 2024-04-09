package com.repositories.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.chamcong.Cong;
import com.entities.luong.ChiTietLuong;
import com.entities.luong.Luong;
import com.entities.luong.LuongPage;
import com.repositories.dao.ILuong;

@Repository
public class LuongRepo extends CommonRepo<Luong> implements ILuong{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(Luong entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Luong findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Luong category = (Luong) session.createQuery("from Luong where id = :id")
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
	public LuongPage paginate(String keyword, int pageno, int pagesize) {
		 System.out.println("Vao paginate");
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			int records=0;
			Query query=null;
			if( keyword.equals("")) {

				  query = session.createQuery(
						  "from ChiTietLuong "
					          );
				records=query.getResultList().size();
				query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
			}else if( keyword!=null) {
				
				  query = session.createQuery(	
						  "from ChiTietLuong where nhanVien.ten like :ten"
						  );
			        query.setParameter("ten", "%"+ keyword +"%");
				records=query.getResultList().size();
				query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
			}
			List<ChiTietLuong> result=query.getResultList();
			 System.out.println("Ra paginate");

			LuongPage fp=new LuongPage();
			fp.setLuongs(result);
			fp.setCurrentPage(pageno);
			fp.setPageSize(pagesize);
			fp.setTotalRecord(records);
			int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
			fp.setTotalPages(totalpage);
			

		    return fp;
	}

	@Override
	public Luong edit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(Luong.class, id);

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

}
