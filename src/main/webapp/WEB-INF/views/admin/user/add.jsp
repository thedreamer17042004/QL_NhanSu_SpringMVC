<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <f:form action="${contextPath }/admin1/user/user-post" method="post" modelAttribute="account" enctype="multipart/form-data">
 
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <spring:message code="user.table"/>
        <small><spring:message code="add"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="user.table"/></a></li>
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
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
         
            <div class="col-md-6">
              <div class="form-group">
               <p style="color: red">${error }</p>
                <label><spring:message code="user.form.email"/></label>
	               
	              <div class="input-group">
	                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
	                <f:input path="email" class="form-control" placeholder="Email"/>
	                 
	              </div>
	              <f:errors cssClass="error" path="email"></f:errors>
              </div>
               <div class="form-group">
                <label><spring:message code="user.form.username"/></label>
             
               <div class="input-group">
	                <span class="input-group-addon"> <i class="fa fa-fw fa-user"></i></span>
	             <f:input path="userName" class="form-control" id="exampleInputEmail1" placeholder="Enter fullName"/>
	              
	              
	               
	              </div>
	               <f:errors cssClass="error" path="userName"></f:errors>
	               
              
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><spring:message code="user.form.password"/></label>
                
                  <div class="input-group">
	                <span class="input-group-addon"> <i class="fa fa-fw fa-lock"></i></span>
	              <f:input path="password" class="form-control" placeholder="Enter password"/>
	              
	              </div>
                 <f:errors cssClass="error" path="password"></f:errors>
              </div>
              <!-- /.form-group -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
                <label><spring:message code="user.form.address"/></label>
                 <f:input path="address" class="form-control" id="exampleInputEmail1" placeholder="Enter address"/>
               </div>
              <!-- /.form-group -->
             <div class="form-group">
                <label><spring:message code="user.form.phone"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-phone"></i>
                  </div>
                  <f:input path="phone" class="form-control" data-inputmask="'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']" data-mask=""/>
               
                </div>
       
                <!-- /.input group -->
              </div>
              
                   <div class="form-group">
                <label style="display: block"><spring:message code="user.form.gender"/></label>
			
	              	<f:select class="select2 css_add" style="text-align: center" path="gender">
	              	
					   <optgroup label="Gender">
					   
					  <f:option value="1">Nam</f:option>
					  <f:option value="0">Nữ</f:option>
					  
					  </optgroup>
					
					   
					</f:select>
              </div>
              
              
              
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
       
            

      
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
                <label><spring:message code="user.form.birthday"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <f:input path="birthday" type="date" class="form-control" />
                </div>
                <!-- /.input group -->
                <f:errors cssClass="error" path="birthday"></f:errors>
              </div>
              
               <div class="form-group">
                <label><spring:message code="user.form.picture"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                 
					<i class="fa fa-fw fa-file-photo-o"></i>

                  </div>
                  <input type="file" name="file"  class="form-control" />
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
              <h3 class="box-title">Authorize</h3>
            </div>
            <div class="box-body">
              <!-- Date dd/mm/yyyy -->
              <div class="form-group">
                <label><spring:message code="user.form.role"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                   <i class="fa fa-fw fa-unlock"></i>
                  </div>
                 
                 	<select class="select2 css_add" style="text-align: center" name="role[]"  multiple="multiple">
	              	
					   <optgroup label="Role">
					   <c:forEach var="r" items="${roles }">
					   
					     <option value="${r.roleId }">${r.roleName }</option>
						
					   </c:forEach>
					 
					  
					  </optgroup>
					
					   
					</select>
                 
                </div>
 
                <!-- /.input group -->
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
  