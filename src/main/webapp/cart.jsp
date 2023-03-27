<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
      <title>pomato</title>
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
      <script>
         function checkQuantity(input) {
          console.log("check quantity is fired");
            var maxQuantity = Number(input.getAttribute("max"));
            var currentQuantity = Number(input.value);
            var errorSpan = document.getElementById("quantity-error-" + input.id);
            errorSpan.innerText = "hello world";
            // console.log("product id ->"+input.id);
            // console.log("currentQuantity ->"+currentQuantity);
            // console.log("maxQuantity ->"+maxQuantity);
            if (currentQuantity > maxQuantity) {
              // console.log("Quantity cannot exceed " + maxQuantity)
              errorSpan.textContent = "Quantity cannot exceed " + maxQuantity;
            } else {
              //console.log("allowable")
              errorSpan.textContent = "";
            }
        }
      </script>
    </head>
    <!-- body -->

    <body class="main-layout">
      <c:set var="totalPrice" value="0" />
      <!-- loader  -->
      <div class="loader_bg">
        <div class="loader"><img src="images/loading.gif" alt="#" /></div>
      </div>
      <!-- end loader -->
      <!-- header -->
      <header>
        <!-- header inner -->
        <div class="header">

          <div class="container">
            <div class="row">
              <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                <div class="full">
                  <div class="center-desk">
                    <div class="logo">
                      <a href="index.html"><img src="images/logo.png" alt="#"></a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                <div class="menu-area">
                  <div class="limit-box">
                    <nav class="main-menu">
                      <ul class="menu-area-main">
                        <li class="active"> <a href="index.html">Home</a> </li>
                        <li> <a href="about.jsp">About</a> </li>
                        <li><a href="category.jsp">Category</a></li>
                        <li><a href="special.jsp">Specials</a></li>
                        <li class="last">
                          <a href="#"><img src="images/search_icon.png" alt="icon" /></a>
                        </li>
                        <li><a href="cart"><img src="images/cart.png" alt="icon" /></a></li>
                        <li><a href="profile.jsp"><img src="images/profile.png" alt="icon" /></a></li>
                        <%-- check the value of the 'LoggedIn' attribute --%>
                          <c:choose>
                            <%-- if the attribute is 'true' , show the 'Welcome' message --%>
                              <c:when test="${sessionScope.LoggedIn == true}">
                                <li> <button id="logout" class="send"><img src="images/logout.png"
                                      alt="icon" />Logout</button></li>
                              </c:when>
                              <%-- if the attribute is 'false' or not set, show the 'Please log in' message --%>
                                <c:otherwise>
                                  <li> <button id="login" class="send"><img src="images/login.png"
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
                                <h6 class="mb-0 text-muted"><c:out value="${fn:length(cartProducts)}" /> Items</h6>

                              </div>
                              <hr class="my-4">

                              <c:forEach var="product" items="${cartProducts}">
                                <div class="row mb-4 d-flex justify-content-between align-items-center">
                                  <div class="col-md-2 col-lg-2 col-xl-2">
                                    <img
                                      src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img5.webp"
                                      class="img-fluid rounded-3" alt="Cotton T-shirt">
                                  </div>
                                  <div class="col-md-3 col-lg-3 col-xl-3">
                                    <h6 class="text-muted">${product.brand}</h6>
                                    <h6 class="text-black mb-0">${product.name}</h6>
                                  </div>
                                  <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                    <button class="btn btn-link px-2"
                                      onclick="const downInputField = this.parentNode.querySelector('input[type=number]');
                                      downInputField.stepDown();
                                      checkQuantity(downInputField);">
                                      <i class="fas fa-minus"></i>
                                    </button>
                                    
                                    <input name="quantity" value="${product.qtyInCart}" id="${product.productId}" type="number" min="1" max="${product.qtyInStock}" style="width: 70px;"  oninput="checkQuantity(this)">
  
                                    <button class="btn btn-link px-2"
                                      onclick="const upInputField = this.parentNode.querySelector('input[type=number]');
                                      upInputField.stepUp();
                                      checkQuantity(upInputField);">
                                      <i class="fas fa-plus"></i>
                                    </button>
                                    <span style="color: #c41a17;" class="text-danger" id="quantity-error-${product.productId}"></span>
                                  </div>
                                  <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                    <h6 class="mb-0">€ ${product.price}</h6>
                                  </div>
                                  <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                    <a onclick="deleteProductFromCart(${product.productId})" class="text-muted"><i class="fas fa-times"></i></a>
                                  </div>
                                </div>
                                <hr class="my-4">
  
                            </c:forEach>

                              <!-- <div class="row mb-4 d-flex justify-content-between align-items-center">
                                <div class="col-md-2 col-lg-2 col-xl-2">
                                  <img
                                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img5.webp"
                                    class="img-fluid rounded-3" alt="Cotton T-shirt">
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-3">
                                  <h6 class="text-muted">Shirt</h6>
                                  <h6 class="text-black mb-0">Cotton T-shirt</h6>
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                    <i class="fas fa-minus"></i>
                                  </button>

                                  <input name="quantity" value="1" type="number" min="0" style="width: 70px;">

                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                    <i class="fas fa-plus"></i>
                                  </button>
                                </div>
                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                  <h6 class="mb-0">€ 44.00</h6>
                                </div>
                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                  <a href="#!" class="text-muted"><i class="fas fa-times"></i></a>
                                </div>
                              </div>

                              <hr class="my-4">

                              <div class="row mb-4 d-flex justify-content-between align-items-center">
                                <div class="col-md-2 col-lg-2 col-xl-2">
                                  <img
                                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img6.webp"
                                    class="img-fluid rounded-3" alt="Cotton T-shirt">
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-3">
                                  <h6 class="text-muted">Shirt</h6>
                                  <h6 class="text-black mb-0">Cotton T-shirt</h6>
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                    <i class="fas fa-minus"></i>
                                  </button>

                                  <input name="quantity" value="1" type="number" min="0" style="width: 70px;">

                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                    <i class="fas fa-plus"></i>
                                  </button>
                                </div>
                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                  <h6 class="mb-0">€ 44.00</h6>
                                </div>
                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                  <a href="#!" class="text-muted"><i class="fas fa-times"></i></a>
                                </div>
                              </div>

                              <hr class="my-4">

                              <div class="row mb-4 d-flex justify-content-between align-items-center">
                                <div class="col-md-2 col-lg-2 col-xl-2">
                                  <img
                                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img7.webp"
                                    class="img-fluid rounded-3" alt="Cotton T-shirt">
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-3">
                                  <h6 class="text-muted">Shirt</h6>
                                  <h6 class="text-black mb-0">Cotton T-shirt</h6>
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                    <i class="fas fa-minus"></i>
                                  </button>

                                  <input name="quantity" value="1" type="number" min="0" style="width: 70px;">

                                  <button class="btn btn-link px-2"
                                    onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                    <i class="fas fa-plus"></i>
                                  </button>
                                </div>
                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                  <h6 class="mb-0">€ 44.00</h6>
                                </div>
                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                  <a href="#!" class="text-muted"><i class="fas fa-times"></i></a>
                                </div>
                              </div> -->

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
                                <h5 class="text-uppercase">Items <c:out value="${fn:length(cartProducts)}" /> </h5>
                                <c:forEach items="${cartProducts}" var="item">
                                  <c:set var="totalPrice" value="${totalPrice + item.price}" />
                              </c:forEach>
                                <h5>${totalPrice} egy</h5>
                              </div>

                              <hr class="my-4">

                              <div class="d-flex justify-content-between mb-5">
                                <h5 class="text-uppercase">Total price</h5>
                                <h5>${totalPrice} egy</h5>
                              </div>

                              <button type="button" class="btn btn-dark btn-block btn-lg"
                                data-mdb-ripple-color="dark">Buy</button>

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
                      <li> <a href="#">Home</a></li>
                      <li> <a href="#">About</a></li>

                      <li> <a href="#">Brand </a></li>
                      <li> <a href="#">Specials </a></li>
                      <li> <a href="#">Sign In</a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="copyright">
            <div class="container">
              <p>© 2019 All Rights Reserved. Design By<a href="https://html.design/"> Free Html Templates</a></p>
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