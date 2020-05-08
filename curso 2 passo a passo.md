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