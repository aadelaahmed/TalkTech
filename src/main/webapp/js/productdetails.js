function onAddToCartClicked(){
    console.log(typeof productJson);
    console.log(productJson);
    console.log("product quantity ->"+productJson.quantity);
    var quantity = productJson.quantity;
    var quantityInput = document.getElementById().value;
    if(quantityInput > quantity)
        console.log("you can't")
}

function onAddTemp(productJson){
    console.log(productJson);
    console.log("new button click");
}

