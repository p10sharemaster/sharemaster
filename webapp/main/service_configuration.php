<?php
$SQL_getServiceData = "SELECT
service_configuration.service_id,
service_configuration.card_id,
service_configuration.exptime,
service_configuration.maxamtallowed,
service_configuration.maxtransnumber,
service_configuration.active,
services.service_name
FROM
service_configuration
INNER JOIN services ON services.id = service_configuration.service_id
WHERE
service_configuration.card_id = ".$cardid;
$service_array=$db->SelectQueryArray($SQL_getServiceData);

if(isset($_GET['serviceid']))
{
   
    $SQL_getServiceData = "SELECT
    service_configuration.service_id,
    service_configuration.card_id,
    service_configuration.exptime,
    service_configuration.maxamtallowed,
    service_configuration.maxtransnumber,
    service_configuration.active,
    services.service_name
    FROM
    service_configuration
    INNER JOIN services ON services.id = service_configuration.service_id
    WHERE
    service_configuration.card_id = ".$cardid." AND ".
    "service_id = ".$_GET['serviceid'];
    $service_array1=$db->SelectQueryArray($SQL_getServiceData);
    
    $serviceactive = $service_array1[0]['active'];
    $expirationdays = $service_array1[0]['exptime'];
    $maxamountallowed = $service_array1[0]['maxamtallowed'];
    $maxnumtrans = $service_array1[0]['maxtransnumber'];

    
}
else 
{
    $serviceactive = $service_array[0]['active'];
    $expirationdays = $service_array[0]['exptime'];
    $maxamountallowed = $service_array[0]['maxamtallowed'];
    $maxnumtrans = $service_array[0]['maxtransnumber'];
    
}



?>
<div id="content-s">
                    <div id="content-header-s">
                        <i class="fa fa-info-circle"></i> Service configuration
                    </div>
                    <div id="content-body-s-nf">
                        <div id="service-selection">
                            <div class="row">
                                <div class="col-12">
                                    <select id="cmbServiceSelection" class="form-control form-control-sm" data-cardid="<?php if(isset($_GET['cardid'])){echo $_GET['cardid'];}else{echo $cardid;}?>" >
                                    <?php 
                                    for($i=0;$i<count($service_array);$i++)
                                    {
                                    ?>
                                        <option value="<?php echo  $service_array[$i]['service_id'];?>"><?php echo  $service_array[$i]['service_name'];?></option>
                                    <?php
                                    }
                                    ?>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="toggle-option">
                            <div class="row">
                                <div class="col-9">
                                    Active service
                                    
                                </div>
                                <div class="col-3 text-right">
                                    <div class="form-check checkbox-slider--b">
                                        <label>
                                            <input type="checkbox" name="active_service" id="active_service" <?php if($serviceactive ==1){echo "checked";}?>><span></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="payment-limits">
                            <div class="row">
                                <div class="col-12">
                                    Expiration days : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">
                                    
                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="expiration-time" value="<?php echo  $expirationdays;?>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    Max amount allowed : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">
                                    
                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="max-amount-allowed" value="<?php echo number_format($maxamountallowed,2);?>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    Max number of transactions : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">
                                    
                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="max-num-transactions" value="<?php echo  $maxnumtrans;?>">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>

