# Desafio de projeto: Modelagem e Diagramação de um Componente iPhone


## Linguagens de Programação
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java](https://img.shields.io/static/v1?label=&message=UML&color=FABD14&logo=uml&logoColor=FFFFFF)

### Desafio: 
Com base no vídeo de lançamento do iPhone de 2007 (link abaixo), você deve elaborar a diagramação das classes e interfaces utilizando uma ferramenta UML de sua preferência. Em seguida, implemente as classes e interfaces no formato de arquivos .java.

[Lançamento iPhone 2007](https://www.youtube.com/watch?v=9ou608QQRq8)

Minutos relevantes: 00:15 até 00:55.

### Funcionalidades a Modelar
1. Reprodutor Musical
   - Métodos: `tocar()`, `pausar()`, `selecionarMusica(String musica)`
2. Aparelho Telefônico
    - Métodos : `ligar(String numero)`, `atender()`, `iniciarCorreioVoz()`
3. Navegador na Internet
    - Métodos:` exibirPagina(String url)`, `adicionarNovaAba()`, `atualizarPagina()`

## Diagrama UML do iPhone

```mermaid
classDiagram
    class ReprodutorMusical {
        +tocar()
        +pausar()
        +selecionarMusica(musica: String)
    }

    class AparelhoTelefonico {
        +ligar(numero: String)
        +atender()
        +iniciarCorreioVoz()
    }

    class NavegadorInternet {
        +exibirPagina(url: String)
        +adicionarNovaAba()
        +atualizarPagina()
    }

    class iPhone {
        +reprodutorMusical: ReprodutorMusical
        +aparelhoTelefonico: AparelhoTelefonico
        +navegadorInternet: NavegadorInternet
    }

    iPhone --> ReprodutorMusical
    iPhone --> AparelhoTelefonico
    iPhone --> NavegadorInternet