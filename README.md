# verbose-pancake
# LIBS
 Springboot + Spring data + Feign + Mockito + Benas-Generate Random Beans
# WHY

 Simples aplicação com H2DB e alguns ceps que já são inseridos no boot da app. A aplicação responde serviços rests, como a segunda questão falava de 'consultar o serviço de cep' para validar. Utilizei o feign para demonstrar a utilização de um client declarativo que é o Feign.
 
 Como se trata de um microservices fiz a configuração do Dockerfile. O ideal é um docker-compose com um nginx na frente de 2 containers fazendo HA, está demonstrado. 
 
 
# TESTES
 Para os testes utilizei o Mockito com Junit. 
# HOW-TO
- Clone o projeto. 
- A melhor forma é rodar via docker. (https://www.docker.com/products/docker)
  - sh docker.sh
- Ou... mvn clean package
  - cd target/
  - java -jar verbose-pancake.jar

- Acessar o o swagger da aplicação no endereço http://localhost:8082
  - utilizar algum dos ceps que já foram inseridos inicializados com a aplicaçao
  - CEPS:
    - 11703558
    - 11703559
    - 11720320
    - 05416000
    - 02545333
    - 12345678
    - 11114444
    - 55556666
    - 55550000
    - 77770000
