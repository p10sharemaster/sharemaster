<div id="content-s">
                    <div id="content-header-s">
                        <i class="fa fa-random"></i> Notification channel
                    </div>
                    <div id="content-body-s-nf">
                    <?php 
                    $SQL_return_channels = 'SELECT
                                            notification_channel.`name`,
                                            user_link_channel.active
                                            FROM
                                            user_link_channel
                                            INNER JOIN notification_channel ON notification_channel.id = user_link_channel.channel_id
                                            WHERE
                                            user_link_channel.user_id = '.$_SESSION["SESS_USERID"];
                    $return_channels_array = $db->SelectQueryArray($SQL_return_channels);
                    for($i=0;$i<count($return_channels_array);$i++)
                    {
                    ?>
                        <div id="toggle-option">
                            <div class="row">
                                <div class="col-9">
                                    <?php echo $return_channels_array[$i]['name'];?>
                                </div>
                                <div class="col-3 text-right">
                                    <div class="form-check checkbox-slider--b">
                                        <label>
                                            <input type="checkbox" name="same_for_all" id="<?php echo $return_channels_array[$i]['name'];?>" <?php if($return_channels_array[$i]['active'] == 1){echo 'checked';}?>><span></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <?php
                        }
                        ?>
                    </div>
                </div>

