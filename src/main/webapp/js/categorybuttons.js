var xhr = new XMLHttpRequest();
var url = "category";
var activeFilters = [];
var params = "";
document.addEventListener("DOMContentLoaded", function(event) {
        const filterButtons = document.querySelectorAll('.filter-button');
        console.log("categoriiiiziiing buttons");
        console.log("active filters -> "+activeFilters);
        params = "filters=" + activeFilters.join(',');
        console.log("the params in empty category ->"+params);
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(params);
        xhr.onreadystatechange = function() {
          if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText); // This is the response from the servlet
            populateUI(xhr.responseText);
          }else
            {
              console.log(xhr.responseText);
              onsole.log("can't load the initial state in js");
            }
        }
        filterButtons.forEach(button => {
            button.addEventListener('click', () => {
                button.classList.toggle('active');
                activeFilters = [];
                filterButtons.forEach(button => {
                    if (button.classList.contains('active')) {
                        activeFilters.push(button.getAttribute('data-filter'));
                    }
                });
                params = "filters=" + activeFilters.join(',');
                xhr.open("POST", url);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send(params);
            });
        });
    });

    function populateUI(responseStr){
      var products = JSON.parse(responseStr);
      var productsContainer = document.getElementById('products-container');
      var html = '';
      for (var i = 0; i < products.length; i++) {
        var product = products[i];
        var productHTML = `
          <div style="cursor: pointer;" onclick="onProductItemClicked(${product.productId})" class="col-xl-4 col-lg-4 col-md-4 col-sm-6 margin">
            <div class="card">
              <div class="imgBox">
                <img src="images/1.png" alt="mouse corsair" class="mouse">
              </div>
              <div class="contentBox">
                <h3>${product.name}</h3>
                <h2 class="price">${product.price}.<small>EGY</small></h2>
                <a onclick="onBuyNowClicked(event,${product.productId})" class="buy">Buy Now</a>

              </div>
            </div>
          </div>
        `;
        html += productHTML;
      }
      productsContainer.innerHTML = html;
      
    }
    function onBuyNowClicked(event,productId){
      event.stopPropagation();
      //var productId = productJson.productId;
      console.log("buy now button was fired");
      console.log("check on product id on buy now -> "+productId);
      var url = 'addToCart';
      var params = 'productId=' + productId;
      var xhr = new XMLHttpRequest();
      xhr.open('POST', url, true);
      xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhr.onreadystatechange = function() {
          if (xhr.readyState == 4 && xhr.status == 200) {
              console.log("Product added to cart.");
              window.location.href = "showCart";
              //move to cart screen with get request and close the ajax
              // handle success here
          }
      }
      xhr.send(params);
  }
    