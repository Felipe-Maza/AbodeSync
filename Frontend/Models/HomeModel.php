<?php
class HomeModel extends Query{
 
    public function __construct()
    {
        parent::__construct();
    }
    public function getCondominios()
    {
        $sql = "SELECT * FROM condominio";
        return $this->selectAll($sql);
    }
}
 
?>