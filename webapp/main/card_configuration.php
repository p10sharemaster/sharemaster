<?php
$SQL_getcconfig = "SELECT
card_configuration.paymentlimit,
card_configuration.atmplimit,
card_configuration.internetpayment,
card_configuration.active
FROM
card_configuration
WHERE
card_configuration.card_id =".$cardid;
$card_conf_array=$db->SelectQueryArray($SQL_getcconfig);

?>
<div id="content-s">
                    <div id="content-header-s">
                        <i class="fa fa-power-off"></i> Card usage
                    </div>
                    <div id="content-body-s-nf">
                        <div id="toggle-option">
                            <div class="row">
                                <div class="col-9">
                                    Active card
                                </div>
                                <div class="col-3 text-right">
                                    <div class="form-check checkbox-slider--b">
                                        <label>
                                            <input type="checkbox" name="active-card" id="active-card" <?php if($selected_card_array[0]['active'] == 1){echo "checked";}?>><span></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

<div id="content-s">
                    <div id="content-header-s">
                        <i class="fa fa-cc-mastercard"></i> Card configuration
                    </div>
                    <div id="content-body-s-nf">
                        <div id="payment-limits">
                            <div class="row">
                                <div class="col-12">
                                    Payment limit : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">

                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="payment-limit" value="<?php echo number_format($card_conf_array[0]['paymentlimit'],2); ?>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    ATM limit : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">

                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="atm-limit" value="<?php echo number_format($card_conf_array[0]['atmplimit'],2); ?>">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    Internet limit : 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-7">

                                </div>
                                <div class="col-5">
                                    <input class="form-control form-control-sm text-right" type="text" id="internet-limit" value="<?php echo number_format($card_conf_array[0]['internetpayment'],2); ?>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="content-footer-s">
                        <div class="row">
                            <div class="col-9">
                        Use same configuration for all cards
                            </div>
                            <div class="col-3 text-right">
                                <div class="form-check checkbox-slider--b">
                                    <label>
                                        <input type="checkbox" name="same_for_all" id="same_for_all"><span></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

