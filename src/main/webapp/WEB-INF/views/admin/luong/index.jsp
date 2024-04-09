<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <spring:message code="user.data"/>
        <small><spring:message code="luong.table"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i><spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="luong.table"/></a></li>
        <li class="active"><spring:message code="list"/></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
           <div class="box-header">
              <h3 class="box-title">
              
              <a href="${contextPath }/admin1/luong/add" class="btn btn-success"><spring:message code="add"/></a>
             
              
              </h3>
					
					
			   <form action="" method="get" class="box-tools">
              <div class="box-tools">
	                <div class="input-group input-group-sm" style="width: 150px;margin-left: 16px">
	                 <input type="hidden" value="1" name="pageno"/>
	                  <input type="text" style="width: 180px" value="${searchname }" name="searchname" class="form-control pull-right" placeholder="<spring:message code="search.name" />">
	                  <div class="input-group-btn">
	                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
	                  </div>
	                </div>
	              </div>
	              
	            </div>
	            </form>
          
            <!-- /.box-header -->
            <div class="box-body">
            <span style="color: red">${msg }</span>
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
 				<th><label>
                  <input type="checkbox" id="checkAll" class="" >
                </label></th>
                  <th><spring:message code="table.stt"/></th>
                  <th><spring:message code="table.name"/></th>
                  <th>Lương tối thiểu</th>
                  <th>Ngày tháng lĩnh lương</th>
                  <th>Số công</th>
                  <th>Tổng lương</th>
                  <th style="width: 200px"><spring:message code="table.action"/>
                  </th>
                </tr>
                </thead>
                <tbody>
                 
                <c:forEach var="c" items="${luongs }" varStatus="loop">
                	<tr class="js-delete-${c.maChiTietBangLuong }">
             <td> 
        			<label>
                  <input type="checkbox" class="minimal checkbox-js" id="${c.maChiTietBangLuong }" >
                </label></td>
                  <td>${loop.index + 1}</td>
                  <td>
                  ${c.nhanVien.ten }
                  </td>
                  <td>${c.luongCoBan }</td>
                  <td>
                  <fmt:formatDate type = "date" 
         	value = "${c.ngayNhanLuong }" />
                  </td>
                  <td>${c.soCong }</td>
                   <td>${c.tongTienLuong }</td>
                  <td>
                  <a href="${contextPath }/admin1/luong/edit/${c.maChiTietBangLuong}" class="btn btn-primary"><spring:message code="edit"/></a>
                    <a class="btn btn-danger" href="${contextPath }/admin1/luong/delete/${c.maChiTietBangLuong}"><spring:message code="delete"/></a>
                  </td>
                </tr>
                </c:forEach>
 
                </tbody>
              
              </table>
              
              <!-- PAGINATE -->
            
            <div class="row" style="display: flex; align-items: center"><div class="col-sm-5">
              <div class="dataTables_info" style="font-style: italic;" id="example2_info" role="status" aria-live="polite"><span style="font-weight: bold;">${totalRecords }</span> records</div></div>
              <div class="col-sm-7">
              	<div class="dataTables_paginate paging_simple_numbers" style="text-align: right" id="example2_paginate">
              		<ul class="pagination">
		              <li class="paginate_button previous disabled" id="example2_previous">
		              
		               <c:choose>
					<c:when test="${currentpage==1}">
						  <a href="?status=${status}&pageno=${totalpage}" aria-controls="example2" data-dt-idx="7" tabindex="0">Previous</a>
					</c:when>
					<c:otherwise>
						  <a href="?status=${status}&pageno=${currentpage-1}" aria-controls="example2" data-dt-idx="0" tabindex="0">Previous</a>
					</c:otherwise>
				</c:choose>
       
		             
		              </li>
		              	<c:forEach var="i" begin="1" end="${totalpage}">
				<c:choose>
					<c:when test="${i==currentpage}">
						 <li class="paginate_button active">
			              <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0">${i }</a>
			              </li>
					</c:when>
					<c:otherwise>
						  <li class="paginate_button ">
				              <a href="?status=${status}&pageno=${i}" aria-controls="example2" data-dt-idx="2" tabindex="0"> ${i}</a>
				            </li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
             
            
             
             <li class="paginate_button next" id="example2_next">
             <c:choose>
					<c:when test="${currentpage==totalpage}">
						  <a href="?status=${status}&pageno=${1}" aria-controls="example2" data-dt-idx="7" tabindex="0">Next</a>
					</c:when>
					<c:otherwise>
						 <a href="?status=${status}&pageno=${currentpage+1}" aria-controls="example2" data-dt-idx="7" tabindex="0">Next</a>
					</c:otherwise>
				</c:choose>
       
              </li>
              </ul>
              </div>
              </div>
              </div>
            <!-- END PAGINATE -->
		
            </div>
            <!-- /.box-body -->
            
            
           
            
          </div>
          <!-- /.box -->

      
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  

  