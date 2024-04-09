<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <f:form >
 
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <spring:message code="language.name"/>
        <small><spring:message code="view"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="language.name"/></a></li>
        <li class="active"><spring:message code="view"/></li>
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
                <label><spring:message code="table.name"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-fw fa-file-text"></i>
                  </div>
                  <input type="text" readonly="readonly" value="${language.name }" name="name" class="form-control" />
                </div>
                <!-- /.input group -->
             
              
              </div>
              
              
              
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

        </div>
        
         <div class="col-md-6">
          <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title">Canonical</h3>
            </div>
            <div class="box-body">
      

               <div class="form-group">
                <label><spring:message code="table.image"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                 
					<i class="fa fa-fw fa-battery-quarter"></i>

                  </div>
                 
                   <input type="text" readonly="readonly" value="${language.canonical }"  class="form-control" />
                </div>
                </div>
                <!-- /.input group -->
                
              </div>
             
              
               
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          
          
          
           <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title">Image</h3>
            </div>
            <div class="box-body">
      

               <div class="form-group">
                

              
              <img style="width: 400px" src="${contextPath }/${language.image}"/>
                </div>
                <!-- /.input group -->
                
              </div>
             
              
               
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          <div class="col-md-6">
         <a type="submit" href="${contextPath }/admin1/language" class="btn btn-primary">Back</a>
       </div>
        

        </div>
        
        
            
       

      
    


 </f:form>
  