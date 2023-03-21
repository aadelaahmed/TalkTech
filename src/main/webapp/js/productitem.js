function onProductItemClicked(productId){
    console.log("hello from product item js ");
    const servletUrl = 'productdetails?productid=' + productId;
    window.location.href = servletUrl;
}