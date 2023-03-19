<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <title>Edit Profile</title>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $.ajax({
                    url: "ProfileServlet",
                    type: "POST",
                    success: function (user) {
                        $("#email").val(user.email);
                        $("#name1").html(user.name);
                        $("#name2").val(user.name);
                        $("#password").val(user.password);
                        $("#address1").html(user.address);
                        $("#address2").val(user.address);
                        $("#interests").val(user.interests);
                        $("#creditlimit").val(user.creditLimit);
                        $("#job1").html(user.job);
                        $("#job2").val(user.job);

                    }
                });
            });
        </script>
        <script>
            function EditProfile(event) {
                event.preventDefault();
                console.log('Inside Edit Profile Function');

                // Get the form data
                const form = document.querySelector('#editprofile');
                const formData = new FormData(form);
                console.log(formData);
                // Get the email value from the formData object
                const email = formData.get('email');
                console.log('email from form: ' + email);

                // Set the email and usertype values to localStorage
                localStorage.setItem('email', email);
                localStorage.setItem('usertype', 'Customer');
                localStorage.setItem('LoggedIn', 'true');


                let value = JSON.stringify(Object.fromEntries(formData.entries()));
                console.log(value);
                // Send the JSON object to the servlet with the specified parameter name
                $.ajax({
                    type: 'POST',
                    url: 'EditProfileServlet',
                    data: value,
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (response) {
                        console.log('Data sent successfully');
                        window.location.href = 'index.jsp';

                    },
                    error: function (xhr, status, error) {
                        console.log('Error sending data');
                        window.location.href = 'index.jsp';
                    }
                });



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
                                        <a href="index.jsp"><img src="images/logo.png" alt="#"></a>
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
                                            <li><a href="contact.jsp">Contact Us</a></li>
                                            <li class="last">
                                                <a href="#"><img src="images/search_icon.png" alt="icon" /></a>
                                            </li>
                                            <li><a href="cart.jsp"><img src="images/cart.png" alt="icon" /></a></li>
                                            <li><a href="profile.jsp"><img src="images/profile.png" alt="icon" /></a>
                                            </li>
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
                            <h2>Edit Profile</h2>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- cart -->

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="main-body">
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex flex-column align-items-center text-center">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Admin"
                                                class="rounded-circle p-1 bg-primary" width="110">
                                            <div class="mt-3">
                                                <h4 id="name1">John Doe</h4>
                                                <p class="text-secondary mb-1" id="job1">Full Stack Developer</p>
                                                <p class="text-muted font-size-sm" id="address1">Bay Area, San
                                                    Francisco, CA
                                                </p>
                                            </div>
                                        </div>
                                        <hr class="my-4">

                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-8">
                                <form id="editprofile">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Full Name</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="name2" name="name"
                                                        value="John Doe">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Email</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="email" name="email"
                                                        value="john@example.com">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Password</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="password"
                                                        name="password" value="john@example.com">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Job</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="job2" name="job"
                                                        value="(239) 816-9029">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">CreditLimit</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="creditlimit"
                                                        name="creditLimit" value="(320) 380-4539">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Address</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="address2" name="address"
                                                        value="Bay Area, San Francisco, CA">
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-sm-3">
                                                    <h6 class="mb-0">Interests</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <input type="text" class="form-control" id="interests"
                                                        name="interests" value="Bay Area, San Francisco, CA">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-3"></div>
                                                <div class="col-sm-9 text-secondary">

                                                    <input type="submit" class="btn btn-primary px-4"
                                                        onclick="EditProfile(event)" value="Save Changes">

                                                    <!-- <input type="button" class="btn btn-primary px-4" value="Save Changes"> -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>

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
                                        <li> <a href="#"> Contact us</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="copyright">
                    <div class="container">
                        <p>© 2019 All Rights Reserved. Design By<a href="https://html.design/"> Free Html Templates</a>
                        </p>
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

    </body>

    </html>