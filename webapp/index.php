<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to Share Master ::</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <form class="form-signin" action="login.php" method="post">
            <img class="mb-4" src="img/logo_big.png" alt=""/>
            <div class="form-fields">
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus name="username">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required name="password">
                <input name="loginvalue" type="hidden" value="1">
                <div class="checkbox mb-3">
                  <label>
                    <input type="checkbox" value="remember-me"> Remember me
                  </label>
                </div>
                <button class="btn btn-lg btn-warning btn-block" type="submit">Sign in</button>
                <p class="mt-5 mb-3 text-muted text-center">&copy; Payten Hackaton 2018</p>
            </div>
          </form>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>
