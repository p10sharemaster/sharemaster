<?php
session_start();

if(isset($_POST["loginvalue"]))
{
	require_once('connection/database.php');
	
	$db = new dboperation();

	//lets fetch posted details
	$uname = trim(htmlentities($_POST['username']));
	$passw = trim(htmlentities($_POST['password']));
	//check username is present

	//$salt = md5($passw);

	$user_array = $db->ReturnUser($uname,$passw);

    
    if (count($user_array) > 0 )
    {
        $_SESSION["SESS_USERID"] = stripslashes($user_array[0]["id"]);
        $_SESSION["SESS_NAME"] = stripslashes($user_array[0]["firstname"])." ".stripslashes($user_array[0]["lastname"]);

        setcookie("userloggedin", trim(htmlentities($_POST['username'])), time()+21600); // expires in 1 hour

        header("location: main.php"); 
    }
    else
    {
        header("location: index.php");
    }
	
}
else
{
	header("location: index.php");
}
?>

