

function checkQuantity(input,productPrice) {
  
  console.log("check quantity is fired");
    var maxQuantity = Number(input.getAttribute("max"));
    var currentQuantity = Number(input.value);
    var errorSpan = document.getElementById("quantity-error-" + input.id);
    var currentProductId = input.id;
    console.log("max Quantity -> "+maxQuantity);
    if (currentQuantity > maxQuantity) {
      errorSpan.textContent = "Quantity cannot exceed " + maxQuantity;
    }
    else if(currentQuantity === 0)
      errorSpan.textContent = "The Quantity can't be zero";
    else {
      var currentValue = parseInt(input.value);
      var previousValue = parseInt(input.getAttribute('value'));
      var difference = currentValue - previousValue;
      console.log("print the passed productPrice -> "+productPrice);
      var newProductPrice = difference * Number(productPrice);
      console.log("type of newproductprice ->"+typeof newProductPrice);
      // Update the value attribute to the new value
      input.setAttribute('value', currentValue);
      console.log("test the difference price -> "+newProductPrice);
      increaseTotalPriceUI(newProductPrice);
      updateProductQuantity(currentProductId,currentQuantity);
      errorSpan.textContent = "";
    }
}

function checkout(allProductsValid){
  console.log("check on the validation products ->"+allProductsValid);
  // allProducts.forEach(prod => {
  //   console.log("prod qty in cart test -> "+prod.qtyInCart);
  // });
  if(allProductsValid){
    console.log("checkout fired");
    var xhr = new XMLHttpRequest();
    var url = 'checkout';
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        if(xhr.responseText == "success_order")
        {
        console.log("respone sucess from updating cart item -> "+ xhr.responseText);
        var popupSuccess = document.getElementsByClassName("pop-up-success")[0]; // select the first element with the class name
        popupSuccess.style.display = "block";
        }else{
        
          var popupError = document.getElementsByClassName("pop-up-error")[0]; // select the first element with the class name
          var errorEle = document.getElementById("pop-up-error-message");
          errorEle.textContent = xhr.responseText;
          popupError.style.display = "block";
        }
      }
    }
    xhr.send();
  }else
  {
    console.log("the quantity is not valid in some product");
  }
  
}

function updateProductQuantity(currentProductId,currentQuantity){
  var xhr = new XMLHttpRequest();
  var url = 'updateInCart';
  var params = 'productId=' + currentProductId+'&quantity='+currentQuantity;
  xhr.open('POST', url, true);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      console.log("respone sucess from checkout cart -> "+ xhr.responseText);
      // handle response here
    }
  }
  xhr.send(params);
}
function increaseTotalPriceUI(productPrice){
  console.log("check new product price type ->"+typeof productPrice);
  var totalPriceEles = document.querySelectorAll(".total-price-summary");
for (var i = 0; i < totalPriceEles.length; i++) {
  var totalPriceEle = totalPriceEles[i];
  console.log("value of totalPriceEle -->"+totalPriceEle.textContent);
  var totalPrice = Number(totalPriceEle.textContent.split(" ")[0]);
  var newTotalPrice = totalPrice + Number(productPrice);
  console.log("typeof newTotalPrice -->"+typeof newTotalPrice);
  console.log("newTotalPrice -->"+ newTotalPrice);
  if(isNaN(newTotalPrice))
  totalPriceEle.textContent =   "0 E£";
else
  totalPriceEle.textContent = newTotalPrice + " E£";
}
}

function decreaseTotalPriceUI(productPrice){
var totalPriceEles = document.querySelectorAll(".total-price-summary");
for (var i = 0; i < totalPriceEles.length; i++) {
  var totalPriceEle = totalPriceEles[i];
  console.log("value of totalPriceEle -->"+totalPriceEle.textContent);
  var totalPrice = Number(totalPriceEle.textContent.split(" ")[0]);
  var newTotalPrice = totalPrice - Number(productPrice);
  console.log("typeof newTotalPrice -->"+typeof newTotalPrice);
  console.log("newTotalPrice -->"+ newTotalPrice);
  if(isNaN(newTotalPrice)){
    totalPriceEle.textContent = "0 E£";
  }else
    totalPriceEle.textContent = newTotalPrice + " E£";
}
}
function decreaseProductsCount(){
  var countProdcutsEles = document.querySelectorAll(".count-products");
for (var i = 0; i < countProdcutsEles.length; i++) {
  var countProdEle = countProdcutsEles[i];
  console.log("value of countProdEle -->"+countProdEle.textContent);
  var countProd = Number(countProdEle.textContent.split(" ")[1]);
  var newCountProducts = countProd - 1;
  console.log("typeof newCountProducts -->"+typeof newCountProducts);
  console.log("newCountProducts -->"+ newCountProducts);
  if(isNaN(newCountProducts))
  countProdEle.textContent = "0 ITEMS";
  else
  countProdEle.textContent = newCountProducts + " ITEMS";
}
}
function removeProduct(button,event,productPrice){
  var productId = button.getAttribute('data-product');
    var productQtyInCart = document.getElementById(productId).value;    
    console.log("qty in cart test -> "+productQtyInCart);
      var totalProductPrice = Number(productPrice) * Number(productQtyInCart);
      console.log("remove product price * qty is ->"+totalProductPrice);
      var xhr = new XMLHttpRequest();
      decreaseTotalPriceUI(totalProductPrice);
      decreaseProductsCount();
      const container = event.target.closest('.product-container');
      container.remove();
      var url = 'removeFromCart';
      var params = 'productId=' + productId;
      xhr.open('POST', url, true);
      xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
          console.log("respone sucess from removing cart item -> "+ xhr.responseText);
          // handle response here
        }
      }
      xhr.send(params);
}



// $('a.text-muted').click(function() {
//   var productId = $(this).data('productid');
//   console.log("when click on remove -> "+productId);
//   // Perform other actions to remove the product from the cart and update the UI
// });

// $('a.text-muted').click(function() {
//   var productId = $(this).closest('.row').find('input[name="quantity"]').attr('id');
//   console.log("when click on remove -> "+productId);
//   // Perform other actions to remove the product from the cart and update the UI
// });



// const email = "aa@gmail.com";
// initializeCart();
// function checkQuantity(input) {
//   console.log("check quantity is fired");
//     var maxQuantity = Number(input.getAttribute("max"));
//     var currentQuantity = Number(input.value);
//     var errorSpan = document.getElementById("quantity-error-" + input.id);
//     errorSpan.innerText = "hello world";
//     // console.log("product id ->"+input.id);
//     // console.log("currentQuantity ->"+currentQuantity);
//     // console.log("maxQuantity ->"+maxQuantity);
//     if (currentQuantity > maxQuantity) {
//       // console.log("Quantity cannot exceed " + maxQuantity)
//       errorSpan.textContent = "Quantity cannot exceed " + maxQuantity;
//     } else {
//       //console.log("allowable")
//       errorSpan.textContent = "";
//     }
// }
// function initializeCart() {
//   //const email = "${sessionScope.email}";
//   // Send AJAX request to retrieve the list of products
//   // $.ajax({
//   //   url: "cart",
//   //   type: "POST",
//   //   data: {
//   //     action: "getInitialCart",
//   //     email: email
//   //   },
//   //   success: function(response) {
//   //     // Populate the cart container with the list of products
//   //     //$("#cart-container").html(response);
//   //     console.log("success initial cart -> "+response);
//   //   }
//   // });

//   console.log("start initilizing the cart screen");
//   $.ajax({
//     url: "cart",
//     method: "POST",
//     dataType: "json",
//     data: {
//         action: "getInitialCart",
//         email: email
//     },
//     success: function(response) {
//         // Parse the JSON response
//         console.log("respone from initilize cart ->"+response);
//         try{
//           var cartItems = JSON.parse(response);

//         }
//         catch(error) {
//           console.error('Error parsing JSON:', error);
//       }
//         console.log("respone from initilize cart ->"+response);
//         // Clear the contents of the cart-items div
//         populateProducts(cartItems);
//     }, error: function(jqXHR, textStatus, errorThrown) {
//       console.log(textStatus, errorThrown);
//     }
// });

// }
// function populateProducts(cartItems){
//         $('#cart-items').empty();
//         console.log("cart items in populate products -> "+cartItems);
//         // Iterate over the cartItems array and append each item to the cart-items div
//         $.each(cartItems, function(index, product) {
//             var itemHtml = '<div class="row mb-4 d-flex justify-content-between align-items-center">' +
//                                '<div class="col-md-2 col-lg-2 col-xl-2">' +
//                                    '<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img5.webp" class="img-fluid rounded-3" alt="Cotton T-shirt">' +
//                                '</div>' +
//                                '<div class="col-md-3 col-lg-3 col-xl-3">' +
//                                    '<h6 class="text-muted">' + product.brand + '</h6>' +
//                                    '<h6 class="text-black mb-0">' + product.name + '</h6>' +
//                                '</div>' +
//                                '<div class="col-md-3 col-lg-3 col-xl-2 d-flex">' +
//                                    '<button class="btn btn-link px-2" onclick="const downInputField = this.parentNode.querySelector(\'input[type=number]\'); downInputField.stepDown(); checkQuantity(downInputField);"><i class="fas fa-minus"></i></button>' +
//                                    '<input name="quantity" value="' + product.qtyInCart + '" id="' + product.productId + '" type="number" min="1" max="' + product.qtyInStock + '" style="width: 70px;" oninput="checkQuantity(this)">' +
//                                    '<button class="btn btn-link px-2" onclick="const upInputField = this.parentNode.querySelector(\'input[type=number]\'); upInputField.stepUp(); checkQuantity(upInputField);"><i class="fas fa-plus"></i></button>' +
//                                    '<span style="color: #c41a17;" class="text-danger" id="quantity-error-' + product.productId + '"></span>' +
//                                '</div>' +
//                                '<div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">' +
//                                    '<h6 class="mb-0">€ ' + product.price + '</h6>' +
//                                '</div>' +
//                                '<div class="col-md-1 col-lg-1 col-xl-1 text-end">' +
//                                    '<a onclick="deleteProductFromCart(' + product.productId + ')" class="text-muted"><i class="fas fa-times"></i></a>' +
//                                '</div>' +
//                            '</div>' +
//                            '<hr class="my-4">';

//             $('#cart-items').append(itemHtml);
//         });
// }




// function deleteProductFromCart(productId) {
//   //const email = "${sessionScope.email}";
//   // Send AJAX request to delete the product from the cart
//   $.ajax({
//     url: "cart",
//     type: "POST",
//     data: {
//       action: "removeProduct",
//       email: email,
//       productId: productId
//     },
//     success: function(response) {
//       // Reload the cart by calling initializeCart() to retrieve the updated list of products
//       console.log("success removed case in cart -> "+response);
//       var cartItems = JSON.parse(response);
//       populateProducts(cartItems);
//       //initializeCart();
//     }
//   });
// }