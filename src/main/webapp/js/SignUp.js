

function SignUp() {

  console.log('Inside Sign Up Function');
  const isEmailValid = validatemail();
  const isPasswordValid = validatePassword();
  const isOthersValid = validateOthers();

  if (isEmailValid && isPasswordValid && isOthersValid) {
    console.log("All Fields are valid.");
    // Get the form data
    const form = document.querySelector('#register');
    const formData = new FormData(form);
    console.log(formData);
    // Get the email value from the formData object
    const email = formData.get('email');
    console.log('email from form: '+ email);
    
    // Set the email and usertype values to localStorage
    localStorage.setItem('email', email);
    localStorage.setItem('usertype', 'Customer');
    localStorage.setItem('LoggedIn', 'true');
    

    let value = JSON.stringify(Object.fromEntries(formData.entries()));
    console.log(value);
    // Send the JSON object to the servlet with the specified parameter name
    $.ajax({
      type: 'POST',
      url: 'SignUpServlet',
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

  } else {
    console.log("Email and/or password are not valid.");
  }






}

function validatemail() {
  console.log("inside js email validation");
  const email = document.getElementById("email").value;
  console.log(email);
  document.getElementById("emailValidate").innerHTML = '';
  const emailRegex = /^\S+@\S+\.\S+$/;
  const isValid = emailRegex.test(email);
  if (!isValid) {
    console.log("email is not valid");
    document.getElementById("email").value = "";
    document.getElementById("emailValidate").style.color='red';
    document.getElementById("emailValidate").innerHTML = 'Please Enter A Valid Email.';
    document.getElementById("email").focus();
    return false;
  }
  return true;
}

function validatePassword() {
  const password = document.getElementById("password").value;
  document.getElementById("passvalidation").innerHTML = '';
  if (password.length < 6) {
    document.getElementById("password").value = "";
    document.getElementById("passvalidation").innerHTML = 'Password Must Be At Least 6 Char.';
    document.getElementById("password").focus();
    return false;
  }
  return true;
}

function validateOthers() {
  const job = document.getElementById("job").value;
  const creditLimit = document.getElementById("creditLimit").value;
  const address = document.getElementById("address").value;
  const interests = document.getElementById("interests").value;
  const name = document.getElementById("name").value;

  const jobLabel = document.getElementById("jobvalidation");
  const creditLimitLabel = document.getElementById("creditlimitvalidation");
  const addressLabel = document.getElementById("addressvalidation");
  const interestsLabel = document.getElementById("interestsvalidation");
  const nameLabel = document.getElementById("namevalidation");

  let isValid = true;

  if (!name) {
    nameLabel.innerHTML = "Name field is required";
    isValid = false;
  } else {
    nameLabel.innerHTML = "";
  }

  if (!job) {
    jobLabel.innerHTML = "Job field is required";
    isValid = false;
  } else {
    jobLabel.innerHTML = "";
  }

  if (!creditLimit) {
    creditLimitLabel.innerHTML = "Credit limit field is required";
    isValid = false;
  } else {
    creditLimitLabel.innerHTML = "";
  }

  if (!address) {
    addressLabel.innerHTML = "Address field is required";
    isValid = false;
  } else {
    addressLabel.innerHTML = "";
  }

  if (!interests) {
    interestsLabel.innerHTML = "Interests field is required";
    isValid = false;
  } else {
    interestsLabel.innerHTML = "";
  }

  return isValid;
}

