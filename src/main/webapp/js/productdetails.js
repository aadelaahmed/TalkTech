function onAddToCartClicked(){
    var productId = productJson.productId;
    console.log("add to cart button was fired");
    console.log("check on product id -> "+productId);
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

