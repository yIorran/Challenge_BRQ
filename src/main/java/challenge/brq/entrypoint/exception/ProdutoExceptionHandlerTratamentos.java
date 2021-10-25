package challenge.brq.entrypoint.exception;

import challenge.brq.usecase.exception.produto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionHandlerTratamentos extends ExceptionModelResponse {


    @ExceptionHandler(NenhumProdutoException.class)
    public final ResponseEntity<?> nenhumProdutoException(Exception e) {
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, e);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(ProdutoPorIDNaoEncontrado.class)
    public final ResponseEntity<?> produtoPorIDNaoEncontrado(Exception e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, e);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(AdicionarProdutosIncompletoException.class)
    public final ResponseEntity<?> adicionarProdutosIncompleto(Exception exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(QuantidadeMenorQueZeroException.class)
    public final ResponseEntity<?> quantidadeInvalida(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(QuantidadeZeroEProdutoAtivo.class)
    public final ResponseEntity<?> quantidadeZeroEProdutoAtivo(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ExceptionModelResponse exceptionModelResponse = montarRespostaExcecao(httpStatus, exception);

        return ResponseEntity.status(httpStatus).body(exceptionModelResponse);
    }

    @ExceptionHandler(PorcentagemMaiorQueZeroException.class)
    public final ResponseEntity<?> porcentagemMaiorQueZero(Exception exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

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
