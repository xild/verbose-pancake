[![Build Status](https://travis-ci.org/xild/verbose-pancake.svg?branch=master)](https://travis-ci.org/xild/verbose-pancake)
# verbose-pancake
# LIBS
 Springboot + Spring data + Feign + Mockito + Benas-Generate Random Beans
# WHY

 Simples aplicação com H2DB e alguns ceps que já são inseridos no boot da app. A aplicação responde serviços rests, como a segunda questão falava de 'consultar o serviço de cep' para validar. Utilizei o feign para demonstrar o desenvolvimento de um client http.
 
 Como se trata de um microservices fiz a configuração do Dockerfile caso queira migrar para algo de alta disponibilidade com um orquestrator de containers, loadbalances e afins.
 
 
# TESTES
 Foquei nos services. 
 ![screen shot 2016-06-28 at 12 41 33 am](https://cloud.githubusercontent.com/assets/1268884/16403185/fd677b00-3cc8-11e6-8d14-4f9d0c482fde.png)
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

# PÁGINA INICIAL - SWAGGER 
  ``` 
  http://localhost:8082/
  ```
# HEALTHCHECK :) 
  ```
 curl -X GET http://localhost:8082/api/healthcheck
 "LIVE" 
 ```
# BUSCA UM ENDEREÇO COM UM CEP VÁLIDO 
  ```
 curl -X GET --header "Accept: application/json" --header "X-User: swagger" "http://localhost:8082/api/buscaCep/77770000"
 {
  "id": 10,
  "rua": "Rua 77770000",
  "cidade": "SP",
  "estado": "SP",
  "numero": "88",
  "bairro": "jaguare",
  "complemento": "sem complemento"
} 
``` 
# BUSCA UM ENDEREÇO VÁLIDO PASSANDO UM CEP INVÁLIDO 
  ```
 curl -X GET --header "Accept: application/json" --header "X-User: swagger" "http://localhost:8082/api/buscaCep/77771111"
 {
  "id": 10,
  "rua": "Rua 77770000",
  "cidade": "SP",
  "estado": "SP",
  "numero": "88",
  "bairro": "jaguare",
  "complemento": "sem complemento"
} 
``` 
# BUSCA ENDEREÇO COM CEP INVÁLIDO
````
curl -X GET --header "Accept: application/json" --header "X-User: swagger" "http://localhost:8082/api/buscaCep/32132456"
{
  "error": "NOT_FOUND",
  "message": "Cep Inválido",
  "httpStatus": 404,
  "errors": "NOT_FOUND"
}
```

# CADASTRA UM ENDEREÇO
```
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" --header "X-User: swagger" -d "{
  \"rua\": \"rua da ns\",
  \"bairro\": \"vergueiro\",
  \"cidade\": \"SP\",
  \"estado\": \"SP\",
  \"complemento\": \"durden\",
  \"cep\": \"77770000\",
  \"numero\": \"253\"
}" "http://localhost:8082/api/address/"
{
  "id": 11,
  "rua": "rua da ns",
  "cidade": "SP",
  "estado": "SP",
  "numero": "253",
  "cep": "77770000",
  "bairro": "vergueiro",
  "complemento": "durden"
}
```
# ATUALIZAR UM ENDEREÇO
``` 
curl -X PUT --header "Content-Type: application/json" --header "Accept: application/json" --header "X-User: swagger" -d "{
  \"id\": 11,
  \"rua\": \"rua da nao\",
  \"cidade\": \"SP\",
  \"estado\": \"SP\",
  \"numero\": \"253\",
  \"cep\": \"77770000\",
  \"bairro\": \"vergueiro\",
  \"complemento\": \"durdem\"
}" "http://localhost:8082/api/address/"

{
  "id": 11,
  "rua": "rua da nao",
  "cidade": "SP",
  "estado": "SP",
  "numero": "253",
  "cep": "77770000",
  "bairro": "vergueiro",
  "complemento": "durdem"
}
```

# DELETAR UM ENDEREÇO

``` 
curl -X DELETE --header "Accept: application/json" --header "X-User: swagger" "http://localhost:8082/api/address/11"

no content
```

# BUSCAR UM ENDEREÇO
``` 
curl -X GET --header "Accept: application/json" --header "X-User: swagger" "http://localhost:8082/api/address/1" 
{
  "id": 1,
  "rua": "Rua A",
  "cidade": "SP",
  "estado": "SP",
  "numero": "253",
  "cep": "11703558",
  "bairro": "tupi",
  "complemento": "253"
}
``` 


