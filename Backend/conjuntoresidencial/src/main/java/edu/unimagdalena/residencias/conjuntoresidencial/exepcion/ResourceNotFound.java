package edu.unimagdalena.residencias.conjuntoresidencial.exepcion;

public class ResourceNotFound extends RuntimeException {

    
    public ResourceNotFound() {
        super("Resource not found");
    }

    public ResourceNotFound(String message) {
        super(message);
    }
    
}

