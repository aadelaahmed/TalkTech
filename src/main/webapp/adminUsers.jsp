﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>View Users</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- MORRIS CHART STYLES-->

    <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!-- TABLE STYLES-->
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
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
font-size: 16px;"> &nbsp; <button onclick="Logout(event)" class="btn btn-danger square-btn-adjust">Logout</button> </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li class="text-center">
                        <img src="assets/img/find_user.png" class="user-image img-responsive" />
                    </li>
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
                        <h2>View Users</h2>
                        <h5>Welcome ${sessionScope.userName} , Love to see you back. </h5>

                    </div>
                </div>
                <!-- /. ROW  -->
                <hr />

                <div class="col-md-9">
                    <!--    Context Classes  -->
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            Users
                        </div>

                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr style="background-color: rgba(33, 168, 202, 0.678);">
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Job</th>
                                            <th>CreditLimit</th>
                                            <th>Address</th>
                                            <th>Interests</th>
                                            <th>UserType</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" items="${dataList}">
                                        <tr>
                                              <td>${user.userId}</td>
                                              <td>${user.name}</td>
                                              <td>${user.email}</td>
                                              <td>${user.job}</td>
                                              <td>${user.creditLimit}</td>
                                              <td>${user.address}</td>
                                              <td>${user.interests}</td>
                                              <td>${user.userType}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--  end  Context Classes  -->
                </div>
            </div>
            <!-- /. ROW  -->
        </div>

    </div>
    <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
    <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>


</body>

</html>