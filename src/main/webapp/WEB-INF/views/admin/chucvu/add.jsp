<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <f:form action="${contextPath }/admin1/chucvu/chucvu-post" method="post" >
 
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <spring:message code="chucvu.name"/>
        <small><spring:message code="add"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="chucvu.name"/></a></li>
        <li class="active"><spring:message code="add"/></li>
      </ol>
    </section>


	<div style="width: 100%;height: 30px"></div>
     
     <!-- CKEDITOR -->
      
     <!-- CKEDITOR -->
  		<div class="col-md-6">
          <div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title">Data</h3>
            </div>
            <div class="box-body">
              <!-- Date dd/mm/yyyy -->
              <div class="form-group">
                <label><spring:message code="table.namecv"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-fw fa-file-text"></i>
                  </div>
                  <input type="text"  name="name" class="form-control" />
                </div>
                <!-- /.input group -->
                <span style="color: red">${error}</span>
              
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
  