package com.conde.gestaoprojetosarq.infrastructure.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException(String mensagem){
        super(mensagem);
    }

    public ConflictException(Throwable throwable){
        super(throwable);
    }

    public ConflictException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
