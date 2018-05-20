<div id="content-s">
                    <div id="content-header-s">
                        <i class="fa fa-bullhorn"></i> Notifications
                    </div>
                    <div id="content-body-s-nf">
                        
                    <?php 
                    $SQL_return_notifications = 'SELECT
                                                notification_list.`name`,
                                                user_link_notification.active
                                                FROM
                                                user_link_notification
                                                INNER JOIN notification_list ON notification_list.id = user_link_notification.notification_id
                                                WHERE
                                                user_link_notification.user_id = '.$_SESSION["SESS_USERID"];
                    $return_notification_array = $db->SelectQueryArray($SQL_return_notifications);
                    for($i=0;$i<count($return_notification_array);$i++)
                    {
                    ?>
                        <div id="toggle-option">
                            <div class="row">
                                <div class="col-9">
                                    <?php echo $return_notification_array[$i]['name'];?>
                                </div>
                                <div class="col-3 text-right">
                                    <div class="form-check checkbox-slider--b">
                                        <label>
                                            <input type="checkbox" name="same_for_all" id="<?php echo $return_notification_array[$i]['name'];?>" <?php if($return_notification_array[$i]['active'] == 1){echo 'checked';}?>><span></span>
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

