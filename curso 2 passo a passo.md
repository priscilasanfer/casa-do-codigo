### **Aula 1 -  Desempenho com Cache**

1) Na parte 2 desse curso sobre Spring MVC, você continuará utilizando o mesmo projeto criado na parte 1. 
Para sua conveniência, você pode fazer o download aqui do ZIP do código fonte do projeto do completo do curso anterior.

Uma vez baixado basta extrair e importar o projeto como Maven Project no Eclipse. 
Para rodar o projeto, você também precisa ter configurado o Apache Tomcat.

2) Para usar o JSP da página do Casa do Código, é aconselhado usar o arquivo JSP disponibilizado como download nesse zip aqui.

Ao extrair o ZIP, você encontrará:

O arquivo JSP limpo (home-limpo.jsp), mas ainda sem expressions language.
O arquivo JSP finalizado (home-pronto.jsp), com todas as alterações da aula já aplicadas.
Caso queira seguir o vídeo, é aconselhado usar o arquivo home-limpo.jsp, renomeá-lo para home.jsp e 
adicionar as tags e expressions language. O arquivo deve ficar dentro da pasta src/main/webapp/WEB-INF/views/. 
O arquivo home-pronto.jsp serve como referência.

3) Garanta que o HomeController vai enviar todas as informações que a home precisa para ser exibida corretamente. 
Altere o método index, do HomeController, para enviar uma lista de produtos para a view, usando o método listar da classe ProdutoDAO.

4) Altere também seu arquivo home.jsp, para que ele possa exibir os livros que estão atualmente cadastrados no sistema.
Lembre-se de adicionar mais uma taglib, que será a do Spring, ao JSP.

5) 5) No caso da Casa do Código, os produtos da Home mudam semanalmente. 
Sendo assim, não é necessário ficar o tempo todo buscando as informações no banco de dados. 
Uma forma de manter isso em memória é usando cache. Habilite o cache para a Home. 
Então, no método index, do HomeController, utilize a anotação @Cachable.

7) Além disso, configure qual mecanismo de cache deverá ser utilizado, através da classe AppWebConfiguration. 
Crie o método cacheManager, retorne o Guava e adicione a anotação @EnableCaching na classe.

8) Quando um novo produto é cadastrado, ele não aparece na listagem, pois ela está em cache. Então, quando um produto for 
cadastrado, o cache deve ser atualizado. No método gravar, do ProdutoController, utilize a anotação @CacheEvict para resolver esse problema.

### **Aula 2 - Criação do Serviço Rest**

1) Para exportar o produto como JSON, XML, etc, crie a classe JsonViewResolver, no pacote br.com.casadocodigo.loja.conf, 
que implemente a interface ViewResolver para atender os pedidos de dados em JSON.

2) Para agrupar todos os viewResolver, crie o método contentNegotiationViewResolver na sua classe AppWebConfiguration.

### **Aula 3 - Usando o Bootstrap**

1) No curso, é utilizada a versão 3.3.x do Bootstrap. 
Você pode fazer o download dela aqui: https://s3.amazonaws.com/caelum-online-public/springmvc-2-integracao-cache-seguranca-e-templates/bootstrap-3.3.7-dist.zip. 
Mas caso queira uma versão mais atual, você pode baixá-la pelo site do Bootstrap.

2) Extraia o conteúdo do Bootstrap na pasta src/main/webapp/resources. 
Diferentemente do video você já possui essa pasta. Não se esqueça que, para o Bootstrap funcionar, é preciso linkar os 
arquivos no head das suas páginas. Use o c:url, visto no curso anterior (Spring MVC I: Criando aplicações web).

3) Por padrão, o Spring pega qualquer requisição, inclusive o link de arquivos CSS e JavaScript.
Para corrigir isso, faça a classe de configuração AppWebConfiguration herdar de WebMvcConfigurerAdapter e sobrescrever o 
método configureDefaultServletHandling.

4) Com o Bootstrap configurado no projeto, comece a usar as classes do mesmo para estilizar as páginas da aplicação. 
Comece pela listagem dos produtos (lista.jsp), adicionando classes na tabela e trocando as tags de título do cabeçalho da tabela de td para th.

5) Envolver a listagem dos produtos com uma div que usa a classe container do Bootstrap. 
Ela centraliza os elementos na página, não deixando que tudo fique muito nos cantos da tela.

6) Toda vez que você quiser ir de uma página para outra, tem que digitar a URL da página na barra de endereços do navegador.
Crie um menu que facilite essa navegação entre as páginas, usando as classes do próprio Bootstrap 
(o código deve ficar acima da listagem, logo depois da tag <body>).

7) Para o menu não ficar sobrepondo o título da página, adicione a tag style dentro da tag head, modificando a 
propriedade padding-top da tag body, adicionando uma margem de 60px.

8) Estilize também a página de cadastro de produtos. Copie todo o código do menu junto com a div que tem a classe 
container e cole no form.jsp, assim como o código CSS do head também, 
alterando o padding para não espaçar somente o topo da página, mas também sua parte inferior.

9) Todos os campos estão envolvidos por uma div e a maioria deles são criados com as tags do próprio Spring, 
como por exemplo <form:input>. Nestes casos, adicione a classe form-group em cada uma das divs e o atributo 
cssClass com o valor form-control em cada um dos <form:input>. Além disso, adicione a classe form-control no input 
do sumário e as classes btn btn-primary no botão de submissão do formulário. E como o Bootstrap já estiliza bem os 
campos do formulário, remova os atributos cols e rows do campo <form:textarea>, adicionando também um título antes do formulário.

### **Aula 4 - Spring Security**

1) Para criar um login e senha usando o Spring Security, primeiramente adicione as seguintes dependências ao seu pom.xml.

2) Para configurar o Spring Security, crie a classe SpringSecurityFilterConfiguration no pacote br.com.casadocodigo.loja.conf.

3) Também no pacote br.com.casadocodigo.loja.conf, crie a classe SecurityConfiguration, anotando-a com @EnableWebSecurity 
(a anotação @EnableWebMvcSecurity foi descontinuada).

4) Adicione as classe SecurityConfiguration, AppWebConfiguration e JPAConfiguration no método getRootConfigClasses, da classe ServletSpringMVC.

5) Ainda na classe ServletSpringMVC, deixe o array de classes do método getServletConfigClasses vazio.

6) Habilite as páginas públicas. Para isso, sobrescreva o método configure da classe SecurityConfiguration, para que o 
acesso a home, detalhes do produto e carrinho estejam sempre liberados. Adicione também a regra para liberar o acesso 
à pasta resources. Sem essa liberação, os arquivos CSS e imagens não poderão ser carregados. O mesmo aplica se para a URL de pagamentos.

7) Para poder conhecer e trabalhar o usuário, prepare o seu modelo. Para tal, crie as classes Usuario e Role no pacote br.com.casadocodigo.loja.models. 
O usuário terá um e-mail, senha e nome, além de implementar a interface UserDetails do Spring Security. 
A classe Role terá apenas um nome e implementa a interface GrantedAuthority. O Usuario e Role devem se associar 
(List<Role>) para definir as permissões. Use as anotações do JPA para mapear as regras de integridade.

9) Na classe SecurityConfiguration injeto o DAO e sobrescreva o método configure. Nesse método, passe o usuarioDao para 
o AuthenticationManagerBuilder e defina a criptografia da senha. Para tal, use o método passwordEncoder, que recebe um objeto 
do tipo BCryptPasswordEncoder.

10) No MySQl, insira um usuário e uma senha. Para facilitar segue o código SQL preparado:

```
insert into Role values('ROLE_ADMIN');

insert into Usuario (email, nome, senha) values ('admin@casadocodigo.com.br', 'Administrador', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq');

insert into Usuario_Role(Usuario_email, roles_nome) values('admin@casadocodigo.com.br', 'ROLE_ADMIN');

```
A senha real é 123456.


11) Caso você queira gerar a sua própria senha, execute a classe GeraSenha inserindo a senha desejada.
 O resultado da impressão deve ser inserido no banco de dados.

12) Na página home.jsp, adicione a taglib do Spring Security.

13) Ainda no home.jsp, embrulhe os dois links para listar e cadastrar produtos com security:authorize, testando o role ROLE_ADMIN.

14) Em lista.jsp, adicione a taglib do Spring Security e logo abaixo da ul de listagem e cadastro de produtos, 
insira uma nova ul com as classes padrões do Bootstrap para criação de um menu alinhado à direita da barra de menu. 
Dentro da ul, crie uma nova li usando a security:authentication para mostrar o nome do usuário logado.

15) Para evitar um ataque CSRF e trabalhar com um token que identifica o formulário, use em todos os formulários sempre a 
tag form:form, por exemplo nos arquivos detalhe.jsp e lista.jsp. Por exemplo, onde há:
```
<form action='<c:url value="/carrinho/add" />' 
    method="post" class="container">
```

Ficará:

```
<form:form servletRelativeAction="/carrinho/add" 
    method="post" cssClass="container">
```

16) Crie a nova página formLogin.jsp dentro da pasta src/main/webapp/WEB_INF/views.

17) Crie um novo controller, no pacote br.com.casadocodigo.loja.controllers, para poder acessar o formulário.

18) Na classe SecurityConfiguration, no método configure(HttpSecurity http) logo após da chamada authenticated(), 
adicione novas regras login e logout.

19) Na página lista.jsp, no menu de navegação, crie um link Sair que chama a URL /logout.