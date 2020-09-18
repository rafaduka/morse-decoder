![png](https://i.imgur.com/TktHCgh.png) 

Decodificador morse para Text2Morse, Morse2Text and Bits2Morse


# Morse Decoder

Morse Decoder é uma API utilizada para avaliação de entrevista do Mercado Livre.


```bash
,--.   ,--.  ,-----.  ,------.   ,---.   ,------.
|   `.'   | '  .-.  ' |  .--. ' '   .-'  |  .---'
|  |'.'|  | |  | |  | |  '--'.' `.  `-.  |  `--,
|  |   |  | '  '-'  ' |  |\  \  .-'    | |  `---.
`--'   `--'  `-----'  `--' '--' `-----'  `------'
```

## Installation

###Local
```bash
git clone git@github.com:rafaduka/morse-decoder.git
cd morse-decoder
./gradlew clean build
./gradlew bootRun
```

###Profiles
| Ambiente      | Valor                                  | Tomcat Port   |
| ------------- |:--------------------------------------:|:-------------:| 
| [AWS]         | `-Dspring.profiles.active=prod`        | 5000          |
| [Local]       | `<empty> (default)`                              | 8080          |


## Usage

Decoder de Texto para Morse:
```bash
curl --location --request POST 'http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/api/v1/decoder/text2morse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text":"HOLA MELI"
}'
```

Decoder de Morse para Texto:
```bash
curl --location --request POST 'http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/api/v1/decoder/morse2text' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text":".... --- .-.. .-   -- . .-.. .."
}'
```

Decoder de Bits para Morse:
```bash
curl --location --request POST 'http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/api/v1/decoder/bits2morse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text":"000000001101101100111000001111110001111110011111100000001110111111110111011100000001100011111100000111111001111110000000110000110111111110111011100000011011100000000000"
}'
```

## Demo

![gif](https://i.imgur.com/mXlszJO.gif)


## Swagger

[Swagger Link] 

![](https://validator.swagger.io/validator?url=http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/v2/api-docs)

## Health Check

```bash
curl http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/actuator/health
```

## Tasklist
- [x] Conversão de Texto para Morse
- [x] Conversão de Morse para Texto
- [x] Conversão de Bits para Morse
- [ ] Suporte a outros caracteres especiais [números, etc]
- [x] Remoção de ruído do início e fim da mensagem na conversão de bits para morse
- [x] Validação das entradas da api

## Contact 

Rafael Horácio :octocat:

rafaduka@gmail.com

## License
[MIT](https://choosealicense.com/licenses/mit/)


[AWS]: http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com
[Local]: http://localhost:8080/
[Swagger Link]:http://morsedecoder-env.eba-yefvahgc.us-east-2.elasticbeanstalk.com/swagger-ui.html