<?php
session_start();

if(!isset($_SESSION["SESS_USERID"]))
{
    header("location: index.php");
}

include_once 'connection/database.php';
$db = new dboperation();

$SQL_getData = "SELECT
card.cardname,
card.cardnumber,
card.balance,
card.expdate,
card.bankaccount,
card.active,
card.id
FROM
users
INNER JOIN user_link_card ON user_link_card.user_id = users.id
INNER JOIN card ON card.id = user_link_card.card_id
WHERE
users.id =".$_SESSION["SESS_USERID"];
$card_array=$db->SelectQueryArray($SQL_getData);

if(isset($_GET['cardid']))
{
    $cardid = $_GET['cardid'];
}
 else 
{
    $cardid=1;
}

$SQL_card_data = "SELECT
card.cardname,
card.cardnumber,
card.balance,
card.expdate,
card.bankaccount,
card.active,
card.id
FROM
users
INNER JOIN user_link_card ON user_link_card.user_id = users.id
INNER JOIN card ON card.id = user_link_card.card_id
WHERE
users.id = ".$_SESSION["SESS_USERID"]." 
AND
card_id = ".$cardid;
$selected_card_array=$db->SelectQueryArray($SQL_card_data);

?>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to Share Master Admin portal::</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/titatoggle-dist.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> 
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <div class="container">
            <nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
              <a class="navbar-brand" href="#"><img src="img/logo_300.png" alt="" width="150px"/></a>
            </nav>
        </div>
            <div class="container">
            <div class="row">
                <div class="col-sm-8">
                       <div id="card_view">
                           <div class="row">
                               <?php include "main/cards.php"; ?>
                               <div class="col-6">
                                   
                                   <?php include "main/card_details.php"; ?>
                               </div>
                           </div>
                           <div class="row">
                               <div class="col-6">
                                  <?php include "main/navigation.php"; ?>
                               </div>
                               <div class="col-6">
                                   <?php include "main/ballance.php"; ?>
                               </div>
                           </div>
                       </div>
                </div>
                <div class="col-sm-4">
                       
                       <?php include "main/person.php"; ?>
                </div>
          </div>
         </div>  
         <div class="container">  
          <div class="row">
            <div class="col-sm">
                <?php include "main/card_configuration.php"; ?>
                <?php include "main/customer_feedback.php"; ?>
            </div>
              <div class="col-sm">
                <?php include "main/service_configuration.php"; ?>
                <?php include "main/customer_loan.php"; ?>
              </div>
            <div class="col-sm">
                <?php include "main/notifications.php"; ?>
                <?php include "main/notification_channel.php"; ?>
            </div>
          </div>
        </div>
      </div>
      <footer class="footer">
        <div class="container text-center">
          <span class="text-muted ">&copy; Payten Hackaton 2018</span>
        </div>
      </footer>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>

