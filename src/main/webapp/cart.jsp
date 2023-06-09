<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<!-- Assuming the CartProduct class is located in the com.example package -->
<%@ page import = "org.dto.ProductCartDto" %>
  <%

// Get the list of cart products from the request object
List<ProductCartDto> cartProducts = (List<ProductCartDto>) request.getAttribute("cartProducts");

// Convert the list to a JSON string using Gson
Gson gson = new Gson();
String cartProductsJson = gson.toJson(cartProducts);
%>  
  <!DOCTYPE html>
    <html lang="en">

    <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Cart</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- Responsive-->
      <link rel="stylesheet" href="css/responsive.css">
      <!-- fevicon -->
      <link rel="icon" href="images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
      <!-- owl stylesheets -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="css/owl.carousel.min.css">
      <link rel="stylesheet" href="css/owl.theme.default.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
        media="screen">
      <link rel="stylesheet" href="css/cart.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
      <link rel="stylesheet"
        href="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/css/dist/mdb5/standard/core.min.css">
      <link rel="stylesheet" id="roboto-subset.css-css"
        href="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/css/mdb5/fonts/roboto-subset.css?ver=3.9.0-update.5"
        type="text/css" media="all">
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
      <script src="js/cart.js"></script>

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
    <!-- body -->

    <body class="main-layout">
      <!-- <div>
        <%= cartProductsJson %>
      </div> -->
      <c:set var="totalPrice" value="0" />
      <!-- loader  -->
      <div class="loader_bg">
        <div class="loader"><img src="images/loading.gif" alt="#" /></div>
      </div>
      <!-- end loader -->
      <!-- header -->
      <header>
        <div class="popup_box" id="popup" style="display:none">
          <i class="fas fa-exclamation"></i>
          <h1>You Have Successfully Logged Out</h1>
          <div class="btns">
            <a href="home" class="btn1">Continue</a>
          </div>
        </div>
        <!-- header inner -->
        <div class="header">

          <div class="container">
            <div class="row">
              <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                <div class="full">
                  <div class="center-desk">
                    <div class="logo">
                      <a href="home"><img src="images/logo.png" alt="#"></a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                <div class="menu-area">
                  <div class="limit-box">
                    <nav class="main-menu">
                      <ul class="menu-area-main">
                        <li class="active"> <a href="home">Home</a> </li>
                        <li> <a href="about.jsp">About</a> </li>
                        <li><a href="category.jsp">Category</a></li>
                        <li><a href="special.jsp">Specials</a></li>
                        <li class="last">
                          <a href="#"><img src="images/search_icon.png" alt="icon" /></a>
                        </li>
                        <li><a href="showCart"><img src="images/cart.png" alt="icon" /></a></li>
                        <li><a href="profile.jsp"><img src="images/profile.png" alt="icon" /></a></li>
                        <%-- check the value of the 'LoggedIn' attribute --%>
                          <c:choose>
                            <%-- if the attribute is 'true' , show the 'Welcome' message --%>
                              <c:when test="${sessionScope.LoggedIn == true}">
                                <li> <button id="logout" onclick="Logout(event)" class="send"><img src="images/logout.png"
                                      alt="icon" />Logout</button></li>
                              </c:when>
                              <%-- if the attribute is 'false' or not set, show the 'Please log in' message --%>
                                <c:otherwise>
                                  <li> <button id="login" onclick="window.location.href='login.jsp'" class="send"><img src="images/login.png"
                                        alt="icon" />Login</button></li>
                                </c:otherwise>
                          </c:choose>
                      </ul>
                    </nav>
                  </div>
                </div>
              </div>
              <div class="col-md-6 offset-md-6">
                <div class="location_icon_bottum">
                  <ul>
                    <li><img src="icon/call.png" />(+71)9876543109</li>
                    <li><img src="icon/email.png" />TalkTech@gmail.com</li>
                    <li><img src="icon/loc.png" />Location</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- end header inner -->
      </header>
      <!-- end header -->
      <div class="brand_color">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="titlepage">
                <h2>Cart</h2>
              </div>
            </div>
          </div>
        </div>

      </div>

      <!-- cart -->

      <div class="container" style="background-color: #c41a17;">
        <div class="row">
          <div class="col-md-12">

            <section class="h-100 h-custom">
              <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                  <div class="col-12">
                    <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                      <div class="card-body p-0">
                        <div class="row g-0">
                          <div class="col-lg-8">
                            <div class="p-5">
                              <div class="d-flex justify-content-between align-items-center mb-5">
                                <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                                <h6 class="count-products mb-0 text-muted">Items <c:out value="${fn:length(cartProducts)}" /></h6>
                                
                              </div>
                              <hr class="my-4">
                              <c:forEach var="product" items="${cartProducts}">
                                <div id="container-cart-${product.productId}" class="product-container row mb-4 d-flex justify-content-between align-items-center">
                                  <div class="col-md-2 col-lg-2 col-xl-2">
                                   <img src="../../images/${product.imageUrl}" alt="${product.name}" class="mouse">

                                  </div>
                                  <div class="col-md-3 col-lg-3 col-xl-3">
                                    <h6 class="text-muted">${product.brand}</h6>
                                    <h6 class="text-black mb-0">${product.name}</h6>
                                  </div>
                                  <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                    <button class="btn btn-link px-2"
                                      onclick="const downInputField = this.parentNode.querySelector('input[type=number]');
                                      downInputField.stepDown();
                                      checkQuantity(downInputField,`${product.price}`);">
                                      <i class="fas fa-minus"></i>
                                    </button>

                                    <input name="quantity" value="${product.qtyInCart}" id="${product.productId}" type="number" min="1" max="${product.qtyInStock}" style="width: 70px;" onblur="checkQuantity(this,`${product.price}`)"  >

                                    <button class="btn btn-link px-2"
                                      onclick="const upInputField = this.parentNode.querySelector('input[type=number]');
                                      upInputField.stepUp();
                                      checkQuantity(upInputField,`${product.price}`);">
                                      <i class="fas fa-plus"></i>
                                    </button>
                                    <span style="color: #c41a17;" class="text-danger" id="quantity-error-${product.productId}">
                                      <c:if test="${product.qtyInCart > product.qtyInStock}">
                                        Quantity cannot exceed ${product.qtyInStock}
                                      </c:if>
                                    </span>
                                  </div>
                                  <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                    <h6 class="mb-0">€${product.price}</h6>
                                  </div>
                                  <div onclick="removeProduct(this,event,`${product.price}`)" data-product="${product.productId}"  class="remove-product col-md-1 col-lg-1 col-xl-1 text-end">
                                    <a style="cursor: pointer;"  class="text-muted"><i class="fas fa-times"></i></a>
                                  </div>
                                </div>
                                <hr class="my-4">

                            </c:forEach>

                            <div class="popup_box pop-up-success" id="popup" style="display:none">
                              <i class="fas fa-exclamation"></i>
                              <h1 id="pop-up-sucess-message">Your order is placed successfully</h1>
                              <div class="btns">
                                <a href="home" class="btn1">Continue</a>
                              </div>
                            </div>
                            <div class="popup_box pop-up-error" id="popup" style="display:none">
                              <i class="fas fa-exclamation"></i>
                              <h1 id="pop-up-error-message"></h1>
                              <div class="btns">
                                <a href="showCart" class="btn1">Continue</a>
                              </div>
                            </div>

                              <hr class="my-4">

                              <div class="pt-5">
                                <h6 class="mb-0"><a href="category.jsp" class="text-body"><i
                                      class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                              </div>
                            </div>
                          </div>
                          <div class="col-lg-4 bg-grey">
                            <div class="p-5">
                              <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                              <hr class="my-4">

                              <div class="d-flex justify-content-between mb-4">
                                <h5 class="count-products text-uppercase">Items <c:out value="${fn:length(cartProducts)}" /> </h5>
                                <c:forEach items="${cartProducts}" var="item">
                                  <c:set var="totalPrice" value="${totalPrice + item.price * item.qtyInCart}" />
                              </c:forEach>
                                <h5 class="total-price-summary" >${totalPrice} E£</h5>
                              </div>

                              <hr class="my-4">

                              <div class="d-flex justify-content-between mb-5">
                                <h5 class="text-uppercase">Total price</h5>
                                <h5 class="total-price-summary">${totalPrice} E£</h5>
                              </div>
                              <c:set var="allProductsValid" value="true" />
                              <c:forEach var="checkProduct" items="${cartProducts}">
                                <c:choose>
                                  <c:when test="${checkProduct.qtyInCart >= checkProduct.qtyInStock}">
                                    <!-- If quantity in cart is greater than or equal to quantity in stock, product is not valid -->
                                    <c:set var="allProductsValid" value="false" />
                                  </c:when>
                                  <c:otherwise>
                                    <c:set var="allProductsValid" value="true" />
                                    <!-- If quantity in cart is less than quantity in stock, product is valid -->
                                  </c:otherwise>
                                </c:choose>
                              </c:forEach>
                              
                              <button type="button" class="btn btn-dark btn-block btn-lg"
                              data-mdb-ripple-color="dark" onclick="checkout(`${allProductsValid}`)">Checkout</button>
                      
                              
                              <!-- <button type="button" class="btn btn-dark btn-block btn-lg"
                                data-mdb-ripple-color="dark">Checkout</button> -->
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            </section>
          </div>
        </div>

      </div>
      <!-- end cart -->

      <!-- footer -->
      <footer>
        <div id="contact" class="footer">
          <div class="container">
            <div class="row pdn-top-30">
              <div class="col-md-12 ">
                <div class="footer-box">
                  <div class="headinga">
                    <h3>Address</h3>
                    <span>Healing Center, 176 W Streetname,New York, NY 10014, US</span>
                    <p>(+71) 8522369417
                      <br>demo@gmail.com
                    </p>
                  </div>
                  <ul class="location_icon">
                    <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                    <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li> <a href="#"><i class="fa fa-instagram"></i></a></li>

                  </ul>
                  <div class="menu-bottom">
                    <ul class="link">
                      <li> <a href="home">Home</a></li>
                      <li> <a href="about.jsp">About</a></li>
                      <li> <a href="category.jsp">Category </a></li>
                      <li> <a href="special.jsp">Specials </a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="copyright">
            <div class="container">
            </div>
          </div>
        </div>
      </footer>
      <!-- end footer -->
      <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <script src="js/plugin.js"></script>
      <!-- sidebar -->
      <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="js/custom.js"></script>
      <!-- javascript -->
      <script src="js/owl.carousel.js"></script>
      <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
      <script>
        $(document).ready(function () {
          $(".fancybox").fancybox({
            openEffect: "none",
            closeEffect: "none"
          });

          $(".zoom").hover(function () {

            $(this).addClass('transition');
          }, function () {

            $(this).removeClass('transition');
          });
        });
      </script>
    </body>

    </html>