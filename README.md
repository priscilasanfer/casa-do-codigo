# Spring MVC I: Criando aplicações web

Curso disponível em: https://cursos.alura.com.br/course/spring-mvc-1-criando-aplicacoes-web  


Aula 1 - Começando com SpringMVC

1) Baixe o JBoss Forge.

2) Extraia o zip. Em seguida, abra o seu terminal e acesse o seu workspace.

3) Dentro do seu workspace, inicie o terminal do JBoss Forge, acessando a sua subpasta bin, e executando o aplicativo forge, por exemplo:
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

9) Agora, dentro da classe HomeController, crie o método home, que será o responsável por atender a home da aplicação, ou seja, o endereço /. 
Por fim, anote esse método com @RequestMapping("/"), para atender o endereço mencionado anteriormente, imprima a mensagem Exibindo a home da CDC e retorne o nome da página JSP que será exibida, home.

10) Configure a Servlet do Spring MVC para que ela atenda as requisições recebidas pelo servidor. 
Para isso, crie a classe ServletSpringMVC dentro do pacote br.com.casadocodigo.loja.conf. 
Em seguida, estenda a classe AbstractAnnotationConfigDispatcherServletInitializer e implemente os métodos conforme abaixo:

11) Dentro do método getServletConfigClasses(), está sendo utilizada uma referência da classe AppWebConfiguration. 
A partir dessa classe, faça a configuração do MVC para a Servlet do Spring. 
Portanto, crie também essa classe, no mesmo pacote br.com.casadocodigo.loja.conf, com o seguinte código:

12) Dentro dessa classe, crie o método internalResourceViewResolver, responsável por indicar qual diretório estão as views:
A partir dos métodos setPrefix() e setSuffix(), você define o local (WEB-INF/views/) e extensão (jsp) das views, respectivamente. 
Por fim, anotando esse método com @Bean, você transforma-o em um Bean, para que o Spring o gerencie.

13) Resta criar o diretório do projeto no qual o Spring vai procurar as views, isto é, as suas páginas. 
Para isso, dentro do diretório src/main/webapp crie o diretório WEB-INF/views, onde as views serão armazenadas.

14) Agora, dentro do diretório src/main/webapp/WEB-INF/views, crie a página home.jsp, com o seguinte conteúdo:

15) Por fim, inicie o servidor e acesse http://localhost:8080/casadocodigo/.


Aula 2 - Cadastro de produtos

1) Crie um controller específico para os produtos dentro do seu sistema, chamado ProdutosController.
 Nessa classe, crie o método form(), que atende à URL produtos/form. Esse método deve retornar produtos/form, que é justamente o local onde você irá criar o formulário de cadastro de produtos.
 
2) Crie a view, ou seja, o formulário de cadastro de produtos. Crie a pasta produtos dentro de src/main/webapp/WEB-INF/views/ e dentro dela, crie a página form.jsp.

3) Crie o método gravar() na classe ProdutosController.java, que atenderá à URL casadocodigo/produtos que é justamente o endereço que o formulário está enviando os dados. 
Esse método será o responsável por receber os dados do formulário, então receba-os por parâmetro e imprima-os dentro do método. 
O nome dos atributos devem ser exatamente os mesmos valores contidos nos atributos name dos inputs do formulário de cadastro, pois o binding do Spring vincula cada valor de acordo com o seu nome.
Por fim, esse método deve retornar produtos/ok, que será a página para onde o usuário será enviado.

4) ) Ao invés de receber diversos parâmetros no método, o ideal é receber uma classe que representa todos esses eles.
Então, crie a classe Produto no pacote br.com.casadocodigo.loja.models com os atributos titulo, descricao e paginas, além dos seus getters e setters e de uma implementação do método toString:

5) No método gravar, de ProdutosController, ao invés de passar todos aqueles parâmetros, envie apenas um Produto e imprima-o dentro do método.

6) Agora, crie a view ok.jsp, dentro src/main/webapp/WEB-INF/views/produtos.

7) Adicione a dependência do Hibernate ao seu projeto. 

8) Para transformar as classes modelos como entidades do banco de dados, anote-as com @Entity.

9) Além disso, o Hibernate obriga que toda entidade precisa de um id, ou seja, um campo que contenha um valor único para cada registro. 
Atualmente, a classe Produto não tem nenhum atributo que possa ser um id, portanto, crie mais um atributo, chamado id, do tipo int, e também gere seus getters e setters.

10) Para o Hibernate entender que o atributo id é o id da entidade, adicione a anotação @Id no atributo. 
Além disso, popule-o antes de persisti-lo, fazendo com que o próprio banco já atribua um valor do id automaticamente, anotando-o com @GeneratedValue.
Por fim, informe ao Hibernate como ele deve atribuir esse id automaticamente a partir do atributo strategy da anotação @GeneratedValue. 
Nesse caso, faça com que ele seja auto-incremental, enviando o parâmetro strategy = GenerationType.IDENTITY.

11) Crie a classe JPAConfiguration, que será a responsável por configurar o framework, passando informações relevantes como o banco a ser utilizado, seu usuário e senha, e assim por diante.

12) Crie a classe ProdutoDAO. Dentro dessa classe, faça a comunicação com o banco de dados, adicionando um atributo do tipo EntityManager, e crie o método gravar(), que recebe um produto como parâmetro, e então, a partir do atributo do tipo EntityManager, persista um objeto a partir do método persist(), enviando o parâmetro do tipo Produto como argumento.
Já que o EntityManager trata-se de um recurso persistente, utilize a anotação @PersistenceContext para que ele seja injetável.

13) Dentro do ProdutosController, crie um atributo do tipo ProdutoDAO e anote com @Autowired, para que ele seja injetado.
Em seguida, dentro do método gravar() do controller, use o atributo do tipo ProdutoDAO e chame o método gravar(), enviando o produto recebido por parâmetro.

14) Para o Spring encontrar o DAO, adicione-o na anotação @ComponentScan, na classe AppWebConfiguration.

15) E para o Spring encontrar a classe JPAConfiguration, adicione-a no método getServletConfigClasses, da classe ServletSpringMVC.

16) Para o Spring gerenciar as transações para nós, adicione a anotação @EnableTransactionManagement na classe JPAConfiguration.

17) Em seguida, adicione um bean que será o gerenciador das transações, isto é, a partir desse bean, o Spring fornecerá as transações para o EntityManager. Adicionando o metodo JpaTransactionManager.

18) O ProdutoDAO é um recurso persistente (persiste dados) dentro do sistema, portanto, anote-o com @Repository. 
E embora o Spring esteja configurado para gerenciar as transações, ainda é necessário indicar que o ProdutoDAO precisa de uma transação. 
Faça isso anotando-o com @Transactional.

19) Agora, crie a base de dados casadocodigo no MySQL.

20) Por fim, reinicie o Tomcat e acesse a URL: http://localhost:8080/casadocodigo/produtos/form 
 