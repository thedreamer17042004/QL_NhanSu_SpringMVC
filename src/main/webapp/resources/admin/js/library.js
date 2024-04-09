
 $(function () {
});
HT = {};
	
	function addBasket(productId) {
	$.get(path + "/addItem/" + productId, function(data) {
		$('.cart-item-count').html(data);
		window.scrollTo({ top: 0, behavior: 'smooth' });
	});
}
	
	HT.deleteAll = () => {
		$(".delete-all").on('click', function(e){
			e.preventDefault();
		
				//lấy tất cả input checked 
				  let _this = $(this)
				  let idss = [];
		       		$('input[type="checkbox"].minimal, input[type="radio"].minimal').each(function(){
				    let checkBox = $(this);
				 
				    if(checkBox.iCheck('update')[0].checked){
				        //idss.push(checkBox.attr('id'));
				        idss = idss.concat(checkBox.attr('id'));
				    }
					});
		
		let data = {
			ids: JSON.stringify(idss),
		}
		 
            // Perform AJAX call or any other actions based on the switch state
          
            // Example of an AJAX call:
            
            $.ajax({
                type: 'POST',
                url: '/BtlSem2/admin1/delete-all', // Replace with your endpoint URL
                data: data,
				
                success: function(response) {
                    // Handle success response
                    
                    if(response=="ok"){

                    for(let i = 0; i < idss.length; i++){
                          $(".js-delete-"+idss[i]).remove();
					}
					}
                    
                  
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error(error);
                }
            });
		
		})
		
		
		
		
	}
	

	
	
HT.changeStatusAll = () => {
	$(".active-status-all").on('click', function(e){
		e.preventDefault();
		
		//lấy tất cả input checked 
		  let _this = $(this)
		  let idss = [];
       $('input[type="checkbox"].minimal, input[type="radio"].minimal').each(function(){
		    let checkBox = $(this);
		 
		    if(checkBox.iCheck('update')[0].checked){
		        //idss.push(checkBox.attr('id'));
		        idss = idss.concat(checkBox.attr('id'));
		    }
		});
		
		
		
		
		
		
                
         
		let data = {
			ids: JSON.stringify(idss),
			switchState: _this.attr("data-value")
		}
		 
            // Perform AJAX call or any other actions based on the switch state
          
            // Example of an AJAX call:
            
            $.ajax({
                type: 'POST',
                url: '/BtlSem2/admin1/change-status-all', // Replace with your endpoint URL
                data: data,
				
                success: function(response) {
                    // Handle success response
                    if(response=="ok"){
						
				  let cssActive1 = 'background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 15.5px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;';
                    let cssActive2 = 'left: 20px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;';
                    let cssUnActive = 'background-color: rgb(255, 255, 255); border-color: rgb(223, 223, 223); box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s;'
                    let cssUnActive2 = 'left: 0px; transition: background-color 0.4s ease 0s, left 0.2s ease 0s;'

                    for(let i = 0; i < idss.length; i++){
                        if(data.switchState == "ON"){
                           $(".js-switch2-"+idss[i])
                           .find('span.switchery')
                           .attr('style', cssActive1)
                           .find('small')
                           .attr('style', cssActive2)

                           
                        }else if(data.switchState == "OFF"){
							console.log("off")
							
	
                            $(".js-switch2-"+idss[i])
                            .find('span.switchery')
                            .attr('style', cssUnActive)
                            .find('small')
                            .attr('style', cssUnActive2)
                        }
                    }
				
					}
                    
                  
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error(error);
                }
            });
	})
}
	

HT.checkBox1 = () => {

	   
	$('input[type="checkbox"].minimal, input[type="radio"].minimal').on('ifChecked', function(event) {
	    let _this = $(this);
	   
	    HT.changeBackground(_this);
	});
	
	$('input[type="checkbox"].minimal, input[type="radio"].minimal').on('ifUnchecked', function(event) {
	    let _this = $(this);
	 
	    HT.changeBackground(_this);
	});
   		    
}

HT.changeBackground = (object) => {
    let isChecked = object.prop('checked');
 
    if(isChecked){
        object.closest('tr').addClass('active-bg')
    }else{
        object.closest('tr').removeClass('active-bg')
    }
}

	var checkedInputs1 = 0;
	
    // Event handler for when a checkbox or radio button is checked using iCheck
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').on('ifChanged', function(event) {
        // Reset the count of checked inputs to zero
        checkedInputs1 = 0;

        // Iterate through each iCheck element
        $('input[type="checkbox"].minimal:checked, input[type="radio"].minimal:checked').each(function() {
            // Increment the count of checked inputs
            checkedInputs1++;
        });

        // Log the count of checked inputs
      
     
	    if(checkedInputs1>0){
		console.log( $(".advanced"))
		  $("#advanced").addClass('hover_event');
		  console.log('qua đây')
	  }else {
		    $("#advanced").removeClass('hover_event');
	  }
        
         let allCheck = HT.getAllCheckedAndUnchecked();
        
        let allChecked = checkedInputs1 === allCheck;
      
        $('#checkAll').prop('checked', allChecked);
        
    });


	HT.getAllCheckedAndUnchecked = () => {
		 var checkedInputs = 0;
		 $('input[type="checkbox"].minimal, input[type="radio"].minimal').each(function() {
        // Check if the current element is checked using iCheck's methods
       checkedInputs++;
    });
    
    return checkedInputs;
	}
	

	
	HT.checkAll = () => {
		  if($('#checkAll').length){
            $(document).on('click', '#checkAll', function(){
			let isCheck = $(this).prop("checked");
			 if(isCheck){
				console.log( $(".advanced"))
				  $("#advanced").addClass('hover_event');
				  console.log('qua đây')
			  }else {
				    $("#advanced").removeClass('hover_event');
			  }
			 $('input[type="checkbox"].minimal, input[type="radio"].minimal').prop('checked', isCheck);
            $('input[type="checkbox"].minimal, input[type="radio"].minimal').each(function() {
				let _this = $(this);
				HT.changeBackground(_this);
				 $('input[type="checkbox"].minimal').iCheck('update');
			})
            $('input[type="checkbox"].minimal').iCheck('update');
            })
        }
		
	}
	


/*HT.switcheryUser = () => {
	var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
			
		 for (var i = 0; i < elems.length; i++) {
            var switchery = new Switchery(elems[i]);
           
            elems[i].addEventListener('change', function() {
                handleSwitchChange(this);
                
            });
        }

        function handleSwitchChange(element) {
            // Determine the state of the switch
            var switchState = element.checked ? "ON" : "OFF";
            
            // Perform AJAX call or any other actions based on the switch state
       
            // Example of an AJAX call:
            
            $.ajax({
                type: 'POST',
                url: '/BtlSem2/admin1/change-status', // Replace with your endpoint URL
                data: { switchId: element.id, switchState: switchState },
                success: function(response) {
                    // Handle success response
                    console.log(response);
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error(error);
                }
            });
            
        }
}
	*/

HT.switcheryUser = (url1,class1) => {
	var elems = Array.prototype.slice.call(document.querySelectorAll(class1));
			
		 for (var i = 0; i < elems.length; i++) {
            var switchery = new Switchery(elems[i]);
           
            elems[i].addEventListener('change', function() {
                handleSwitchChange(this, url1);
                
            });
        }
        
        function handleSwitchChange(element) {
            // Determine the state of the switch
            var switchState = element.checked ? "ON" : "OFF";
            
            // Perform AJAX call or any other actions based on the switch state
       
            // Example of an AJAX call:
            console.log(url1, class1)
            $.ajax({
                type: 'POST',
                url: url1, // Replace with your endpoint URL
                data: { switchId: element.id, switchState: switchState },
                success: function(response) {
                    // Handle success response
                    console.log(response);
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error(error);
                }
            });
            
        }

       
}
	
	
	
	 
	
	
	
	
HT.selectFilter = () => {
 $(".select21").select2({ width: '150px'});
}

HT.selectRole = () => {
	 $(".css_add").select2({ placeholder: "Select a role",allowClear: true,width: '100%' })
}
HT.iCheckBox = () => {
	  $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });
    
}

 var initialOrder = [];
    $("#image-album1").find('div').each(function() {
        initialOrder.push($(this).find('img').attr('src'));
    });
    $("#imageOrder").val(initialOrder.join(','));


HT.remove = () => {
	$(".button-remove").on('click',function(e){
		e.preventDefault();
		$(this).closest('div').remove();
		$("#image-album1").sortable("option", "update").call($("#image-album1"));
	})
}

HT.sortableEdit = () => {
	$("#image-album1").sortable({
       
        update: function(event, ui) {
            // Get the new order of image URLs
            var newOrder = [];
            $(this).find('div').each(function() {
                newOrder.push($(this).find('img').attr('src'));
            });
            // Set the new order as a hidden input value
            $("#imageOrder").val(newOrder.join(','));
        }
    });
}
HT.sortableAdd = () => {
	$("#image-album").sortable({  
    cursor: "move",
      tolerance: "pointer"
	});
}

function calculateDivHeight() {
    var totalHeight = 0;
    $('#image-album img').each(function() {
        totalHeight += $(this).outerHeight(true); // Include padding and borders
    });
    return totalHeight;
}

  function previewImages(event) {
	 
            var files = event?.target?.files;
            var imagesContainer = $('#image-album');
            imagesContainer.empty(); // Clear previous previews
            
            var loadedImages = 0;
        
            for (var i = 0; i < files?.length; i++) {
                var file = files[i];
                var reader = new FileReader();
                reader.onload = function(event) {
                    var imageUrl = event.target.result;
                    var divElement = $('<div>')
                    divElement.css({
                        position: 'relative'
                     })
                  
				var button = $('<button>', {
				    'class': 'delete-image', // Add class
				    'css': { // Add CSS styles
				        'position': 'absolute',
				        'left': '0',
				        'top': '5px',
				        'background-color': 'green',
				        'border-radius': '30%'
				    },
				    'html': '<i class="fa fa-trash" style="color: black"></i>', // Add inner HTML
				    'click': function() {
				        // Define what happens when the button is clicked
				        // For example, you can delete the corresponding image
				         $(this).closest('div').remove();
				    }
				});
				
                    
                    var imgElement = $('<img>');
                    imgElement.attr('src', imageUrl);
                    imgElement.css({
                         width: '120px', // Enclosed in quotes
                         border: '2px solid red', // Enclosed in quotes
                         marginRight: '10px', // Enclosed in quotes
                         marginTop: '5px', // Enclosed in quotes
                         position: 'relative'
                       
                    });

                    
                    imgElement.addClass('image-item');
                    divElement.append(imgElement)
                    divElement.append(button)
                    imagesContainer.append(divElement);
                   /*imagesContainer.css('height', calculateDivHeight() + 'px');*/
                //     $(imgElement).draggable({
                // containment: "#preview-container",
                // cursor: "move",
                //     })
                };
                reader.readAsDataURL(file);
            }
        }
  
$(document).ready(function() {
	HT.checkBox1();
	HT.iCheckBox();
	HT.selectRole();
	HT.selectFilter();
	HT.switcheryUser('/BtlSem2/admin1/category/change-status-category', '.js-switch-category');
	HT.switcheryUser('/BtlSem2/admin1/change-status', '.js-switch');
	HT.switcheryUser('/BtlSem2/admin1/brand/change-status-brand', '.js-switch-brand');
	HT.switcheryUser('/BtlSem2/admin1/product/change-status-product', '.js-switch-product');
	HT.switcheryUser('/BtlSem2/admin1/postcategory/change-status-postcategory', '.js-switch-postcategory');
	HT.switcheryUser('/BtlSem2/admin1/post/change-status-post', '.js-switch-post');
	HT.checkAll();
	HT.changeStatusAll();
	HT.deleteAll();
	HT.sortableAdd();
	HT.sortableEdit();
	HT.remove();
	previewImages();
	    

})
