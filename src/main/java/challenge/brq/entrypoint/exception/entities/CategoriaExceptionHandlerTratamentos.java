package challenge.brq.entrypoint.exception.entities;

import challenge.brq.usecase.exception.categoria.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CategoriaExceptionHandlerTratamentos extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public final ResponseEntity<?> categoriaNaoEncontrada(Exception e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, e);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(CategoriaDuplicadaException.class)
    public final ResponseEntity<?> categoriaDuplicada(Exception e) {
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

    @ExceptionHandler(CategoriaNaoExistenteParaAtualizacaoParcialException.class)
    public final ResponseEntity<?> categoriaNaoExistenteParaAtualizacaoParcialException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(CamposFaltantesException.class)
    public final ResponseEntity<?> camposFaltantesException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }


    private ExceptionModelResponse montarRespostaExcecao(HttpStatus httpStatus, Exception exception) {
        return ExceptionModelResponse.builder()
                .codigo(String.valueOf(httpStatus.value()))
                .mensagem(exception.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }



}
