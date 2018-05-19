<?php

header("Content-Type: application/json;charset=utf-8");

switch($_SERVER['REQUEST_METHOD'])
{
    case "POST" :
        $jsonRequest = file_get_contents('php://input');
        if(!empty($jsonRequest))
        {
            $jv = json_validate($jsonRequest);
            if( $jv == 0)
            {
                WriteToLog("Json input",$jsonRequest);
                $requestClass = new RequestData($jsonRequest);

                echo $requestClass->warrantProceed();
            }
            else
            {
                echo '{"errorCode":"05","errorMessage":"'.$jv.'"}';
            }
        }
        else
        {
            echo '{"errorCode":"405","errorMessage":"Request is empty"}';
            http_response_code(405);
        }
        break;
    default :
        echo '{"errorCode":"405","errorMessage":"Request method not allowed"}';
        http_response_code(405);
        break;
}


?>

