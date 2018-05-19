<?php
class dboperation
{
    protected $mysqli;

    public function __construct()
    {
        if(!isset($this->mysqli))
        {
            $config = parse_ini_file('./config.ini');
            $this->mysqli = new mysqli($config['hostname'], $config['username'], $config['password'], $config['dbname']);
        }

        if ($this->mysqli->connect_errno)
        {
            echo "Failed to connect to MySQL: (" . $this->mysqli->connect_errno . ") " . $this->mysqli->connect_error;
        }

        if (!$this->mysqli->set_charset("utf8"))
        {
            printf("Error loading character set utf8: %s\n", $this->mysqli->error);
            exit();
        }

    }

    public function __destruct()
    {
        if( $this->mysqli)
        {
            $this->Disconnect();
        }
    }

    public function SelectQueryArray($SqlQuery)
    {
        $array_assoc = array();

        $result = $this->mysqli->query($SqlQuery);

        if($result->num_rows > 0)
        {
            $result->data_seek(0);

            $counter = 0;

            while ($row = $result->fetch_assoc())
            {
                $array_assoc[$counter] = $row;

                $counter = $counter + 1;
            }

            return $array_assoc;
        }
        else
        {
            return null;
        }

    }

    public function Disconnect()
    {
        $this->mysqli->close();
    }

    public function SelectQueryScalar($SqlQuery)
    {
        $result = $this->mysqli->query($SqlQuery);

        if($result->num_rows > 0)
        {
            $row = $result->fetch_array(MYSQLI_NUM);

            return $row[0];
        }
        else
        {
            return null;
        }

    }

    public function UpdateQuery($SqlQuery)
    {
        $result = $this->mysqli->query($SqlQuery);

        if( !$result )
        {
            echo "Could not successfully run update query ($SqlQuery) from DB: " . $this->mysqli->error;
            exit;
            return false;
        }
        else
        {
            return true;
        }

    }

    public function InsertQuery($SqlQuery)
    {
        $result = $this->mysqli->query($SqlQuery);

        if( !$result )
        {
            echo "Could not successfully run insert query ($SqlQuery) from DB: " . $this->mysqli->error;
            exit;
            return false;
        }
        else
        {
            return $this->mysqli->insert_id;
        }

    }

    public function DeleteQuery($SqlQuery)
    {
        $result = $this->mysqli->query($SqlQuery);

        if( !$result )
        {
            echo "Could not successfully run delete query ($SqlQuery) from DB: " . $this->mysqli->error;
            exit;
            return false;
        }
        else
        {
            return true;
        }

    }

    public function GetTotalRow($SqlQuery)
    {
        $result = $this->mysqli->query($SqlQuery);

        if($result->num_rows != 0)
        {
            return $result->num_rows;
        }
        else
        {
            return 0;
        }
    }

    public function GetLastID()
    {
        return $this->mysqli->insert_id;
    }

    public function StartTransaction()
    {
        $this->mysqli->begin_transaction(MYSQLI_TRANS_START_READ_WRITE);
    }

    public function EndTransaction()
    {
        $this->mysqli->commit();
    }

    public function RollBackTransaction()
    {
        $this->mysqli->rollback();
    }
}

?>
