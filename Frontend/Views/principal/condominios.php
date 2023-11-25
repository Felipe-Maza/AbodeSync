<body>
<?php include_once 'Views/template-principal/header.php'; ?>
   
<!-- Start Featured Product -->
<section class="bg-light">
        <div class="container py-5">
            <div class="row text-center py-3">
                <div class="col-lg-6 m-auto">
                    <h1 class="h1">Condominios</h1>
                </div>
            </div>
            <div class="row">
                <?php foreach ($data['condominio'] as $condominio){ ?>
                <div class="col-12 col-md-4 mb-4">
                    <div class="card h-100">
                        <a href="<?php echo BASE_URL . 'principal/detalles/' . $condominio['id']; ?>">
                            <img src="<?php echo $condominio['imagen']?>" class="card-img-top" alt="...">
                        </a>
                        <div class="card-body">
                            <ul class="list-unstyled d-flex justify-content-between">
                                <li>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-warning fa fa-star"></i>
                                    <i class="text-muted fa fa-star"></i>
                                    <i class="text-muted fa fa-star"></i>
                                </li>
                                <li class="text-muted text-right"><?php echo MONEDA . ' ' . $condominio['precio']?></li>
                            </ul>
                            <a href="<?php echo BASE_URL . 'principal/detalles/' . $condominio['id']; ?>" class="h2 text-decoration-none text-dark"><?php echo $condominio['nombreCondominio']?></a>
                            <p class="card-text">
                            <?php echo $condominio['descripcion']?>
                            </p>
                            <p class="text-muted">Reviews (24)</p>
                        </div>
                    </div>
                </div>
                <?php }?>
            </div>
        </div>
    </section>

    <?php include_once 'Views/template-principal/footer.php'; ?>


</body>