<?php
class Principal extends Controller
{
    public function __construct() {
        parent::__construct();
        session_start();
    }
    public function index()
    {
       
    }
    //vista inicio
    public function inicio()
    {
        $data['title'] = 'Pagina Principal';
        $this->views->getView('principal', "inicio", $data);
    }
    //vista condominios
    public function condominios($id_condominio)
    {
        $data['condominio']=$this->model->getCondominios($id_condominio);
        $data['title'] = 'ver';
        $this->views->getView('principal', "condominios", $data);
    }
    //vista contacto
    public function contacto()
    {
        $data['title'] = 'Contactar';
        $this->views->getView('principal', "contacto", $data);
    }
    //vista detalles
    public function detalles($id_condominio)
    {   
        $data['condominio']=$this->model->getCondominio($id_condominio);
        $data['title'] = $data['condominio']['nombreCondominio'];
        $this->views->getView('principal', "detalles", $data);
    }

}