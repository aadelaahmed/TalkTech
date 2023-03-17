

function SignUp(){

    console.log('Inside Sign Up Function');
     const isEmailValid = validatemail();
      const isPasswordValid = validatePassword();

      if (isEmailValid && isPasswordValid) {
        console.log("Both email and password are valid.");
        // Get the form data
const form = document.querySelector('#register');
const formData = new FormData(form);
console.log(formData);

let value = JSON.stringify(Object.fromEntries(formData.entries()));
console.log(value);
// Send the JSON object to the servlet with the specified parameter name
$.ajax({
    type: 'POST',
    url: 'SignUpServlet',
    data:value,
    contentType: 'application/json',
    dataType: 'json',
    success: function(response) {
        console.log('Data sent successfully');
        window.location.href = 'login.jsp';

    },
    error: function(xhr, status, error) {
        console.log('Error sending data');
        window.location.href = 'login.jsp';
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
    document.getElementById("emailValidate").innerHTML='';
    const emailRegex = /^\S+@\S+\.\S+$/;
    const isValid = emailRegex.test(email);
    if (!isValid) {
    console.log("email is not valid");
      document.getElementById("email").value = "";
      document.getElementById("emailValidate").innerHTML='Please Enter A Valid Email.';
      document.getElementById("email").focus();
      return false;
    }
    return true;
  }

  function validatePassword() {
    const password = document.getElementById("password").value;
    document.getElementById("passvalidation").innerHTML='';
    if (password.length < 6) {
      document.getElementById("password").value = "";
      document.getElementById("passvalidation").innerHTML='Password Must Be At Least 6 Char.';
      document.getElementById("password").focus();
      return false;
    }
    return true;
  }
