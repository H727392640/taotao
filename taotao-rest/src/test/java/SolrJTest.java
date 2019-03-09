import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/8
 * Time: 13:42
 * Description: No Description
 */
public class SolrJTest {

//    @Test
//    public void addDocument() throws Exception{
//        SolrServer solrServer = new HttpSolrServer("http://192.168.180.129:8080/solr");
//        SolrInputDocument document = new SolrInputDocument();
//        document.addField("id", "test001");
//        document.addField("item_title", "测试商品2");
//        document.addField("item_price", 54321);
//        solrServer.add(document);
//        solrServer.commit();
//    }
//
//    @Test
//    public void deleteDocument() throws Exception{
//        SolrServer solrServer = new HttpSolrServer("http://192.168.180.129:8080/solr");
//        solrServer.deleteById("test001");
//        //solrServer.deleteById(new ArrayList<>());
//        //solrServer.deleteByQuery("*:*");
//        solrServer.commit();
//    }
}
