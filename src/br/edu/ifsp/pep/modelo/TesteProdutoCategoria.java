
package br.edu.ifsp.pep.modelo;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author aluno
 */
public class TesteProdutoCategoria {
    public static void main(String[] args) {
        
        EntityManager em = Persistence
                .createEntityManagerFactory("aula1PU")
                .createEntityManager();
        
        Categoria categoria = new Categoria();
        categoria.setDescricao("Informática");
        
        Produto produto = new Produto();
        produto.setDescricao("Teclado");
        produto.setPreco(new BigDecimal(60.0));
        produto.setQuantidade(100);
        produto.setCategoria(categoria);
        
        em.getTransaction().begin();
        
        em.persist(categoria);
        em.persist(produto);
        
        em.getTransaction().commit();
        
        //consulta JPQL ,uma linguagem do jpa
        //sempre pensar em clsse
        //fazer uma busca SELECT p FROM Produto p - esse "Produto" é o nome da clsse
        //Vai retornar uma lista desses objetos p
        //Vai retornar do tipo Produto
        TypedQuery<Produto> query = em.createQuery("select p from Produto p", Produto.class);
        
        List<Produto> produtos = query.getResultList();
        for (Produto p : produtos){
            System.out.println("Descrição: " + p.getDescricao());
            System.out.println("Categoria: " + p.getCategoria().getDescricao());
        }
        
        em.close();
        
        
    }
    
    
    
}
