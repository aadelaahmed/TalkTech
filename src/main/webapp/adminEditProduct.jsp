﻿<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit Product</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="css/popup.css">
    
<script>
        function Logout(event) {
            console.log('inside Logout');
            event.preventDefault();   
              $.ajax({
                  url: "LogoutServlet",
                  type: "POST",
                  success: function(){
                      var popup = document.getElementById("popup");
                      popup.style.display = "block";
                  }                                
              });
          
        }</script>
    <script>
        function EditProduct() {
            const form = document.querySelector('#editForm');



            const formData = new FormData(form);
            const json = JSON.stringify(Object.fromEntries(formData.entries()));
            console.log(json);
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'EditProductServlet');
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(json);

            xhr.onload = () => {
                if (xhr.status === 200) {
                    console.log(xhr.statusText)
                    window.location.href = 'AdminProductsServlet';
                } else {
                    console.error(xhr.statusText);
                }
            };
        }
    </script>

</head>

<body>
    <div class="popup_box" id="popup" style="display:none">
        <i class="fas fa-exclamation"></i>
        <h1>You Have Successfully Logged Out</h1>
        <div class="btns">
          <a href="home" class="btn1">Continue</a>
        </div>
      </div>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="admin.jsp">Admin</a>
            </div>
            <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;">&nbsp; <button onclick="Logout(event)" class="btn btn-danger square-btn-adjust">Logout</button>
            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li class="text-center">
                        <img src="assets/img/find_user.png" class="user-image img-responsive" />
                    <li>
                        <a href="AdminUsersServlet"><i class="fa fa-table fa-3x"></i>View Users</a>
                    </li>
                    <li>
                        <a href="AdminProductsServlet"><i class="fa fa-table fa-3x"></i>View Products</a>
                    </li>


                    <li>
                        <a href="adminAddProduct.jsp"><i class="fa fa-edit fa-3x"></i> Add Product</a>
                    </li>
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h2>Edit Product</h2>
                        <h5>Welcome ${sessionScope.userName} , Love to see you back. </h5>

                    </div>
                </div>
                <!-- /. ROW  -->
                <hr />
                <div class="row">
                    <div class="col-md-12">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Edit Product
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <form role="form" id="editForm" name="editForm">
                                            <div class="form-group">
                                                <img style="width: 280px; height: 350px;" src="${param.imageUrl}">
                                            </div>
                                            <div class="form-group">
                                                <label>ID</label>

                                                <input type="text" class="form-control" id="productId" name="productId"
                                                    value="${param.productId}">
                                            </div>
                                            <div class="form-group">
                                                <label>Name</label>

                                                <input type="text" class="form-control" id="productName" name="name"
                                                    value="${param.name}">
                                            </div>
                                            <div class="form-group">
                                                <label>Description</label>

                                                <input type="text" class="form-control" id="productDescription"
                                                    name="description" value="${param.description}">
                                            </div>
                                            <div class="form-group">
                                                <label>Price</label>

                                                <input type="number" class="form-control" id="productPrice" name="price"
                                                    value="${param.price}">
                                            </div>
                                            <div class="form-group">
                                                <label>Quantity</label>

                                                <input type="number" class="form-control" id="productQuantity"
                                                    name="quantity" value="${param.quantity}">
                                            </div>
                                            <div class="form-group">
                                                <label>Category</label>

                                                <input type="text" class="form-control" name="category"
                                                    value="${param.category}">
                                            </div>
                                            <div class="form-group">
                                                <label>Brand</label>

                                                <input type="text" class="form-control" id="productBrand" name="brand"
                                                    value="${param.brand}">
                                            </div>
                                            <div class="form-group">
                                                <label>Color</label>

                                                <input type="text" class="form-control" id="productColor" name="color"
                                                    value="${param.color}">
                                            </div>

                                            <button type="button" id="submit" name="submit " class="btn btn-default"
                                                style="background-color: #f00; color: white;"
                                                onclick="EditProduct()">Save Changes</button>
                                            <a href="AdminProductsServlet"><button type="button"
                                                    class="btn btn-primary">Back To Products</button></a>

                                        </form>


                                    </div>


                                </div>
                            </div>
                        </div>
                        <!-- End Form Elements -->
                    </div>
                </div>

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>


</body>

</html>