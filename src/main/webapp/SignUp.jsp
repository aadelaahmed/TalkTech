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
            <title>Sign Up</title>
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


            <script src="js/SignUp.js" type="text/javascript"></script>

            <script>
                function showLoginForm() {
                    document.getElementById("login_form").style.display = "block";
                    document.getElementById("register_form").style.display = "none";
                }
                function showRegisterForm() {
                    document.getElementById("register_form").style.display = "block";
                    document.getElementById("login_form").style.display = "none";
                }
                function validateEmail() {
                    const isEmailValid = validatemail();
                    if (isEmailValid){
                    console.log('inside validateEmail');
                    var req = new XMLHttpRequest();
                    var emailValidate = document.getElementById("emailValidate");
                    req.onreadystatechange = function () {
                        if (req.readyState == 4 && req.status == 200) {
                            var xmlvalue = req.responseText;
                            if (xmlvalue.trim() === 'Already Exists') {
                                console.log('Already Exists');
                                emailValidate.style.color = 'red';
                                emailValidate.style.fontWeight = 'bold';
                            }
                            else {
                                console.log('Email Is Available');
                                emailValidate.style.color = 'green';
                                emailValidate.style.fontWeight = 'bold';
                            }
                            emailValidate.innerHTML = xmlvalue;
                        }
                        else {
                            var xmlvalue = req.responseText;
                            if (xmlvalue.trim() === 'Already Exists') {
                                console.log('Already Exists');
                                emailValidate.style.color = 'red';
                                emailValidate.style.fontWeight = 'bold';
                            }
                            else {
                                console.log('Email Is Available');
                                emailValidate.style.color = 'green';
                                emailValidate.style.fontWeight = 'bold';
                            }

                            emailValidate.innerHTML = xmlvalue;
                        }
                    };

                    var emailValue = document.getElementById("email").value;
                    document.getElementById("emailValidate").innerHTML = emailValue;
                    var url = "SignUpValidate" + "?timeStamp=" + new Date().getTime();
                    req.open("post", url, true);
                    req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
                    req.send("email=" + emailValue);
                    }
                }
            </script>

        </head>
        <!-- body -->

        <body class="main-layout">
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
                                                <li><a href="cart.jsp"><img src="images/cart.png" alt="icon" /></a></li>
                                                <li><a href="profile.jsp"><img src="images/profile.png"
                                                            alt="icon" /></a></li>
                                                <%-- check the value of the 'LoggedIn' attribute --%>
                                                    <c:choose>
                                                        <%-- if the attribute is 'true' , show the 'Welcome' message
                                                            --%>
                                                            <c:when test="${sessionScope.LoggedIn == true}">
                                                                <li> <button id="logout" class="send"><img
                                                                            src="images/logout.png"
                                                                            alt="icon" />Logout</button></li>
                                                            </c:when>
                                                            <%-- if the attribute is 'false' or not set, show
                                                                the 'Please log in' message --%>
                                                                <c:otherwise>
                                                                    <li> <button id="login" onclick="window.location.href='login.jsp'" class="send"><img
                                                                                src="images/login.png"
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


            <!-- sign up -->


            <div class="brand_color">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="titlepage">
                                <h1 id="registration-header">
                                    <b>Sign Up</b>
                                </h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="contact">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12" style="width: 600px;">
                            <form class="signUP_form" id="register" style="width: 500px; margin: auto; width: 60%;">

                                <div class="row" style="width: 500px;">
                                    <div class=" col-md-12">
                                        <label for="name">Name*:</label><label id="namevalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="text" id="name" name="name" required>
                                    </div>

                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="password">Password*:</label><label id="passvalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="password" id="password" name="password"
                                            required>
                                    </div>
                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="job">Job*:</label><label id="jobvalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="text" id="job" name="job">
                                    </div>
                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="email">Email*:</label><label id="emailValidate"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="text" id="email" name="email"
                                            onblur="validateEmail()" required>
                                    </div>
                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="creditlimit">Credit Limit*:</label><label id="creditlimitvalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="number" id="creditLimit" name="creditLimit">
                                    </div>
                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="address">Address*:</label><label id="addressvalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <textarea class="form-control" id="address" name="address"></textarea>
                                    </div>
                                    <div class=" col-md-12" style="width: 200px;">
                                        <label for="interests">Interests*:</label><label id="interestsvalidation"
                                            style="color: crimson; font-weight: bold;"></label>
                                        <input class="form-control" type="text" id="interests" name="interests">
                                    </div>
                                    <div class="col-md-12">
                                        <button type="button" class="send" name="register" value="register"
                                            onclick="SignUp()">Submit</button>
                                    </div>
                                    <p>Already have an account ? <a href="login.jsp">Login here</a>.</p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
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