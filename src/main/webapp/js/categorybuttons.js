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
            console.log("can't load the initial state in js");
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
        productsContainer.innerHTML = '';
        for (var i = 0; i < products.length; i++) {
            var product = products[i];
            var productHTML = `
              <div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 margin">
                <div class="card">
                  <div class="imgBox">
                    <img src="images/1.png" alt="mouse corsair" class="mouse">
                  </div>
                  <div class="contentBox">
                    <h3>${product.name}</h3>
                    <h2 class="price">${product.price}.<small>EGY</small></h2>
                    <a href="#" class="buy">Buy Now</a>
                  </div>
                </div>
              </div>
            `;
            productsContainer.innerHTML += productHTML;
          }
    }