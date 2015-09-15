Explorando-Marte-Server
=======================
API Rest para receber comandos de controle de sondas espaciais. Desenvolvido para demonstrar o uso de uma API Rest 
fornecida através de JAX-RS e a sua implementação Jersey.

Tecnologias usadas
----------------------- 
 - framework Jersey como uma implementação do JAX-RS;
 - biblioteca org.json para manipulação de objetos JSON; 
 - JUnit para testes unitários;
 - JMockit para mocks e verificações nos testes unitários.
 
 Design
 -------
 Tive um pouco de dificuldade de fazer uma estrutura de pacotes que atendesse um modelo não anêmico. No final das contas,
 tentei utilizar uma estrutura baseada no DDD separada nas seguintes camadas:
 - Application
 
  Camada responsável por coordenar as chamadas ao domínio e expor os recursos REST de forma
  a desacoplar as tecnologias Rest com a lógica de domínio.
  
 - Domain
  
  Camada responsável por conter o modelo com a lógica da aplicação e expor os serviços de modelo de forma organizada.

Observação : A camada de apresentação ficará no projeto cliente.


Recursos REST
-------------

Caminho do recurso                                                                 | Classe do recurso        | Métodos HTTP | Observação
-----------------------------------------------------------------------------------| ------------------------ | -------------| --------------------------------------------------------------------------
**_/planalto?limiteX={number}&limiteY={number}_**                                  |  ExplorandoMarteResource |  POST        |  Define o planalto a ser usado pelas sondas tamanhoX e tamanhoY requeridos
**_/sondas/{posicaoX}/{posicaoY}/controlar?direcao={character}&comandos={string}_**|  ExplorandoMarteResource |  POST        |  Controla uma sonda informada pela posição X e Y.
**_/sondas_**                                                                      |  ExplorandoMarteResource |  GET         |  Retorna uma lista das sondas em ordem de processamento.
