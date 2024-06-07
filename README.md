
![Text_Grey](https://github.com/CleanOceanic/devops/assets/80494196/2cf34a3c-d5bb-491a-bea0-ccf152eb3fe4)

Este projeto é um backend Java que foi dockerizado e está hospedado no Azure. 
A seguir estão as instruções para execução local e via web.

## Integrantes do Grupo

- Kaique Santos de Andrade - RM99562
- Marcelo Augusto de Mello Paixão - RM99466
- Rodrigo Batista Freire - RM99513
- Thiago Martins Bezerra - RM98644
- Vinicius Oliveira de Almeida - RM97967

#

## Docker Hub
A imagem Docker do projeto está disponível no Docker Hub e pode ser acessada através do seguinte link:
```sh
  marceloamellopaixao/cleanoceanic-java
```

## Execução Local
Para executar o projeto localmente, siga os passos abaixo:

### 1º Abra o projeto Java.
### 2º Abra o terminal.
### 3º Execute os seguintes comandos:

O comando docker pull baixa a imagem Docker do Docker Hub.
```sh
  docker pull marceloamellopaixao/cleanoceanic-java:latest
```

O comando docker run inicia um contêiner com a imagem baixada e mapeia a porta 8080 do contêiner para a porta 8080 da sua máquina local.
```sh
  docker run -d -p 8080:8080 marceloamellopaixao/cleanoceanic-java
```

## Execução via Web
Você pode acessar a aplicação hospedada no Azure através dos seguintes endpoints:

- http://20.241.219.113:8080/swagger-ui/index.html
- http://20.241.219.113:8080/api/public/usuarios
- http://20.241.219.113:8080/api/public/empresas
- http://20.241.219.113:8080/api/public/enderecos
- http://20.241.219.113:8080/api/public/pontoColetas
- http://20.241.219.113:8080/api/public/residuoColetados
- http://20.241.219.113:8080/api/public/usuarioEnderecos
- http://20.241.219.113:8080/api/public/empresaEnderecos
- http://20.241.219.113:8080/api/public/pontoColetaEnderecos
- http://20.241.219.113:8080/api/public/destinoResiduos
- http://20.241.219.113:8080/api/public/destinoResiduoPontoColetas
