package org.example;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransferenciaController {
    private final QueueMessagingTemplate queueMessagingTemplate;
    @Autowired
    public TransferenciaController(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate =  new QueueMessagingTemplate(amazonSQSAsync);
    }


    @Value("${cloud.aws.queue.name}")
    private String queueUrl;

    @PostMapping(value = "/transferencia")
    public void realizarTransferencia(@RequestBody TransferenciaModel transferRequest) {

        enviarMensagemPagamentoRealizado(transferRequest);
    }

    private void enviarMensagemPagamentoRealizado(TransferenciaModel transferRequest) {
        // Construa a mensagem com os campos necessários
        String mensagem = String.format("Valor: %.2f, NumeroConta: %s, AgenciaConta: %s, Origem: %s, TipoPagamento: %s",
                transferRequest.getValor(), transferRequest.getNumeroConta(), transferRequest.getAgenciaConta(),
                transferRequest.getOrigem(), transferRequest.getTipoPagamento());

        // Envie a mensagem para a fila
        queueMessagingTemplate.convertAndSend(queueUrl, mensagem);
    }


    @SqsListener("notificacao")
    public void processMessage(@Payload String message) {
        System.out.println("Mensagem Recebida: " + message);
    }

}
