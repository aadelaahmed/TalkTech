<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
         <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
         <link rel="stylesheet" href="css/owl.carousel.min.css">
         <link rel="stylesheet" href="css/owl.theme.default.min.css">
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen">
         <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
      </head>
      <!-- body -->

      <body class="main-layout special-page">
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
                              <div class="logo"> <a href="home"><img src="images/logo.png" alt="#"></a> </div>
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
                                    <li><a href="contact.jsp">Contact Us</a></li>
                                    <li class="last"><a href="#"><img src="images/search_icon.png" alt="icon" /></a>
                                    </li>
                                    <li><a href="cart.jsp"><img src="images/cart.png" alt="icon" /></a></li>
                                    <li><a href="profile.jsp"><img src="images/profile.png" alt="icon" /></a></li>
                                    <%-- check the value of the 'LoggedIn' attribute --%>
                                       <c:choose>
                                          <%-- if the attribute is 'true' , show the 'Welcome' message --%>
                                             <c:when test="${sessionScope.LoggedIn == true}">
                                                <li> <button id="logout" class="send"><img src="images/logout.png"
                                                         alt="icon" />Logout</button></li>
                                             </c:when>
                                             <%-- if the attribute is 'false' or not set, show the 'Please log in'
                                                message --%>
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
         <section class="slider_section">
            <div id="myCarousel" class="carousel slide banner-main" data-ride="carousel">
               <div class="carousel-inner">
                  <div class="carousel-item active">
                     <img class="first-slide" src="images/banner.jpg" alt="First slide">
                     <div class="container">
                        <div class="carousel-caption relative">
                           <span>All New Phones </span>
                           <h1>up to 25% Flat Sale</h1>
                           <p>It is a long established fact that a reader will be distracted by the readable content
                              <br> of a page when looking at its layout. The point of using Lorem Ipsum is that
                           </p>
                           <a class="buynow" href="#">Buy Now</a>

                        </div>
                     </div>
                  </div>
                  <div class="carousel-item">
                     <img class="second-slide" src="images/banner1.png" alt="Second slide">
                     <div class="container">
                        <div class="carousel-caption relative">
                           <span>All New Phones </span>
                           <h1>up to 50% Flat Sale</h1>
                           <p>It is a long established fact that a reader will be distracted by the readable content
                              <br> of a page when looking at its layout. The point of using Lorem Ipsum is that
                           </p>
                           <a class="buynow" href="#">Buy Now</a>

                        </div>
                     </div>
                  </div>

               </div>
               <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                  <i class='fa fa-angle-left'></i>
               </a>
               <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                  <i class='fa fa-angle-right'></i>
               </a>
            </div>
         </section>


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
                              <p>(+71) 8522369417<br>demo@gmail.com</p>
                           </div>
                           <ul class="location_icon">
                              <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                              <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                              <li> <a href="#"><i class="fa fa-instagram"></i></a></li>

                           </ul>
                           <div class="menu-bottom">
                              <ul class="link">
                                 <li> <a href="home">Home</a></li>
                                 <li> <a href="#">About</a></li>

                                 <li> <a href="#">Brand </a></li>
                                 <li> <a href="#">Specials </a></li>
                                 <li> <a href="#"> Contact us</a></li>
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