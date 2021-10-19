package challenge.brq.entrypoint.exception;

import challenge.brq.usecase.exception.CategoriaDuplicadaException;
import challenge.brq.usecase.exception.CategoriaEmUsoException;
import challenge.brq.usecase.exception.CategoriaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerTratamentos  extends ExceptionModelResponse {


    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public final ResponseEntity<?> categoriaNaoEncontrada(Exception e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, e);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(CategoriaDuplicadaException.class)
    public final ResponseEntity<?> categoriaDuplicada(Exception e){
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, e);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(CategoriaEmUsoException.class)
    public final ResponseEntity<?> handlerEntidadeEmUso(Exception exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }


    private ExceptionModelResponse montarRespostaExcecao(HttpStatus httpStatus, Exception exception) {
        return ExceptionModelResponse.builder()
                .codigo(String.valueOf(httpStatus.value()))
                .mensagem(exception.getMessage())
                .build();
    }

}
