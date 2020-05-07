# Spring MVC I: Criando aplicações web

Curso disponível em: https://cursos.alura.com.br/course/spring-mvc-1-criando-aplicacoes-web  


### **Aula 1 - Começando com SpringMVC**

1) Baixe o JBoss Forge.

2) Extraia o zip. Em seguida, abra o seu terminal e acesse o seu workspace.

3) Dentro do seu workspace, inicie o terminal do JBoss Forge, acessando a sua subpasta bin, e executando o aplicativo 
forge, por exemplo:
```
alura-Mac-mini:workspace alura$ forge/bin/forge
```

4) Agora, crie o projeto casadocodigo:
```
project-new --named casadocodigo
```

5) Importe o projeto na sua IDE como um projeto Maven

6) Utilize o Tomcat como servidor de aplicacoes

7) Atualize as dependencias do Maven no pom.xml

8) Crie a classe HomeController dentro do pacote br.com.casadocodigo.loja.controllers. 
Em seguida, anote a classe com @Controller, para que o Spring MVC a reconheça como um controller.

9) Agora, dentro da classe HomeController, crie o método home, que será o responsável por atender a home da aplicação, 
ou seja, o endereço /. 
Por fim, anote esse método com @RequestMapping("/"), para atender o endereço mencionado anteriormente, imprima a mensagem 
"Exibindo a home da CDC" e retorne o nome da página JSP que será exibida, home.

10) Configure a Servlet do Spring MVC para que ela atenda as requisições recebidas pelo servidor. 
Para isso, crie a classe ServletSpringMVC dentro do pacote br.com.casadocodigo.loja.conf. 
Em seguida, estenda a classe AbstractAnnotationConfigDispatcherServletInitializer e implemente os métodos obrigatórios.

11) Dentro do método getServletConfigClasses(), está sendo utilizada uma referência da classe AppWebConfiguration. 
A partir dessa classe, faça a configuração do MVC para a Servlet do Spring. 
Portanto, crie também essa classe, no mesmo pacote br.com.casadocodigo.loja.conf, com o seguinte código:

12) Dentro dessa classe, crie o método internalResourceViewResolver, responsável por indicar qual diretório estão as views.
A partir dos métodos setPrefix() e setSuffix(), você define o local (WEB-INF/views/) e extensão (jsp) das views, respectivamente. 
Por fim, anotando esse método com @Bean, você transforma-o em um Bean, para que o Spring o gerencie.

13) Resta criar o diretório do projeto no qual o Spring vai procurar as views, isto é, as suas páginas. 
Para isso, dentro do diretório src/main/webapp crie o diretório WEB-INF/views, onde as views serão armazenadas.

14) Agora, dentro do diretório src/main/webapp/WEB-INF/views, crie a página home.jsp, com o seguinte conteúdo:

15) Por fim, inicie o servidor e acesse http://localhost:8080/casadocodigo/.


### **Aula 2 - Cadastro de produtos**

1) Crie um controller específico para os produtos dentro do seu sistema, chamado ProdutosController.
Nessa classe, crie o método form(), que atende à URL produtos/form. Esse método deve retornar produtos/form, que é 
justamente o local onde você irá criar o formulário de cadastro de produtos.
 
2) Crie a view, ou seja, o formulário de cadastro de produtos. Crie a pasta produtos dentro de src/main/webapp/WEB-INF/views/ 
e dentro dela, crie a página form.jsp.

3) Crie o método gravar() na classe ProdutosController.java, que atenderá à URL casadocodigo/produtos que é justamente o 
endereço que o formulário está enviando os dados. 
Esse método será o responsável por receber os dados do formulário, então receba-os por parâmetro e imprima-os dentro do método. 
O nome dos atributos devem ser exatamente os mesmos valores contidos nos atributos name dos inputs do formulário de 
cadastro, pois o binding do Spring vincula cada valor de acordo com o seu nome.
Por fim, esse método deve retornar produtos/ok, que será a página para onde o usuário será enviado.

4) ) Ao invés de receber diversos parâmetros no método, o ideal é receber uma classe que representa todos esses eles.
Então, crie a classe Produto no pacote br.com.casadocodigo.loja.models com os atributos titulo, descricao e paginas, 
além dos seus getters e setters e de uma implementação do método toString:

5) No método gravar, de ProdutosController, ao invés de passar todos aqueles parâmetros, envie apenas um Produto e 
imprima-o dentro do método.

6) Agora, crie a view ok.jsp, dentro src/main/webapp/WEB-INF/views/produtos.

7) Adicione a dependência do Hibernate ao seu projeto. 

8) Para transformar as classes modelos como entidades do banco de dados, anote-as com @Entity.

9) Além disso, o Hibernate obriga que toda entidade precisa de um id, ou seja, um campo que contenha um valor único para 
cada registro. 
Atualmente, a classe Produto não tem nenhum atributo que possa ser um id, portanto, crie mais um atributo, chamado id, 
do tipo int, e também gere seus getters e setters.

10) Para o Hibernate entender que o atributo id é o id da entidade, adicione a anotação @Id no atributo. 
Além disso, popule-o antes de persisti-lo, fazendo com que o próprio banco já atribua um valor do id automaticamente, 
anotando-o com @GeneratedValue.
Por fim, informe ao Hibernate como ele deve atribuir esse id automaticamente a partir do atributo strategy da anotação 
@GeneratedValue. 
Nesse caso, faça com que ele seja auto-incremental, enviando o parâmetro strategy = GenerationType.IDENTITY.

11) Crie a classe JPAConfiguration, que será a responsável por configurar o framework, passando informações relevantes 
como o banco a ser utilizado, seu usuário e senha, e assim por diante.

12) Crie a classe ProdutoDAO. Dentro dessa classe, faça a comunicação com o banco de dados, adicionando um atributo do tipo 
EntityManager, e crie o método gravar(), que recebe um produto como parâmetro, e então, a partir do atributo do tipo EntityManager, 
persista um objeto a partir do método persist(), enviando o parâmetro do tipo Produto como argumento.
Já que o EntityManager trata-se de um recurso persistente, utilize a anotação @PersistenceContext para que ele seja injetável.

13) Dentro do ProdutosController, crie um atributo do tipo ProdutoDAO e anote com @Autowired, para que ele seja injetado.
Em seguida, dentro do método gravar() do controller, use o atributo do tipo ProdutoDAO e chame o método gravar(), enviando 
o produto recebido por parâmetro.

14) Para o Spring encontrar o DAO, adicione-o na anotação @ComponentScan, na classe AppWebConfiguration.

15) E para o Spring encontrar a classe JPAConfiguration, adicione-a no método getServletConfigClasses, da classe ServletSpringMVC.

16) Para o Spring gerenciar as transações para nós, adicione a anotação @EnableTransactionManagement na classe JPAConfiguration.

17) Em seguida, adicione um bean que será o gerenciador das transações, isto é, a partir desse bean, o Spring fornecerá 
as transações para o EntityManager. 
Adicionando o metodo JpaTransactionManager.

18) O ProdutoDAO é um recurso persistente (persiste dados) dentro do sistema, portanto, anote-o com @Repository. 
E embora o Spring esteja configurado para gerenciar as transações, ainda é necessário indicar que o ProdutoDAO precisa 
de uma transação. 
Faça isso anotando-o com @Transactional.

19) Agora, crie a base de dados casadocodigo no MySQL.

20) Por fim, reinicie o Tomcat e acesse a URL: http://localhost:8080/casadocodigo/produtos/form 
 
 
### **Aula 3 - Cadastro de preços do produto**

1) O tipo do preço de cada livro está limitado a 3 opções (ebook, impresso e combo), portanto, crie a Enum TipoPreco, 
com os valores EBOOK, IMPRESSO e COMBO.

2) Crie a classe Preco com o atributo valor, do tipo BigDecimal, e tipo, do tipo TipoPreco. Gere também seus getters e 
setters desses atributos.

3) Agora, adicione esses preços na classe Produto. Como cada livro pode ter mais de um preço, utilize uma lista. 
Gere também o getter e setter desse atributo.

4) Anote a classe Preco com @Embeddable, que a permite ser persistida, desde que ela seja um atributo de uma entidade, 
e no caso ela é um atributo da classe Produto, que é uma entidade.

5) Para indicar que você irá armazenar uma lista de Preco, de uma classe @Embeddable, utilize a anotação @ElementCollection. 
Portanto, anote o atributo precos da classe Produto.

6) Para adicionar os campos de preço na view, passe os tipos de preço para a JSP. 
Para isso, utilize o ModelAndView, que, além de carregar a página, permite enviar objetos para a view. 
No controller ProdutosController, altere o método form() para retornar um ModelAndView no lugar de uma string. 
Em seguida, crie um objeto dessa classe que retorna a página produtos/form.
Além de retornar a página, envie o objeto que representa os tipos de preço, através do método addObject, da classe 
ModelAndView, enviando uma string que indica o nome do objeto como primeiro parâmetro e os valores do objeto como 
segundo parâmetro.

7) Percorra a lista de tipos para exibir os dados no formulário, usando o forEach da JSTL. Então, em form.jsp, antes do 
botão de cadastro e adicione.

8) E não esqueça de importar a JSTL através da taglib, antes da tag <html>.

### **Aula 4 - Listando os produtos**

1) Com alguns livros cadastrados no banco de dados, chegou a hora de listá-los. 
Primeiramente, busque-os na base de dados, então crie o método listar() na classe ProdutoDAO, que devolve uma lista de produtos. 
Dentro desse método, utilize o createQuery() para realizar a query que busca todos os produtos. Por fim, utilize o método 
getResultList() para retornar a lista de produtos.

2) Com a busca de livros no banco de dados pronta, crie o método listar() no ProdutoController, que mapeia o endereço produtos. 
Esse método irá buscar os livros no banco, criar um objeto do tipo ModelAndView que devolve a view produtos/lista e 
adicionar a lista de produtos na view. 
Ao final, retorne o objeto de ModelAndView.

3) Agora que todos os produtos contidos no banco de dados estão sendo devolvidos, exiba-os em uma view. 
Crie a JSP lista.jsp dentro do diretório src/main/webapp/WEB-INF/views/produtos. 

4) 4) Em ProdutoController, os métodos gravar e listar respondem à mesma URL, então indique a sua intenção utilizando os 
métodos de requisição do HTTP, como o GET para listar os dados e POST para inserir.

5) Todos os mapeamentos da classe ProdutosController começam com /produtos, mas ficar digitando e dar manutenção neles 
é complicado. Para resolver isso, adicione a anotação @RequestMapping("/produtos") na classe ProdutosController e 
apague o prefixo de todos os outros métodos.

6) Reinicie o Tomcat e acesse http://localhost:8080/casadocodigo/produtos. Na listagem dos produtos, os dados que 
possuem acentos estão com problemas de apresentação. Então defina qual é o encoding da aplicação, através de um filtro 
do Spring. Para isso, adicione o  método getServletFilters() na classe ServletSpringMVC.

### **Aula 5 - Redirect com Escopo de Flash**

1) Após a gravação de um produto, envie o usuário para a listagem de produtos. 
Para isso, na classe ProdutosController, no método gravar, utilize o ModelAndView, passando a string com o prefixo redirect.

2) Faça uso do escopo de Flash para enviar uma mensagem de sucesso, que será exibida após o redirect do navegador, 
na tela de listagem. Adicione o atributo ${sucesso} na JSP lista.jsp.

3) Agora, no ProdutosController, receba o RedirectAttributes para adicionar o atributo sucesso com escopo de Flash.

### **Aula 6 - Validação e conversão de dados**

1) Para realizar a validação dos dados, adicione as dependências do javax.validation e org.hibernate no pom do projeto.

2) Para validar um produto, crie a classe  ProdutoValidation que será responsável por realizar essa validação e implemente
a interface Validator do Spring. 
Para adicionar os erros de validação, rejeitar se o campo estiver vazio, você pode usar o método rejectIfEmpty(), da 
classe ValidationUtils. 
E no método supports(), você pode usar o método isAssignableFrom() para verificar se classe recebida por parâmetro é de 
fato um Produto.

3) Para que o ProdutosController possa reconhecer o validador, crie o método initBinder(), que ficará responsável por 
vincular o validador com o controller. Esse método recebe um WebDataBinder, e então, anote-o com @InitBinder, para que 
o Spring o chame automaticamente. 
A partir do parâmetro WebDataBinder, utilize o método addValidators() para adicionar o ProdutoValidation para o ProdutosController:

4) Com a estrutura para realizar a validação pronta, falta executá-la. Ainda no ProdutosController, faça com que o 
produto seja validado, então vá até o método gravar() e, no parâmetro do tipo Produto, anote com @Valid.

5) Além de validar o produto, você precisa saber quais foram os erros obtidos durante a validação. 
Para pegá-los, adicione o parâmetro BindingResult no método gravar(). 
Durante a validação, o Spring vai coletar todos os erros e irá adicionar nesse parâmetro mas para que isso funcione, 
é necessário adicioná-lo logo em seguida do objeto que será validado, em outras palavras, o BindingResult tem que ser 
declarado logo após o produto.

6) Por fim, antes de executar qualquer instrução, faça um if, passando como argumento o método hasErrors(), do BindingResult, 
e se houver erros, retorne o método form(), ou seja, redirecione o usuário para o formulário. Dessa forma, você fará 
com que o usuário volte para o formulário todas as vezes que ocorrer uma falha de validação.

### **Aula 7 - Mais Validações**

1) Para adicionar mensagens de validação no projeto, crie o arquivo que define essas mensagens, conhecido como messages.properties. 
Ele segue um formato bem parecido com chave/valor. Por exemplo, caso você queira adicionar mensagens para campos que são obrigatórios, 
basta preencher a chave field.required com o valor Campos obrigatórios. Você também pode definir mensagens mais específicas. 
Então, na pasta WEB-INF, crie o arquivo messages.properties.

2) Assim que o arquivo for salvo, clique nele com o botão direito do mouse e selecione Properties. 
Em Text file encoding, verifique se o encoding é UTF-8, caso não seja, altere-o.

3) Para o Spring encontrar o arquivo messages.properties, crie um novo método na classe AppWebConfiguration, que irá retornar 
o arquivo com algumas configurações. Crie o método messageSource().

4) Para adicionar as mensagens no formulário form.jsp, adicione uma nova taglib.

5) Além disso, substitua a tag <form>.
Caso esteja usando dependências atualizadas e der o erro Unable to find setter method for attribute: [commandName], 
troque o commandName para modelAttribute.

6) Adicione as mensagens de erro para os campos de título, descrição e páginas.

7) Por fim, para evitar de ficar mexendo na action do formulário, peça para o Spring preencher a URL para você. 
Para isso, adicione a taglib.

8) E use o método mvcUrl("controller#metodo").build() para criar a URL.
O controller é referenciado pelas suas letras maiúsculas, então ProdutosController vira PC.

9) Por fim, para que a URL possa ser construída de forma correta, separando os contextos, modifique 
o @RequestMapping do ProdutosController para /produtos.

### **Aula 8 - Trabalhando com datas**

1) Agora que o livro terá data de lançamento, na classe Produto, crie o atributo dataLancamento, do tipo Calendar e 
adicione a anotação @DateTimeFormat, para que o Spring consiga converter os valores de texto para Calendar. 
Além disso, gere o getter e setter desse atributo,

2) No formulário form.jsp, logo após a div das páginas, crie mais uma div para representar a data de lancamento.

3) Para não ter que configurar o formato da data por anotações, configure-o através do AppWebConfiguration, criando o 
método mvcConversionService().

4) E para não perder as informações se um campo estiver inválido, troque as tags do formulário no form.jsp de 
<input type="text" name="nome" /> para <form:input path="nome" />.

5) Para isso funcionar, faça o método form() do ProdutoController receber um Produto.

6) O método gravar, também do ProdutoController irá apresentar um erro, pois dentro dele há uma chamada para o método form. 
Então passe um Produto para ele.

### **Aula 9 - Enviando arquivos para o servidors**

1) Permita que o usuário envie um sumário do livro em PDF. No form.jsp, dentro do formulário, antes do botão de cadastro,
crie um novo campo do tipo file. Além disso, defina o valor do enctype do formulário para multipart/form-data.

2) Na classe Produto, adicione um atributo chamado sumarioPath do tipo String, juntamente com seu getter e setter.

3) Configure o Spring para trabalhar com arquivos. Na classe AppWebConfiguration, crie o metodo multipartResolver().

4) Além disso, altere a configuração das servlets. Então, na classe ServletSpringMVC, sobrescreva o método customizeRegistration().

5) Crie a lógica de salvar um arquivo. Para isso ,crie a classe FileSaver, no pacote br.com.caelum.loja.infra, com um 
método chamado write, que deverá salvar o arquivo em uma pasta recebida por parâmetro. Lembre-se que você pode usar o 
método transferTo da classe MultiPartFile para realizar essa tarefa, e para pegar o caminho completo até a pasta, use 
um HttpServletRequest, utilizando a anotação @Autowired para o Spring injetar essa dependência.

6) Com a classe criada, use-a no controller. Adicione um novo parâmetro no método gravar, em ProdutosController, 
chamado sumario, do tipo MultipartFile. Dentro do método, chame o método write, da classe FileSaver, salvando o arquivo 
na pasta arquivos-sumario. Lembre-se que para tudo funcionar, corretamente o Spring deve injetar um FileSaver.

7) Crie a pasta arquivos-sumario dentro de src/main/webapp/.

8) Por fim, para o Spring encontrar o FileSaver, adicione-o na anotação @ComponentScan, na classe AppWebConfiguration.

### **Aula 10 - URLs amigáveis**

1) Baixe o arquivo  "https://s3.amazonaws.com/caelum-online-public/spring-mvc-1-criando-aplicacoes-web/springmvc-arquivos-extras-aula10.zip"

2) Copie o arquivo detalhe.jsp para a pasta src/main/webapp/WEB-INF/views/produtos do seu projeto.

3) Copie toda a pasta resources para a pasta src/main/webapp do seu projeto.

4)  Por padrão, o Spring MVC nega o acesso à pasta resources. 
Consequentemente, o Tomcat não pode carregar os arquivos CSS (e a página fica sem design). 
Para liberar o acesso, é preciso fazer duas alterações na classe AppWebConfiguration.
    - A classe deve estender a classe WebMvcConfigurerAdapter.
    - A classe deve implementar o método configureDefaultServletHandling para liberar o acesso.
    
5) Ajuste o código Java começando pelo ProdutoDAO. Escreva um novo método, chamado find, que recebe o id do produto por 
parâmetro e retorna o produto com todas as informações preenchidas, inclusive os preços. 
O método deve executar uma query planejada através do EntityManager, que vai realizar o join entre as tabelas Produto e precos.

6) Ajuste o controller, para carregar a JSP com os dados do produto. 
Primeiramente, crie o método detalhe, que recebe o id do produto por parâmetro, retorna para a JSP de detalhe e carrega 
o objeto ModelAndView com um produto.

7) O Spring já traz um sistema de URLs amigáveis, que são URLs que parecem texto e não ficam poluídas com ? e &. 
Então edite o método detalhe, adicionando uma variável na rota e usando a anotação @PathVariable para mapear essa variável a um parâmetro.

8) Por fim, adicione um link na listagem de produtos (lista.jsp) que irá te direcionar para a página de detalhe do produto. 
Para construir a URL, use o método mvcUrl. Além disso, invoque o método arg para adicionar um parâmetro à URL. 
Lembre-se que é preciso fazer o import da taglib para poder usar o mvcUrl.

### **Aula 11 - Expondo atributos no JSP**

1) Ao abrir a página detalhe.jsp, todas as informações são exibidas corretamente, com exceção da data. 
Isso acontece porque não foi definido como ela deve ser apresentada. Use a tag formatDate para corrigir esse problema. 
Ela só aceita o tipo Date, então utilize o atributo time da data de lançamento.
Não esqueça também de adicionar a taglib.

2) Comece a criar o carrinho de compras! Todo carrinho de compras possui vários itens, e cada item é composto, além do 
próprio produto, do tipo que está sendo comprando (Ebook, Impresso, Combo). 
Portanto, crie a classe CarrinhoItem no pacote br.com.casadocodigo.loja.models, com os atributos produto e tipoPreco, 
além dos seus getters e setters. E já que para um CarrinhoItem existir, é preciso tanto do produto quanto o tipoPreco, 
peça-os por parâmetro no construtor.

3) Com a representação de um item do carrinho, crie o carrinho de fato. Para isso, crie a classe CarrinhoCompras, 
com um atributo que possua um mapa que armazena o item e a quantidade no carrinho. 
Crie os métodos add, que adiciona o item no mapa, e getQuantidade, que retorna a quantidade de determinado item.

4) Para tudo funcionar corretamente, reescreva os métodos hashCode e equals, com os atributos produto e tipoPreco, da classe CarrinhoItem.

5) Faça o mesmo para a classe Produto, mas somente com o atributo id.

6) Com as classes do carrinho prontas, é preciso acessá-lo. 
Crie o controller CarrinhoComprasController para o carrinho  e implemente os métodos add, que irá adicionar um item no carrinho, 
e crieItem, que irá criar um item para ser adicionado no carrinho. Não esqueça de utilizar as classes que foram criadas anteriormente. 
E para não se preocupar com a criação dos objetos, peça para o Spring injetá-los (@Autowired).

7) Para ver o carrinho funcionando, mostre a quantidade de itens de do lado do link Carrinho. Na classe CarrinhoCompras, 
crie o método getQuantidade, que conta os itens do carrinho.

E chame-o na JSP detalhe.jsp.

8) Para enviar o carrinho de compras para a JSP, utilize o método setExposedContextBeanNames, da classe InternalResourceViewResolver, 
para deixar o carrinho disponível para todas as views. 
Além disso, para o Spring achar a classe CarrinhoCompras, adicione-a na anotação ComponentScan.


### **Aula 12 - Escopo sessão**

Por padrão, o Spring define que o escopo de todos os componentes é de application, ou seja, apenas uma instância existe 
desde quando a aplicação foi criada. O principal problema dessa abordagem é esse que acabamos de encontrar, 
os usuários compartilham sempre os mesmos dados, uma alteração afeta todo mundo que está conectado à nossa aplicação. 
Para resolver isso, precisamos mudar o escopo do nosso carrinho.


Qual a diferença escopo: aplicação, sessão e request?
O primeiro escopo apresentado é o escopo de aplicação, isto é, desde que o servidor é iniciado, apenas um objeto na 
memória é manipulado, o que causa conflito quando temos mais de um usuário usando a nossa aplicação. O segundo escopo é 
o de sessão, no qual o objeto é criado para cada usuário que se conecta à aplicação, ou seja, usuários diferentes usam o
bjetos diferentes, o que é ideal para um carrinho de compras, uma vez que cada usuário possui o seu próprio carrinho. 
O último escopo apresentado é o escopo de request, no qual cada vez que acessamos a página, um objeto é criado.

1) Para a nossa aplicação funcionar corretamente, através da anotação @Scope, altere o escopo da classe CarrinhoCompras para session.

2) E altere o escopo da classe CarrinhoComprasController para request.

### **Aula 13 -  Carrinho de compras**

1) Se você ainda não baixou, faça o download do código fonte da página JSP para renderizar os itens do carrinho 
aqui: https://s3.amazonaws.com/caelum-online-public/spring-mvc-1-criando-aplicacoes-web/springmvc-arquivos-extras-aula13.zip.

2) Escolha qual JSP usar, renomeie-a para itens.jsp e copie-a para a pasta src/main/webapp/WEB-INF/views/carrinho (que deve ser criada) do seu projeto

3) Copie a imagem excluir.png para a pasta src/main/webapp/resources/imagens da aplicação.

4) Crie o método precoPara na classe Produto para descobrir o preço de um produto de acordo com o tipo.

5) Na classe CarrinhoItem, crie os métodos getTotal, apenas realizando uma multiplicação, e getPreco, que chama 
o método precoPara da classe Produto.

6) Na classe CarrinhoCompras, crie o método getItens, que irá retornar uma coleção de itens. Na mesma classe, 
crie também dois métodos getTotal, um que apenas repassa a chamada para o método de mesmo nome da classe 
CarrinhoItem e outro para saber o total que o usuário está pagando.

7) No CarrinhoComprasController, crie o método itens.

8) Ainda em CarrinhoComprasController, altere o método add para que, em vez do usuário ser redirecionado para a página 
de produtos, ele seja redirecionado para o carrinho.

10) Por fim, todo componente do Spring que possua escopo de sessão precisa implementar a interface Serializable, 
então faça isso com a classe CarrinhoCompras.

