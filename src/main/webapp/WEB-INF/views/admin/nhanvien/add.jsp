<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <f:form action="${contextPath }/admin1/nhansu/nhansu-post" method="post" modelAttribute="nhanVienForm" enctype="multipart/form-data">
 
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <spring:message code="staff.table"/>
        <small><spring:message code="add"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> <spring:message code="home"/></a></li>
        <li><a href="#"><spring:message code="staff.table"/></a></li>
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
                <label><spring:message code="table.name"/></label>
	               
	              <div class="input-group">
	                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
	                <f:input path="ten" class="form-control" placeholder="Ten"/>
	                 
	              </div>
	              <f:errors cssClass="error" path="ten"></f:errors>
              </div>
               <div class="form-group">
                <label><spring:message code="staff.form.age"/></label>
             
               <div class="input-group">
	                <span class="input-group-addon"> <i class="fa fa-fw fa-user"></i></span>
	             <f:input path="tuoi" type="number" class="form-control" id="exampleInputEmail1" placeholder="Enter tuoi"/>           
	              </div>
	               <f:errors cssClass="error" path="tuoi"></f:errors>
	           
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><spring:message code="user.form.phone"/></label>
                
                  <div class="input-group">
	                <span class="input-group-addon"> <i class="fa fa-fw fa-lock"></i></span>
	              <f:input path="sdt" class="form-control" type="number" placeholder="Enter sdt"/>
	              
	              </div>
                 <f:errors cssClass="error" path="sdt"></f:errors>
              </div>
              <!-- /.form-group -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
                <label><spring:message code="user.form.address"/></label>
                 <f:input path="queQuan" class="form-control" id="exampleInputEmail1" placeholder="Enter address"/>
               </div>
              <!-- /.form-group -->
             <div class="form-group">
                <label><spring:message code="user.form.birthday"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-phone"></i>
                  </div>
                  <f:input path="ngaySinh" type="date" class="form-control" />
               
                </div>
       
                <!-- /.input group -->
              </div>
              
                   <div class="form-group">
                <label style="display: block"><spring:message code="user.form.gender"/></label>
			
	              	<f:select class="select2 css_add" style="text-align: center" path="gioiTinh">
	              	
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
              <h3 class="box-title">File</h3>
            </div>
            <div class="box-body">
              <!-- Date dd/mm/yyyy -->
            
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
              <h3 class="box-title">Important</h3>
            </div>
            <div class="box-body">
              <!-- Date dd/mm/yyyy -->
              <div class="form-group">
                <label><spring:message code="chucvu.name"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                   <i class="fa fa-fw fa-unlock"></i>
                  </div>
                 
                 	<f:select class="select2 css_add" style="text-align: center" path="machucVu">
	              	
					   <optgroup label="Default">
	
					   
					     <f:option value="">Null</f:option>
						   </optgroup>
				
					   <optgroup label="ChucVu">
					   <c:forEach var="c" items="${chucVus }">
					   
					     <f:option value="${c.maChucVuNV }">${c.tenChucVu }</f:option>
						
					   </c:forEach>
					 
					  
					  </optgroup>
					
					   
					</f:select>
                 
                </div>
 				 <f:errors cssClass="error" path="machucVu"></f:errors>
                <!-- /.input group -->

              </div>
                             
              <!-- ROOM -->
              <div class="form-group">
                <label><spring:message code="room.name"/></label>

                <div class="input-group">
                  <div class="input-group-addon">
                 <i class="fa fa-fw fa-flask"></i>
                  </div>
                 
                 	<f:select class="select2 css_add" style="text-align: center" path="roomId"  >
	              	 <optgroup label="Default">
	
					   
					     <f:option value="">Null</f:option>
						   </optgroup>
				
					   <optgroup label="Room">
					   <c:forEach var="r" items="${rooms }">
					   
					     <f:option value="${r.id }">${r.roomName }</f:option>
						
					   </c:forEach>
					 
					  
					  </optgroup>
					
					   
					</f:select>
                 
                </div>
 				<f:errors cssClass="error" path="roomId"></f:errors>
                <!-- /.input group -->
                           
              </div>
              
              <!-- HOc van -->
              <div class="form-group">
                <label><spring:message code="trinhdo.name"/></label>
 				
				
                <div class="input-group">
                  <div class="input-group-addon">
                   <i class="fa fa-fw fa-firefox"></i>
                  </div>
                 
                 	<f:select class="select2 css_add" style="text-align: center" path="maHocVan" >
	              	<optgroup label="Default">
	
					   
					     <f:option value="">Null</f:option>
						   </optgroup>
					   <optgroup label="Trinh Độ">
					   <c:forEach var="hv" items="${hocVans }">
					   
					     <f:option value="${hv.maTrinhDo }">${hv.tenTrinhDo }</f:option>
						
					   </c:forEach>
					 
					  
					  </optgroup>
					
					   
					</f:select>
                 
                </div>
     			<f:errors cssClass="error" path="maHocVan"></f:errors>
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
  