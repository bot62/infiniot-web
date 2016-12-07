$(document).ready(function() {

  // var reg = /\w+[@]{1}\w+[.]\w+/; // regular expression for email

  // When #signin division is clicked,
  // hide options at input form.
  $('#signin').click(function() {
    $('#li-signin').addClass('active');
    $('#li-signup').removeClass('active');
    init();
    hideMoreOpts();
  });

  // When #signup division is clicked,
  // show more options at input form.
  $('#signup').click(function() {
    $('#li-signin').removeClass('active');
    $('#li-signup').addClass('active');
    init();
    showMoreOpts();
  });

  // Encrypt password with SHA1 when submit
  $('#target').submit(function() {
    var shaObj1 = new jsSHA('SHA-1', 'TEXT');
    var shaObj2 = new jsSHA('SHA-1', 'TEXT');
    var pass1 = $('#login-password').val();
    var pass2 = $('#login-password2').val();
    shaObj1.update(pass1);
    shaObj2.update(pass2);
    $('#login-password').val(shaObj1.getHash('HEX'));
    $('#login-password2').val(shaObj2.getHash('HEX'));
    // event.preventDefault(); // cancel event (when test)
  });

  // Check input tag on blur
  $('input').blur(function() { validate(); });

  // Show more options
  function showMoreOpts() {
    $('#login-message').removeClass('hidden');
    $('#div-password2').removeClass('hidden');
    $('#div-email > input').prop('disabled', true);
    $('#div-password > input').prop('disabled', true);
    $('#div-password2 > input').prop('disabled', true);
    $('#submit').text('Sign up');
  }

  // Hide more options
  function hideMoreOpts() {
    $('#login-message').addClass('hidden');
    $('#div-email > input').prop('disabled', false);
    $('#div-password > input').prop('disabled', false);
    $('#div-password2').addClass('hidden');
    $('#submit').text('Sign in');
  }

  // (re)initialization
  function init() {
    $('.err-msg').remove();
    // $("#login-email").val(null);
    $('#login-password').val(null);
    $('#login-password2').val(null);
    //$("#submit").attr("class", "btn pull-right disabled");
    $('#div-email').attr('class', 'form-group');
    $('#div-password').attr('class', 'form-group');
    if ($('#li-signin').attr('class') == 'active') {
      $('#div-password2').attr('class', 'hidden');
    } else {
      $('#div-password2').attr('class', 'form-group');
    }
  }
  /*
  // check whether the formula is ready for submit
  function isReady() {
          var email = $("#login-email").val();
          var pw = $("#login-password").val();
          var pw2 = $("#login-password2").val();
          var mode = $("#li-signup").attr("class") == "active" ? "SIGNUP" :
  "SIGNIN";
          var isEmailCorr = false;
          var isPwCorr = false;
          var isPwFocus = $("#login-password").is(":focus");	//TODO: how to
  avoid unnecessary validation ?
          // Email checking
          $(".err-msg").remove();
  // initialize .err-msg
          $("#div-email").attr("class", "form-group");		// initialize
  #div-email
          if (email == "") {
                  $("#div-email").addClass("has-error");
                  $("#div-email").append("<p id=\"p-email\" class=\"text-danger
  err-msg\">Email vide</p>");
          } else if (!reg.test(email)) {
                  $("#div-email").addClass("has-error");
                  $("#div-email").append("<p id=\"p-email\" class=\"text-danger
  err-msg\">Format incorrect</p>");
          } else {
                  isEmailCorr = true;
          }
          // Password initializing
          $("#div-password").attr("class", "form-group");		//
  initialize #div-password
          $("#div-password2").attr("class", "form-group");	// initialize
  #div-password2
          if(mode == "SIGNIN")
                  $("#div-password2").addClass("hidden");			//
  initialize #div-password2
          // Password checking
          if (isEmailCorr && !isPwFocus) {
                  if (pw == "") {
                          $("#div-password").addClass("has-error");
                          $("#div-password").append("<p id=\"p-password\"
  class=\"text-danger err-msg\">Mdp vide</p>");
                  } else if(mode == "SIGNUP" && pw2 == "") {
                          $("#div-password2").addClass("has-error");
                          $("#div-password2").append("<p id=\"p-password2\"
  class=\"text-danger err-msg\">Mdp vide</p>");
                  } else if(mode == "SIGNUP" && pw != pw2) {
                          $("#div-password").addClass("has-error");
                          $("#div-password2").addClass("has-error");
                          $("#div-password2").append("<p id=\"p-password2\"
  class=\"text-danger err-msg\">Mdp incorrect</p>");
                  } else {
                          isPwCorr = true;
                  }
          }
          // is everything correct ?
          return (isEmailCorr && isPwCorr) ? true : false;
  }

  // validate formula
  function validate() {
          if (isReady()) {
                  // remove every warning
                  $(".err-msg").remove();
                  // change submit button
                  $("#submit").removeClass("disabled");
                  $("#submit").addClass("btn-success");
          }
  }*/

});
