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

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.room.Room;
import com.entities.room.RoomPage;
import com.entities.room.RoomView;
import com.repositories.dao.IPhongBan;

@Repository
public class RoomRepo extends CommonRepo<Room> implements IPhongBan{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(Room entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Room findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Room category = (Room) session.createQuery("from Room where id = :id")
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
	public RoomPage paginate(String keyword, int pageno, int pagesize) {
		Locale locale = LocaleContextHolder.getLocale();
		 
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if( keyword.equals("")) {

			  query = session.createQuery(
					  "SELECT r.id,r.address,r.phoneRoom, rl.room,rl.language,rl.roomName,rl.createDate " +
		              "FROM Room r " +
		              "JOIN RoomLanguage rl ON r.id = rl.room.id " +
		              "WHERE rl.language.canonical = :languageId"
				          );
		        query.setParameter("languageId", locale.getLanguage());
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if( keyword!=null) {
			
			  query = session.createQuery(	
		  "SELECT r.id,r.address,r.phoneRoom, rl.room,rl.language,rl.roomName,rl.createDate " +
         "FROM Room r " +
         "JOIN RoomLanguage rl ON r.id = rl.room.id " +
         "WHERE rl.language.canonical = :languageId AND rl.roomName like :roomName"
					  );
		        query.setParameter("languageId", locale.getLanguage()).setParameter("roomName", "%"+ keyword +"%");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		}
		List result=query.getResultList();
		
		
		List<RoomView> roomViews = new ArrayList<>();
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    RoomView roomView = new RoomView(row);
		    roomViews.add(roomView);
		 
		}

		RoomPage fp=new RoomPage();
		fp.setRooms(roomViews);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		

	    return fp;
	}

	@Override
	public RoomView edit(int id) {
		Locale locale = LocaleContextHolder.getLocale();
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		Query query=null;
		 query = session.createQuery( 
				 
			 "SELECT r.id,r.address,r.phoneRoom, rl.room,rl.language,rl.roomName,rl.createDate " +
			 "FROM Room r " +
			 "JOIN RoomLanguage rl ON r.id = rl.room.id " +
			 "WHERE rl.language.canonical = :languageId AND r.id = :id"
						          );
	       query.setParameter("languageId", locale.getLanguage()).setParameter("id", id);
	       
	      List result =  query.getResultList();
		

		
	      RoomView roomView =null;
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    roomView = new RoomView(row);
		}

	      return roomView;
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(Room.class, id);

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
