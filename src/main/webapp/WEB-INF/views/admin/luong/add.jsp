<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <f:form action="${contextPath }/admin1/luong/luong-post" method="post" modelAttribute="luong" enctype="multipart/form-data">
 
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <spring:message code="luong.table"/>
        <small><spring:message code="add"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="luong.table"/></a></li>
        <li class="active"><spring:message code="add"/></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
         <div class="box box-default">
        <div class="box-header with-border">
          <h3 class="box-title"><spring:message code="user.info"/></h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
          </div>
        </div>
        <span style="color: red">${error }</span>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
         
            <div class="col-md-6">
              <div class="form-group">
                	<label style="display: block">Nhân sự</label>
			
	              	<select class="select2 css_add" style="text-align: center" name="nhanSu">
	              	
					   <optgroup label="Gender">
					   <c:forEach var="c" items="${nhanviens }">
					    <option value="${c.maNhanSu }">${c.ten }</option>
					   </c:forEach>
						 
						 
					  </optgroup>
					
					   
					</select>
              </div>

        </div>
        <!-- /.box-body -->
      
      	<div class="col-md-6">
              <div class="form-group">
                	<label style="display: block">Tiền lương cơ bản</label>
			 	<div class="input-group">
	                <span class="input-group-addon"> <i class="fa fa-fw fa-lock"></i></span>
	              <f:input path="luongToiThieu" class="form-control" type="number" placeholder="Enter So cong"/>
	              
	              </div>
                 <f:errors cssClass="error" path="luongToiThieu"></f:errors>
	              	
              </div>
           
          </div>
          <!-- /.row -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    

 
     
     <!-- CKEDITOR -->
      
     <!-- CKEDITOR -->
     
  		<div class="col-md-6">
          <div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title">Date time</h3>
            </div>
            <div class="box-body">
              <!-- Date dd/mm/yyyy -->
              <div class="form-group">
                <label>Ngày tháng nhận lương</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <f:input path="ngayNhanLuong" type="date" class="form-control" />
                </div>
                <!-- /.input group -->
                <f:errors cssClass="error" path="ngayNhanLuong"></f:errors>
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

        </div>
        

       <div class="col-md-12">
         <button type="submit" class="btn btn-primary">Submit</button>
       </div>
  
  </div>
  

  

 </f:form>
  