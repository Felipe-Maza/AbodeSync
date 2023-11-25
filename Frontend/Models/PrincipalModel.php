<?php
class PrincipalModel extends Query{
 
    public function __construct()
    {
        parent::__construct();
    }
    public function getCondominio($id_condominio)
    {
        $sql = "SELECT * FROM condominio WHERE id = $id_condominio";
        return $this->select($sql);
    }
    public function getCondominios()
    {
        $sql = "SELECT * FROM condominio";
        return $this->selectAll($sql);
    }

}
 
?>