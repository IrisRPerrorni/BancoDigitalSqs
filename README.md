# BancoDigitalSqs
exercico da trilha da handora 
SQS
Adicionando um producer em um banco digital
Você pegou uma tarefa no serviço de um banco digital para alterar o fluxo de transferência, ao finalizar uma transferência deverá ser enviada uma mensagem para a fila de pagamentos_realizados, para que seja emitido o comprovante do cliente.
O modelo esperado da mensagem deve ser gerada com os seguintes campos: valor, numeroConta, agenciaConta, origem ( qual serviço solicitou o envio ), tipoPagamento(sendo que possuem alguns tipos padrões : TED, PIX, BOLETO).
